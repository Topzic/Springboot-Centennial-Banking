package com.example.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	

	@Query(value = "SELECT * FROM account WHERE CustomerId = :id", nativeQuery = true)
	List<Account> findAccountByCustomerId(int id);
		
	//List customers with savings account balances >$1000.00
	@Query(value = "SELECT c.firstName, c.lastName FROM a account WHERE a.customerId = c.customerID and a.balance > 1000 and a.accountType = 'Savings'", nativeQuery = true)
	List<Customer> findNamesWithHighSavings(String firstName, String lastName);
	
	@Query(value = "SELECT * FROM account WHERE accountno = :accountNo", nativeQuery = true)
	Account findAccount(String accountNo);
	
	@Modifying
	@Query(value = "DELETE FROM account WHERE accountNo = :accountNo", nativeQuery = true)
	void deleteAccountByAccountNo(String accountNo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE account SET balance = :accountBalance, overdraftlimit = :accountOverdraftLimit WHERE accountno = :accountNumber", nativeQuery = true)
    void updateAccountParameters(String accountBalance, String accountOverdraftLimit, String accountNumber);

    
  /**
   * Administration Queries
   */
    
    //customers count by account type
    @Query(value = "SELECT COUNT(*) AS accountCount FROM account WHERE AccountTypeCode = 1;", nativeQuery = true)
    int countBySavingsAccountType();
    
  //customers count by account type
    @Query(value = "SELECT COUNT(*) AS accountCount FROM account WHERE AccountTypeCode = 2", nativeQuery = true)
    int countByChequingAccountType();
    
  //customers count by account type
    @Query(value = "SELECT COUNT(*) AS accountCount FROM account WHERE AccountTypeCode = 3", nativeQuery = true)
    int countByDepositAccountType();
    
  //customers count by account type
    @Query(value = "SELECT COUNT(*) AS accountCount FROM account WHERE AccountTypeCode = 4", nativeQuery = true)
    int countByRESPAccountType();
}
