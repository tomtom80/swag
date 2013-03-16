package de.klingbeil.swag.core.backend.entity;

public interface EntityCallback<T> {
  /**
   * Returns the entity type class for which the callback should be called.
   * @return the entity type class.
   */
  Class<T> getEntityType();
}