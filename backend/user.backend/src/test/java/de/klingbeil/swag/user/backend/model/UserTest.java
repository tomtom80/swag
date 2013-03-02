package de.klingbeil.swag.user.backend.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	private String firstName;
	private Long id;
	private String lastName;
	private String eMail;
	private Long version;

	@Before
	public void setUp() {
		user = new User();
		id = Long.valueOf(324);
		firstName = "Hans";
		lastName = "Wurst";
		eMail = "tom@klingbeil.de";
	}

	@Test
	public void testId() throws Exception {

		user.setId(id);

		assertEquals(id, user.getId());
	}

	@Test
	public void testVersion() throws Exception {

		user.setVersion(version);

		assertEquals(version, user.getVersion());
	}

	@Test
	public void testFirstName() {

		user.setFirstName(firstName);

		assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testLastName() throws Exception {

		user.setLastName(lastName);

		assertEquals(lastName, user.getLastName());
	}

	@Test
	public void testEmail() throws Exception {

		user.setEmail(eMail);

		assertEquals(eMail, user.getEmail());
	}

	@Test
	public void testCreationTime() {
		Date creationTime = new Date();

		user.setCreationTime(creationTime);

		assertEquals(creationTime, user.getCreationTime());
	}

	@Test
	public void testModificationTime() throws Exception {
		Date modificationTime = new Date();

		user.setModificationTime(modificationTime);

		assertEquals(modificationTime, user.getModificationTime());
	}
}
