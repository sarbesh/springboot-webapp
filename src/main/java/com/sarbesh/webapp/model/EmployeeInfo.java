package com.sarbesh.webapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Emp_Info")
//@Embeddable 
public class EmployeeInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_info_id")
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_ID_id", nullable = false )
	private Employee employee;
	
	@NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    private String firstName;
	
	@Size(max = 65)
    @Column(name = "last_name")
    private String lastName;
	
	@NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true, name="email", nullable = false)
    private String email;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;
	
//	@Temporal(TemporalType.DATE)
//    @Column(name = "dob")
//    private Date dateOfBirth;
	
	private String designation;
	private float experience;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
//	public Date getDateOfBirth() {
//		return dateOfBirth;
//	}
//	public void setDateOfBirth(Date dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public float getExperience() {
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public EmployeeInfo(Long id, Employee employee, @NotNull @Size(max = 65) String firstName,
			@Size(max = 65) String lastName, @NotNull @Email @Size(max = 100) String email, Gender gender,
			Date dateOfBirth, String designation, float experience) {
		super();
		this.id = id;
		this.employee = employee;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
//		this.dateOfBirth = dateOfBirth;
		this.designation = designation;
		this.experience = experience;
	}
	
	
	
}
