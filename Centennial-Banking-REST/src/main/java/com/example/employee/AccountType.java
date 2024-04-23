package com.example.employee;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="accounttype")
public class AccountType {
	
	@Id
	@Column(name="accounttypecode")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int AccountTypeCode;
	
	@Column(name="accounttypename")
	private String AccountTypeName;
	
	@Column(name="accounttypedesc")
	private String AccountTypeDesc;
	
	
	public AccountType()
	{
	}

	public AccountType(int accountTypeCode, String accountTypeName, String accountTypeDesc) {
		super();
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
	
	
	
	//OverDraft Validation
		public String overDraftOK(int AccountTypeCode)
		{
			String status = "false";
			final String message; 
			
			//AccountTypeCode = this.AccountTypeCode;
			AccountTypeName = this.getAccountTypeName();
			
			//validate account type
			if(AccountTypeName == "Chequing")
			{
				status = "OK";
			}
			return status;
		}
		
}
