package it.uniroma1.fabbricasemantica.servlet.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.fabbricasemantica.data.StandardTask;
import it.uniroma1.fabbricasemantica.data.Task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma1.fabbricasemantica.servlet.BaseServlet;

/**
 * La classe fornisce un'implementazione per la classe astratta BaseServlet ed espone il metono
 * {@link #doSomething(HttpServletRequest request, HttpServletResponse response)} per ottenere
 * un nuovo task casuale preso dall'enum {@link it.uniroma1.fabbricasemantica.data.StandardTask} 
 * 
 * @author Mirco Valentini 1612930
 *
 */
@WebServlet(name="RandomTaskServlet", urlPatterns="/randomTask.jsp")
public class RandomTaskServlet extends BaseServlet 
{
	private static final long serialVersionUID = 8783416660707020469L;
	
	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'randomTask.jsp' e,
	 * preso il contenuto dell'enum {@link it.uniroma1.fabbricasemantica.data.StandardTask} , 
	 * sceglie un task casuale e reindirizza il chiamante alla pagina html associata.
	 *
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Task> tasks = Arrays.stream(StandardTask.values()).collect(Collectors.toList());
		String s[] = tasks.get((int)(Math.random() * tasks.size())).toString().split("_");
		String output1 = s[0].substring(0, 1).toUpperCase() + s[0].substring(1).toLowerCase()
				+s[1].substring(0, 1).toUpperCase() + s[1].substring(1).toLowerCase();		
		response.sendRedirect(output1 + ".html");
	}
}