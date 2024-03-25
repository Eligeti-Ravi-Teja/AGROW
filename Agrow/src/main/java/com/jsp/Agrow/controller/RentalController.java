package com.jsp.Agrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Rental;
import com.jsp.Agrow.service.RentalService;

@RestController
public class RentalController {
	@Autowired
	RentalService service;
	
	@PostMapping("/saveRental")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam int eid,@RequestParam int uid,@RequestParam String startTime,@RequestParam String endTime){
		return service.saveRental(eid, uid, startTime, endTime);
	}
}
