package de.klingbeil.swag.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.klingbeil.swag.user.config.context.ApplicationContextUser;

@Configuration
@ComponentScan(basePackages = { "de.klingbeil.swag.user" })
@Import({ ApplicationContextUser.class, ApplicationContextCoreUi.class })
public class ApplicationContextUserUi {
}
