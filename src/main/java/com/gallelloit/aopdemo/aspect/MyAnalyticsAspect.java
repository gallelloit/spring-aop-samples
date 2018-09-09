package com.gallelloit.aopdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * Simple aspect simulating logging to some analytics engine. It is set to be executed before the target methods,
 * as specified through @Before annotation
 * 
 * The majority of the work of this project is in `MyLoggingAspect.java`. But two more aspects have been created only
 * to be able to test the @Order feature.
 * 
 * Therefore, the three classes involved in the order test are:
 * 
 * - MyAnalyticsAspect
 * - MyCloudLogAspect
 * - MyLoggingAspect
 * 
 * Each of them has the @Order annotation defining a number. This feature is intended to tell Spring AOP the order in which
 * the aspects have to be executed in the case that the pointcut expressions for them coincide. In other words, the
 * three aspects will have to be executed, and @Order defines which will be in the first and following positions. 
 * 
 * @author pgallello
 *
 */

@Aspect
@Component
@Order(3)
public class MyAnalyticsAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Before("com.gallelloit.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void performAnalyticsAdvice() {
		
		myLogger.info(" ===>>> Performing analytics advice");
		
	}
		
}
