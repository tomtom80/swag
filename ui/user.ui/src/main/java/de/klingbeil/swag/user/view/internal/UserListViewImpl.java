package de.klingbeil.swag.user.view.internal;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinMessageSource;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.UserListView;

@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserListViewImpl implements UserListView {

	private static final long serialVersionUID = 1L;

	private static final String BEAN_ID_PROPERTY = "id";

	private Layout component;
	private Layout controlsComponent;
	private BeanContainer<Long, UserViewModel> viewModelContainer;
	private Runnable createUserButtonCallback;

	@Resource
	private VaadinMessageSource messages;

	@PostConstruct
	public void initView() {
		initComponet();
	}

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public String getCaption() {
		return messages.getMessage("view.user.list.caption");
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
		addCaptionComponent();
		addUserTableComponent();
		addControlsComponent();
	}

	private void addCaptionComponent() {
		final Label caption = new Label(getCaption());
		caption.addStyleName("view-caption");
		component.addComponent(caption);
	}

	private void addControlsComponent() {
		controlsComponent = createControlsComponent();
		addCreateNewUserButton();
		component.addComponent(controlsComponent);
	}

	private HorizontalLayout createControlsComponent() {
		HorizontalLayout result = new HorizontalLayout();
		result.addStyleName("view-controls");
		return result;
	}

	private void addCreateNewUserButton() {
		Button button = new Button(messages.getMessage("view.user.list.create"));
		button.addClickListener(createCreateUserButtonClickListener());
		controlsComponent.addComponent(button);
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
		viewModelContainer = new BeanContainer<Long, UserViewModel>(
				UserViewModel.class);
		viewModelContainer.setBeanIdProperty(BEAN_ID_PROPERTY);
		Table userTable = new Table("", viewModelContainer);
		component.addComponent(userTable);
	}

	private VerticalLayout createMainComponent() {
		return new VerticalLayout();
	}

}
