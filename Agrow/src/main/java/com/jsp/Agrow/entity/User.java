package com.jsp.Agrow.entity;


import java.util.List;


import com.jsp.Agrow.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "first name should not be empty")
	@NotBlank(message = "first name cannot be blank")
	private String firstName;
	
	@NotNull(message = "last name should not be empty")
	@NotBlank(message = "last name cannot be blank")
	private String lastName;
	
	@Column(unique = true)
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	
	@Min(value= 6000000000l, message = " phone number must be valid" )
	@Max(value= 9999999999l, message = " phone number must be valid" )
	private long phone;
	
    
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must meet criteria")
	private String password;
	
	@Pattern(regexp="^(?:[1-9]|[1-9][0-9]|100)$", message="Age must be a number between 1 to 100")
	private String age;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> posts;
	
	
	
	
}
