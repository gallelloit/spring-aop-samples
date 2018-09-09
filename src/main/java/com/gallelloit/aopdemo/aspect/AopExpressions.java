package com.gallelloit.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 
 * Pointcut Expressions can be defined on the header of each method. But
 * a repository of them can be included an organized in classes with the
 * only purpose of having them in a known and localized point.
 * 
 * When an aspect needs to use an expression defined in other class, it will
 * has to use the qualified name of the method over which the expression is
 * defined.
 * 
 * @author pgallello
 *
 */
@Aspect
public class AopExpressions {

	@Pointcut("execution(* com.gallelloit.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {};

	@Pointcut("execution(* com.gallelloit.aopdemo.dao.*.get*(..))")
	public void getter() {};
	
	@Pointcut("execution(* com.gallelloit.aopdemo.dao.*.set*(..))")
	public void setter() {};
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {};
	
}