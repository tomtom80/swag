package de.klingbeil.swag.user.view.internal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinMessageSource;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.user.controller.CreateUserCallback;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.CreateUserView;

@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CreateUserViewImpl implements CreateUserView {

	private static final long serialVersionUID = 1L;

	private Layout component;
	private Layout controlsComponent;

	private TextField firstNameTextField;
	private TextField lastNameTextField;
	private TextField emailTextField;

	private CreateUserCallback callback;

	@Resource
	private VaadinMessageSource messages;

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public String getCaption() {
		return messages.getMessage("view.create.user.caption");
	}

	@Override
	public void setSubmitButtonCallback(CreateUserCallback callback) {
		this.callback = callback;
	}

	@PostConstruct
	private void initView() {
		component = createMainComponent();
		addCaptionComponent();
		addFirstNameComponent();
		addLastNameComponent();
		addEmailComponent();
		addControlsComponent();
	}

	private void addCaptionComponent() {
		final Label caption = new Label(getCaption());
		caption.addStyleName("view-caption");
		component.addComponent(caption);
	}

	private void addControlsComponent() {
		controlsComponent = createControlsComponent();
		addSubmitComponent();
		component.addComponent(controlsComponent);
	}

	private HorizontalLayout createControlsComponent() {
		HorizontalLayout result = new HorizontalLayout();
		result.addStyleName("view-controls");
		return result;
	}

	private void addSubmitComponent() {
		Button submitButton = createButton(messages.getMessage(
				"view.create.user.submit"));
		submitButton.addClickListener(createButtonClickListener());
		controlsComponent.addComponent(submitButton);
	}

	private void addEmailComponent() {
		Label emailLabel = createLabel(messages.getMessage(
				"view.create.user.email"));
		emailTextField = createTextField();
		component.addComponent(emailLabel);
		component.addComponent(emailTextField);
	}

	private void addLastNameComponent() {
//		Label lastNameLabel = createLabel(messageSource.getMessage(
//				"view.create.user.lastName", null, Locale.US));
		Label lastNameLabel = createLabel("Last Name");
		lastNameTextField = createTextField();
		component.addComponent(lastNameLabel);
		component.addComponent(lastNameTextField);
	}

	private void addFirstNameComponent() {
//		Label firstNameLabel = createLabel(messageSource.getMessage(
//				"view.create.user.firstName", null, Locale.US));
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
