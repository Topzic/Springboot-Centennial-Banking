package com.example.employee;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="accountno")
	private int AccountNo;
	
	@Column(name="accounttypecode")
	private int AccountTypeCode;
	
	@Column(name="customerid")
	private int CustomerId;
	
	@Column(name="balance")
	private double Balance;
	
	@Column(name="overdraftlimit")
    private String OverDraftLimit;

	public Account() {
		
	}
	
	public Account(int accountNo, int accountTypeCode, int customerId, double balance, String overDraftLimit) {
		this.AccountNo = accountNo;
		this.AccountTypeCode = accountTypeCode;
		this.CustomerId = customerId;
		this.Balance = balance;
		this.OverDraftLimit = overDraftLimit;
	}

	//getters and setters
	public int getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(int accountNo) {
		this.AccountNo = accountNo;
	}
	
	public int getAccountTypeCode() {
		return AccountTypeCode;
	}
	public void setAccountTypeCode(int accountTypeCode) {
		this.AccountTypeCode = accountTypeCode;
	}
	
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		this.CustomerId = customerId;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		this.Balance = balance;
	}

	public String getOverDraftLimit() {
		return OverDraftLimit;
	}

	public void setOverDraftLimit(String overDraftLimit) {
		this.OverDraftLimit = overDraftLimit;
	}	
	
}
