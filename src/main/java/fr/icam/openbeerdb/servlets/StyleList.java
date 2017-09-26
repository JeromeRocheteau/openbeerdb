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

import fr.icam.openbeerdb.entities.Style;

public class StyleList extends JdbcQueryServlet<List<Style>> {

	private static final long serialVersionUID = 15L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Style> styles = this.doProcess(request);
		this.doWrite(styles, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception { }

	@Override
	protected List<Style> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Style> items = new LinkedList<Style>();
		while (resultSet.next()) {
			Integer id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Integer styleId = resultSet.getInt("styleId");
			if (resultSet.wasNull()) {
				styleId = null;
			}
			String styleName = resultSet.getString("styleName");
			if (resultSet.wasNull()) {
				styleName = null;
			}
			Style style = styleId == null ? null : new Style(styleId, styleName, null);
			Style item = new Style(id, name, style);
			items.add(item);
		}
		return items;
	}
	
}
