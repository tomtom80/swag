package de.klingbeil.swag.user.controller.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.user.controller.LoginCallback;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.LoginView;

public class LoginViewControllerTest {

	private static final String PASSWORT = "secret";
	private static final String USERNAME = "horst";

	private LoginViewController controller;

	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	private LoginCallback callback;

	@Mock
	private ViewManager viewManager;

	@Mock
	private UriFragmentNavigator navigator;

	@Mock
	private Page page;

	@Mock
	private LoginView loginView;
	private UserViewModel user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new LoginViewController();
		controller.view = loginView;
		controller.viewManager = viewManager;
		controller.authenticationManager = authenticationManager;
		controller.navigator = navigator;
		user = createUserViewModel(USERNAME, PASSWORT);
	}

	@Test
	public void testGetView() {
		assertSame(loginView, controller.getView());
	}

	@Test
	public void testLoginCallbackOnInit() {
		controller.initView();
		LoginCallback loginCallback = captureCallbackFromInitViewCall();

		loginCallback.login(user);

		UsernamePasswordAuthenticationToken capturedToken = captureTokenOnAuthenticate();
		assertEquals(USERNAME, capturedToken.getPrincipal());
		assertEquals(PASSWORT, capturedToken.getCredentials());
		verify(navigator).navigateTo(UserListViewController.URI_FRAGMENT);
	}

	@Test
	public void testLoginCallbackOnInitWithLoginFailure() {
		String expectedErrorMsg = "wrong password or username";
		controller.initView();
		LoginCallback loginCallback = captureCallbackFromInitViewCall();
		when(authenticationManager.authenticate(any(Authentication.class)))
				.thenThrow(new BadCredentialsException(expectedErrorMsg));

		loginCallback.login(user);

		String capturedMsg = captureErrorMsgOnSetComponentError();
		assertEquals(expectedErrorMsg, capturedMsg);
	}

	private String captureErrorMsgOnSetComponentError() {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(loginView).setComponentError(captor.capture());
		return captor.getValue();
	}

	@Test
	public void testUriFragmentChanged() {
		UriFragmentChangedEvent event = new UriFragmentChangedEvent(page,
				LoginViewController.URI_FRAGMENT);

		controller.uriFragmentChanged(event);

		verify(viewManager).setContentView(loginView);
	}

	@Test
	public void testUriFragmentChangedForOtherFragment() {
		UriFragmentChangedEvent event = new UriFragmentChangedEvent(page,
				"otherFragment");

		controller.uriFragmentChanged(event);

		verify(viewManager, never()).setContentView(loginView);
	}

	private LoginCallback captureCallbackFromInitViewCall() {
		ArgumentCaptor<LoginCallback> captor = ArgumentCaptor
				.forClass(LoginCallback.class);
		verify(loginView).setLoginButtonCallback(captor.capture());
		LoginCallback capturedCallback = captor.getValue();
		return capturedCallback;
	}

	private UserViewModel createUserViewModel(String username, String password) {
		UserViewModel result = new UserViewModel();
		result.setUsername(username);
		result.setPassword(password);
		return result;
	}

	private UsernamePasswordAuthenticationToken captureTokenOnAuthenticate() {
		ArgumentCaptor<UsernamePasswordAuthenticationToken> tokenCaptor = ArgumentCaptor
				.forClass(UsernamePasswordAuthenticationToken.class);
		verify(authenticationManager).authenticate(tokenCaptor.capture());
		return tokenCaptor.getValue();
	}

}
