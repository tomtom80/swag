package de.klingbeil.swag.user.util;

import static org.junit.Assert.assertEquals;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.model.UserViewModel;

public class UserUtil {

	public static void assertUser(UserViewModel expected, User actual) {
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getEmail(), actual.getEmail());
	}

	public static void assertUser(User expected, UserViewModel actual) {
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getEmail(), actual.getEmail());
	}
}
