package com.proofcalc.handgame.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.net.URL;
import java.util.TimeZone;

public class WebServer {

    static {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        java.util.logging.LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static final Logger log = LoggerFactory.getLogger(WebServer.class);

    private static String JETTY_XML = "/jetty.xml";
    private static String WAR_XML = "/WEB-INF/web.xml";
    private static String WAR_URL = "webapp/" + WAR_XML;
    private static String CONTEXT_PATH = "/";

    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.run();
    }

    private void run() {
        log.info("Initializing web server...");
        Server jettyServer = new Server();
        try {
            URL jettyUrl = WebServer.class.getResource(JETTY_XML);
            if (jettyUrl == null) {
                throw new Error("Jetty config was not found: " + JETTY_XML);
            }
            XmlConfiguration jettyConfiguration = new XmlConfiguration(jettyUrl);
            jettyConfiguration.configure(jettyServer);

            URL warUrl = WebServer.class.getClassLoader().getResource(WAR_URL);

            WebAppContext webAppContext = new WebAppContext();
            webAppContext.setWar(assembleWarPath(warUrl));
            webAppContext.setContextPath(CONTEXT_PATH);

            HandlerCollection handlers = new HandlerCollection();
            handlers.addHandler(webAppContext);

            jettyServer.setHandler(handlers);

            log.info("Starting...");
            jettyServer.start();
            log.info("Started");
            jettyServer.join();
            log.info("Stopped");
            System.exit(0);
        } catch (Throwable e) {
            log.error("Problem has occurred: {}", e);
            System.exit(1);
        }
    }

    private String assembleWarPath(URL warUrl) {
        String webApp = null;
        if (warUrl != null) webApp = warUrl.toExternalForm();
        if (webApp != null) webApp = webApp.substring(0, webApp.length() - WAR_XML.length());
        if (webApp == null) webApp = "src/main/webapp";
        return webApp;
    }


}
