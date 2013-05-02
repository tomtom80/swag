package de.klingbeil.swag.user.view;

import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.controller.LoginCallback;

public interface LoginView extends View {
	void setLoginButtonCallback(LoginCallback callback);

	void setComponentError(String errorMsg);
}
