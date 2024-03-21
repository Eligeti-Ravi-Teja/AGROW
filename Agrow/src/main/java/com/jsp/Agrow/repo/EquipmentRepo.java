package com.jsp.Agrow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agrow.entity.Equipment;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer>{
	@Query("select e from Equipment e where name=?1")
	public List<Equipment> fetchEquipByName(String name);

}
