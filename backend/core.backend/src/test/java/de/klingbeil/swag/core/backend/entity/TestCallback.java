package de.klingbeil.swag.core.backend.entity;

import org.springframework.stereotype.Component;

@Component
public class TestCallback
		implements
			PrePersistCallback<TestEntity>,
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
