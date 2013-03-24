package de.klingbeil.swag.web.controller;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.klingbeil.swag.user.controller.CreateUserViewController;
import de.klingbeil.swag.user.view.CreateUserView;
import de.klingbeil.swag.user.view.CreateUserViewImpl;

public class ContentViewControllerTest {

	private ContentViewController contentViewController;
	@Mock
	private CreateUserViewController userViewController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		contentViewController = new ContentViewController();
		contentViewController.userViewController = userViewController;
	}

	@Test
	public void testGetView() {
		CreateUserView view = new CreateUserViewImpl();
		when(userViewController.getView()).thenReturn(view);

		assertSame(view, contentViewController.getView());
	}

}
