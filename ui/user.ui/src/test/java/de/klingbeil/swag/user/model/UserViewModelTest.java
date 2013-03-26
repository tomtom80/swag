package de.klingbeil.swag.user.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.klingbeil.swag.user.util.UserUtil;

public class UserViewModelTest {

	private static final String EMAIL = "hans@wurst.de";
	private static final String LASTNAME = "Wurst";
	private static final String FIRSTNAME = "Hans";
	private static final Long ID = Long.valueOf(34);
	private UserViewModel model;

	@Before
	public void setUp() {
		model = new UserViewModel();
	}

	@Test
	public void testId() {
		model.setId(ID);

		assertEquals(ID, model.getId());
	}

	@Test
	public void testFirstName() {
		model.setFirstName(FIRSTNAME);

		assertEquals(FIRSTNAME, model.getFirstName());
	}

	@Test
	public void testLastName() {
		model.setLastName(LASTNAME);

		assertEquals(LASTNAME, model.getLastName());
	}

	@Test
	public void testEmail() {
		model.setEmail(EMAIL);

		assertEquals(EMAIL, model.getEmail());
	}

	@Test
	public void testToUser() {
		model.setFirstName(FIRSTNAME);
		model.setLastName(LASTNAME);
		model.setEmail(EMAIL);

		UserUtil.assertUser(model, model.toUser());
	}
}
