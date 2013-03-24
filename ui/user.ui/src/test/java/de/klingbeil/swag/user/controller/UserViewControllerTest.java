package de.klingbeil.swag.user.controller;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.util.UserUtil;
import de.klingbeil.swag.user.view.UserView;

public class UserViewControllerTest {

	@Mock
	private UserView userView;

	@Mock
	private CreateUserCallback callback;

	@Mock
	private UserService userService;

	private UserViewController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UserViewController();
		controller.view = userView;
		controller.userService = userService;
	}

	@Test
	public void testGetView() throws Exception {
		assertSame(userView, controller.getView());
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
	}

	private CreateUserCallback captureCallbackFromInitViewCall() {
		ArgumentCaptor<CreateUserCallback> captor = ArgumentCaptor
				.forClass(CreateUserCallback.class);
		verify(userView).setCreateUserCallback(captor.capture());
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
