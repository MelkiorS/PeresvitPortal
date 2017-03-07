package ua.peresvit.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
//import com.google.common.base.Preconditions;

@Configuration
@EnableJpaRepositories(basePackages = "ua.peresvit")
@EnableTransactionManagement
@PropertySource({"classpath:persistence-mysql.properties"})
@ComponentScan( basePackages = { "ua.peresvit.entity" })
@ActiveProfiles("test")
public class PersistenceTestConfig {

    private static String PROP_DATABASE_DRIVER = "dataSourceTest.driverClassName";
    private static String PROP_DATABASE_PASSWORD = "dataSourceTest.password";
    private static String PROP_DATABASE_URL = "dataSourceTest.url";
    private static String PROP_DATABASE_USERNAME = "dataSourceTest.username";
    private static final String PROP_HIBERNATE_DIALECT = "hibernateTest.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
    private static final String PROP_SPRING_JPA_GENERATE_DDL = "spring.jpa.generate-ddl";

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN).split(","));
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN).split(","));
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName("PeresvitTest");
        emf.setJpaProperties(hibernateProperties());
        return emf;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource db = new BasicDataSource();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));
        return dataSource;
    }

//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//
//        return txManager;
//    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // convert exception to
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
                setProperty(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
                setProperty(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
//                setProperty(PROP_HIBERNATE_DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_DDL_AUTO));
//                setProperty(PROP_SPRING_JPA_GENERATE_DDL, env.getRequiredProperty(PROP_SPRING_JPA_GENERATE_DDL));
            }
        };
    }
}
