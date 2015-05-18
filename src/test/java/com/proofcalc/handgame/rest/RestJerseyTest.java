package com.proofcalc.handgame.rest;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Before;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class RestJerseyTest extends JerseyTest {

    protected WebResource webResource = null;

    @Override
    protected URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9977)).build();
    }

    @Override
    protected AppDescriptor configure() {
        return WebAppDescriptorBuilder.build();
    }

    @Before
    public void initTest() {
        webResource = resource();
    }

    private static class WebAppDescriptorBuilder {
        public static AppDescriptor build() {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException ignore) {
            }

            return new WebAppDescriptor.Builder(new String[]{"com.proofcalc.handgame.rest"})
                    .contextParam("contextConfigLocation", "classpath:app-context.xml")
                    .clientConfig(new DefaultClientConfig())
                    .contextListenerClass(org.springframework.web.context.ContextLoaderListener.class)
                    .requestListenerClass(org.springframework.web.context.request.RequestContextListener.class)
                    .contextPath("service")
                    .servletClass(com.sun.jersey.spi.spring.container.servlet.SpringServlet.class)
                    .build();
        }

    }
}
