package de.klingbeil.swag.user.view;

import javax.annotation.PostConstruct;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.user.controller.CreateUserCallback;
import de.klingbeil.swag.user.model.UserViewModel;

@org.springframework.stereotype.Component
public class CreateUserViewImpl implements CreateUserView {

	private Layout component;

	private TextField firstNameTextField;
	private TextField lastNameTextField;
	private TextField emailTextField;

	private CreateUserCallback callback;

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public void setSubmitButtonCallback(CreateUserCallback callback) {
		this.callback = callback;
	}

	@PostConstruct
	private Layout initComponet() {
		component = createMainComponent();
		addFirstNameComponent();
		addLastNameComponent();
		addEmailComponent();
		addSubmitComponent();
		return component;
	}

	private void addSubmitComponent() {
		Button submitButton = createButton("Submit");
		submitButton.addClickListener(createButtonClickListener());
		component.addComponent(submitButton);
	}

	private void addEmailComponent() {
		Label emailLabel = createLabel("Email");
		emailTextField = createTextField();
		component.addComponent(emailLabel);
		component.addComponent(emailTextField);
	}

	private void addLastNameComponent() {
		Label lastNameLabel = createLabel("Last Name");
		lastNameTextField = createTextField();
		component.addComponent(lastNameLabel);
		component.addComponent(lastNameTextField);
	}

	private void addFirstNameComponent() {
		Label firstNameLabel = createLabel("First Name");
		firstNameTextField = createTextField();
		component.addComponent(firstNameLabel);
		component.addComponent(firstNameTextField);
	}

	private ClickListener createButtonClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				callback.createUser(toUserViewModel());
			}

		};
	}

	private UserViewModel toUserViewModel() {
		UserViewModel result = new UserViewModel();
		result.setFirstName(firstNameTextField.getValue());
		result.setLastName(lastNameTextField.getValue());
		result.setEmail(emailTextField.getValue());
		return result;
	}

	private Button createButton(String caption) {
		return new Button(caption);
	}

	private VerticalLayout createMainComponent() {
		return new VerticalLayout();
	}

	private TextField createTextField() {
		return new TextField();
	}

	private Label createLabel(String caption) {
		Label label = new Label();
		label.setCaption(caption);
		return label;
	}

}
