package modelo;

/**
 * Excepción relativa a las semillas.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class SemillaException extends Exception {
	
	/**
	 * Mensaje de error mostrado.
	 */
	String mensajeDelError;
	
	
	/**
	 * Constructor de la clase.
	 * @param mensajeDelError
	 *            Mensaje de error a mostrar.
	 */
	public SemillaException(String mensajeDelError) {
		this.mensajeDelError = mensajeDelError;
	}
	
	
	/**
	 * Devuelve el mensaje de error.
	 * @return Mensaje de error.
	 */
	public String getMensajeDelError() {
		return mensajeDelError;
	}

}
