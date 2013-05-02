package de.klingbeil.swag.user.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.util.UserUtil;

public class UserListViewModelTest {

	private static final String EMAIL = "hans@wurst.de";
	private static final String LASTNAME = "Wurst";
	private static final String FIRSTNAME = "Hans";
	private static final String USERNAME = "hansW";
	private static final String PASSWORD = "secret";
	private static final Long ID = Long.valueOf(324);

	@Test
	public void testFromUserList() {
		User user = new User();
		user.setId(ID);
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setEmail(EMAIL);

		List<UserViewModel> userViewModels = UserListViewModel.from(Arrays
				.asList(user));

		assertEquals(1, userViewModels.size());
		UserUtil.assertUser(user, userViewModels.get(0));
	}
}
