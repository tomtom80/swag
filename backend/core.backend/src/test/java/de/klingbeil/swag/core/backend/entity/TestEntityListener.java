package de.klingbeil.swag.core.backend.entity;

import javax.persistence.PrePersist;

public class TestEntityListener {

	private static boolean prePersist;

	@PrePersist
	public void prePersist(Object entity) {
		prePersist = true;
	}

	public static boolean isPrePersist() {
		return prePersist;
	}

}