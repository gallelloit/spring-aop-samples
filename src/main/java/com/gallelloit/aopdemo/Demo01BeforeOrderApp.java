package com.gallelloit.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gallelloit.aopdemo.config.DemoConfig;
import com.gallelloit.aopdemo.dao.AccountDAO;
import com.gallelloit.aopdemo.entity.Account;

/**
 * 
 * Simple main app to check the order of three @Before aspects that are triggered for
 * the method: `accountDao.addAccount(theAccount, true);`
 * 
 * `MyLoggingAspect` uses a JoinPoint argument to display target method name and
 * arguments. 
 * 
 * @author pgallello
 *
 */
public class Demo01BeforeOrderApp {

	public static void main(String[] args) {
		
		// Read Spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the spring container
		AccountDAO accountDao = context.getBean("accountDAO", AccountDAO.class);
		
		Account theAccount = new Account();
		
		theAccount.setName("Pedro");
		theAccount.setLevel("Platinum");
		
		// Call business method
		accountDao.addAccount(theAccount, true);
		
		// Close Spring context
		context.close();

	}

}
