package coursServlet;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CounterServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	private AtomicLong count = new AtomicLong(0);
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		count.incrementAndGet();
		response.getWriter().println("actually we have "+count);

	}

}
