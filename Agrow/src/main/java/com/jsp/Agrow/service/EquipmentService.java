package com.jsp.Agrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agrow.dao.EquipmentDao;
import com.jsp.Agrow.dao.UserDao;
import com.jsp.Agrow.dao.exception.EquipmentNotFound;
import com.jsp.Agrow.dao.exception.UserDoesNotExit;
import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Equipment;
import com.jsp.Agrow.entity.User;

@Service
public class EquipmentService {
	@Autowired
	EquipmentDao dao;
	
	@Autowired
	UserDao udao;
	
	
//	=====================================add equipment========================================
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(int uid,String name,double cost,int qty){
		User user=udao.findUserById(uid);
		if(user!=null) {
			Equipment equipment=new Equipment();
			equipment.setCostPerHour(cost);
			equipment.setName(name);
			equipment.setQuantity(qty);
			equipment.setUsers(user);
			Equipment e=dao.saveEquipment(equipment);
			ResponseStructure<Equipment> rs= new ResponseStructure<Equipment>();
			rs.setStatus(HttpStatus.OK.value());
			rs.setData(e);
			rs.setMessage("Equipmnet added successfully");
			return new ResponseEntity<ResponseStructure<Equipment>>(rs,HttpStatus.OK);
		}
		else {
			throw new UserDoesNotExit("No user with id"+uid);
		}
	}
//	=========================================================================================
	
	
//	================================find Equipment by id=====================================
	public ResponseEntity<ResponseStructure<Equipment>> findEquipById(int id){
		Equipment e=dao.fetchEquipById(id);
		if(e!=null) {
			ResponseStructure<Equipment> rs=new ResponseStructure<Equipment>();
			rs.setData(e);
			rs.setMessage("equipment fetched successfully");
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(rs,HttpStatus.OK);
			
		}
		else {
			throw new EquipmentNotFound("No equipment with id: "+id+".");
		}
	}
//	=======================================================================================
	
//	================================find equipment by name=================================
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipByName(String name){
		Equipment e=dao.fetchEquipByName(name);
		if(e!=null) {
			ResponseStructure<Equipment> rs=new ResponseStructure<Equipment>();
			rs.setMessage("equipment fetched successfully");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setData(e);
			return new ResponseEntity<ResponseStructure<Equipment>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new EquipmentNotFound("No equipment with name +"+name+".");
		}
	}
//	==================================================================================
	
//	===============================fetch All Equipments==================================
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchAllEquipments(){
		 List<Equipment> data = dao.fetchAllEquip();
		 ResponseStructure<List<Equipment>> rs=new ResponseStructure<List<Equipment>>();
		 rs.setMessage("fetched all equipments successfully");
		 rs.setData(data);
		 rs.setStatus(HttpStatus.ACCEPTED.value());
		 
		 return new ResponseEntity<ResponseStructure<List<Equipment>>>(rs,HttpStatus.ACCEPTED);
	}
//	===================================================================================
}