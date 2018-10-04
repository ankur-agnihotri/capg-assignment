package com.assessment.service;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.assessment.bo.CurrentAccountBO;
import com.assessment.bo.CustomerBO;
import com.assessment.exception.LowBalanceException;
import com.assessment.exception.RecordNotFoundException;
import com.assessment.manager.CustomerManager;

@Path("/")
public class CustomerService {
	private final static Logger LOGGER = LogManager.getLogger(CustomerService.class);
	@Autowired
	private CustomerManager customerManager;
	
	  @GET
	  @Path("customer/{customerId}")
	  @Produces(MediaType.APPLICATION_JSON) 
	  public Response getCustomerDetails(@PathParam("customerId") Integer customerID) {
		  try {
			CustomerBO customerBO = customerManager.getCustomerDetailsByID(customerID);
			return Response.ok().entity(customerBO).build();
		} catch (RecordNotFoundException e) {
			LOGGER.error("Checked Error:{}",e.getMessage());
			return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unchecked Error:{}",e.getCause());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server Error").build();
		}
		  }
	  
	 

	@POST
	@Path("/account/{customerId}/{initialCredit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAccount(@PathParam("customerId") Integer customerID,
			@PathParam("initialCredit") Integer initialCredit) {

		try {
			LOGGER.info("create account for customer id:{} and initialCredit:{}",customerID,initialCredit);
			CurrentAccountBO currentAccountBO = customerManager.createCurrentAccount(customerID, initialCredit);
			LOGGER.info("Account Created with Account Number:{}",currentAccountBO.getCurrentAccountNumber());
			return Response.ok().entity(currentAccountBO.getCurrentAccountNumber()).build();
		} catch (RecordNotFoundException | LowBalanceException e) {
			e.printStackTrace();
			LOGGER.error("Checked Error:{}",e.getMessage());
			return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unchecked Error:{}",e.getCause());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server Error").build();
		}

	}
}
