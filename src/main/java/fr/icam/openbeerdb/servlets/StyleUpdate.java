package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class StyleUpdate extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 22L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		statement.setString(1, name);
		if (category == null) {
			statement.setNull(2, Types.INTEGER);
		} else {
			statement.setInt(2, Integer.valueOf(category));
		}
		statement.setInt(3, Integer.valueOf(id));
	}

	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}
	
}
