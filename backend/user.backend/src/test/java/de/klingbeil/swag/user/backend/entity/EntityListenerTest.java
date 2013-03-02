package de.klingbeil.swag.user.backend.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.config.context.ApplicationContextBackend;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextBackend.class})
public class EntityListenerTest {

	private EntityListener listener;
	@Resource
	private TestCallback testCallback;
	@PersistenceContext
	private EntityManager entityManager;

	@Before
	public void setUp() {
		listener = new EntityListener();
		// reset test callback
		testCallback.setCalled(false);
	}

	// (tk) TODO
	@Ignore("Find a solution for entity manager to additionally scan for test entities ")
	@Test
	public void testPrePersistIsCalled() {
		entityManager.persist(new TestEntity());

		assertTrue(TestEntityListener.isPrePersist());
	}

	@Test
	public void testPrePersistCallback() {
		listener.prePersist(new TestEntity());

		assertTrue(testCallback.isCalled());
	}

	@Test
	public void testPrePersistCallbackWithSubEntity() {
		listener.prePersist(new TestEntitySubClass());

		assertTrue(testCallback.isCalled());
	}

	@Test
	public void testPrePersistCallbackWithNotMatchingEntityType() {
		listener.prePersist(new User());

		assertFalse(testCallback.isCalled());
	}

	@Test
	public void testPreUpdateCallback() throws Exception {
		listener.preUpdate(new TestEntity());

		assertTrue(testCallback.isCalled());
	}

	@Test
	public void testPreUpdateCallbackWithNotMatchingEntityType() {
		listener.preUpdate(new User());

		assertFalse(testCallback.isCalled());
	}

	@Test
	public void testPreUpdateWithSubEntity() throws Exception {
		listener.preUpdate(new TestEntitySubClass());

		assertTrue(testCallback.isCalled());
	}

}
