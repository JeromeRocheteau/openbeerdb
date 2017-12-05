package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.openbeerdb.entities.Style;

public class FeatureList extends StyleList {

	private static final long serialVersionUID = 20L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Style> styles = this.doProcess(request);
		this.doWrite(styles, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Integer id = Integer.valueOf(request.getParameter("beer"));
		statement.setInt(1, id);
	}
	
}
