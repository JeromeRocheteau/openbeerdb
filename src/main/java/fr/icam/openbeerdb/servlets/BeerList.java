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
import fr.icam.openbeerdb.entities.Brand;
import fr.icam.openbeerdb.entities.Brewery;

public class BeerList extends JdbcQueryServlet<List<Beer>> {

	private static final long serialVersionUID = 34L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Beer> beers = this.doProcess(request);
		this.doWrite(beers, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception { }

	@Override
	protected List<Beer> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Beer> beers = new LinkedList<Beer>();
		while (resultSet.next()) {
			Integer beerId  = resultSet.getInt("beerId");
			String beerUser = resultSet.getString("beerUser");
			String beerName = resultSet.getString("beerName");
			Float abv = resultSet.getFloat("abv");
			Integer brandId = resultSet.getInt("brandId");
			if (resultSet.wasNull()) {
				brandId = null;
			}
			String brandUser = resultSet.getString("brandUser");
			if (resultSet.wasNull()) {
				brandUser = null;
			}
			String brandName = resultSet.getString("brandName");
			if (resultSet.wasNull()) {
				brandName = null;
			}
			Integer breweryId = resultSet.getInt("breweryId");
			String breweryUser = resultSet.getString("breweryUser");
			String breweryName = resultSet.getString("breweryName");
			String city = resultSet.getString("city");
			String country = resultSet.getString("country");
			String address = resultSet.getString("address");
			if (resultSet.wasNull()) {
				address = null;
			}
			Brewery brewery = new Brewery(breweryId, breweryUser, breweryName, address, city, country);
			Brand brand = brandId == null ? null : new Brand(brandId, brandUser, brandName, brewery);
			Beer beer = new Beer(beerId, beerUser, beerName, abv, brewery, brand);
			beers.add(beer);
		}
		return beers;
	}
	
}
