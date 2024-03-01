package com.jsp.Agrow.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	private int likes;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;
	private LocalDate date;
	private String caption;
	private String location;
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> tags;
	
	
	
}
