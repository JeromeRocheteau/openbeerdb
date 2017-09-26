package fr.icam.openbeerdb.servlets;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class StylePage extends StyleList {

	private static final long serialVersionUID = 16L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Integer offset = Integer.valueOf(request.getParameter("offset"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		statement.setInt(1, offset);
		statement.setInt(2, length);
	}
	
}
