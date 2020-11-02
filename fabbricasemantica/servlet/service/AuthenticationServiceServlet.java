package it.uniroma1.fabbricasemantica.servlet.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma1.fabbricasemantica.servlet.BaseServlet;


/**
 * La classe estende la classe astratta {@link it.uniroma1.fabbricasemantica.servlet.BaseServlet}
 * e viene invocata all'indirizzo 'isLoggedIn.jsp'.
 */
@WebServlet(name="AuthenticationServiceServlet", urlPatterns="/isLoggedIn.jsp")
public class AuthenticationServiceServlet extends BaseServlet 
{
	private static final long serialVersionUID = 8484501789787L;
	
	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'isLoggedId.jsp' e
	 * controlla se l'utente e' autenticato al sistema, scrivendo il risultato nell'oggetto response.
	 *
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean isLoggedIn = session.getAttribute("username") != null;
		response.getWriter().write(isLoggedIn + "");
	}

}
