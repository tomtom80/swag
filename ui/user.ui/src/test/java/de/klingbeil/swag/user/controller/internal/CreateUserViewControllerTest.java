package de.klingbeil.swag.user.controller.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.controller.CreateUserCallback;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.util.UserUtil;
import de.klingbeil.swag.user.view.CreateUserView;

public class CreateUserViewControllerTest {

	@Mock
	private CreateUserView createUserView;

	@Mock
	private CreateUserCallback callback;

	@Mock
	private UserService userService;

	@Mock
	private ViewManager viewManager;

	@Mock
	private UriFragmentNavigator navigator;

	@Mock
	private Page page;

	private CreateUserViewController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new CreateUserViewController();
		controller.view = createUserView;
		controller.userService = userService;
		controller.viewManager = viewManager;
		controller.navigator = navigator;
	}

	@Test
	public void testGetView() throws Exception {
		assertSame(createUserView, controller.getView());
	}

	@Test
	public void testSetCreateUserCallbackOnInit() {
		UserViewModel user = createUserViewModel();
		controller.initView();
		CreateUserCallback createUserCallback = captureCallbackFromInitViewCall();

		createUserCallback.createUser(user);

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userService).create(userCaptor.capture());
		UserUtil.assertUser(user, userCaptor.getValue());
		verify(navigator).navigateTo(UserListViewController.URI_FRAGMENT);
	}

	@Test
	public void testUriFragmentChanged() {
		UriFragmentChangedEvent event = new UriFragmentChangedEvent(page,
				CreateUserViewController.URI_FRAGMENT);

		controller.uriFragmentChanged(event);

		verify(viewManager).setContentView(createUserView);
	}

	@Test
	public void testUriFragmentChangedForOtherFragment() {
		UriFragmentChangedEvent event = new UriFragmentChangedEvent(page,
				"otherFragment");

		controller.uriFragmentChanged(event);

		verify(viewManager, never()).setContentView(createUserView);
	}

	private CreateUserCallback captureCallbackFromInitViewCall() {
		ArgumentCaptor<CreateUserCallback> captor = ArgumentCaptor
				.forClass(CreateUserCallback.class);
		verify(createUserView).setSubmitButtonCallback(captor.capture());
		CreateUserCallback capturedCallback = captor.getValue();
		return capturedCallback;
	}

	private UserViewModel createUserViewModel() {
		UserViewModel result = new UserViewModel();
		result.setEmail("Doc@mail.com");
		result.setFirstName("Doc");
		result.setLastName("Hollyday");
		return result;
	}
}
