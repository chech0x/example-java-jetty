package org.example;

import java.io.File;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

public class HelloWorld {
    public static void main(String[] args) throws Exception{
        String portEnv = System.getenv("PORT");
        int port = 5000;
        if (portEnv != null) {
            port = Integer.valueOf(portEnv);
        }

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(Resource.newClassPathResource("static"));
        server.setHandler(context);
        context.addServlet(new ServletHolder(new Healthcheck()),"/healthz");
        addStaticHandler(context,"css");
        addStaticHandler(context,"img");
        addStaticHandler(context,"js");
        addStaticHandler(context,"");
        context.addServlet(new ServletHolder(new Index()),"/demo");
        context.addServlet(new ServletHolder(new CurrentTime()),"/time");
        server.start();
        server.join();
    }

    public static void addStaticHandler(ServletContextHandler context, String subPath){
      ServletHolder holderHome = new ServletHolder(new DefaultServlet());
      holderHome.setInitParameter("resourceBase",resolveStaticFolder(subPath));
      holderHome.setInitParameter("dirAllowed","true");
      holderHome.setInitParameter("pathInfoOnly","true");
      String path="/";
      if(subPath!=null && subPath.trim().length()>0){
    	  path+=subPath+"/";
      }
      path+="*";
      context.addServlet(holderHome,path);
    }

    public static String resolveStaticFolder(String subPath){
    	URL classesDirectory = HelloWorld.class.getClassLoader().getResource(".");
    	if(classesDirectory==null){
    		return "";
    	}
    	return new File(classesDirectory.getFile()).getParentFile().getAbsolutePath()+"/static/"+subPath;
    }
}
