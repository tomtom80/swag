package de.klingbeil.swag.user.backend.callback;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import de.klingbeil.swag.user.backend.model.User;

public class UserCreationTimePrePersistCallbackTest {

	private UserCreationTimePrePersistCallback callback;

	@Before
	public void setUp() {
		callback = new UserCreationTimePrePersistCallback();
	}

	@Test
	public void testGetEntity() {
		assertSame(User.class, callback.getEntityType());
	}

	@Test
	public void testPrePersist() {
		User user = new User();

		callback.prePersist(user);

		assertNotNull(user.getCreationTime());
		assertNotNull(user.getModificationTime());
	}
}
