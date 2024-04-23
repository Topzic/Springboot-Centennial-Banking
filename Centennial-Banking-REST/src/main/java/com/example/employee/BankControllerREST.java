package com.example.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankControllerREST { 
	
	@Autowired
    BankService bankService;
	
	@ResponseBody
	@RequestMapping("")
	public Iterable<Customer> home() throws Exception {
        return bankService.getAllCustomers();
	}
	
	/*
	 * Customers
	 */
	
	@RequestMapping(value = "/customer/{username}/{password}", method = RequestMethod.GET)
	Customer authenticate(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
	    return bankService.authenticate(username, password);
	}
	
    @RequestMapping(value = "/customer/{empId}", method = RequestMethod.GET)
    Optional<Customer> getEmployee(@PathVariable("empId") int empId) throws Exception {
        return bankService.getCustomer(empId);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)	
    Iterable<Customer> getEmployees() throws Exception {
        return bankService.getAllCustomers();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addCustomer(@RequestBody Customer customer) throws Exception {
        bankService.addCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void updateCustomer(@PathVariable("id") int id, @RequestBody Customer customers) throws Exception {
        if (customers != null) {
            Customer updatedCustomer = customers;
            bankService.updateCustomer(id, updatedCustomer);
        } else {
            throw new Exception("Customer is not found");
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    void deleteCustomer(@PathVariable("id") int id) throws Exception {
        bankService.deleteCustomer(id);
    }
    
    /*
     * Accounts
     */
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    List<Account> findAccountsByCustomerId(@PathVariable("id") int id) throws Exception {
        return bankService.findAccountsByCustomerId(id);
    }
    
    @RequestMapping(value = "/add-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addCustomer(@RequestBody Account account) throws Exception {
        bankService.addAccount(account);
    }
    
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    Optional<Account> findAccountByCustomerId(@PathVariable("id") int id) throws Exception {
        return bankService.findAccountByCustomerId(id);
    }
    
    @RequestMapping(value = "/update-account/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void updateAccount(@PathVariable("id") int id, @RequestBody Account account) throws Exception {
        if (account != null) {
            Account updatedAccount = account;
            bankService.updateAccount(id, updatedAccount);
        } else {
            throw new Exception("Customer is not found");
        }
    }
    
    @RequestMapping(value = "/account-delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    void deleteAccount(@PathVariable("id") int id) throws Exception {
        bankService.deleteAccount(id);
    }
    
    /*
     * Administration
     */
    @RequestMapping(value = "/overdraft-customers", method = RequestMethod.GET)	
    List<Customer> getOverDraft() throws Exception {
        return bankService.getOverdraftAccounts();
    }
    
    @RequestMapping(value = "/count-chequing-account", method = RequestMethod.GET)	
    int countByChequingAccountType() throws Exception {
        return bankService.countByChequingAccountType();
    }
    
    @RequestMapping(value = "/count-saving-account", method = RequestMethod.GET)	
    int countBySavingsAccountType() throws Exception {
        return bankService.countBySavingsAccountType();
    }
    
    @RequestMapping(value = "/count-deposit-account", method = RequestMethod.GET)	
    int countByDepositAccountType() throws Exception {
        return bankService.countByDepositAccountType();
    }
    
    @RequestMapping(value = "/count-resp-account", method = RequestMethod.GET)	
    int countByRESPAccountType() throws Exception {
        return bankService.countByRESPAccountType();
    }
    
    @RequestMapping(value = "/list-high-customer-savings", method = RequestMethod.GET)	
    List<Customer> listHighSavingsCustomers() throws Exception {
        return bankService.listHighSavingsCustomers();
    }
    
}
