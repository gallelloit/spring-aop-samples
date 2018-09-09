package com.gallelloit.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gallelloit.aopdemo.config.DemoConfig;
import com.gallelloit.aopdemo.dao.AccountDAO;
import com.gallelloit.aopdemo.entity.Account;

/**
 * 
 * Simple main app to check the execution of several aspects triggered when findAccounts is called with
 * the argument set to false.
 * 
 * 1st. Since it is a DAO method and it is not a setter or a getter, the @Before methods are triggered
 * `@Before("com.gallelloit.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")`
 * 
 * 2nd. @After method is called
 * `@After("execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))")`
 * 
 * 3rd. @AfterReturning method is called. The result argument is used to display the returning value
 * from the triggered method.
 * ```
 * @AfterReturning(
 *			pointcut="execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))",
 *			returning="result"
 *			)
 * ```
 * 
 * Additionally, the three methods in `MyLoggingAspect` class use a JoinPoint argument to display target method name and
 * arguments.
 * 
 * @author pgallello
 *
 */
public class Demo02AfterAfterReturningApp {

	private static Logger myLogger = Logger.getLogger(Demo02AfterAfterReturningApp.class.getName());

	public static void main(String[] args) {
		
		// Read spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the spring container
		AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
		
		// Call findAccount.
		List<Account> theAccounts = accountDao.findAccounts(false);
		
		// Display the accounts
		myLogger.info("Main Program: AfterReturningDemoApp" );
		myLogger.info("------------");
		myLogger.info(theAccounts.toString());
		myLogger.info("\n");
		
		// Close Spring context
		context.close();

	}

}
