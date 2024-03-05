package com.jsp.Agrow.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Image;
import com.jsp.Agrow.service.ImageService;

@RestController
public class ImageController {
	@Autowired
	ImageService service;
	
	
//	=====================setProfile====================================================
	@PostMapping("/setProfile")
	public ResponseEntity<ResponseStructure<Image>> setProfile(@RequestParam int id,@RequestParam("image") MultipartFile file) throws Exception{
//		Image image=new Image();
//		image.setName(file.getOriginalFilename());
//		image.setImage(file.getBytes());
		return service.setProfile(id, file);
		
	}
//	========================================================================================
	
//	========================fetch profile================================================
	@GetMapping("/fetchimg")
	public ResponseEntity<ResponseStructure<Image>> fetchImage(int id){
		return service.fetchImage(id);
	}
//	==============================================================================
	
//	==========================update====================================================
	@PutMapping("/updateImg")
	public ResponseEntity<ResponseStructure<Image>> updateImg(@RequestParam("image") MultipartFile file, @RequestParam int id) throws IOException{
		return service.updateImage(id,file);
	}
//	===================================================================================
	
//	=========================delete Image================================================
	@DeleteMapping("/deleteImg")
	public ResponseEntity<ResponseStructure<Image>> deleteImg(@RequestParam int id){
		return service.deleteImage(id);
	}
	

}
