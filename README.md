# spring-samples-annotations
Plain and simple Spring samples using Spring annotations components scan.

# Configuration

* Spring 5.8.0

# Use case

Simple and plain Spring samples repository that uses main java classes to test each case.
It includes basic functionality:

* Creating and retrieving beans
* Bean Lifecycle
* Bean Scope

# Getting started

To get this Maven project working:

* Clone this repo
* Build using Maven
* In your IDE, get to the com.gallelloit.springdemo.main package, and execute each of the proposed Demo Main Apps
* Play around
  
# Proposed Demo Main Apps

## Demo01BasicSpringAnnotationsApp.java:

Simple Spring application that creates a context from a XML file (applicationContext.xml),
which sets the scanning configuration at com.gallelloit.spring package. Spring will search in that package and all subpackages
for classes defined with @Component annotation.

This app gets the bean cricketCoach, which implements the interface Coach. This coach implementation uses internally
the implementation RandomFortuneService of interface FortuneService using @Autowired and @Qualifier annotations
in the fortuneService property.

The values of the plain private properties in CricketCoach implementation are fetch from sports.properties
This configuration is specified also in the applicationContext.xml:

 - First tell Spring a placeholder property: '<context:property-placeholder location="classpath:sport.properties"/>'
 - Then, in the bean itself, i.e., in the class definition, a @Value annotation is used on each property:

 ```
		@Value(value="${foo.email}")
 		private String emailAddress;
 	
 		@Value(value="${foo.team}")
 		private String team;
 ```

This Coach implementation uses internally the implementation RandomFortuneService of interface FortuneService

## Demo02LifeCycleAnnotationsApp.java:
 
Simple Spring application that creates a context from a XML file (applicationContext.xml),
which sets the scanning configuration at com.gallelloit.spring package. Spring will search in that package and all subpackages
for classes defined with @Component annotation.

This app gets the bean trackCoach, which implements the interface Coach. This coach implementation uses internally the implementation RandomFortuneService of interface FortuneService using
@Autowired and @Qualifier annotations in the constructor.

This demo application tests the lifecycle of Spring beans. TrackCoach object defines two methods with the annotations
@Postconstruct and @Predestroy. Some messages are displayed to see when each method is executed.

## Demo03BeanScopeAnnotationsApp.xml

Simple Spring application that creates a context from a XML file (applicationContext.xml),
which sets the scanning configuration at com.gallelloit.spring package. Spring will search in that package
and all subpackages for classes defined with @Component annotation.

It gets the bean footballCoach, which implements the interface Coach
This Coach implementation uses internally the implementation HappyFortuneService of interface FortuneService
as a result of the use of @Component and @Qualifier annotations in the constructor.

The resolved issue of this example is testing the two most used bean scopes from Spring: Singleton and Prototype

In the FootballCoach object, two alternative versions of the bean definition are specified through the
@Scope annotation, which must be specified with the scope strategy. The original proposed solution
has both alternative lines, one of which must be commented.

- On one side, Singleton (`@Scope(value="singleton")`), the default stratey, will make Spring to create and
return one instance of the bean no matter how many times the bean is retrieved. You can see in the example how
the memory location for the two retrieved objects is the same.

- Whereas when Prototype strategy is defined (`@Scope(value="prototype")`), any time a new bean is requested to the container, a different
instance is created and retrieved. This test can be performed commenting the singleton line and uncommenting
the prototype one.
