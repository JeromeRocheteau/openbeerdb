package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class BeerCreate extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 31L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer id = this.doProcess(request);
		this.doWrite(id, response.getWriter());
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String name = request.getParameter("name");
		String brewery = request.getParameter("brewery");
		String brand = request.getParameter("brand");
		String abv = request.getParameter("abv");
		statement.setString(1, user);
		statement.setString(2, name);
		statement.setInt(3, Integer.valueOf(brewery));
		if (brand == null) {
			statement.setNull(4, Types.INTEGER);
		} else {
			statement.setInt(4, Integer.valueOf(brand));
		}
		statement.setFloat(5, Float.valueOf(abv));
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
