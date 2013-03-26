package de.klingbeil.swag.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.util.UserUtil;
import de.klingbeil.swag.user.view.CreateUserView;
import de.klingbeil.swag.user.view.UserListView;

public class UserListViewControllerTest {

	@Mock
	private UserListView userListView;

	@Mock
	private CreateUserView createUserView;

	@Mock
	private UserService userService;

	private UserListViewController controller;

	@Mock
	private CreateUserViewController createUserViewController;

	@Mock
	private ViewManager viewManager;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UserListViewController();
		controller.view = userListView;
		controller.userService = userService;
		controller.createUserViewController = createUserViewController;
		controller.viewManager = viewManager;
	}

	@Test
	public void testGetView() {
		User user = createUser();
		when(userService.findAll()).thenReturn(Arrays.asList(user));

		View view = controller.getView();

		List<UserViewModel> capturedUserList = captureUserListWhileUpdating();
		assertSame(userListView, view);
		assertEquals(1, capturedUserList.size());
		UserUtil.assertUser(user, capturedUserList.get(0));

	}

	@Test
	public void testSetCreateUserButtonCallbackOnViewInit() {
		when(createUserViewController.getView()).thenReturn(createUserView);

		controller.initView();

		captureCreateUserButtonCallbackOnInitView().run();
		verify(viewManager).setContentView(createUserView);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<UserViewModel> captureUserListWhileUpdating() {
		ArgumentCaptor<List<UserViewModel>> captor = (ArgumentCaptor) ArgumentCaptor
				.forClass(List.class);
		verify(userListView).setViewModel(captor.capture());
		return captor.getValue();
	}

	private Runnable captureCreateUserButtonCallbackOnInitView() {
		ArgumentCaptor<Runnable> captor = ArgumentCaptor
				.forClass(Runnable.class);
		verify(userListView).setCreateUserButtonCallback(captor.capture());
		Runnable runnable = captor.getValue();
		return runnable;
	}

	private User createUser() {
		User result = new User();
		result.setEmail("tom@mail.com");
		result.setFirstName("tom");
		result.setLastName("taylor");
		return result;
	}
}
