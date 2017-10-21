package com.joelnetodev.pizzaria.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.joelnetodev.pizzaria.repositories")
public class ConfigurationDataBase 
{
	//O nome dos metodos precisam ser esses, spring busca pelo nome fixo via reflection
	@Bean
	public JpaTransactionManager transactionManager() throws Exception 
	{
		//Gerenciador de transações do spring
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return transactionManager;
	}
		
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception 
	{
		//LocalContainerEntityManagerFactoryBean do spring = entityManagerFactory
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(getDataSource());
		entityManagerFactoryBean.setPackagesToScan("com.joelnetodev.pizzaria.entities");
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactoryBean.setJpaProperties(propriedades());
		
		return entityManagerFactoryBean;
	}

	@Bean
	public DataSource getDataSource() throws IllegalStateException, PropertyVetoException 
	{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dbteste");
		dataSource.setUser("joeyneto");
		dataSource.setPassword("abcd1234");
		
		return dataSource;
	}
	
	private Properties propriedades()
	{
		Properties props = new Properties();
		
		//Cada banco tem sua sintaxe (dialeto), Hibernate vai trabalhar com sintaxe do MySql
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		//props.put("hibernate.hbm2ddl.auto", "update");
		
		return props;
	}
}