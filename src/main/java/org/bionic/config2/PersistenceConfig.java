//package org.bionic.config2;
//
//
//        import java.util.Properties;
//        import javax.sql.DataSource;
//
//        import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
//        import org.hibernate.SessionFactory;
////import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
//        import org.hibernate.jpa.HibernatePersistenceProvider;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.ComponentScan;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.context.annotation.PropertySource;
//        import org.springframework.core.env.Environment;
//        import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//        import org.springframework.orm.hibernate5.HibernateTransactionManager;
//        import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//        import org.springframework.transaction.annotation.EnableTransactionManagement;
////import com.google.common.baнse.Preconditions;
//
//@Configuration
//@EnableJpaRepositories("org.peresvit")
//@EnableTransactionManagement
//@PropertySource({ "classpath:persistence-mysql.properties" })
//@ComponentScan({ "org.peresvit.entity" })
//public class PersistenceConfig {
//
//    private static final String PROP_DATABASE_DRIVER = "jdbc.driverClassName";
//    private static final String PROP_DATABASE_PASSWORD = "jdbc.password";
//    private static final String PROP_DATABASE_URL = "jdbc.url";
//    private static final String PROP_DATABASE_USERNAME = "jdbc.username";
//    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
//    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
//    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
//    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
//    private static final String PROP_LAZY_LOAD="hibernate.enable_lazy_load_no_trans";
//    private static final String GLOBAL_QUOTED_IDENTIFIERS="hibernate.globally_quoted_identifiers";
//
//
//    private static final String PROP_FORMAT_SQL="hibernate.format_sql";
//    private static final String PROP_SHOW_SQL_COMENT="hibernate.use_sql_comments";
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN).split(","));
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        emf.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN).split(","));
//        emf.setDataSource(dataSource());
//        emf.setPersistenceUnitName("PeresvitDB");
//        emf.setJpaProperties(hibernateProperties());
//        return emf;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
//        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
//        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
//        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));
//        return dataSource;
//    }
//
//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//
//        return txManager;
//    }
//
//
//    // convert exception to
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    Properties hibernateProperties() {
//        return new Properties() {
//            {
//                setProperty(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
//                setProperty(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
//                setProperty(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
//                setProperty(PROP_LAZY_LOAD, env.getRequiredProperty(PROP_LAZY_LOAD));
//                setProperty(PROP_FORMAT_SQL, env.getRequiredProperty(PROP_FORMAT_SQL));
//                setProperty(PROP_SHOW_SQL_COMENT, env.getRequiredProperty(PROP_SHOW_SQL_COMENT));
//                setProperty(PROP_SHOW_SQL_COMENT, env.getRequiredProperty(PROP_SHOW_SQL_COMENT));
//                setProperty(GLOBAL_QUOTED_IDENTIFIERS, env.getRequiredProperty(GLOBAL_QUOTED_IDENTIFIERS));
//            }
//        };
//    }
//}
