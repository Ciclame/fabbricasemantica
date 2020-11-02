package it.uniroma1.fabbricasemantica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe rappresenta un database relazionale PostgreSQL per l'elaborazione dei dati
 * della piattaforma; espone un unico metodo {@link #executeQuery(String query)} per
 * effettuare interrogazioni sulla base di dati. 
 * 
 * Il database e' composto da 3 tabelle:
 * <ol>
 * 	<li> Users(id, username, passwordh) che contiene i dati degli utenti registrati; per
 * 		 ragioni di sicurezza, le password vengono codificate tramite funzione di hashing
 *  	 salato MD5 (tempo medio di decodifica per password contenenti caratteri in [A-Z][a-z][0-9]
 *  	 circa 3 anni) prima di essere salvata
 * 
 *  <li> Tasks(tipo) che contiene i nomi dei task supportati dalla piattaforma
 *  
 *  <li> Usertask(username, id, task, risultato) che contiene in 'risultato' i risultati 
 *  	 dei 'task' task dell'utente con 'username' username
 * </ol>
 * 
 * @author Mirco Valentini 1612930
 *
 */
public class Database 
{
	/**
	 * Driver PostgreSQL per la libreria JDBC
	 */
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	
	/**
	 * URL in locale del database
	 */
	static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	/**
	 * Username per l'accesso al database
	 */
	static final String USER = "postgres";
	
	/**
	 * Password per l'accesso al database
	 */
	static final String PASS = "postgres";

	/**
	 * Il metodo prende in input una stringa, opportunamente formattata, che rappresenta
	 * l'interrogazione che verra' inoltrata alla base di dati. L'eventuale risultato verra'
	 * restituito in una {@link List} di {@link Map} da oggetti di tipo {@link String} (che 
	 * rappresentano il nome della colonna attuale) a {@link Object} (che rappresentano il 
	 * contenuto della colonna); ogni oggetto nella lista corrispondera', quindi, ad un record
	 * del risultato dell'interrogazione.
	 * 
	 * @param  query  l'interrogazione da sottoporre alla base di dati
	 * @return  un oggetto di tipo {@link List} contenente il risultato dell'interrogazione
	 */
	public static List<Map<String, Object>> executeQuery(String query) 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		try 
		{
			Class.forName(JDBC_DRIVER);
		
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		    Map<String, Object> row = null;
		    ResultSetMetaData metaData = rs.getMetaData();
		    Integer columnCount = metaData.getColumnCount();

		    while (rs.next()) 
		    {
		        row = new HashMap<String, Object>();
		        for (int i = 1; i <= columnCount; i++) 
		        {
		            row.put(metaData.getColumnName(i), rs.getObject(i));
		        }
		        resultList.add(row);
		    }
			stmt.close();
			conn.close();
		} 
		
		catch (SQLException se) 
		{ 
			se.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		finally 
		{
			try 
			{
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException se2) {}
			
			try 
			{
				if(conn != null)
					conn.close();
			} 
			catch (SQLException se) 
			{
				se.printStackTrace();
			}
		}
		return resultList;
	}
}
