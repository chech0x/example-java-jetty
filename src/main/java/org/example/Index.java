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
          message = "nn";
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
      String html="";
      html=html+"<!doctype html>";
      html=html+"    <head>";
      html=html+"        <meta charset=\"utf-8\">";
      html=html+"        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">";
      html=html+"        <title></title>";
      html=html+"        <meta name=\"description\" content=\"\">";
      html=html+"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
      html=html+"        <link rel=\"apple-touch-icon\" href=\"apple-touch-icon.png\">";
      html=html+"        <link rel=\"stylesheet\" href=\"css/normalize.min.css\">";
      html=html+"        <link rel=\"stylesheet\" href=\"css/main.css\">";
      html=html+"        <script src=\"js/vendor/modernizr-2.8.3-respond-1.4.2.min.js\"></script>";
      html=html+"    </head>";
      html=html+"    <body>";
      html=html+"        <div class=\"header-container card card-3 \">";
      html=html+"            <header class=\"wrapper clearfix\">";
      html=html+"                <h1 class=\"title\">Demo Talka con Jetty</h1>";
      html=html+"                <nav>";
      html=html+"                </nav>";
      html=html+"            </header>";
      html=html+"        </div>";
      html=html+"        <div class=\"main-container\">";
      html=html+"            <div class=\"main wrapper clearfix\">";
      html=html+"                <article>";
      html=html+"                    <header class=\"card card-2\">";
      html=html+"                        <h1>Corriendo sobre: "+message+"</h1>";
      html=html+"                        <h2>Release: "+release+"</h1>";
      html=html+"                        <p>Esta aplicación se encuentra corriendo en una nube de tipo <strong>PaaS privada</strong>, basada en <strong>contenedores</strong></p>";
      html=html+"                    </header>";
      html=html+"                    <section>";
      html=html+"                      <div class=\"circulo card-1\" style=\"background-color: "+color+";\"></div>";
      html=html+"                    </section>";
      html=html+"                </article>";
      html=html+"                <aside class=\"card card-1\">";
      html=html+"                      <h3>Contenedor: "+container+"</h3>";
      html=html+"                      <p>En este momomento esta petición fue resulta por el contenedor <strong>"+container+"</strong>. El color utilizado para representarlo es: <span style=\"background-color:white;color:"+color+"\"><strong>&nbsp;"+color+"&nbsp;</strong></span></p>";
      html=html+"                </aside>";
      html=html+"            </div>";
      html=html+"        </div>";
      html=html+"        <div class=\"footer-container card card-3\">";
      html=html+"            <footer class=\"wrapper\">";
      html=html+"                <h3>Kuvasz Solutions - Talka</h3>";
      html=html+"            </footer>";
      html=html+"        </div>";
      html=html+"        <script src=\"js/main.js\"></script>";
      html=html+"    </body>";
      html=html+"</html>";
      resp.getWriter().print(html);*/
  }
}
