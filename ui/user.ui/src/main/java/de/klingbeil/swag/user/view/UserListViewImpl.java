package de.klingbeil.swag.user.view;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.user.model.UserViewModel;

public class UserListViewImpl implements UserListView {

	private Layout component;
	private List<UserViewModel> viewModel;

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public void setViewModel(List<UserViewModel> model) {
		this.viewModel = model;
	}

	@PostConstruct
	private Layout initComponet() {
		component = createMainComponent();
		addUserTableComponent();
		return component;
	}

	private void addUserTableComponent() {
		BeanContainer<String, UserViewModel> userContainer = new BeanContainer<String, UserViewModel>(
				UserViewModel.class);
		userContainer.addAll(viewModel);
		Table userTable = new Table("User List", userContainer);
		component.addComponent(userTable);
	}

	private VerticalLayout createMainComponent() {
		return new VerticalLayout();
	}

}
