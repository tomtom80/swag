package de.klingbeil.swag.user.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.klingbeil.swag.core.config.context.ApplicationContextCore;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"de.klingbeil.swag.user.backend.repository"}, entityManagerFactoryRef = "entityManagerFactoryBean", transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = {"de.klingbeil.swag.user.backend"})
@Import({ApplicationContextCore.class})
public class ApplicationContextUser {

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}
