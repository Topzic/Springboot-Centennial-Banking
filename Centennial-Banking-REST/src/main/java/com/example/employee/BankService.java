package com.example.employee;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	/**
	 * Customer
	 */
	
    public void addCustomer(Customer customer) throws Exception {
        if(customer != null) {
  		  	customerRepo.save(customer);
        }
        else {
            throw new Exception("Customer is not found");
        }
    }

    public Customer authenticate(String username, String password) {
        return customerRepo.authenticate(username, password);
    }
    
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepo.findAll();
    }
    
    public Optional<Customer> getCustomer(int id) throws Exception {
    	if(customerRepo.findById(id).isPresent()) {    		
    		return customerRepo.findById(id);
    	} else {
            throw new Exception("Customer is not found");
        }
    }

    public void updateCustomer(int id, Customer updatedCustomer) throws Exception {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new Exception("Customer not found"));

        existingCustomer.setUsername(updatedCustomer.getUsername());
        existingCustomer.setPassword(updatedCustomer.getPassword());
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setCity(updatedCustomer.getCity());
        existingCustomer.setPostalCode(updatedCustomer.getPostalCode());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setEmailId(updatedCustomer.getEmailId());

        customerRepo.save(existingCustomer);
    }

    public void deleteCustomer(int id) throws Exception {
    	customerRepo.deleteById(id);
    }
    
    /*
     * Accounts
     */
    public List<Account> findAccountsByCustomerId(int id) throws Exception {
    	return accountRepo.findAccountByCustomerId(id);
    }
    
    public void addAccount(Account account) throws Exception {
        if(account != null) {
  		  	accountRepo.save(account);
        }
        else {
            throw new Exception("Account is not found");
        }
    }
    
    public Optional<Account> findAccountByCustomerId(int id) throws Exception {
    	if(accountRepo.findById(id).isPresent()) {    		
    		return accountRepo.findById(id);
    	} else {
            throw new Exception("Account is not found");
        }
    }
    
    public void deleteAccount(int id) throws Exception {
    	accountRepo.deleteById(id);
    }
    
    public void updateAccount(int id, Account updatedAccount) throws Exception {
    	Account existingAccount = accountRepo.findById(id)
                .orElseThrow(() -> new Exception("Account not found"));
    	existingAccount.setBalance(updatedAccount.getBalance());
        accountRepo.save(existingAccount);
    }
    
    /*
     * Administration
     */
    
    public List<Customer> getOverdraftAccounts() throws Exception {
    	return customerRepo.listOverDraftCustomers();
    }
    
    public int countByChequingAccountType() throws Exception {
    	return accountRepo.countByChequingAccountType();
    }
    
    public int countBySavingsAccountType() throws Exception {
    	return accountRepo.countBySavingsAccountType();
    }
    
    public int countByDepositAccountType() throws Exception {
    	return accountRepo.countByDepositAccountType();
    }
    
    public int countByRESPAccountType() throws Exception {
    	return accountRepo.countByRESPAccountType();
    }
    
    public List<Customer> listHighSavingsCustomers() throws Exception {
    	return customerRepo.listHighSavingsCustomers();
    }
}
