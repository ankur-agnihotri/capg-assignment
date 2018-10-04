package com.assessment.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.bo.CurrentAccountBO;
import com.assessment.bo.CustomerBO;
import com.assessment.bo.TransactionBO;
import com.assessment.dao.Dao;
import com.assessment.exception.LowBalanceException;
import com.assessment.exception.RecordNotFoundException;
@Service
public class CustomerManager {
	@Autowired
	private Dao customerDao;
	@Autowired
	private TransactionManager transactionManager;

	public CurrentAccountBO createCurrentAccount(Integer customerID, Integer initialCredit)
			throws RecordNotFoundException, LowBalanceException {
		CustomerBO customerBO = customerDao.getCustomerByID(customerID);
		CurrentAccountBO currentAccountBO = customerDao.createCurrentAccount();
		if (initialCredit != 0) {
			transactionManager.createTransaction(currentAccountBO, initialCredit);
		}
		List<CurrentAccountBO> currentAccountBOs = customerBO.getCurrentAccountList();
		if (currentAccountBOs == null) {
			currentAccountBOs = new ArrayList<>();
		}
		currentAccountBOs.add(currentAccountBO);
		customerBO.setCurrentAccountList(currentAccountBOs);
		return currentAccountBO;
	}

	
	public CustomerBO getCustomerDetailsByID(Integer customerID) throws RecordNotFoundException {
		CustomerBO customerBO = customerDao.getCustomerByID(customerID);
		return customerBO;
	}
}
