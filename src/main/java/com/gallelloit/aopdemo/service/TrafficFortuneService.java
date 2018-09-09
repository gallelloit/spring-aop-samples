package com.gallelloit.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 * 
 * Simple class that defines a service with two methods
 * 
 * @author pgallello
 *
 */
@Component
public class TrafficFortuneService {

	/**
	 * Simulates an execution with a delay.
	 * 
	 * @return String with a supposed traffic situation.
	 */
	public String getFortune() {
		
		try {
			TimeUnit.SECONDS.sleep(2);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Return a fortune
		return "Expect heavy traffic this morning";
		
	}
	
	/**
	 * 
	 * Accepts a boolean to simulate an exception in case of true.
	 * In other case just calls the `getFortune()` method.
	 * 	
	 * @param tripWire
	 * @return The result of `getFortune()`
	 */
	public String getFortune(boolean tripWire) {
		
		if (tripWire) {
			
			throw new RuntimeException("Major accident! Highway is Closed");
			
		}
		
		return getFortune();
		
	}

}
