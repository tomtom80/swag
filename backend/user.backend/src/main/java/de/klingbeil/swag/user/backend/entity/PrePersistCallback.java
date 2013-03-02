package de.klingbeil.swag.user.backend.entity;

import org.springframework.stereotype.Component;

/**
 * Beans that implement this interface are automatically called when a entity of
 * the given entity type is persisted by the JPA provider. To provide a
 * implementation, simply implement the interface and mark the bean with a
 * stereotype annotation, e.g. {@link Component}. <br/>
 * <code>T</code> is the entity class.
 */
public interface PrePersistCallback<T> extends EntityCallback<T> {

	/**
	 * Is called by the framework whenever an entity is going to be persisted.
	 * Throw a {@link RuntimeException} to prevent this entity from being
	 * persisted.
	 * 
	 * @param entity
	 *            The entity that is going to be persisted.
	 */
	public void prePersist(T entity);
}
