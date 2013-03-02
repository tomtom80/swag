package de.klingbeil.swag.user.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import de.klingbeil.swag.user.backend.entity.EntityListener;

@Entity
@Table(name = "users")
@EntityListeners(EntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Long version;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "creation_time", nullable = false)
	private Date creationTime;
	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return version;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
	}

	public String getEmail() {
		return email;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id=" + id);
		builder.append(" version=" + version);
		builder.append(" firstName=" + firstName);
		builder.append(" lastName=" + lastName);
		builder.append(" eMail=" + email);
		builder.append(" creationTime=" + creationTime);
		builder.append(" modificationTime=" + modificationTime);
		return builder.toString();
	}

}
