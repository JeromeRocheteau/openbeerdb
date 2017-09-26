package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class StyleCreate extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 17L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer id = this.doProcess(request);
		this.doWrite(id, response.getWriter());
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		statement.setString(1, name);
		if (category == null) {
			statement.setNull(2, Types.INTEGER);
		} else {
			Integer style = Integer.valueOf(Integer.valueOf(category));
			statement.setInt(2, style);
		}
	}

	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		Integer id = null;
		while (resultSet.next()) {
			if (id == null) {
				id = resultSet.getInt(1);
			} else {
				throw new Exception("too many generated keys '" + count + "' (already retrieve '" + id + "' and '" + resultSet.getInt(1) + "').");
			}
		}
		return id;
	}
	
}
