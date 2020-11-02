package it.uniroma1.fabbricasemantica.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma1.fabbricasemantica.servlet.BaseServlet;
import it.uniroma1.fabbricasemantica.util.Database;

/**
 * La classe estende la classe astratta {@link it.uniroma1.fabbricasemantica.servlet.BaseServlet}
 * ed elabora i dati ottenuti dalla pagina html associata attraverso il metodo {@link #doSomething(
 * HttpServletRequest request, HttpServletResponse response)}; viene invocata all'indirizzo 
 * 'login.jsp'.
 * 
 * @author Mirco Valentini 1612930
 *
 */
@WebServlet(name="LoginServlet", urlPatterns="/login.jsp")
public class LoginServlet extends BaseServlet 
{
	private static final long serialVersionUID = 8484501789787L;

	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'login.jsp' e valida
	 * la form associata alla pagina 'Login.html' controllando che i dati dell'utente che si vuole
	 * autenticare siano presenti nella base di dati, effettuando un'interrogazione sulla stessa.
	 * 
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
		String username = request.getParameter("username");		
		String password = request.getParameter("password");
				
		List<Map<String, Object>> result = Database.executeQuery("SELECT passwordh = crypt('" + password + "', passwordh) as Result FROM fabbricasemantica.users "
				+ "WHERE username = '" + username + "'");
		
		for(Map<String, Object> map : result)
		{
			if(map.get("result").equals(true))
			{
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				response.sendRedirect("Home.html");
			}
		}
		
		PrintWriter out = response.getWriter();
	    out.println("<script type=\"text/javascript\">");
		out.println("alert('La coppia username-password e' inesistente');");
		out.println("location='Login.html';");
		out.println("</script>");		
	}
}
