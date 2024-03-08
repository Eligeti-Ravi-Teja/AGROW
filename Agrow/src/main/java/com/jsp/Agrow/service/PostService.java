package com.jsp.Agrow.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agrow.dao.ImageDao;
import com.jsp.Agrow.dao.PostDao;
import com.jsp.Agrow.dao.UserDao;
import com.jsp.Agrow.dao.exception.PostNotFound;
import com.jsp.Agrow.dao.exception.UserDoesNotExit;
import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Image;
import com.jsp.Agrow.entity.Post;
import com.jsp.Agrow.entity.User;


@Service
public class PostService {
	@Autowired
	PostDao dao;
	@Autowired
	UserDao udao;
	@Autowired
	ImageDao idao;
//=====================================posting a post========================================	
	public ResponseEntity<ResponseStructure<Post>> postData(int uid,MultipartFile file,String caption,String location) throws IOException{
		 User user=udao.findUserById(uid);
		 if(user!=null) {
			 Image image=new Image();
			 image.setImage(file.getBytes());
			 image.setName(file.getOriginalFilename());
			 Image img=idao.createImage(image);
			 Post post=new Post();
			 post.setImage(img);
			 post.setDate(LocalDate.now());
			 post.setCaption(caption);
			 post.setLocation(location);
			 Post dbpost = dao.createPost(post);
			 List<Post> posts=user.getPosts();
			 posts.add(dbpost);
			 user.setPosts(posts);
			 udao.updateUser(user);
			 ResponseStructure<Post> rs=new ResponseStructure<Post>();
			 rs.setData(dbpost);
			 rs.setMessage("Posted successfully!!!");
			 rs.setStatus(HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.ACCEPTED); 
		 }
		 else {
			 throw new UserDoesNotExit("User with id :"+uid+" is not found!!");
		 }
	}
//	====================================================================================
	
//	=================fetching by id=======================================================
	public ResponseEntity<ResponseStructure<Post>> getById(int id){
		Post post=dao.findPostById(id);
		if(post!=null) {
			ResponseStructure<Post> rs= new ResponseStructure<Post>();
			rs.setData(post);
			rs.setMessage("Post Fetched successfully");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new PostNotFound("post with id :"+id+" is not found");
		}
	}
	

}
