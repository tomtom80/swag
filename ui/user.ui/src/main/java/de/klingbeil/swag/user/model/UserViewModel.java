package de.klingbeil.swag.user.model;

import de.klingbeil.swag.user.backend.model.User;

public class UserViewModel {

	private String firstName;
	private String lastName;
	private String email;
	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User toUser() {
		User result = new User();
		result.setFirstName(getFirstName());
		result.setLastName(getLastName());
		result.setEmail(getEmail());
		return result;
	}

}
