package com.jsp.Agrow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agrow.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {

}
