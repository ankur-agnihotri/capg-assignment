package com.assessment.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.assessment.bo.CurrentAccountBO;
import com.assessment.bo.CustomerBO;
import com.assessment.bo.TransactionBO;
import com.assessment.exception.RecordNotFoundException;
@Repository
public class Dao  {
	
	private static List<CustomerBO> customerBOs= new ArrayList<>();
	private static Integer customerIdSequance = 100;
	private static Integer accountNumberSequance = 1000;
	@PostConstruct
	public void createDummyRecords() {
		// Customer without Current Account
		CustomerBO mikeCustomerBO = new CustomerBO();
		mikeCustomerBO.setCustomerID(customerIdSequance++);
		mikeCustomerBO.setFirstName("Mike");
		mikeCustomerBO.setLastName("Pence");
		// Customer with 1 Current account and 100 as balance 
		CustomerBO peterCustomerBO = new CustomerBO();
		peterCustomerBO.setCustomerID(customerIdSequance++);
		peterCustomerBO.setFirstName("Peter");
		peterCustomerBO.setLastName("Parker");
		CurrentAccountBO currentAccountBO = new CurrentAccountBO();
		currentAccountBO.setBalance(100);
		currentAccountBO.setCurrentAccountNumber(accountNumberSequance++);
		List <CurrentAccountBO> currentAccountBOs = new ArrayList<>();
		currentAccountBOs.add(currentAccountBO);
		peterCustomerBO.setCurrentAccountList(currentAccountBOs);
		customerBOs.add(peterCustomerBO);
		customerBOs.add(mikeCustomerBO);
	}
	
	public CustomerBO getCustomerByID(final Integer customerID) throws RecordNotFoundException {
	Optional<CustomerBO> customer = customerBOs.stream().filter(p->p.getCustomerID().equals(customerID)).findFirst();
	return customer.orElseThrow(()->new RecordNotFoundException("Invalid Customer ID"));
	}
	
	public CurrentAccountBO createCurrentAccount() {
		CurrentAccountBO currentAccountBO = new CurrentAccountBO();
		currentAccountBO.setCurrentAccountNumber(accountNumberSequance++);
      return	currentAccountBO;
	}
	
	
	public TransactionBO createTransaction(Integer amount) {
		TransactionBO transactionBO = new TransactionBO();
		transactionBO.setTransactionAmount(amount);
		if(amount>0) {
			transactionBO.setTransactionType("credit");	
		}else if(amount<0) {
			transactionBO.setTransactionType("debit");	
		}
		return transactionBO;
	}
}
