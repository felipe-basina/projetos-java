package br.com.spring.framework.web.security.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class ContatoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1642165653366366341L;

	@Autowired
	private ContatoComponent componente;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Necessario para injetar dependencias no servlet
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		componente.register();
		componente.getAll();

		RequestDispatcher dispatcher = req.getRequestDispatcher("ok.jsp");
		dispatcher.forward(req, resp);
	}
}
