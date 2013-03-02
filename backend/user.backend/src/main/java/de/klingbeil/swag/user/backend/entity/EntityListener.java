package de.klingbeil.swag.user.backend.entity;

import java.util.Collection;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.context.ApplicationContext;

import de.klingbeil.swag.user.backend.spring.ApplicationContextProvider;

@SuppressWarnings({"rawtypes", "unchecked"})
public class EntityListener {

	@PrePersist
	public void prePersist(Object entity) {
		Collection<PrePersistCallback> registeredCallbacks = getRegisteredCallbacks(PrePersistCallback.class);
		for (PrePersistCallback callback : registeredCallbacks) {
			if (entityTypeMatches(entity, callback)) {
				callback.prePersist(entity);
			}
		}
	}

	@PreUpdate
	public void preUpdate(Object entity) {
		Collection<PreUpdateCallback> registeredCallbacks = getRegisteredCallbacks(PreUpdateCallback.class);
		for (PreUpdateCallback callback : registeredCallbacks) {
			if (entityTypeMatches(entity, callback)) {
				callback.preUpdate(entity);
			}
		}
	}

	private static boolean entityTypeMatches(Object entity,
			EntityCallback callback) {
		return callback.getEntityType().isAssignableFrom(entity.getClass());
	}

	private static <T> Collection<T> getRegisteredCallbacks(
			Class<T> callbackType) {
		return getApplicationContext().getBeansOfType(callbackType).values();
	}

	private static ApplicationContext getApplicationContext() {
		return ApplicationContextProvider.getApplicationContext();
	}

}
