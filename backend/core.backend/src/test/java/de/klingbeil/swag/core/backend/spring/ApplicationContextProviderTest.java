package de.klingbeil.swag.core.backend.spring;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:META-INF/spring/applicationContext-user-backend-test.xml"})
public class ApplicationContextProviderTest {

	@Test
	public void testEnsureApplicationContextIsInjected() {
		assertNotNull(ApplicationContextProvider.getApplicationContext());
	}

}
