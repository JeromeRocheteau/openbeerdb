package fr.icam.openbeerdb.filters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.github.jeromerocheteau.JdbcFilter;

public class PrincipalFilter extends JdbcFilter {

	@Override
	protected void doFill(PreparedStatement statement, ServletRequest request) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String user = httpRequest.getUserPrincipal().getName();
		String id = request.getParameter("id");
		statement.setInt(1, Integer.valueOf(id));
		statement.setString(2, user);
	}
	
	@Override
	protected void doMap(ServletRequest request, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			return;
		} else {
			throw new ServletException("operation forbidden");
		}
	}
	
}
