package com.jsp.Agrow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.Rental;
import com.jsp.Agrow.repo.RentalRepo;

@Repository
public class RentalDao {
	@Autowired
	RentalRepo repo;
	
//	============save rental===============================
	public Rental saveRental(Rental rental) {
		return repo.save(rental);
	}
//	========================================================
	

}
