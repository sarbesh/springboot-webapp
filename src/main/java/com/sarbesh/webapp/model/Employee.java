package com.sarbesh.webapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
//import javax.persistence.Transient;

@Entity
@Table(name="Emp_Id")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name="password")
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@OneToOne(mappedBy = "emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee(long id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
//	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	

}