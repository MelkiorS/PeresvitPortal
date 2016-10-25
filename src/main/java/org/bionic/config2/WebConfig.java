//package org.bionic.config2;
//
//
// import org.peresvit.util.converter.IdToTimeTableConverter;
// import org.peresvit.util.converter.IdToGroupConverter;
// import org.peresvit.util.converter.StringToLocalDateConverter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.FilterType;
// import org.springframework.format.FormatterRegistry;
// import org.springframework.web.servlet.ViewResolver;
// import org.springframework.web.servlet.config.annotation.*;
// import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//
// @Configuration
// @EnableWebMvc
// @ComponentScan(basePackages = {"org."},
// excludeFilters = {
// @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
// })
// public class WebConfig extends WebMvcConfigurerAdapter {
//
// //need to define been
// @Autowired
// IdToGroupConverter idToGroupConverter;
// @Autowired
// IdToTimeTableConverter idToTimeTableConverter;
//
// // Configure a JSP view resolver
// @Bean
// public ViewResolver viewResolver() {
// InternalResourceViewResolver resolver =
// new InternalResourceViewResolver();
// resolver.setPrefix("/WEB-INF/views/");
// resolver.setSuffix(".jsp");
// resolver.setExposeContextBeansAsAttributes(true);
// return resolver;
// }
//
//
// // Configure static content handling
// @Override
// public void configureDefaultServletHandling(
// DefaultServletHandlerConfigurer configurer) {
// configurer.enable();
// }
//
// @Override
// public void addResourceHandlers(final ResourceHandlerRegistry registry) {
// registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
// }
//
// // Configure Converter to be used.
// @Override
// public void addFormatters(FormatterRegistry registry) {
// registry.addConverter(idToGroupConverter);
// registry.addConverter(idToTimeTableConverter);
// registry.addConverter(new StringToLocalDateConverter("dd-MM-yyyy"));
// }
//
// /** It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in
// @PathVaidables argument.
//  * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
//  * This is a workaround for this issue.
// */
//@Override
//public void configurePathMatch(PathMatchConfigurer matcher) {
//        matcher.setUseRegisteredSuffixPatternMatch(true);
//        }
//
//        }
