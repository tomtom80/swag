package de.klingbeil.swag.core.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import de.klingbeil.swag.core.backend.spring.LoggingPropertyPlaceholderConfigurer;

@Configuration
public class ApplicationContextLoggingPropertyPlaceholderConfigurerTest {
	@Bean
	public static LoggingPropertyPlaceholderConfigurer bean() {
		LoggingPropertyPlaceholderConfigurer bean = new LoggingPropertyPlaceholderConfigurer();
		bean.setLocation(new ClassPathResource(
				"META-INF/spring/test.properties"));
		return bean;
	}
}
