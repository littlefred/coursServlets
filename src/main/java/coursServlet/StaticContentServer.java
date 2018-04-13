package coursServlet;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.*;

public class StaticContentServer {
	public static void main(String[] args)
    {
        System.setProperty("org.eclipse.jetty.LEVEL","INFO");

        Server server = new Server(9092);
         // The filesystem paths we will map
        String homePath = System.getProperty("user.home");
        String pwdPath = System.getProperty("user.dir");

        ServletContextHandler context = new ServletContextHandler();
        context.setResourceBase(pwdPath);
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder holderDynamic = new ServletHolder("web", WebServlet.class);
        context.addServlet(holderDynamic, "/web/*");

        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase",homePath);
        holderHome.setInitParameter("dirAllowed","true");
        holderHome.setInitParameter("pathInfoOnly","true");
        context.addServlet(holderHome,"/home/*");

        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("resourceBase", pwdPath);
        holderPwd.setInitParameter("dirAllowed","true");
        context.addServlet(holderPwd,"/");
        
        /*
        ServletHolder holderHtml = new ServletHolder("html", DefaultServlet.class);
        holderPwd.setInitParameter("resourceBase", "user/home/bureau/test/");
        holderPwd.setInitParameter("dirAllowed","true");
        holderHome.setInitParameter("pathInfoOnly","true");
        context.addServlet(holderHtml,"/test/*");
        */
        
        try
        {
            server.start();
            server.dump(System.err);
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}