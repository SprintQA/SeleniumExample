package com.sprintqa.class48;

/**
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author mpmeloche
 * 
 */
class AnnotationFlowTest {

	/**
	 * Does the same thing as @Before
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("executed setUpBeforeClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		System.out.println("executed setUpBeforeEach");
	}

	
	@Test
	@DisplayName(value = "Test One")
	void test() {
		System.out.println("executed Test 1");
	}


	@Test
	@Disabled
	@DisplayName(value = "Test Two")
	void test2() {
		fail("executed Test 2 not implimented");
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDownAfterEach() throws Exception {
		System.out.println("executed tearDownAfterEach");
	}


	/**
	 * Does the same thing as @After
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("executed setUpTearDownAfterClass");
	}
	
	

}
