package it.uniroma1.fabbricasemantica.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma1.fabbricasemantica.servlet.BaseServlet;

/**
 * La classe estende la classe astratta {@link it.uniroma1.fabbricasemantica.servlet.BaseServlet}
 * ed elabora i dati ottenuti dalla pagina html associata attraverso il metodo {@link #doSomething(
 * HttpServletRequest request, HttpServletResponse response)}; viene invocata all'indirizzo 
 * 'logout.jsp'.
 * 
 * @author Mirco Valentini 1612930
 *
 */
@WebServlet(name="LogoutServlet", urlPatterns="/logout.jsp")
public class LogoutServlet extends BaseServlet 
{
	private static final long serialVersionUID = 8484501789787L;

	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'logout.jsp' e, presa
	 * la sessione utente attuale, effettua la disconnessione  dell'utente dalla piattaforma. Infine
	 * si verra' reindirizzati alla pagina di login.
	 * 
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
	    session.setAttribute("username", null);
		session.invalidate();
	    response.sendRedirect(request.getContextPath() + "/Login.html");
	}

}
