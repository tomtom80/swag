package de.klingbeil.swag.web.controller;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.klingbeil.swag.user.controller.UserViewController;
import de.klingbeil.swag.user.view.UserView;
import de.klingbeil.swag.user.view.UserViewImpl;

public class ContentViewControllerTest {

	private ContentViewController contentViewController;
	@Mock
	private UserViewController userViewController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		contentViewController = new ContentViewController();
		contentViewController.userViewController = userViewController;
	}

	@Test
	public void testGetView() {
		UserView view = new UserViewImpl();
		when(userViewController.getView()).thenReturn(view);

		assertSame(view, contentViewController.getView());
	}

}
