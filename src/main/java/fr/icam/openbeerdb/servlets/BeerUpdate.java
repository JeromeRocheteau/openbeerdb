package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class BeerUpdate extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 9L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "feature-delete");
		Integer count = this.doProcess(request);
		Integer id = Integer.valueOf(request.getParameter("id"));
		request.setAttribute("beerId", id);
		this.setStyles(request, response);
		this.doWrite(count, response.getWriter());
	}


	private void setStyles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] styles = request.getParameterValues("style");
		if (styles != null) {
			for (String style : styles) {
				Integer styleId = Integer.valueOf(style);
				request.setAttribute("styleId", styleId);
				this.doCall(request, response, "feature-create");
			}
		}
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Integer id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer brewery = Integer.valueOf(request.getParameter("brewery"));
		Float abv = Float.valueOf(request.getParameter("abv"));
		statement.setString(1, name);
		statement.setInt(2, brewery);
		statement.setFloat(3, abv);
		statement.setFloat(4, id);
	}

	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}
	
}
