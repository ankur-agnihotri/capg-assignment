package com.assessment.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.bo.CurrentAccountBO;
import com.assessment.bo.CustomerBO;
import com.assessment.dao.Dao;
import com.assessment.exception.LowBalanceException;
import com.assessment.exception.RecordNotFoundException;

@Service
public class CustomerManager {
	private final static Logger LOGGER = LogManager.getLogger(CustomerManager.class);
	@Autowired
	private Dao customerDao;
	@Autowired
	private TransactionManager transactionManager;

	public CurrentAccountBO createCurrentAccount(Integer customerID, Integer initialCredit)
			throws RecordNotFoundException, LowBalanceException {
		LOGGER.debug("Getting customer Details for Customer ID:{} " , customerID);
		CustomerBO customerBO = customerDao.getCustomerByID(customerID);
		LOGGER.debug("Creating Account for Customer ID:{} " , customerID);
		CurrentAccountBO currentAccountBO = customerDao.createCurrentAccount();
		if (initialCredit != 0) {
			LOGGER.debug("Creating transaction for Account: {}" , currentAccountBO.getCurrentAccountNumber());
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
		LOGGER.debug("Getting customer Details for Customer ID:{} " , customerID);
		CustomerBO customerBO = customerDao.getCustomerByID(customerID);
		return customerBO;
	}
}
