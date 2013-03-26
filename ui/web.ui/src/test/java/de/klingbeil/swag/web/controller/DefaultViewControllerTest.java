package de.klingbeil.swag.web.controller;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.user.controller.UserListViewController;
import de.klingbeil.swag.user.view.UserListView;

public class DefaultViewControllerTest {

	private DefaultViewController defaultViewController;

	@Mock
	private UserListView userListView;

	@Mock
	private UserListViewController userListViewController;

	@Mock
	private ViewManager viewManager;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		defaultViewController = new DefaultViewController();
		defaultViewController.viewManager = viewManager;
		defaultViewController.controller = userListViewController;
	}

	@Test
	public void testInitialitationSetListViewAsContent() {
		when(userListViewController.getView()).thenReturn(userListView);

		defaultViewController.initView();

		verify(viewManager).setContentView(userListView);
	}

	@Test
	public void testGetView() {
		when(viewManager.getContentView()).thenReturn(userListView);

		assertSame(userListView, defaultViewController.getView());
	}

}
