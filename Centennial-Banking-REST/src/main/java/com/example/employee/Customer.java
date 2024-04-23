package com.example.employee;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column(name="customerid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int CustomerId;
	
	@Column(name="username")
	private String Username;
	
	@Column(name="password") 
	private String Password;
	
	@Column(name="firstname")
    private String FirstName;
	
	@Column(name="lastname")
    private String LastName;
	
	@Column(name="address")
    private String Address;
	
	@Column(name="city")
    private String City;
	
	@Column(name="postalcode")
    private String PostalCode;
	
	@Column(name="phoneno")
    private String PhoneNo;
	
	@Column(name="emailid")
    private String EmailId;
    
	public Customer() 
	{	
		
	}

	public Customer(String username, String password, String firstName, String lastName, String address,
			String city, String postalCode, String phone, String emailId) {
		this.Username = username;
		this.Password = password;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.Address = address;
		this.City = city;
		this.PostalCode = postalCode;
		this.PhoneNo = phone;
		this.EmailId = emailId;		
	}

	/**
	 * Getters & Setters
	 * @return
	 */
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		this.CustomerId = customerId;
	}

	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		this.Address = address;
	}

	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		this.City = city;
	}

	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		this.PostalCode = postalCode;
	}

	public String getPhone() {
		return PhoneNo;
	}
	public void setPhone(String phoneNo) {
		this.PhoneNo = phoneNo;
	}

	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		this.EmailId = emailId;
	}

}
