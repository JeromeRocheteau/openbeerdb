package fr.icam.openbeerdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class PrincipalRole extends JdbcServlet {

	private static final long serialVersionUID = 51L;

	private String role;
	
	@Override
	public void init() throws ServletException {
		super.init();
		role = this.getInitParameter("role");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doWrite(request.isUserInRole(role), response.getWriter());
	}

}
