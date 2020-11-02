package it.uniroma1.fabbricasemantica.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La classe implementa l'interfaccia {@link javax.servlet.Filter} e rappresenta un filtro
 * applicato alle richieste di tipo GET: queste verranno percio' prima elaborate dal filtro
 * stesso e, in caso venissero riscontrate anomalie, la richiesta verra' bloccata e l'utente
 * sara' reindirizzato verso la pagina 'Login.html'. In caso contrario la richiesta potra'
 * continuare il suo normale corso.
 * 
 * Per le specifiche e la mappatura si veda l'opportuna sezione del file 'WEB-INF/web.xml'.
 * 
 * @author Mirco Valentini 1612930
 *
 */
public class MyFilter implements Filter {

	/**
	 * Il metodo viene invocato su ogni pagina con estensione .html e controlla se nella sessione
	 * corrente e' correttamente autenticato un utente; in caso negativo si verra' reindirizzati alla
	 * pagina 'Login.html', altrimenti la richiesta verra' normalmente elaborata.
	 * 
	 * Il filtro esclude le pagina 'Login.html' e 'Signup.html' poiche' esse non richiedono che l'utente
	 * sia autenticato al sistema.
	 *
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
	{	
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    HttpSession session = request.getSession();
	    
	    String loginURL = request.getContextPath() + "/Login.html";
	    String signupURL = request.getContextPath() + "/Signup.html";
    
	    boolean loggedIn = session != null && session.getAttribute("username") != null;
	    boolean loginRequest = loginURL.equals(request.getRequestURI()) || signupURL.equals(request.getRequestURI());
    
	    if (loggedIn || loginRequest) 
	    {   	
	        chain.doFilter(request, response);
	        return;
	    } 
	    else 
	    {	       	
		   	response.sendRedirect(request.getContextPath() + "/Login.html");
		}
     }
}