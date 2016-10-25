package org.bionic.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebAppConfig.class);
		servletContext.addListener(new ContextLoaderListener(ctx));

		ctx.setServletContext(servletContext);

		Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
				new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		
		 //character encoding filter
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, true, "*");
	}

//	public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//		// Map DispatcherServlet
//		@Override
//		protected String[] getServletMappings() {
//			return new String[]{"/"};
//		}
//
//		// Specify configuration class
//		@Override
//		protected Class<?>[] getRootConfigClasses() {
//			return new Class<?>[]{RootConfig.class};
//		}
//
//		// Specify configuration class
//		@Override
//		protected Class<?>[] getServletConfigClasses() {
//			return new Class<?>[]{WebConfig.class};
//		}
//	}

}
