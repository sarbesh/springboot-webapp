package com.sarbesh.AccountService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Entity
//@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long id;
	
	@NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true, name="Email", nullable = false)
    private String email;
	
	@Column(name="Password")
	private String password;
	

	public User(Long id, @NotNull @Email @Size(max = 100) String email, @NotNull String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", email=" + email + "]";
	}
	
	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
