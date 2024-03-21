package com.jsp.Agrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Equipment;
import com.jsp.Agrow.service.EquipmentService;

@RestController
public class EquipmentController {
	@Autowired
	EquipmentService service;
	
//	========================================add equipment===================================
	@PostMapping("/addEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(@RequestParam int uid,@RequestParam String name,@RequestParam double cost,@RequestParam int qty){
		return service.saveEquipment(uid, name, cost, qty);
	}
//	=======================================================================================
	
//	==================================fetch equipment by id===============================
	@GetMapping("/fetchEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipment(@RequestParam int id){
		return service.findEquipById(id);
	}
//	=======================================================================================
	
//	====================fetchEquipmentByName=============================================
	@GetMapping("/fetchEquipmentByName")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipByName(@RequestParam String name){
		return service.fetchEquipByName(name);
	}
//	=========================================================================
	
//	==================fetch All Equipments==================================================
	@GetMapping("/fetchAllEquipments")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchAllEquipments() {
		return service.fetchAllEquipments();
	}
//	=================================================================================
	
//	===========================update Equipments===============================
	@PutMapping("/updateEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment equipment){
		return service.updateEquipment(equipment);
	}
//	========================================================================================
	
//	===============================Delete Equipment=======================================
	@DeleteMapping("/deleteEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam int id){
		return service.deleteEquipment(id);
	}
	
}
