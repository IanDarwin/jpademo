package test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import domain.Address;

public class ValidationTest {

	Validator validator;
	
	@Before
	public void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testValid() {
		// Address must have address and city as a minimum
		final Address address = new Address();
		address.setStreetAddress("123 Yonge St");
		address.setCity("Hogtown");
		final Set<ConstraintViolation<Address>> valid = validator.validate(address);
		for (ConstraintViolation<?> err : valid) {
			System.out.println(err);
		}
		assertTrue(valid.size() == 0);
	}

	@Test
	public void testNotValid() {
		// Address must have address and city as a minimum, so
		// "new Address()" is by definition INVALID out of the box
		final Set<ConstraintViolation<Address>> valid = validator.validate(new Address());
		for (ConstraintViolation<?> err : valid) {
			System.out.println(err);
		}
		assertTrue(valid.size() > 0);
	}

}
