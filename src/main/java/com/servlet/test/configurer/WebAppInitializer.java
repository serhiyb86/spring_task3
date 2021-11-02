package com.servlet.test.configurer;

import com.servlet.test.controller.AddStudentServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    private static final Logger logger = LoggerFactory.getLogger(AddStudentServlet.class);

    public void onStartup(ServletContext container) throws ServletException {
        logger.debug("My web initializer is loaded!");
        XmlWebApplicationContext xctx = new XmlWebApplicationContext();
        xctx.setConfigLocation("classpath:/springContext.xml");
        xctx.setServletContext(container);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(xctx);
        // dispatcherServlet.setDetectAllHandlerAdapters(true);

        ServletRegistration.Dynamic servlet = container.addServlet(
                "myDispatcher", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

    }
}