package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.openbeerdb.entities.Beer;
import fr.icam.openbeerdb.entities.Style;

public class FeatureList extends StyleList {

	private static final long serialVersionUID = 20L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Beer beer = (Beer) request.getAttribute("beer");
		List<Style> styles = this.doProcess(request);
		beer.setStyles(styles);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Beer beer = (Beer) request.getAttribute("beer");
		statement.setInt(1, beer.getId());
	}
	
}
