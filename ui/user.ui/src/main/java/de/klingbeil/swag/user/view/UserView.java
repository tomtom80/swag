package de.klingbeil.swag.user.view;

import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.controller.CreateUserCallback;

public interface UserView extends View {
	void setCreateUserCallback(CreateUserCallback callback);
}