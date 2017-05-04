package fr.icam.openbeerdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class Stats extends JdbcServlet {

	private static final long serialVersionUID = 19L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer level = Integer.valueOf(request.getParameter("level"));
		if (level == 0) {
			this.doCall(request, response, "stats-global");			
		} else if (level == 1) {
			this.doCall(request, response, "stats-local");			
		} else if (level == 2) {
			this.doCall(request, response, "stats-initial");
		}
	}
	
}
