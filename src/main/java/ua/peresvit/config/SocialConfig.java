package ua.peresvit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.web.DisconnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@ComponentScan("ua.peresvit")
public class SocialConfig implements SocialConfigurer {

    private final DataSource dataSource;
    private final Environment environment;

    @Autowired
    public SocialConfig(DataSource dataSource, Environment environment) {
        this.dataSource = dataSource;
        this.environment = environment;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret")));
        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
                env.getProperty("google.clientId"),
                env.getProperty("google.clientSecret")));
        cfConfig.addConnectionFactory(new VKontakteConnectionFactory(
                env.getProperty("vk.app.id"),
                env.getProperty("vk.app.secret")));
    }

//    @Bean
//    @Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
//    public ConnectionFactoryLocator connectionFactoryLocator() {
//        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//
//        registry.addConnectionFactory(new FacebookConnectionFactory(
//                environment.getProperty("facebook.app.id"),
//                environment.getProperty("facebook.app.secret")));
//
//        registry.addConnectionFactory(new GoogleConnectionFactory(
//                environment.getProperty("google.clientId"),
//                environment.getProperty("google.clientSecret")));
//
//        registry.addConnectionFactory(new VKontakteConnectionFactory(
//                environment.getProperty("vk.app.id"),
//                environment.getProperty("vk.app.secret")));
//
//        return registry;
//    }

    @Bean
    @Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository usersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

    @Bean
    public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository, Environment environment) {
        return new DisconnectController(usersConnectionRepository, environment.getProperty("facebook.appSecret"));
    }

    @Bean
    public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        return new ReconnectFilter(usersConnectionRepository, userIdSource);
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public VKontakte vkontakte(ConnectionRepository repository) {
        Connection<VKontakte> connection = repository.findPrimaryConnection(VKontakte.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Google google(ConnectionRepository repository) {
        Connection<Google> connection = repository.findPrimaryConnection(Google.class);
        return connection != null ? connection.getApi() : null;
    }
}
