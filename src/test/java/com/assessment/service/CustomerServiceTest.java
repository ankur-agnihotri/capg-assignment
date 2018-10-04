package com.assessment.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assessment.bo.CurrentAccountBO;
import com.assessment.bo.CustomerBO;
import com.assessment.exception.LowBalanceException;
import com.assessment.exception.RecordNotFoundException;
import com.assessment.manager.CustomerManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	CustomerManager customerManager;

	@Test
	public void createAccountTest() throws RecordNotFoundException, LowBalanceException {

		CurrentAccountBO currentAccountBO = customerManager.createCurrentAccount(100, 10);
		Assert.assertNotNull(currentAccountBO);
		Assert.assertNotNull(currentAccountBO.getCurrentAccountNumber());
	}

	@Test
	public void createAccountZeroBalanceTest() throws RecordNotFoundException, LowBalanceException {

		CurrentAccountBO currentAccountBO = customerManager.createCurrentAccount(100, 0);
		Assert.assertNotNull(currentAccountBO);
		Assert.assertNotNull(currentAccountBO.getCurrentAccountNumber());
	}

	@Test(expected = LowBalanceException.class)
	@SuppressWarnings("unused")
	public void createAccountNegativeBalanceTest() throws RecordNotFoundException, LowBalanceException {
		CurrentAccountBO currentAccountBO = customerManager.createCurrentAccount(100, -100);
	}

	@Test(expected = RecordNotFoundException.class)
	@SuppressWarnings("unused")
	public void createAccountInvalidCustomerIdTest() throws RecordNotFoundException, LowBalanceException {
		CurrentAccountBO currentAccountBO = customerManager.createCurrentAccount(10000, 100);
	}

	@Test
	public void getCustomerDetail() throws RecordNotFoundException {
		CustomerBO customerBO = customerManager.getCustomerDetailsByID(100);
		Assert.assertNotNull(customerBO);
		Assert.assertNotNull(customerBO.getCustomerID());
		Assert.assertNotNull(customerBO.getFirstName());
		Assert.assertNotNull(customerBO.getLastName());

	}

	@Test(expected = RecordNotFoundException.class)
	@SuppressWarnings("unused")
	public void getCustomerDetailInvalidCustomerID() throws RecordNotFoundException {
		CustomerBO customerBO = customerManager.getCustomerDetailsByID(10000);
	}

}
