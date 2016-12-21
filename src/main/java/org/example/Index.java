import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      String message = System.getenv("POWERED_BY");
      if (message == null) {
          message = "NN";
      }

      String release = System.getenv("WORKFLOW_RELEASE");
      if (release == null) {
          release = "unknown";
      }

      String container = "unknown";
      Process proc = Runtime.getRuntime().exec("hostname");
      InputStream is = proc.getInputStream();
      Scanner s = new Scanner(is);
      if (s.hasNext()) {
        container = s.next();
      }
      String color="#"+container.toUpperCase().trim().substring(0,6);
      resp.setContentType("text/html");
      String header = "<!Document html>\n<HTML><HEAD><title>Demo Talka Jetty</title></HEAD><BODY>";
      String footer = "</BODY></HTML>";
      String circle = "<div style=\"background-color: "+color+"; border-radius: 50%;  width: 100px; height: 100px;\"></div>";
      resp.getWriter().print(String.format("%s Powered by %s\nRelease %s on %s\n %s %s", header,message, release, container, circle, footer));
  }
}
