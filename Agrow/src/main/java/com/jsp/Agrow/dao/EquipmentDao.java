package com.jsp.Agrow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.Equipment;
import com.jsp.Agrow.repo.EquipmentRepo;

@Repository
public class EquipmentDao {
	@Autowired
	EquipmentRepo repo;
	
//	=====================save Equipment=============================================
	public Equipment saveEquipment(Equipment equipment) {
		return repo.save(equipment);
	}
//	=============================================================================
	
	
//	====================fetch equipment by id=========================================
	public Equipment fetchEquipById(int id) {
		Optional<Equipment> data = repo.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
//	==================================================================================
	
	
//	===========================FetchAll=================================================
	public List<Equipment> fetchAllEquip(){
		return repo.findAll();
	}
//	==================================================================================
	
	
//	===========================Fetch By name==============================================
	public Equipment fetchEquipByName(String name) {
		return repo.fetchEquipByName(name);
	}
//	=====================================================================================
}
