package de.klingbeil.swag.user.view;

import java.util.List;

import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.model.UserViewModel;

public interface UserListView extends View {
	void setViewModel(List<UserViewModel> model);
}
