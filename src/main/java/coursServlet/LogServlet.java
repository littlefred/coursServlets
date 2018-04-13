package coursServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	private List<String> log = Collections.synchronizedList(new ArrayList());

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		log.add(String.valueOf(request));
		response.getWriter().println("actually we have " + log.size() + " log.\n==>"+log.get(0));

	}

}
