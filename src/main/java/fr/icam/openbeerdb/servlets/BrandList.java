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

import fr.icam.openbeerdb.entities.Brand;
import fr.icam.openbeerdb.entities.Brewery;

public class BrandList extends JdbcQueryServlet<List<Brand>> {

	private static final long serialVersionUID = 14L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Brand> beers = this.doProcess(request);
		this.doWrite(beers, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String brewery = request.getParameter("brewery");
		statement.setInt(1, Integer.valueOf(brewery));
	}

	@Override
	protected List<Brand> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Brand> brands = new LinkedList<Brand>();
		while (resultSet.next()) {
			Integer brandId = resultSet.getInt("brandId");
			String brandUser = resultSet.getString("brandUser");
			String brandName = resultSet.getString("brandName");
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
			brands.add(brand);
		}
		return brands;
	}
	
}
