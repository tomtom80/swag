package de.klingbeil.swag.core.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DummyEntity {
	@Id
	@GeneratedValue
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DummyEntity [id=" + id + "]";
	}

}
