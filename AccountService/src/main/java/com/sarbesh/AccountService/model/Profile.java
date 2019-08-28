package com.sarbesh.AccountService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="Profile")
public class Profile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
    private Long id;
	
	@NotNull
    @Size(max = 65)
    @Column(name = "FirstName")
    private String firstName;
	
	@Size(max = 65)
    @Column(name = "LastName")
    private String lastName;
	
	@NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true, name="Email", nullable = false)
    private String email;
	
	@NotNull
	@Column(name="Age")
	private int age;
	
	@NotNull
	@Column(name="Password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile(Long id, @NotNull @Size(max = 65) String firstName, @Size(max = 65) String lastName,
			@NotNull @Email @Size(max = 100) String email, @NotNull int age, @NotNull String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", age=" + age + "]";
	}

	public Profile() {
		super();
	}
	
	
	
	
}
