# spring-aop-samples
Simple Spring AOP samples using simulated service and DAO layers.

# Configuration

* Spring 5.8.0
* Spring AOP 5.8.0
* AspectJ 1.8.3

# Use case

Simple Spring AOP samples using simulated service and DAO layers.

The following AOP features can be found in this project.

 * @Before, @After advices
 * @Around and exception management. Target method return value and exception handling
 * JoinPoint usage
 * @AfterReturning and result usage.
 * @AfterThrowing and exception usage
 * Different pointcut expressions
 * @Order (in coordination with two other aspects)

# Getting started

To get this Maven project working:

* Clone this repo
* Build using Maven
* In your IDE, get to the com.gallelloit.springdemo.main package, and execute each of the proposed Demo Main Apps
* Play around
  
# Proposed Demo Main Apps

## Demo01BeforeOrderApp.java:

Simple main app to check the order of three @Before aspects that are triggered for the method: `accountDao.addAccount(theAccount, true);`

`MyLoggingAspect` uses a JoinPoint argument to display target method name and arguments. 

## Demo02AfterAfterReturningApp.java:

Simple main app to check the execution of several aspects triggered when findAccounts is called with
the argument set to false.

* 1st. Since it is a DAO method and it is not a setter or a getter, the @Before methods are triggered
	`@Before("com.gallelloit.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")`

* 2nd. @After method is called
	`@After("execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))")`

* 3rd. @AfterReturning method is called. The result argument is used to display the returning value
from the triggered method.
	```
	@AfterReturning(
	 *			pointcut="execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))",
	 *			returning="result"
	 *			)
	```

Additionally, the three methods in `MyLoggingAspect` class use a JoinPoint argument to display target method name and
arguments.

## Demo03AfterThrowingApp.java

Simple main app to check the execution of several aspects triggered when an exception is thrown in the method findAccounts

* 1st. Since it is a DAO method and it is not a setter or a getter, the @Before methods are triggered
	`@Before("com.gallelloit.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")`

* 2nd. The AfterThrowing advice from `MyLogginAspect` is triggered. In this type of advice, the exception cannot be stopped.
So a simple message with the exception is displayed 

	```
	 *	@AfterThrowing(
	 *			pointcut="execution(* com.gallelloit.aopdemo.dao.AccountDAO.findAccounts(..))",
	 *			throwing="theExc"
	 *			)
	```

Additionally, the two methods in `MyLoggingAspect` class use a JoinPoint argument to display target method name and
arguments.

## Demo04AroundApp.java

The Around method is triggered as `getFortune(boolean)` from `TrafficFortuneService.java` is called.
`@Around("execution(* com.gallelloit.aopdemo.service.*.getFortune(..))")`

This method:

* uses a JoinPoint argument to display target method name and arguments.
* uses the result argument to display information about the returned object.
* accepts a boolean argument. In case of true, it simulates an exception. The purposed sample shows the different
possible cases that can take place
  * Normal execution without exception.
  * Execution with a thrown exception. Since this type of aspect is the only that can handle the thrown exception, two different strategies are shown in the code (possible among many others)
   * Option 1: Handle the result and go on with no exception. (Main App will never notice)
	  	```
	  	result = "Major accident! But no worries, helicopter is on the way!!";`
	  	```
   * Option 2: Just rethrow the exception
	  	```
	  	throw e;
	  	```