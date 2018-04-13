package coursServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME_COOKIE = "cnxCookie";
	private static int counterConnection = 1;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// element pour utilisation de Session
		// HttpSession session = request.getSession();
		
		/**
		 * manage of a cookie
		 * 
		 * if there is a cookie, we take this and modify
		 * 
		 * if not we initialise one
		 * 
		 */
		Cookie[] resultCookies = request.getCookies();
		if (resultCookies == null) {
			Cookie cookie = new Cookie(NAME_COOKIE, "1");
			response.addCookie(cookie);
		} else {

			// classic for
			// for (int i = 0; i != resultCookies.length; i++) {
			
			// boucle ternaire
			for (Cookie c : resultCookies) {
				if (c.getName().equals(NAME_COOKIE)) {
					// method in one line to increment the value
					// c.setValue(Integer.toString(Integer.parseInt(c.getValue())+1));

					// decomposed method to increment the value
					int newValue = Integer.parseInt(c.getValue());
					newValue++;
					counterConnection = newValue;
					c.setValue(String.valueOf(newValue));
					response.addCookie(c);
				}
			}
		}

		response.setStatus(200);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\" />");
		out.println("<title>WebServlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p style='color:blue;'>Number of connection in cookie : " + counterConnection + ".</p>");
		out.println("<p style='color:red;'>Voici ma page générée depuis une servlet http.</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
