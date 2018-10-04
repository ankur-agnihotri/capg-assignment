package com.assessment.bo;

import java.util.List;

public class CustomerBO {
	private Integer customerID;
	private String firstName;
	private String lastName;
	private List<CurrentAccountBO> currentAccountList;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public List<CurrentAccountBO> getCurrentAccountList() {
		return currentAccountList;
	}

	public void setCurrentAccountList(List<CurrentAccountBO> currentAccountList) {
		this.currentAccountList = currentAccountList;
	}

}
