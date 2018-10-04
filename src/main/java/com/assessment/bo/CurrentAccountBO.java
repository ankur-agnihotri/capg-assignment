package com.assessment.bo;

import java.util.List;

public class CurrentAccountBO {

	private Integer currentAccountNumber;
	private Integer balance;
	private List<TransactionBO> transactionList;

	public Integer getCurrentAccountNumber() {
		return currentAccountNumber;
	}

	public void setCurrentAccountNumber(Integer currentAccountNumber) {
		this.currentAccountNumber = currentAccountNumber;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public List<TransactionBO> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionBO> transactionList) {
		this.transactionList = transactionList;
	}

}
