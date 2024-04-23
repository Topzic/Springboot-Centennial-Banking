package com.springdata.mvc;

import java.util.List;

public class Account {
	
	private int AccountNo;
	
	private int AccountTypeCode;
	
	private int CustomerId;
	
	private double Balance;
	
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

	/**
	 * Getters & Setters
	 * @return
	 */
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
