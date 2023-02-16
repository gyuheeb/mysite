package com.douzone.mysite.Initializer;

import javax.servlet.Filter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.web.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//Root ApplicationContext's Configuration Class
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		return new Filter[] {new CharacterEncodingFilter("utf-8",false)};
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		DispatcherServlet servlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
		
		servlet.setThrowExceptionIfNoHandlerFound(true);
		return servlet;
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());
//		ServletRegistration.Dynamic servletRegistration= servletContext.addServlet("spring", dispatcherServlet);
//		servletRegistration.setLoadOnStartup(1);
//		
//		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//	}
	
	
	

}
