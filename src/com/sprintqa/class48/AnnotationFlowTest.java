package com.sprintqa.class48;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 Annotation Example This examples shows the order in which the
 * annotations are executed in JUnit.
 * 
 * @author mpmeloche
 */
class AnnotationFlowTest {

	/**
	 * @BeforeAll is used to signal that the annotated method should be executed
	 *            before all tests in the current test class. In contrast
	 *            to @BeforeEach methods, @BeforeAll methods are only executed once
	 *            for a given test class.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("executed setUpBeforeClass");
	}

	/**
	 * @BeforeEach is used to signal that the annotated method should be executed
	 *             before each @Test method in the current test class.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		System.out.println("executed setUpBeforeEach");
	}

	/**
	 * @Test is used to signal that the annotated method is a test method.
	 * 
	 * @Test methods must not be private or static and must not return a value.
	 * 
	 * @Test methods may optionally declare parameters to be resolved by
	 *       ParameterResolvers.
	 * 
	 * @Test may also be used as a meta-annotation in order to create a custom
	 *       composed annotation that inherits the semantics of @Test.
	 * 
	 * @DisplayName is used to declare a custom display name for the annotated test
	 *              class or test method. Display names are typically used for test
	 *              reporting in IDEs and build tools and may contain spaces,
	 *              special characters, and even emoji.
	 */
	@Test
	@DisplayName(value = "Test One")
	void test() {
		System.out.println("executed Test 1");
	}

	/**
	 * @Disabled is used to signal that the annotated test class or test method is
	 *           currently disabled and should not be executed. When applied at the
	 *           class level, all test methods within that class are automatically
	 *           disabled as well.
	 */
	@Test
	@Disabled
	@DisplayName(value = "Test Two")
	void test2() {
		fail("executed Test 2 not implimented");
	}

	/**
	 * @AfterEach is used to signal that the annotated method should be executed
	 *            after each @Test method in the current test class.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDownAfterEach() throws Exception {
		System.out.println("executed tearDownAfterEach");
	}

	/**
	 * @AfterAll is used to signal that the annotated method should be executed
	 *           after all tests in the current test class. In contrast
	 *           to @AfterEach methods, @AfterAll methods are only executed once for
	 *           a given test class.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("executed setUpTearDownAfterClass");
	}

}
