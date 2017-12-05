package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class FeatureDelete extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 22L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doProcess(request);
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Integer beer = Integer.valueOf(request.getParameter("beer"));
		statement.setInt(1, beer);
	}

	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}
	
}
