package fr.icam.openbeerdb.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.openbeerdb.entities.Beer;
import fr.icam.openbeerdb.entities.Brewery;

public class BeerPage extends JdbcQueryServlet<List<Beer>> {

	private static final long serialVersionUID = 3L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Beer> beers = this.doProcess(request);
		this.doWrite(beers, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		int offset = Integer.valueOf(request.getParameter("offset"));
		int length = Integer.valueOf(request.getParameter("length"));
		statement.setInt(1, offset);
		statement.setInt(2, length);
	}

	@Override
	protected List<Beer> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Beer> beers = new LinkedList<Beer>();
		while (resultSet.next()) {
			int beerId  = resultSet.getInt("beerId");
			String beerName = resultSet.getString("beerName");
			float abv = resultSet.getFloat("abv");
			int breweryId = resultSet.getInt("breweryId");
			String breweryName = resultSet.getString("breweryName");
			String city = resultSet.getString("city");
			String country = resultSet.getString("country");
			String address = resultSet.getString("address");
			if (resultSet.wasNull()) {
				address = null;
			}
			Brewery brewery = new Brewery();
			brewery.setId(breweryId);
			brewery.setName(breweryName);
			brewery.setAddress(address);
			brewery.setCity(city);
			brewery.setCountry(country);
			Beer beer = new Beer();
			beer.setId(beerId);
			beer.setBrewery(brewery);
			beer.setName(beerName);
			beer.setAbv(abv);
			beers.add(beer);
		}
		return beers;
	}
	
}
