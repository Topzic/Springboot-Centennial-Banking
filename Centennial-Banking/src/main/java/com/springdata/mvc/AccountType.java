package com.springdata.mvc;

import java.util.List;

public class AccountType {
	
	private int AccountTypeCode;
	
	private String AccountTypeName;

	private String AccountTypeDesc;
	
	
	public AccountType()
	{
		
	}

	public AccountType(int accountTypeCode, String accountTypeName, String accountTypeDesc) {
		this.AccountTypeCode = accountTypeCode;
		this.AccountTypeName = accountTypeName;
		this.AccountTypeDesc = accountTypeDesc;
	}

	public int getAccountTypeCode() {
		return AccountTypeCode;
	}
	public void setAccountTypeCode(int accountTypeCode) {
		this.AccountTypeCode = accountTypeCode;
	}

	public String getAccountTypeName() {
		return AccountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.AccountTypeName = accountTypeName;
	}

	public String getAccountTypeDesc() {
		return AccountTypeDesc;
	}
	public void setAccountTypeDesc(String accountTypeDesc) {
		this.AccountTypeDesc = accountTypeDesc;
	}
		
}
