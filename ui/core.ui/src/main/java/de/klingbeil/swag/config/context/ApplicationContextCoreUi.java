package de.klingbeil.swag.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "de.klingbeil.swag.core" })
public class ApplicationContextCoreUi {

}
