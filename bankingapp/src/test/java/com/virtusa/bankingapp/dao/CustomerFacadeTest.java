package com.virtusa.bankingapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import com.virtusa.bankingapp.models.Customer;
import org.junit.jupiter.api.MethodOrderer.MethodName;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CustomerFacadeTest {
	private CustomerFacade customerFacade;
	
	
	@BeforeEach
	public void instantiateCustomerFacade() {
		customerFacade = new CustomerImpl();
	}
	
	@TestFactory
	//@Order(2)
	Stream<DynamicTest> dynamicTestsForCustomerWorkflows() {
		//initialized the collection
	    List<Customer> inputList = Arrays.asList(
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari1","Bala1",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true),
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari2","Bala2",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true),
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari3","Bala3",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true),
	    		new Customer(new Random().nextInt(10000),
	    				"Parameswari4","Bala4",LocalDate.of(2000+new Random().nextInt(20),
	    						1+new Random().nextInt(10), 
	    						1+new Random().nextInt(25)),true)
	    		);
	        
	    
	    //dynamic test
	    Stream<DynamicTest> saveCustomerStream = inputList.stream()
	      .map(cust -> DynamicTest.dynamicTest(
	        "saveCustomer: " + cust.toString(), 
	          () -> {
	              Customer returned = customerFacade.addCustomer(cust);
	              assertEquals(returned.getCustomerId(), cust.getCustomerId());
	          }
	    ));
	        
	 
	    //dynamic test result    
	    return saveCustomerStream;
	}
	
	@Tag("dev")
	@Execution(ExecutionMode.CONCURRENT)
	@TestFactory
	//@Order(1)
	Iterator<DynamicTest> dynamicTestsWithIterator() {
		customerFacade.addCustomers();
	    return Arrays.asList(
	      DynamicTest.dynamicTest("Get All Customers",
	        () -> assertThat(customerFacade.getAllCustomers(),hasSize(4))),
	      DynamicTest.dynamicTest("Get Customer ById",
	        () -> assertNotNull(customerFacade.getCustomerById(0))))
	        .iterator();
	}

}
