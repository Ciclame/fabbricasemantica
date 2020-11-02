package it.uniroma1.fabbricasemantica.servlet.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma1.fabbricasemantica.data.DataProvider;
import it.uniroma1.fabbricasemantica.data.StandardDataProvider;
import it.uniroma1.fabbricasemantica.data.StandardTask;
import it.uniroma1.fabbricasemantica.data.Task;
import it.uniroma1.fabbricasemantica.servlet.BaseServlet;

/**
 * La classe estende la classe astratta {@link it.uniroma1.fabbricasemantica.servlet.BaseServlet}
 * e viene invocata all'indirizzo 'nextExample.jsp'.
 *
 */
@WebServlet(name="NewExampleServlet", urlPatterns="/nextExample.jsp")
public class NewExampleServiceServlet extends BaseServlet {
	private static final long serialVersionUID = 8783416660707020469L;
	
	private Map<String, Task> tasks;
	private DataProvider<String> dataProvider;
	 
	
	/**
	 * Costruttore della classe: inizializza i campi {@link NewExampleServiceServlet#tasks} e 
	 * {@link NewExampleServiceServlet#dataProvider} ottenendo un oggetto di tipo 
	 * {@link it.uniroma1.fabbricasemantica.data.StandardDataProvider} per il primo e una mappa
	 * da {@link java.lang.String} a {@link it.uniroma1.fabbricasemantica.data.Task} per il 
	 * secondo.
	 * 
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		dataProvider = new StandardDataProvider();
		tasks = Arrays.stream(StandardTask.values()).collect(Collectors.toMap(Task::getTaskID, s -> s));		
	}

	/**
	 * Il metodo viene invocato dopo la chiamata alla servlet all'indirizzo 'nextExample.jsp' e scrive
	 * sull'oggetto response un task casuale ottenuto da {@link it.uniroma1.fabbricasemantica.data.StandardTask}
	 *
	 * @param  request  un oggetto di tipo HttpServletRequest che incapsula la richiesta del client
	 * @param  response  un oggetto di tipo HttpServletResponse che incapsula la risposta del server
	 * @throws  ServletException  eccezione generica lanciata dalla servlet in caso di problemi
	 * @throws  IOException  eccezione lanciata in caso di problemi in un'operazione I/O
	 * 
	 */
	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sTask = (String) request.getParameter("task");
		Task task = tasks.get(sTask);
		String json = dataProvider.getData(task);
		response.getWriter().write(json);
	}


}
