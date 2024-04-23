package com.springdata.mvc;

import java.awt.PageAttributes.MediaType;
import java.net.http.HttpHeaders;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import jakarta.validation.Valid;

@Controller
public class BankController {

	@Autowired
	RestTemplate restTemplate;
	
	Customer currentCustomer;
	
	// Getter for currentCustomer
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	
	// Need to Implement to protect customer pages from none customers
	public boolean checkCurrentCustomer() {
		if (currentCustomer == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";	
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		currentCustomer = null;
		return "index";	
	}
	
    @RequestMapping(value = "/login/username/{username}/password/{password}", method = RequestMethod.GET)
    public ModelAndView login(@PathVariable("username") String username, @PathVariable("password") String password, @ModelAttribute Customer customer) 
    {	
        ModelAndView mv = new ModelAndView();
        Customer retrievedCustomer = restTemplate.getForObject("http://localhost:8080/customer/" + username + "/" + password, Customer.class);

        if (retrievedCustomer != null) {
        	username = retrievedCustomer.getUsername();
            currentCustomer = retrievedCustomer;
            mv.setViewName("view-profile");
            mv.addObject("customer", currentCustomer);
        } else {
            // Handle the case where the customer is not found, e.g., display an error message
            mv.setViewName("index");
            mv.addObject("message", "Login failed. Please check your credentials.");
        }

        return mv;
    }

	/**
	 * Bank Accounts
	 */
	
	/**
	 * GET new-account view
	 */
	@GetMapping("/account")
	public ModelAndView newAccount()
	{
		
		ModelAndView mv = new ModelAndView(); 
		
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
		
		mv.setViewName("new-account");
		mv.addObject("customer", currentCustomer);
		return mv;
	}

	@RequestMapping(value = "/view-accounts", method = RequestMethod.GET)
	public ModelAndView viewAccounts()
	{
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    mv.setViewName("view-accounts");
	    
	    Account[] accounts = restTemplate.getForObject("http://localhost:8080/accounts/" + currentCustomer.getCustomerId(), Account[].class);
	    mv.addObject("customer", currentCustomer);														
	    mv.addObject("accounts", Arrays.asList(accounts));
	    return mv;
	}

	/*
	 * POST new-account
	 */
	@RequestMapping(value = "/new-account", method = RequestMethod.POST)
	public ModelAndView addAccount(@ModelAttribute Account account) 
	{	
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    account.setCustomerId(currentCustomer.getCustomerId());

	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

	    HttpEntity<Account> requestEntity = new HttpEntity<>(account, headers);
	    ResponseEntity<Account> responseEntity = restTemplate.exchange("http://localhost:8080/add-account", HttpMethod.POST, requestEntity, Account.class);
	    Account[] accounts = restTemplate.getForObject("http://localhost:8080/accounts/" + currentCustomer.getCustomerId(), Account[].class);

	    mv.setViewName("redirect:/view-accounts");
	    mv.addObject("accounts", Arrays.asList(accounts));
	    mv.addObject("message", "Account added successfully");
	    return mv;
	}
	
	@RequestMapping(value = "/edit/{accountId}", method = RequestMethod.GET)
	public ModelAndView editAccount(@PathVariable("accountId") int accountId, @ModelAttribute Account account) 
	{	
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    mv.setViewName("edit-account");
	    mv.addObject("customer", currentCustomer);
	    mv.addObject("account", restTemplate.getForObject("http://localhost:8080/account/" + accountId, Account.class));
	    return mv;
	}
	
	/*
	 * POST edit/accountNo
	 */
	@RequestMapping(value = "/edit-account/accountNo/{accountNo}", method = RequestMethod.POST)
	public ModelAndView updateAccount(@PathVariable("accountNo") int accountNo, @ModelAttribute Account account) 
	{	
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    mv.setViewName("redirect:/view-accounts");

	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

	    HttpEntity<Account> requestEntity = new HttpEntity<>(account, headers);
	    ResponseEntity<Account> responseEntity = restTemplate.exchange("http://localhost:8080/update-account/" + accountNo, HttpMethod.PUT, requestEntity, Account.class);

	    Account updatedAccount = responseEntity.getBody();
	    mv.addObject("customer", updatedAccount);

	    return mv;
	}

	/*
	 * POST account-delete/accountId/{accountId}
	 */
	@RequestMapping(value = "/account-delete/accountId/{accountId}", method = RequestMethod.POST)
	public ModelAndView deleteAccount(@PathVariable("accountId") int accountId) 
	{	
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    restTemplate.delete("http://localhost:8080/account-delete/" + accountId);

	    Account[] accounts = restTemplate.getForObject("http://localhost:8080/accounts/" + currentCustomer.getCustomerId(), Account[].class);

	    mv.setViewName("redirect:/view-accounts");
	    mv.addObject("accounts", Arrays.asList(accounts));
	    mv.addObject("message", "Account deleted successfully");
	    return mv;
	}

	/**
	 * Customers
	 */
	
	/**
	 *  GET new-customer
	 */
	@RequestMapping(value = "/new-customer", method = RequestMethod.GET)
	public String newCustomer()
	{
	    return "new-customer";
	}
	
	/*
	 * POST new-customer
	 */
	@RequestMapping(value = "/new-customer", method = RequestMethod.POST)
	public ModelAndView addCustomer(@ModelAttribute Customer customer) 
	{
	    ModelAndView mv = new ModelAndView();
	    
	    mv.setViewName("index");
	    
	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
	    
	    HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
	    ResponseEntity<Customer> responseEntity = restTemplate.exchange("http://localhost:8080/customer", HttpMethod.POST, requestEntity, Customer.class);

	    Customer createdCustomer = responseEntity.getBody();
	    mv.addObject("customer", createdCustomer);
	    return mv;
	}

	/*
	 * GET view-profile
	 */
	@RequestMapping(value = "/view-profile", method = RequestMethod.GET)
	public ModelAndView viewProfile() 
	{
	    ModelAndView mv = new ModelAndView();

	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }

	    mv.setViewName("view-profile");
	    mv.addObject("customer", currentCustomer);
	    mv.addObject("message", "Welcome back " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName());

	    return mv;
	}
	
	/*
	 * GET edit/customerId
	 */
	@RequestMapping(value = "/edit/customerId/{customerId}", method = RequestMethod.GET)
	public ModelAndView editCustomer(@PathVariable("customerId") int customerId, @ModelAttribute Customer Customer) 
	{
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    mv.setViewName("edit-customer");
	    mv.addObject("customer", restTemplate.getForObject("http://localhost:8080/customer/" + customerId, Customer.class));
	    return mv;
	}
	
	/*
	 * POST edit/customerId
	 */
	@RequestMapping(value = "/edit/customerId/{customerId}", method = RequestMethod.POST)
	public ModelAndView updateCustomer(@PathVariable("customerId") int customerId, @ModelAttribute Customer customer) 
	{
	    ModelAndView mv = new ModelAndView();
	    
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
	    mv.setViewName("view-profile");

	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

	    HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
	    ResponseEntity<Customer> responseEntity = restTemplate.exchange("http://localhost:8080/customer/" + customerId, HttpMethod.PUT, requestEntity, Customer.class);

	    Customer updatedCustomer = responseEntity.getBody();

	    if (updatedCustomer != null) {
	        currentCustomer = updatedCustomer;
	        mv.addObject("customer", updatedCustomer);
	    } else {
	        // Handle the error, e.g., set an error message
	        mv.addObject("errorMessage", "Failed to update customer information");
	    }

	    return mv;
	}


	/**
	 * Administration Options
	 */

	/**
	 *  GET view-admin
	 */
	@RequestMapping(value = "/view-admin", method = RequestMethod.GET)
	public ModelAndView viewAdmin() 
	{
		
        ModelAndView mv = new ModelAndView();
        
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }

	    if (!currentCustomer.getUsername().contains("Admin")) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
        mv.setViewName("admin");
		mv.addObject("customer", currentCustomer);
		return mv;
	}
						
	/**
	 *  GET admin
	 */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView viewAdminCustByAccount(@ModelAttribute Customer Customer)
    {
        ModelAndView mv = new ModelAndView();
        
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }

	    if (!currentCustomer.getUsername().contains("Admin")) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
        mv.setViewName("admin");	
	    mv.addObject("customer", currentCustomer);
        mv.addObject("accounts1", restTemplate.getForObject("http://localhost:8080/count-saving-account", int.class));
        mv.addObject("accounts2", restTemplate.getForObject("http://localhost:8080/count-chequing-account", int.class));
        mv.addObject("accounts3", restTemplate.getForObject("http://localhost:8080/count-deposit-account", int.class));
        mv.addObject("accounts4", restTemplate.getForObject("http://localhost:8080//count-resp-account", int.class));
        return mv;
    }  
	
	/**
	 *  GET admin-overdraft
	 */
    @RequestMapping(value = "/admin-overdraft", method = RequestMethod.GET)
    public ModelAndView show(@ModelAttribute Customer Customer)
    {
        ModelAndView mv = new ModelAndView();
        
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }

	    if (!currentCustomer.getUsername().contains("Admin")) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
        mv.setViewName("admin-overdraft");	
	    mv.addObject("customer", currentCustomer);
        mv.addObject("customers", restTemplate.getForObject("http://localhost:8080/overdraft-customers", Customer[].class));
        return mv;
    }    
	
	/**
	 *  GET admin-high-savings
	 */
    @RequestMapping(value = "/admin-high-savings", method = RequestMethod.GET)
	public ModelAndView viewAdminHighSavings(@ModelAttribute Customer Customer) 
	{
        ModelAndView mv = new ModelAndView();
        
	    if (!checkCurrentCustomer()) {
	        mv.setViewName("index");
	        return mv;
	    }

	    if (!currentCustomer.getUsername().contains("Admin")) {
	        mv.setViewName("index");
	        return mv;
	    }
	    
        mv.setViewName("admin-high-savings");
	    mv.addObject("customer", currentCustomer);
        mv.addObject("customers", restTemplate.getForObject("http://localhost:8080/list-high-customer-savings", Customer[].class));
        return mv;
	}
    
}
