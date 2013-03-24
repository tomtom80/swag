package de.klingbeil.swag.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.klingbeil.swag.config.context.ApplicationContextUserUi;

@Configuration
@Import({ ApplicationContextUserUi.class })
@ComponentScan(basePackages = { "de.klingbeil.swag.web" })
public class ApplicationContextWebUi {

}
