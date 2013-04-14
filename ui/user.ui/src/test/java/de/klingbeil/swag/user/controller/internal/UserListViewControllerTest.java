package de.klingbeil.swag.user.controller.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.util.UserUtil;
import de.klingbeil.swag.user.view.UserListView;

public class UserListViewControllerTest {

	@Mock
	private UserListView userListView;

	@Mock
	private UserService userService;

	@Mock
	private UriFragmentNavigator navigator;

	@Mock
	private Page page;

	private UserListViewController controller;

	@Mock
	private ViewManager viewManager;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UserListViewController();
		controller.userService = userService;
		controller.viewManager = viewManager;
		controller.navigator = navigator;
		controller.view = userListView;
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

		controller.initView();

		captureCreateUserButtonCallbackOnInitView().run();
		verify(navigator).navigateTo(CreateUserViewController.URI_FRAGMENT);

	}

	@Test
	public void testUriFragmentChanged() {
		UriFragmentChangedEvent event = new UriFragmentChangedEvent(page,
				UserListViewController.URI_FRAGMENT);

		controller.uriFragmentChanged(event);

		verify(viewManager).setContentView(userListView);
	}

	@Test
	public void testUriFragmentChangedForOtherFragment() {
		UriFragmentChangedEvent event = new UriFragmentChangedEvent(page,
				"otherFragment");

		controller.uriFragmentChanged(event);

		verify(viewManager, never()).setContentView(userListView);
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
