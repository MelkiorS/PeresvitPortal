package org.bionic.mma.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
public class JpaConfig {

	// ${jdbc.driverClassName}
	@Value("${jdbc.driverClassName}")
	private String driverClassName = "com.mysql.jdbc.Driver";
	@Value("${jdbc.url}")
	private String url = "jdbc:mysql://localhost:3306/peresvitDB?useSSL=false";
	@Value("${jdbc.username}")
	private String username = "peresvit";
	@Value("${jdbc.password}")
	private String password = "peresvitPass";

	@Value("${hibernate.dialect}")
	private String hibernateDialect = "MySQLDialect";
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql = "true";
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto = "validate/create/update/create-drop";

	@Bean()
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}

	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

	@Bean
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShowSql);
		properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

		return properties;
	}
}
