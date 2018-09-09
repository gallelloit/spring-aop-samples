package com.gallelloit.aopdemo.dao;

import org.springframework.stereotype.Component;

/**
 * 
 * Simulates a simple DAO with two methods for AOP testing purposes.
 * 
 * @author pgallello
 *
 */

@Component
public class MembershipDAO {
	
	public void addAccount () {
		
		System.out.println("Class " + getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
		
	}

	public void goToSleep () {
		
		System.out.println("Class " + getClass() + ": DOING MY DB WORK: GOING TO SLEEP");
		
	}
	
}
