package coursServlet;


import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.*;

public class HelloServer {
		
	 public static void main(String args[]) throws Exception{
	        Server server = new Server(9092);
	        ServletHandler handler = new ServletHandler();
	        server.setHandler(handler);
	        handler.addServletWithMapping(HelloGenericServlet.class, "/*");
	        handler.addServletWithMapping(StatefulServlet.class, "/test");
	        handler.addServletWithMapping(LogServlet.class, "/log");
	        handler.addServletWithMapping(CounterServlet.class, "/count");
	        handler.addServletWithMapping(CrudServlet.class, "/db");
	        handler.addServletWithMapping(WebServlet.class, "/web");
	        server.start();
	        server.join();
	 }
}