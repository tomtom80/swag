package de.klingbeil.swag.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.controller.internal.UserListViewController;

public class SwagUITest {

	@Mock
	private VaadinRequest request;
	@Mock
	private UriFragmentNavigator navigator;
	@Mock
	private ViewManager viewManager;
	@Mock
	private View view;
	@Mock
	private Component mainComponent;

	private SwagUI swagUI;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		swagUI = new SwagUI();
		swagUI.navigator = navigator;
		swagUI.viewManager = viewManager;

	}

	@Test
	public void testInitalContentView() throws Exception {
		when(viewManager.getContentView()).thenReturn(view);
		when(view.getComponent()).thenReturn(mainComponent);

		swagUI.init(request);

		verify(navigator).navigateTo(UserListViewController.URI_FRAGMENT);
	}
}
