package it.uniroma1.fabbricasemantica.servlet.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma1.fabbricasemantica.servlet.BaseServlet;
import it.uniroma1.fabbricasemantica.util.Database;

/**
 * La classe estende la classe astratta {@link it.uniroma1.fabbricasemantica.servlet.BaseServlet}
 * ed elabora i dati ottenuti dalla pagina html associata attraverso il metodo {@link #doSomething(
 * HttpServletRequest request, HttpServletResponse response)}; viene invocata all'indirizzo 
 * 'wordAnnotation.jsp'.
 * 
 * @author Mirco Valentini 1612930
 *
 */
@WebServlet(name = "TaskWordAnnotationServlet", urlPatterns = "/wordAnnotation.jsp")
public class TaskWordAnnotationServlet extends BaseServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'wordAnnotation.jsp' 
	 * e:
	 * <ul>
	 * <li> se e' stato premuto il pulsante 'Skip', restituisce un nuovo task dello stesso tipo
	 * <li> se e' stato premuto il pulsante 'Next' ed e' stato compilato correttamente il task, allora
	 * 		la risposta verra' mandata al server e salvata. Infine l'utente verra' reindirizzato ad un
	 * 		nuovo task.
	 * <li> altrimenti, se il task non e' stato compilato correttamente e il pulsante 'Next' e' stato
	 *		premuto, verra' visualizzato un messaggio d'errore e il task sara' ricaricato. 
	 * </ul>
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("skip") != null)
			response.sendRedirect("nextExample.jsp");
		if(request.getParameter("next") != null && request.getParameter("traduzione") != "")
		{
			List<Map<String, Object>> result = Database.executeQuery("INSERT INTO usertask(username, task, risultato) VALUES('"
				+ request.getSession().getAttribute("username") + "', 'wordannotation', '" 
				+ "Termine tradotto associato: " + request.getParameter("traduzione") + "') returning username");
			response.sendRedirect("randomTask.jsp");
		}
		else 
		{			
			PrintWriter out = response.getWriter();
		    out.println("<script type=\"text/javascript\">");
			out.println("alert('Fornire il termine tradotto in italiano associato alla frase prima di premere il pulsante next');");
			out.println("location='WordAnnotation.html';");
			out.println("</script>");
		}
	}

}
