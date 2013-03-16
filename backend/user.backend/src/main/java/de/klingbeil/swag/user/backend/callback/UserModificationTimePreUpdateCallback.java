package de.klingbeil.swag.user.backend.callback;

import java.util.Date;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.backend.entity.PreUpdateCallback;
import de.klingbeil.swag.user.backend.model.User;

@Component
public class UserModificationTimePreUpdateCallback
		implements
			PreUpdateCallback<User> {

	@Override
	public Class<User> getEntityType() {
		return User.class;
	}

	@Override
	public void preUpdate(User user) {
		user.setModificationTime(new Date());
	}

}
