package com.springdata.mvc;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

public class Customer {

	private int CustomerId;
	
	@NotBlank(message = "Username is Mandatory")
	private String Username;
	
	@NotBlank(message = "Password is Mandatory")
	private String Password;
	
	@NotBlank(message = "First Name is Mandatory")
    private String FirstName;
	
	@NotBlank(message = "Last Name is Mandatory")
    private String LastName;
	
	@NotBlank(message = "Address is Mandatory")
    private String Address;
	
	@NotBlank(message = "City is Mandatory")
    private String City;
	
	@NotBlank(message = "Postal Code is Mandatory")
    private String PostalCode;
	
	@NotBlank(message = "Phone Number is Mandatory")
    private String PhoneNo;
	
	@NotBlank(message = "Email Address is Mandatory")
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

	//getter and setter methods
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
	
	
	//User Input Validation
	public boolean checkInput(String EmailId, String PostalCode)
	{
		boolean status =false;
		final String message; 
		
		var emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		var postalRegex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
		EmailId = this.EmailId;
		PostalCode = this.PostalCode;
		
		//validate email
		if(EmailId.matches(emailRegex) && PostalCode.matches(postalRegex))
		{
			status =true;
		}
		return status;
	}
	
	

}
