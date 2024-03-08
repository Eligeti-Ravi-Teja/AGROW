package com.jsp.Agrow.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agrow.dao.ImageDao;
import com.jsp.Agrow.dao.UserDao;
import com.jsp.Agrow.dao.exception.ImageNotFound;
import com.jsp.Agrow.dao.exception.UserDoesNotExit;
import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Image;
import com.jsp.Agrow.entity.User;

import jakarta.mail.Header;

@Service
public class ImageService {
	@Autowired
	ImageDao dao;
	@Autowired
	UserDao udao;
	
//	=================set profile=======================================================
	public ResponseEntity<ResponseStructure<Image>> setProfile(int uid,MultipartFile file) throws IOException{
		User user=udao.findUserById(uid);
		if(user!=null) {
			if(user.getImage()==null) {
				Image img=new Image();
				img.setImage(file.getBytes());
				img.setName(file.getOriginalFilename());
		    Image image=dao.createImage(img);
		    user.setImage(image);
		    udao.updateUser(user);
		    ResponseStructure<Image> rs=new ResponseStructure<Image>();
		    rs.setData(image);
		    rs.setMessage("Image gas been uploaded");
		    rs.setStatus(HttpStatus.OK.value());
		    return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.OK);
			}
			else{
				return updateImage(user.getImage().getId(),file );
			}
			
		}
		else {
			throw new UserDoesNotExit("user with id: "+uid+" is not found!!");
		}
	}
	
//	=======================update Profile=======================================
	public ResponseEntity<ResponseStructure<Image>> updateImage(int id,MultipartFile file) throws IOException{
		 Image image = dao.findImageById(id);
		 if(image==null) {
			 throw new UserDoesNotExit("User not found with id :"+id);
		 }
		 else {
			 image.setImage(file.getBytes());
			 image.setName(file.getOriginalFilename());
			 Image db = dao.updateImage(image);
			 ResponseStructure<Image> rs=new ResponseStructure<Image>();
			 rs.setMessage("image has been updated");
			 rs.setData(db);
			 rs.setStatus(HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.ACCEPTED);
		 }
		
		
	}
//	=============================================================================
	
//	================================fetch by id========================================
	public ResponseEntity<byte[]> fetchImage(int id){
		Image image = dao.findImageById(id);
		if(image!=null) {
			byte[] img=dao.findImageById(id).getImage();
	    	HttpHeaders headers=new HttpHeaders();
	    	headers.setContentType(MediaType.IMAGE_JPEG);
	    	return new ResponseEntity<>(img,headers,HttpStatus.OK);
		}
		else {
			throw new UserDoesNotExit("User not found with id :"+id);
		}
	}
//	================================================================================
	
//	=========================delete by id==========================================
	public ResponseEntity<ResponseStructure<Image>> deleteImage(int id){
		Image image = dao.findImageById(id);
		if(image!=null) {
			User user=udao.fetchUserByImage(image);
			if(user!=null) {
				user.setImage(null);
				udao.updateUser(user);
				dao.deleteImage(id);
				ResponseStructure<Image> rs=new ResponseStructure<Image>();
				rs.setData(image);
				rs.setMessage("Image has been deleted successfully");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.ACCEPTED);
				
			}
			else {
				dao.deleteImage(id);
				ResponseStructure<Image> rs=new ResponseStructure<Image>();
				rs.setData(image);
				rs.setMessage("Image has been deleted successfully");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.ACCEPTED);
				
			}
		}
		else {
			throw new ImageNotFound("Image with id "+id+" id not found");
		}
	}
//	}

}
