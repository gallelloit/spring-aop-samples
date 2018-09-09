package com.gallelloit.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.gallelloit.aopdemo.entity.Account;


/**
 * 
 * Simulates a DAO with some methods for AOP testing purposes.
 * 
 * @author pgallello
 *
 */
@Component
public class AccountDAO {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	private String name;
	private String serviceCode;
	
	public void addAccount (Account theAccount, boolean vipFlag) {
		
		myLogger.info("==> Class " + getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
		
	}
	
	public boolean doWork() {
		
		myLogger.info("==> Class " + getClass() + ": DOING MY DB WORK: DOING WORK");
		
		return false;

	}
	
	public List<Account> findAccounts(boolean tripWire) {
		
		// For academic purpose: trying to simulate execption
		if (tripWire) {
			throw new RuntimeException("Some exception!!!");
		}
		
		// Create list
		List<Account> myAccounts = new ArrayList<>();
		
		// Add sample accounts
		myAccounts.add(new Account("Pedro", "Silver"));
		myAccounts.add(new Account("Madhu", "Gold"));
		myAccounts.add(new Account("Luca", "Platinum"));
		
		return myAccounts;
		
	}

	public String getName() {
		
		myLogger.info(getClass() + ": in getName()");
		
		return name;
	}

	public void setName(String name) {

		myLogger.info(getClass() + ": in setName()");
		
		this.name = name;
	}

	public String getServiceCode() {
		
		myLogger.info(getClass() + ": in getServiceCode()");
		
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {

		myLogger.info(getClass() + ": in setServiceCode()");

		this.serviceCode = serviceCode;
	}
	
	
	
}