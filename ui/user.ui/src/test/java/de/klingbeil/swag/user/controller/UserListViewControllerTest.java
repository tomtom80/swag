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

	private UserListViewController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UserListViewController();
		controller.view = userListView;
		controller.userService = userService;
	}

	@Test
	public void testGetView() {
		assertSame(userListView, controller.getView());
	}

	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testFindAllUsersOnViewInit() {
		User user = createUser();
		when(userService.findAll()).thenReturn(Arrays.asList(user));

		controller.initView();

		ArgumentCaptor<List<UserViewModel>> captor = (ArgumentCaptor) ArgumentCaptor
				.forClass(List.class);
		verify(userListView).setViewModel(captor.capture());
		List<UserViewModel> capturedUserList = captor.getValue();
		assertEquals(1, capturedUserList.size());
		UserUtil.assertUser(user, capturedUserList.get(0));
	}

	private User createUser() {
		User result = new User();
		result.setEmail("tom@mail.com");
		result.setFirstName("tom");
		result.setLastName("taylor");
		return result;
	}
}
