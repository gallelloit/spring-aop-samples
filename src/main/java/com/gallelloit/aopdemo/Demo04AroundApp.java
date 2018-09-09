package com.gallelloit.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gallelloit.aopdemo.config.DemoConfig;
import com.gallelloit.aopdemo.service.TrafficFortuneService;

/**
 * 
 * The Around method is triggered as `getFortune(boolean)` from `TrafficFortuneService.java` is called.
 * `@Around("execution(* com.gallelloit.aopdemo.service.*.getFortune(..))")`
 * 
 * This method:
 * 
 * - uses a JoinPoint argument to display target method name and arguments.
 * - uses the result argument to display information about the returned object.
 * - accepts a boolean argument. In case of true, it simulates an exception. The purposed sample shows the different
 * possible cases that can take place
 *  - Normal execution without exception.
 *  - Execution with a thrown exception. Since this type of aspect is the only that can handle the thrown exception,
 *  two different strategies are shown in the code (possible among many others)
 *   - Option 1: Handle the result and go on with no exception. (Main App will never notice)
 *   	```
 *   	result = "Major accident! But no worries, helicopter is on the way!!";`
 *   	```
 *   - Option 2: Just rethrow the exception
 *   	```
 *   	throw e;
 *   	```
 * @author pgallello
 *
 */
public class Demo04AroundApp {

	private static Logger myLogger = Logger.getLogger(Demo04AroundApp.class.getName());
	
	public static void main(String[] args) {
		
		// Read spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		myLogger.info("Main program: AroundHandleExceptionDemoApp");
		
		myLogger.info("Calling getFortune");
		
		// True to simulate normal execution with no exceptions
		// False to simulate exception throwing
		boolean tripWire = false;
		
		String data = theFortuneService.getFortune(tripWire);
		
		myLogger.info("My fortune is: " + data);
		
		myLogger.info("Finished");
				
		// Close Spring context
		context.close();

	}

}
