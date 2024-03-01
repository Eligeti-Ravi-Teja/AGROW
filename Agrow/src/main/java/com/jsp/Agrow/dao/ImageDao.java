package com.jsp.Agrow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.Image;
import com.jsp.Agrow.repo.ImageRepo;

@Repository
public class ImageDao {
	@Autowired
	ImageRepo repo;
	
//	=====insert Image===========================================================
	public Image registerUser(Image image) {
		return repo.save(image);
	}
//	==========================================================================
	
//	==================================update==========================================
	public Image updateUser(Image image) {
		return repo.save(image);
	}
//	=============================================================================
	
//============================Fetch by id===============================================
	public Image findUserById(int id) {
		Optional<Image> s = repo.findById(id);
		if(s.isPresent()) {
			return s.get();
		}
		else {
			return null;
		}
	}
//	===================================================================================
	
//	===============================fetchAll===========================================
	public List<Image> FetchAll(){
		return repo.findAll();
	}
//	=====================================================================================

//	============================delete by id =========================================
	public Image deleteUser(int id) {
		Optional<Image> data = repo.findById(id);
		repo.deleteById(id);
		return data.get();
	}
}
