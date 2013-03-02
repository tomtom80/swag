package de.klingbeil.swag.user.backend.callback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.klingbeil.swag.user.backend.model.User;

public class UserModificationTimePreUpdateCallbackTest {

	private UserModificationTimePreUpdateCallback callback;

	@Before
	public void setUp() {
		callback = new UserModificationTimePreUpdateCallback();
	}

	@Test
	public void testGetEntityType() {
		assertEquals(User.class, callback.getEntityType());
	}

	@Test
	public void testPreUpdate() throws Exception {
		User user = new User();

		callback.preUpdate(user);

		assertNotNull(user.getModificationTime());
	}

}
