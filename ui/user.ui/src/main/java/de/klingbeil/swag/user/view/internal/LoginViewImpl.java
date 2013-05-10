package de.klingbeil.swag.user.view.internal;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinMessageSource;

import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.user.controller.LoginCallback;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.LoginView;

@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LoginViewImpl implements LoginView {

	private static final long serialVersionUID = 1L;
	private static final String USERNAME_PROPERTY_ID = "username";
	private static final String PASSWORT_PROPERTY_ID = "password";

	private Panel mainContainer;
	private Layout mainLayout;
	private FormLayout formLayout;
	private Layout controlsLayout;
	private FieldGroup formGroup;
	private LoginCallback callback;
	private UserViewModel model;

	@Resource
	VaadinMessageSource messages;

	@PostConstruct
	public void initView() {
		initModel();
		initComponet();
	}

	@Override
	public Component getComponent() {
		return mainContainer;
	}

	@Override
	public String getCaption() {
		return messages.getMessage("view.login.caption");
	}

	@Override
	public void setLoginButtonCallback(LoginCallback callback) {
		this.callback = callback;
	}

	@Override
	public void setComponentError(String errorMsg) {
		Notification.show(messages.getMessage("view.login.errormsg.caption"),
				errorMsg, Type.ERROR_MESSAGE);
	}

	private void initModel() {
		model = new UserViewModel();
		model.setUsername("");
		model.setPassword("");
	}

	private Layout initComponet() {
		mainContainer = new Panel();
		mainLayout = new VerticalLayout();
		mainContainer.setContent(mainLayout);
		addCaptionComponent();
		addUserPasswordForm();
		addControlsComponent();
		return mainLayout;
	}

	private void addCaptionComponent() {
		final Label caption = new Label(getCaption());
		caption.addStyleName("view-caption");
		mainLayout.addComponent(caption);
	}

	private void addUserPasswordForm() {
		formGroup = new FieldGroup();
		formLayout = new FormLayout();
		unbindAll();
		BeanItem<UserViewModel> userItem = new BeanItem<UserViewModel>(model);
		formGroup.setFieldFactory(new DefaultFieldGroupFieldFactory());
		formGroup.setItemDataSource(userItem);
		addUsernameComponent();
		addPasswordComponent();
		mainLayout.addComponent(formLayout);
	}

	private void unbindAll() {
		final Collection<Field<?>> fields = new HashSet<Field<?>>(
				formGroup.getFields());
		for (Field<?> field : fields) {
			formGroup.unbind(field);
		}
	}

	private void addUsernameComponent() {
		final TextField usernameTextField = formGroup.buildAndBind(
				messages.getMessage("view.login.username"),
				USERNAME_PROPERTY_ID, TextField.class);

		usernameTextField.setRequired(true);
		formLayout.addComponent(usernameTextField);
	}

	private void addPasswordComponent() {
		final PasswordField passwordField = formGroup.buildAndBind(
				messages.getMessage("view.login.password"),
				PASSWORT_PROPERTY_ID, PasswordField.class);
		passwordField.setRequired(true);
		formLayout.addComponent(passwordField);
	}

	private void addControlsComponent() {
		controlsLayout = createControlsComponent();
		addSubmitComponent();
		mainLayout.addComponent(controlsLayout);
	}

	private HorizontalLayout createControlsComponent() {
		HorizontalLayout result = new HorizontalLayout();
		result.addStyleName("view-controls");
		return result;
	}

	private void addSubmitComponent() {
		Button submitButton = createButton(messages
				.getMessage("view.login.login"));
		submitButton.addClickListener(createButtonClickListener());
		controlsLayout.addComponent(submitButton);
	}

	private Button createButton(String caption) {
		return new Button(caption);
	}

	private ClickListener createButtonClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					formGroup.commit();
					callback.login(model);
				} catch (CommitException e) {
					setComponentError(messages
							.getMessage("view.login.empty.error"));
				}
			}

		};
	}
}
