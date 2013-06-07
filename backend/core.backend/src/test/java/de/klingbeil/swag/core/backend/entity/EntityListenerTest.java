package de.klingbeil.swag.core.backend.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.klingbeil.swag.testhelper.backend.entity.TestEntity;
import de.klingbeil.swag.testhelper.backend.entity.TestEntityListener;
import de.klingbeil.swag.testhelper.backend.entity.TestEntitySubClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"classpath*:META-INF/spring/applicationContext-persistence-test.xml",
		"classpath*:META-INF/spring/applicationContext-core-backend-test.xml"})
@Transactional
public class EntityListenerTest {

	@Component
	public static class TestCallback implements PrePersistCallback<TestEntity>,
			PreUpdateCallback<TestEntity> {
		private boolean isCalled;

		@Override
		public Class<TestEntity> getEntityType() {
			return TestEntity.class;
		}

		@Override
		public void prePersist(TestEntity entity) {
			isCalled = true;
		}

		@Override
		public void preUpdate(TestEntity entity) {
			isCalled = true;
		}

		public boolean isCalled() {
			return isCalled;
		}

		public void setCalled(boolean isCalled) {
			this.isCalled = isCalled;
		}

	}

	private static final String ENTITY_ID = "132";

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

	@Test
	public void testPrePersistIsCalled() {
		entityManager.persist(createTestEntity());

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
		listener.prePersist(new Object());

		assertFalse(testCallback.isCalled());
	}

	@Test
	public void testPreUpdateCallback() throws Exception {
		listener.preUpdate(createTestEntity());

		assertTrue(testCallback.isCalled());
	}

	@Test
	public void testPreUpdateCallbackWithNotMatchingEntityType() {
		listener.preUpdate(new Object());

		assertFalse(testCallback.isCalled());
	}

	@Test
	public void testPreUpdateWithSubEntity() throws Exception {
		listener.preUpdate(new TestEntitySubClass());

		assertTrue(testCallback.isCalled());
	}

	private static TestEntity createTestEntity() {
		TestEntity entity = new TestEntity();
		entity.setId(ENTITY_ID);
		return entity;
	}

}
