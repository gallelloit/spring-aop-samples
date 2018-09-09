package com.gallelloit.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gallelloit.aopdemo.entity.Account;

/**
 * 
 * The majority of the work of this project is performed in this aspect. It checks a number of the most important
 * Spring AOP features. Such us:
 * 
 *  - @Before, @After advices
 *  - @Around and exception management
 *  - JoinPoint usage
 *  - @AfterReturning and result usage.
 *  - @AfterThrowing and exception usage
 *  - Different pointcut expressions
 *  - @Order (in coordination with two other aspects)
 * 
 * Javadoc in each method will explain the different features.
 * 
 * @author pgallello
 *
 */

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	/**
	 * 
	 * Displays a debug message, the name of the method and the arguments it is executed with.
	 * 
	 * It will be executed any time the expression defined in the annotation takes place.
	 * Since the AOP annotation used is @Before, it will be executed before the target method.
	 * 
	 * In this case, the pointcut expression is defined in another package, as specified in the
	 * value of the annotation.
	 * 
	 * Other two method of other aspects match the same pointcut expression. The @Order annotation
	 * specified on the heading of the class will decide the order of the execution of the
	 * coincident methods.
	 * 
	 * @param theJoinPoint Information about the target method, i.e., the method that triggers the
	 * execution of the advice method
	 * 
	 */
	@Before("com.gallelloit.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		myLogger.info(" ===>>> Do stuff before adding account");
		
		// Display the method signature
		MethodSignature theMethodSignature = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info(" ====>>> Method signature: " + theMethodSignature);
		
		// Display the method arguments
		Object [] args = theJoinPoint.getArgs();
		
		
		myLogger.info(" ===>>> Method arguments (" + args.length + "):");
		int i = 1;
		for (Object theObject : args) {
			myLogger.info("Argument " + i + ": " + theObject.toString());
			
			if (theObject instanceof Account) {
				Account theAccount = (Account) theObject;
				
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
			
			i++;
			
		}
		
	}
	
	/**
	 * 
	 * Displays a debug message, the name of the method and the arguments it is executed with.
	 * 
	 * It will be executed any time the expression defined in the annotation takes place.
	 * Since the AOP annotation used is @After, it will be executed after the target method.
	 * 
	 * @param theJoinPoint Information about the target method, i.e., the method that triggers the
	 * execution of the advice method
	 * 
	 */
	@After("execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// Print method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info(" =====>>> Executing @After (Finally) on method : " + method);
		
	}

	/**
	 * 
	 * Executed after the method returns a result.
	 * 
	 * As specified in the pointcut parameter of the @AfterReturning annotation, it will be
	 * triggered any time the findAccounts method from AccountDAO is executed. 
	 * 
	 * @param theJoinPoint Information about the target method, i.e., the method that triggers the
	 * execution of the advice method
	 * @param result Object retourned by the target method.
	 */
	@AfterReturning(
			pointcut="execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result"
			)
	public void afterReturning(JoinPoint theJoinPoint, List<Account> result) {
		
		// Print out which method we are advising on
		String method = theJoinPoint.getSignature().toLongString();
		myLogger.info(" =====>>> Executing @AfterReturning on method : " + method);
		
		// Print out the results
		myLogger.info(" =====>>> Result is: " + result);
		
		// Let's post-process data
		// Convert all account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		myLogger.info(" =====>>> After post-processing, result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// Loop the accounts and update names with upper case	
		for (Account tempAccount : result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
		
	}
	
	/**
	 * 
	 * Executed after an exception has been thrown from the target method. With this strategy
	 * (@AfterThrowing), it is not possible to stop the exception propagation. It does has been
	 * already thrown.  
	 * 
	 * As specified in the pointcut parameter of the @AfterThrowing annotation, it will be
	 * triggered anytime the findAccounts method from AccountDAO is executed. 
	 * 
	 * @param theJoinPoint Information about the target method, i.e., the method that triggers the
	 * execution of the advice method
	 * @param theExc Exception thrown by the target method
	 */
	@AfterThrowing(
			pointcut="execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc"
			)
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// Print method we are advising on
		String method = theJoinPoint.getSignature().toLongString();
		myLogger.info(" =====>>> Executing @AfterThrowing on method : " + method);
		
		// Log exception
		myLogger.info(" =====>>> After throwing exception: " + theExc);
		
	}
	
	/**
	 * 
	 * A portion of this method is executed before the target method and the other portion after.
	 * @Around annotation achieves this magic.
	 * 
	 * It display some debug information, including method signature of the target method,
	 * before proceeding to it.
	 * 
	 * `theProceedingJoinPoint.proceed()` launches the target method. After that, it allows to handle
	 * the result, which might be useful in a number of cases, such us appending fix data to the
	 * returned object or changing it in case of exception.
	 * 
	 * At the end of the method, some more debug information is displayed.
	 * 
	 * Finally, it is this method responsibility to return the value to the caller.
	 * 
	 * @param theProceedingJoinPoint Object with the information of the execution of the target method.
	 * In a normal use, it needs to be used to make the aspect to continue with the execution of
	 * the method.
	 * @return The result of the target method. It can be modified
	 * @throws Throwable 
	 */
	@Around("execution(* com.gallelloit.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortuneAdvice(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// Print the method we are advising on
		// Print method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info(" =====>>> Executing @Around on method : " + method);
		
		// Get begin timestamp
		long begin = System.currentTimeMillis();
		
		Object result = null;
		
		// Execute method
		try {
			result = theProceedingJoinPoint.proceed();
			
		} catch (Exception e) {
			
			// Log the exception (custom message)
			myLogger.warning(e.getMessage());
			
			// Option 1: Handle the result. Main App will never now!!
			// result = "Major accident! But no worries, helicopter is on the way!!";
			
			// Option 2: Rethrow the exception
			throw e;
			
		}
		
		// Get end timestamp
		long end = System.currentTimeMillis();
		
		// Compute duration
		long duration = end - begin;
		myLogger.info(" ======>>>Duration: " + duration/1000.0 + " seconds.");
		
		return result;
		
	}
}
