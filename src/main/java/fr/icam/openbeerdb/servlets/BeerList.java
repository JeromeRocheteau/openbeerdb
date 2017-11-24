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
import fr.icam.openbeerdb.entities.Style;

public class BeerList extends JdbcQueryServlet<List<Beer>> {

	private static final long serialVersionUID = 34L;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Beer> beers = this.doProcess(request);
		System.out.println("[OpenBeer DB] beer size = " + beers.size());
		for (Beer beer : beers) {
			System.out.println("[OpenBeer DB] beer id = " + beer.getId());
			request.setAttribute("beerId", beer.getId());
			this.doCall(request, response, "feature-list");
			List<Style> styles = (List<Style>) request.getAttribute("styles");
			System.out.println("[OpenBeer DB] style size = " + styles.size());
			beer.setStyles(styles);
		}
		this.doWrite(beers, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception { }

	@Override
	protected List<Beer> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Beer> beers = new LinkedList<Beer>();
		while (resultSet.next()) {
			Integer beerId  = resultSet.getInt("beerId");
			String beerName = resultSet.getString("beerName");
			Float abv = resultSet.getFloat("abv");
			Integer brandId = resultSet.getInt("brandId");
			String brandName = resultSet.getString("brandName");
			Integer breweryId = resultSet.getInt("breweryId");
			String breweryName = resultSet.getString("breweryName");
			String city = resultSet.getString("city");
			String country = resultSet.getString("country");
			String address = resultSet.getString("address");
			if (resultSet.wasNull()) {
				address = null;
			}
			Brewery brewery = new Brewery(breweryId, breweryName, address, city, country);
			Brand brand = brandId == null ? null : new Brand(brandId, brandName, brewery);
			Beer beer = new Beer(beerId, beerName, abv, brewery, brand);
			beers.add(beer);
		}
		return beers;
	}
	
}
