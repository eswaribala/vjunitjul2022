package com.virtusa.bankingapp.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class CustomerTest {
	private static Customer customer;
	private Account account;
	
	@BeforeAll
	public static void instantiateCustomer() {
		customer=new Customer();
		
	}
	
	@BeforeEach
	public void setObjectValues() {
		customer.setCustomerId(248658);
		customer.setFirstName("Parameswari");
		customer.setLastName("Bala");
		customer.setDob(LocalDate.of(1970,2,12));
	}
	
	@Test
	@Tag("dev")
	@Order(3)
	@Disabled
	public void customerNotNUll(){
		assertNotNull(customer);
		
	}
	
	
	@RepeatedTest(5)
	@Order(4)
	@DisplayName("Parmeterized Constructor Test")
	public void parmeterizedConstructorTest() {
	
		Customer customer1=new Customer(1,
				"Parameswari","Bala",LocalDate.of(2000+new Random().nextInt(20),
						1+new Random().nextInt(10), 1+new Random().nextInt(25)),true);
	    Customer	customer2=new Customer(1,
				"Parameswari","Bala",LocalDate.of(2000+new Random().nextInt(20),
						1+new Random().nextInt(10), 1+new Random().nextInt(25)),true);
	   assertEquals(customer1, customer2);
	   
		
	}
	
	@ParameterizedTest
	@Order(2)
    @ValueSource(ints = {428578,3597349,3570837,10})
	public void customerIdShouldbeGreaterThanZero(long customerId) {
		assertTrue(customerId>customer.getCustomerId());
		
	}
	
	
	@ParameterizedTest
    @CsvFileSource(resources = "customer.csv", numLinesToSkip = 1)
	@Tag("dev")
	@Order(100)
	public void customerFieldsNotEmptyNotNull(long customerId,
			String dob,String firstName,String lastName, String status) {
		customer.setCustomerId(customerId);
		customer.setDob(LocalDate.parse(dob));
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setStatus(Boolean.parseBoolean(status));
		assertTrue(customer.getDob().isBefore(LocalDate.now().minusMonths(6)));
    	
    }
	
	
	@ParameterizedTest
    //@MethodSource("getCustomers")
	@MethodSource() //method name and source name are same
	@Tag("dev")
	public void customerFieldsNotEmptyNotNullUsingMethodSource(Customer customerObj) {
		customer.setCustomerId(customerObj.getCustomerId());
		customer.setDob(customerObj.getDob());
		customer.setFirstName(customer.getFirstName());
		customer.setLastName(customer.getLastName());
		customer.setStatus(customer.isStatus());
		assertTrue(customer.getDob().isBefore(LocalDate.now().minusMonths(6)));
    	
    }
	
	 @ParameterizedTest
	 @EnumSource(value = AccountType.class, names = { "Savings", "Current" })
	    void testWithEnumSourceInclude(AccountType accountType) {
	        assertTrue(EnumSet.of(AccountType.Savings,AccountType.Current).contains(accountType));
	    }
	 
	 
	@Nested
	@DisplayName("CSV Test from Inner Class")
	class CustomerInnerTest{
		
		@ParameterizedTest
		@Order(1)
		@Tag("dev")
	    @ValueSource(strings = {"1990-12-02","2022-12-02","2004-11-15",
	    		"2019-04-05"})
		public void dobShouldBeBeforeCurrentDate(String dob) {
			
			assertTrue(LocalDate.parse(dob).isBefore(LocalDate.now()));
		}
	}
	
	
	//exception
	
	@Test
	public void nullExceptionTest() {
		
		Throwable exception=assertThrows(NullPointerException.class,()->account.getAccountNo());
		assertEquals(exception.getMessage(), null);
	}
	
	
	@Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("customers",
            () -> assertEquals("Parameswari", customer.getFirstName()),
            () -> assertEquals("Bala", customer.getLastName())
        );
    }

	@Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
            () -> {
                String firstName = customer.getFirstName();
                assertNotNull(firstName);

                // Executed only if the previous assertion is valid.
                assertAll("First Name",
                    () -> assertTrue(firstName.startsWith("P")),
                    () -> assertTrue(firstName.endsWith("i"))
                );
            },
            () -> {
                // Grouped assertion, so processed independently
                // of results of first name assertions.
                String lastName = customer.getLastName();
                assertNotNull(lastName);

                // Executed only if the previous assertion is valid.
                assertAll("LastName",
                    () -> assertTrue(lastName.startsWith("B")),
                    () -> assertTrue(lastName.endsWith("a"))
                );
            }
        );
    }

	 @Test
	    void timeoutExceeded() {
	        // The following assertion fails with an error message similar to:
	        // execution exceeded timeout of 10 ms by 91 ms
		 //will be executed in the same thread as that  of the calling code.
	        assertTimeout(ofMillis(10), () -> {
	            // Simulate task that takes more than 10 ms.
	            Thread.sleep(100);
	        });
	    }
	 
	 @Test
	    void timeoutExceededPreemptively() {
	        // The following assertion fails with an error message similar to:
	    
		 /*
		  * will be executed in a different thread than  that of the calling code.
		  */
	        assertTimeoutPreemptively(ofMillis(10), () -> {
	            // Simulate task that takes more than 10 ms.
	            Thread.sleep(100);
	        });
	    }
	 
	
	private static Stream<Customer> customerFieldsNotEmptyNotNullUsingMethodSource(){
	List<Customer> customers=Arrays.asList(
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
		return customers.stream();
	}
	
	
	
}
