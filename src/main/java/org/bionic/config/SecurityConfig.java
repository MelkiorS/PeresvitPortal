package org.bionic.config;

import org.bionic.security.UserDetailsService;
import org.bionic.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import org.bionic.account.AccountService;

import javax.sql.DataSource;

@Configuration
//@ComponentScan(basePackages = { "org.bionic.security" })
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = false)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select email, password, enabled from user where email=?");
//                .authoritiesByUsernameQuery(
//                        "select email, rangName from rang r, user u where u.rangId=r.rangId AND email=?");
        auth.userDetailsService(userDetailsService).and();
//                .inMemoryAuthentication().withUser("test@test").password("123").roles("ADMIN");
//        auth
//            .inMemoryAuthentication()
//                .withUser("test").password("123").roles("ADMIN")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN","USER");
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests().antMatchers("/admin/**", "/**", "/workField/office", "/registration/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login/success")
                .failureUrl("/login")
                .permitAll();

//                .and();
//            .authorizeRequests()
//                .antMatchers("/", "/favicon.ico", "/resources/**", "/signup").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/signin")
//                .permitAll()
//                .failureUrl("/signin?error=1")
//                .loginProcessingUrl("/authenticate")
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .permitAll()
//                .logoutSuccessUrl("/signin?logout")
//                .and()
//            .rememberMe()
//                .rememberMeServices(rememberMeServices())
//                .key("remember-me-key");
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}