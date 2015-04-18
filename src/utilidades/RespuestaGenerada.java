package utilidades;


/**
 * Clase RespuestaGenerada.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class RespuestaGenerada {

	/**
	 * Cadena de texto.
	 */
	private String text;
	/**
	 * Booleano que indica si es correcto o no.
	 */
	private boolean correct;
	/**
	 * Cadena con el feedback.
	 */
	private String feedback;	
		
	/**
	 * Constructor de RespuestaGenerada.
	 * @param text Texto.
	 * @param correct Correcta / no correcta.
	 * @param feedback Feedback
	 */
	public RespuestaGenerada(String text, boolean correct,String feedback)
	{
		this.text=text;
		this.correct=correct;
		this.feedback=feedback;
	}
	
	/**
	 * Obtiene el  texto.
	 * @return Texto.
	 * @see setText.
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Obtiene el feedback.
	 * @return Feedback.
	 * @see setFeedback.
	 */
	public String getFeedback()
	{
		return feedback;
	}
	
	/**
	 * Obtiene si es correcta o no.
	 * @return Correcta / no correcta.
	 */
	public boolean getCorrect()
	{
		return correct;
	}
	
	/**
	 * Establece el texto.
	 * @param text Texto.
	 * @see getText.
	 */
	public void setText(String text)
	{
		this.text=text;
	}
	
	/**
	 * Establece si es correcta o no.
	 * @param correct Correcta / no correcta.
	 */
	public void setCorrecta(boolean correct)
	{
		this.correct=correct;
	}
	
	/**
	 * Establece el feedback.
	 * @param feedback Feedback.
	 * @see getFeedback.
	 */
	public void setFeedback(String feedback)
	{
		this.feedback=feedback;
	}
}
