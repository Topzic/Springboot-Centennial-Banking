package com.example.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.Username = :username AND c.Password = :password")
    Customer authenticate(@Param("username") String username, @Param("password") String password);
    
	//***********
	//ADMIN QUERIES
	//************
	
	//List customer with activated Overdraft
	@Query(value = "SELECT c.* FROM customer c JOIN account a ON c.customerid = a.customerid WHERE a.overdraftlimit != -1", nativeQuery = true)
	List<Customer> listOverDraftCustomers();

	//List of customers with savings account over $1000.00
	@Query(value = "SELECT c.* FROM customer c JOIN account a WHERE c.customerId = a.customerId AND a.accountTypeCode = 2 AND a.balance >= 1000", nativeQuery = true)
	List<Customer> listHighSavingsCustomers();
    
}
