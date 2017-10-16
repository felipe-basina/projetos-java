package org.baeldung.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

import org.baeldung.util.CaptchaContextHolder;

public class CaptchaContextFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
	    throws IOException, ServletException {

	if (request instanceof HttpServletRequest) {

	    final HttpServletRequest req = (HttpServletRequest) request;

	    System.out.println("\n ########## req.getPathInfo() [" + req.getPathInfo() + "]"
		    + "\n req.getContextPath(): " + req.getContextPath() + "\n req.getRequestURI(): "
		    + req.getRequestURI() + "\n req.getServletPath(): " + req.getServletPath());

	    if (req.getServletPath().contains("jpg")) {
		chain.doFilter(request, response);
		return;
	    }

	    final String captcha = req.getParameter("valorCaptcha");
	    System.out.println("\n ######### Captcha digitado: " + captcha);

	    CaptchaContextHolder.setValue(captcha);

	    Captcha sessionCaptcha = null;

	    final HttpSession session = req.getSession();
	    if (session != null) {
		sessionCaptcha = (Captcha) req.getSession().getAttribute(Captcha.NAME);
	    }

	    if (sessionCaptcha == null) {
		System.out.println("\n ######### ATENCAO: sessionCaptcha nulo!!!");
	    } else {
		System.out.println("\n ######### Captcha desafio: " + sessionCaptcha.getAnswer());

		boolean captchaOk = false;
		if (captcha != null) {
		    captchaOk = sessionCaptcha.isCorrect(captcha);
		    CaptchaContextHolder.setResponse(sessionCaptcha.getAnswer());
		}
		System.out.println("\n ######### Captcha OK? " + captchaOk);

		// Define verificacao do captcha no contexto
		CaptchaContextHolder.setValid(captchaOk);
	    }
	}
	chain.doFilter(request, response);
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
    }

}