package de.klingbeil.swag.web.config.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import de.klingbeil.swag.user.config.context.ApplicationContextUserUi;

@Configuration
@ImportResource("classpath:applicationContext.xml")
@Import({ApplicationContextUserUi.class})
public class ApplicationContextWebUi {
	
}
