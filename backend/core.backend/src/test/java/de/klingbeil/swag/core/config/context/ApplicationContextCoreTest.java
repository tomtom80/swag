package de.klingbeil.swag.core.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"de.klingbeil.swag.core.backend"})
@Import({ApplicationContextDataSource.class})
public class ApplicationContextCoreTest {
	
}
