package it.uniroma1.fabbricasemantica.data;


/**
 * L'enum incapsula al suo intero tutti i tipi di @link {it.uniroma1.fabbricasemantica.data.Task}
 * utilizzati dalla piattaforma.
 *
 */
public enum StandardTask implements Task 
{
	TRANSLATION_ANNOTATION,
	WORD_ANNOTATION,
	DEFINITION_ANNOTATION,
	SENSE_ANNOTATION,
	TRANSLATION_VALIDATION,
	SENSE_VALIDATION,
	MY_ANNOTATION
}
