package com.gallelloit.aopdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * Simple configuration file with support for Aspects enable and Component Scan.
 * 
 * @author pgallello
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.gallelloit.aopdemo")
public class DemoConfig {

}
