package de.klingbeil.swag.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;

import de.klingbeil.swag.user.view.CreateUserView;
import de.klingbeil.swag.web.controller.DefaultViewController;

public class SwagUITest {

	@Mock
	private VaadinRequest request;
	@Mock
	private DefaultViewController contentViewController;
	@Mock
	private CreateUserView userView;
	@Mock
	private Component userComponent;

	private SwagUI swagUI;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		swagUI = new SwagUI();
		swagUI.defaultViewController = contentViewController;

	}

	@Test
	public void testInitalContentView() throws Exception {

	}
}
