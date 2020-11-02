package it.uniroma1.fabbricasemantica.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * La classe astratta rappresenta una generica servlet per l'elaborazione delle richieste lato client
 * e server. I metodi {@link #doGet(HttpServletRequest request, HttpServletResponse response)} e
 * {@link #doPost(HttpServletRequest request, HttpServletResponse response)} sono stati uniti in un
 * unico metodo {@link #doSomething(HttpServletRequest request, HttpServletResponse response)} per
 * semplificare il lavoro delle servlet.
 *
 */
public abstract class BaseServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6784574842574L;
       
	/**
	 * Metodo per l'elaborazione delle richieste di tipo 'Get'; richiama il metodo 
	 * {@link #doSomething(HttpServletRequest request, HttpServletResponse response)} per elaborare
	 * la richiesta.
	 * 
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSomething(request, response);
	}

	/**
	 * Metodo per l'elaborazione delle richieste di tipo 'Post'; richiama il metodo 
	 * {@link #doSomething(HttpServletRequest request, HttpServletResponse response)} per elaborare
	 * la richiesta.
	 * 
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSomething(request, response);
	}

	/**
	 * Metodo che implementa la logica richiesta nelle specifiche del backend.
	 * 
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * */
	protected abstract void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
}
