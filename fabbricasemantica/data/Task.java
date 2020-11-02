package it.uniroma1.fabbricasemantica.data;


/**
 * L'interfaccia rappresenta un Task generico
 *
 */
public interface Task 
{

	default String getTaskID() 
	{
		return toString();
	}
	
}
