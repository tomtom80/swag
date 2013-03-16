package de.klingbeil.swag.core.backend.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Entity
@EntityListeners({TestEntityListener.class})
public class TestEntity {
	@Id
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TestEntity [id=" + id + "]";
	}

}
