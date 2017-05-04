package fr.icam.openbeerdb.servlets;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class StatsLocal extends StatsGlobal {

	private static final long serialVersionUID = 18L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String filter = request.getParameter("filter");
		statement.setString(1, filter);
	}

}
