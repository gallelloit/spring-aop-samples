package com.gallelloit.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gallelloit.aopdemo.config.DemoConfig;
import com.gallelloit.aopdemo.dao.AccountDAO;
import com.gallelloit.aopdemo.entity.Account;

/**
 * 
 * Simple main app to check the execution of several aspects triggered when an exception is thrown in the method findAccounts
 * 
 * 1st. Since it is a DAO method and it is not a setter or a getter, the @Before methods are triggered
 * `@Before("com.gallelloit.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")`
 * 
 * 2nd. The AfterThrowing advice from `MyLogginAspect` is triggered. In this type of advice, the exception cannot be stopped.
 * So a simple message with the exception is displayed 
 * 
 * ```
 *	@AfterThrowing(
 *			pointcut="execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))",
 *			throwing="theExc"
 *			)
 * ```
 * 
 * Additionally, the two methods in `MyLoggingAspect` class use a JoinPoint argument to display target method name and
 * arguments.
 * 
 * @author pgallello
 *
 */
public class Demo03AfterThrowingApp {

	private static Logger myLogger = Logger.getLogger(Demo03AfterThrowingApp.class.getName());

	public static void main(String[] args) {
		
		// Read spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the spring container
		AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = null;
		
		try {

			// Call findAccount
			// Add a flag to simulate exceptions
			boolean tripWire = true;
			
			theAccounts = accountDao.findAccounts(tripWire);
			
		} catch (Exception exc) {
			
			myLogger.info("==> Main program... caught exception: " + exc);
			
		}
		
		// Display the accounts
		myLogger.info("===> Main Program: AfterThrowingDemoApp" );
		myLogger.info("------------");
		myLogger.info("===> " + theAccounts);
		
		// Close Spring context
		context.close();

	}

}
