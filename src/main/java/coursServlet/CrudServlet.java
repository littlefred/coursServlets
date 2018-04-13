package coursServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CrudServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	private long count = 0;
	private AtomicLong countBis = new AtomicLong(0);
	private long dbCount = 0;
	private final String url = "jdbc:postgresql://baasu.db.elephantsql.com:5432/zagfmozf";// your url access to your
																							// database postgresql
	private final String user = "zagfmozf";// your user to your postgresql
	private final String pass = "Uj1zrCPaMm0MlmQaJZ5tRCjkcYoGXboU";// your password to your postgresql

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try (Connection connection = DriverManager.getConnection(url, user, pass)) {
			Statement cmd = connection.createStatement();
			String sql = "INSERT INTO student (kind,name,firstname,birthdate,class_pk_id) VALUES ('Mr','Server','Servlet','1111-11-11',2)";
			String sql2 = "SELECT COUNT(*) FROM student WHERE name='Server'";
			cmd.execute(sql);
			ResultSet rs = cmd.executeQuery(sql2);
			while (rs.next()) {
			dbCount = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		countBis.incrementAndGet();
		count++;
		response.getWriter()
				.println("actually we have " + countBis + " request\n But without atomic synchronize we have " + count
						+ "\nIn the Database we have " + dbCount + " lines.");

	}

}
