package com.gbjavacourse.hw.repository.launcher;

import java.net.URL;
import java.security.ProtectionDomain;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {

    public static void main(String[] args) throws Exception {
        try {
            Server server = new Server(8189);

            ProtectionDomain domain = Launcher.class.getProtectionDomain();
            URL location = domain.getCodeSource().getLocation();

            WebAppContext webAppContext = new WebAppContext();
            webAppContext.setWar(location.toExternalForm());

            server.setHandler(webAppContext);
            server.start();
            server.join();
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }
}
