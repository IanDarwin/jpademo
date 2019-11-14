package test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Address;

/**
 * Show how to test the javax.validation constraints on a POJO.
 * See https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/
 * (doc is for Hibernate impl but should work with any impl of javax.validation API).
 * @author Ian Darwin
 */
public class AddressValidationTest {

	static ValidatorFactory factory;
	protected Validator validator;
	
	@BeforeClass
	public static void setupFactory() {
		factory = Validation.buildDefaultValidatorFactory();
	}
	@AfterClass
	public static void closeFactory() {
		// factory.close();
	}
	
	@Before
	public void setUp() throws Exception {
		validator = factory.getValidator();
	}
	
	@Test
	public void testValid() {
		// Address must have address and city as a minimum;
		// make test subject VALID by providing values.
		final Address address = new Address();
		address.setStreetAddress("123 Yonge St");
		address.setCity("Hogtown");
		final Set<ConstraintViolation<Address>> valid = 
				validator.validate(address);
		for (ConstraintViolation<?> err : valid) {
			System.out.println(err);
		}
		assertTrue(valid.size() == 0);
	}

	@Test
	public void testNotValid() {
		// Address must have address and city as a minimum, so
		// "new Address()" is by definition INVALID out of the box
		final Set<ConstraintViolation<Address>> valid = 
				validator.validate(new Address());
		for (ConstraintViolation<?> err : valid) {
			System.out.println(err);
		}
		assertTrue(valid.size() > 0);
	}

}
