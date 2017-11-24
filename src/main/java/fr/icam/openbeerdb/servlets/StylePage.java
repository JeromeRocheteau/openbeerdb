package fr.icam.openbeerdb.servlets;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class StylePage extends StyleList {

	private static final long serialVersionUID = 25L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String offset = request.getParameter("offset");
		String length = request.getParameter("length");
		statement.setInt(1, Integer.valueOf(offset));
		statement.setInt(2, Integer.valueOf(length));
	}
	
}
