package de.klingbeil.swag.user.backend.service.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.config.context.ApplicationContextUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextUser.class})
@Transactional
public class UserServiceIntegrationTest {

	@Resource
	private UserService userService;
	@PersistenceContext
	private EntityManager entityManager;
	private String email;
	private String firstName;
	private String lastName;
	private User user;

	@Before
	public void setUp() {
		email = "foo@bar.com";
		firstName = "Fred";
		lastName = "Feuerstein";
		user = createValidUser(email, firstName, lastName);
	}

	@Test
	public void testCreate() {

		userService.create(user);

		assertEquals(email, user.getEmail());
		assertNotNull(user.getCreationTime().getTime());
	}

	@Test
	public void testUpdate() throws Exception {
		String newFirstName = "Wilma";
		User createdUser = userService.create(user);
		createdUser.setFirstName(newFirstName);

		userService.update(createdUser);
		entityManager.flush();

		User updatedUser = userService.findById(createdUser.getId());
		assertNotNull(updatedUser.getModificationTime());
		assertTrue(
				"Modification date must be after creation date",
				updatedUser.getModificationTime().compareTo(
						updatedUser.getCreationTime()) > 0);
		assertEquals(newFirstName, updatedUser.getFirstName());
	}

	@Test
	public void testFindByEmail() throws Exception {
		userService.create(user);

		List<User> foundUsers = userService.findByEmail(email);

		assertEquals(1, foundUsers.size());
		User foundUser = foundUsers.get(0);
		assertEquals(user.getEmail(), foundUser.getEmail());
		assertEquals(lastName, foundUser.getLastName());
		assertEquals(firstName, foundUser.getFirstName());
	}

	private static User createValidUser(String email, String firstName,
			String lastName) {
		User result = new User();
		result.setEmail(email);
		result.setFirstName(firstName);
		result.setLastName(lastName);
		return result;
	}
}
