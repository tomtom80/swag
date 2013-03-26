package de.klingbeil.swag.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ApplicationContextUserUi.class, ApplicationContextCoreUi.class })
@ComponentScan(basePackages = { "de.klingbeil.swag.web" })
public class ApplicationContextWebUi {

}
