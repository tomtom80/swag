package de.klingbeil.swag.user.backend.service.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.repository.UserRepository;

public class UserServiceImplTest {

	private UserServiceImpl service;
	@Mock
	private UserRepository userRepository;
	private Long id;
	private String firstName;
	private String lastName;
	private String eMail;
	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new UserServiceImpl();
		service.userRepository = userRepository;
		id = Long.valueOf(3324);
		firstName = "Blub";
		lastName = "Bla";
		eMail = "tom@tom.de";
		user = createUser(id, firstName, lastName, eMail);
		when(userRepository.findOne(id)).thenReturn(user);
	}

	@Test
	public void testCreate() throws Exception {
		when(userRepository.save(user)).thenReturn(user);

		User createdUser = service.create(user);

		assertUserEquals(user, createdUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWithNull() throws Exception {
		service.create(null);
	}

	@Test
	public void testDelete() throws Exception {

		service.delete(id);

		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).delete(captor.capture());
		User capturedUser = captor.getValue();
		assertUserEquals(user, capturedUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteWithNull() throws Exception {
		service.delete(null);
	}

	@Test
	public void testDeleteWithNotExitingUser() throws Exception {
		Long notExistingId = Long.valueOf(-1);
		doThrow(new IllegalArgumentException()).when(userRepository).findOne(
				notExistingId);

		try {
			service.delete(notExistingId);
		} catch (IllegalArgumentException expected) {
		}
	}

	@Test
	public void testFindById() throws Exception {

		User foundUser = service.findById(id);

		assertUserEquals(user, foundUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByIdWithNull() throws Exception {
		service.findById(null);
	}

	@Test
	public void testFindByIdNotFound() throws Exception {
		Long notExistingId = Long.valueOf(-1);
		doThrow(new IllegalArgumentException()).when(userRepository).findOne(
				notExistingId);

		try {
			service.findById(notExistingId);
		} catch (IllegalArgumentException expected) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));

		List<User> users = service.findAll();

		assertUserEquals(user, users.get(0));
	}

	@Test
	public void testUpdate() throws Exception {
		String updateFirstName = "Helmut";
		String updateLastName = "Kohl";
		String UpdateEmail = "helmut@kanzleramt.de";
		User updateUser = createUser(id, updateFirstName, updateLastName,
				UpdateEmail);
		when(userRepository.findOne(id)).thenReturn(user);
		when(userRepository.save(updateUser)).thenReturn(updateUser);

		User updatedUser = service.update(updateUser);

		assertUserEquals(updatedUser, updatedUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateWithNull() throws Exception {
		service.update(null);
	}

	@Test
	public void testUpdateWithNotExistingUser() throws Exception {
		Long notExistingId = Long.valueOf(-1);
		user.setId(notExistingId);
		doThrow(new IllegalArgumentException()).when(userRepository).findOne(
				notExistingId);
		try {
			service.update(user);
		} catch (IllegalArgumentException expected) {
		}
	}

	@Test
	public void testFindByEmail() throws Exception {
		when(userRepository.findByEmail(eMail)).thenReturn(Arrays.asList(user));

		List<User> foundUsers = service.findByEmail(eMail);

		assertEquals(1, foundUsers.size());
		assertSame(user, foundUsers.get(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindUserByEmailWithNullEmail() throws Exception {
		service.findByEmail(null);
	}

	private void assertUserEquals(User expected, User actual) {
		assertSame(expected.getId(), actual.getId());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getEmail(), actual.getEmail());
	}

	private User createUser(Long id, String firstName, String lastName,
			String eMail) {
		User result = new User();
		result.setId(id);
		result.setFirstName(firstName);
		result.setLastName(lastName);
		result.setEmail(eMail);
		return result;
	}
}
