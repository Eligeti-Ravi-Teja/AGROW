package com.jsp.Agrow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.Address;
import com.jsp.Agrow.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	AddressRepo repo;
	
//	update=====================================================================
	public Address updateStudent(Address address) {
		Optional<Address> data = repo.findById(address.getId());
		if(data.isPresent()) {
		  return repo.save(address);
		}
		else {
			return null;
		}
	}
//	============================================================================
	
}
