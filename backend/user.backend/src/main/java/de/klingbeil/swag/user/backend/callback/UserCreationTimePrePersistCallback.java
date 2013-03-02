package de.klingbeil.swag.user.backend.callback;

import java.util.Date;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.user.backend.entity.PrePersistCallback;
import de.klingbeil.swag.user.backend.model.User;

@Component
public class UserCreationTimePrePersistCallback
		implements
			PrePersistCallback<User> {

	@Override
	public Class<User> getEntityType() {
		return User.class;
	}

	@Override
	public void prePersist(User user) {
		Date now = new Date();
		user.setCreationTime(now);
		user.setModificationTime(now);
	}

}
