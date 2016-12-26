import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.servlet.DefaultServlet;

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
        context.addServlet(new ServletHolder(new Index()),"/");
        addStaticHandler("css");
        addStaticHandler("img");
        addStaticHandler("js");
        server.start();
        server.join();
    }

    public void addStaticHandler(ServletContextHandler context, String subPath){
      ServletHolder holderHome = new ServletHolder(new DefaultServlet());
      holderHome.setInitParameter("resourceBase",resolveStaticFolder(subPath));
      holderHome.setInitParameter("dirAllowed","true");
      holderHome.setInitParameter("pathInfoOnly","true");
      context.addServlet(holderHome,"/"+subPath+"/*");
    }

    public String resolveStaticFolder(String subPath){
      return this.getClass().getClassLoader().getResource("static/"+subPath).getFile().getAbsolutePath();
    }
}
