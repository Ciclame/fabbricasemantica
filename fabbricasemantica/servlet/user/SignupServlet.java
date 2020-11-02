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
 * 'signup.jsp'.
 * 
 * @author Mirco Valentini 1612930
 *
 */
@WebServlet(name="SignupServlet", urlPatterns="/signup.jsp")
public class SignupServlet extends BaseServlet 
{
	private static final long serialVersionUID = 8484501789787L;

	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'signup.jsp' e valida
	 * la form associata alla pagina 'Signup.html' controllando che tutti i campi della form associata
	 * siano stati compilati in maniera corretta. 
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
		String confermaPassowrd = request.getParameter("confermaPassword");
		String livelloInglese = request.getParameter("radioInglese");
		String livelloItaliano = request.getParameter("radioItaliano");
		
		PrintWriter out = response.getWriter();
		if(username.isEmpty() || password.isEmpty() || confermaPassowrd.isEmpty() || livelloInglese == null
				|| livelloItaliano == null)
		{	
		    out.println("<script type=\"text/javascript\">");
			out.println("alert('Compila correttamente i campi');");
			out.println("location='Signup.html';");
			out.println("</script>");
			return;
		}
		if(!password.equals(confermaPassowrd))
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Le password inserite non corrispondono');");
			out.println("location='Signup.html';");
			out.println("</script>");
			return;
		}
		
		List<Map<String, Object>> result = Database.executeQuery("SELECT EXISTS(SELECT 1 FROM fabbricasemantica.users WHERE username = '"
				+ username +"') as result");
		for(Map<String, Object> map : result)
		{
			if(map.get("result").equals(true))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('L\\x27username inserito esiste gia\\x27');");
				out.println("location='Signup.html';");
				out.println("</script>");
				return;
			}
		}
		
		Database.executeQuery("INSERT INTO fabbricasemantica.users(username, passwordh)" + 
				"VALUES ('" + username + "', crypt('" + password + "', gen_salt('md5'))) returning username");
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		response.sendRedirect("Home.html");
	}

}
