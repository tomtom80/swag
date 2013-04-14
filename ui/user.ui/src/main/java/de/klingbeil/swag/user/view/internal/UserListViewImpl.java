package de.klingbeil.swag.user.view.internal;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.UserListView;

@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserListViewImpl implements UserListView {

	private static final String BEAN_ID_PROPERTY = "id";

	private Layout component;
	private BeanContainer<String, UserViewModel> viewModelContainer;
	private Runnable createUserButtonCallback;

	public UserListViewImpl() {
		initComponet();
	}

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public void setViewModel(List<UserViewModel> model) {
		viewModelContainer.addAll(model);
	}

	@Override
	public void setCreateUserButtonCallback(Runnable callback) {
		this.createUserButtonCallback = callback;
	}

	private void initComponet() {
		component = createMainComponent();
		addUserTableComponent();
		addCreateNewUserButton();
	}

	private void addCreateNewUserButton() {
		Button button = new Button("Create");
		button.addClickListener(createCreateUserButtonClickListener());
		component.addComponent(button);
	}

	private ClickListener createCreateUserButtonClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				createUserButtonCallback.run();
			}
		};
	}

	private void addUserTableComponent() {
		viewModelContainer = new BeanContainer<String, UserViewModel>(
				UserViewModel.class);
		viewModelContainer.setBeanIdProperty(BEAN_ID_PROPERTY);
		Table userTable = new Table("User List", viewModelContainer);
		component.addComponent(userTable);
	}

	private VerticalLayout createMainComponent() {
		return new VerticalLayout();
	}

}
