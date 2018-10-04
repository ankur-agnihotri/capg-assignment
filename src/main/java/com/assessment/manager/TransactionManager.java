package com.assessment.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.bo.CurrentAccountBO;
import com.assessment.bo.TransactionBO;
import com.assessment.dao.Dao;
import com.assessment.exception.LowBalanceException;
@Service
public class TransactionManager {
	@Autowired
	private Dao transactionDao;

public void createTransaction(CurrentAccountBO currentAccountBO, Integer amount) throws LowBalanceException {
		
		Integer currentBalance = currentAccountBO.getBalance();
		if(currentBalance==null) {
			currentBalance = 0;
		}
		if (amount < 0 && currentBalance < (-amount)) {
			throw new LowBalanceException("you have low balance to perform this tranaction");
		}
		
		currentBalance = currentBalance + amount;
		currentAccountBO.setBalance(currentBalance);
		TransactionBO transactionBO = transactionDao.createTransaction(amount);
		List<TransactionBO> transactionBOList = currentAccountBO.getTransactionList();
		if (transactionBOList == null) {
			transactionBOList = new ArrayList<>();
		}
		transactionBOList.add(transactionBO);
		currentAccountBO.setTransactionList(transactionBOList);
	}
}
