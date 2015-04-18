package utilidades;

/**
 * Clase a la que se recurre para completar las diferentes partes de las preguntas.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class FormatosPreguntas {

	/**
	 * Guarda la pregunta en formato GIFT.
	 */
	private String giftFormat;
	/**
	 * Guarda la pregunta en formato xml.
	 */
	private String xmlFormat;
	/**
	 * Guarda el enunciado de la pregunta.
	 */
	public static String enunciado;	
	/**
	 * Guarda la respuesta.
	 */
	private String respuesta;
	
	/**
	 * Constructor de FormatosPreguntas.
	 */
	public FormatosPreguntas()
	 {
		 giftFormat="";
		 xmlFormat="";
		 enunciado="";
		 respuesta="";
	 }
	/**
	 * Añade texto a la cadena con formato GIFT.
	 * @param gift Cadena con formato GIFT.
	 * @param texto Texto a añadir.
	 * @see getGift.
	 */
	public void setGift(String gift,String texto)
	{
		giftFormat=gift+texto;
	}
	/**
	 * Añade texto a la cadena con formato xml.
	 * @param xml Cadena con formato xml.
	 * @param texto Texto a añadir.
	 * @see getXml.
	 */
	public void setXml(String xml,String texto)
	{
		xmlFormat=xml+texto;
	}
	/**
	 * Añade texto a la cadena de enunciado.
	 * @param question Cadena que contiene el enunciado.
	 * @param texto Texto a añadir.
	 * @see getEnunciado.
	 */
	public void setEnunciado(String question,String texto)
	{
		enunciado=question+texto;
	}
	/**
	 * Añade texto a la cadena de la respuesta.
	 * @param resp Cadena que contiene la respuesta.
	 * @param texto Texto a añadir.
	 * @see getRespuesta
	 */
	public void setRespuesta(String resp,String texto)
	{
		respuesta=resp+texto;
	}
	/**
	 * Devuelve el contenido de la cadena giftFormat.
	 * @return Cadena giftFormat.
	 * @see setGift.
	 */
	public String getGift()
	{
		return giftFormat;
	}
	/**
	 * Devuelve el contenido de la cadena xmlFormat.
	 * @return Cadena xmlFormat.
	 * @see setXml.
	 */
	public String getXml()
	{
		return xmlFormat;
	}
	/**
	 * Devuelve el contenido de la cadena enunciado.
	 * @return Cadena enunciado.
	 * @see setEnunciado.
	 */
	public String getEnunciado()
	{
		return enunciado;
	}
	/**
	 * Devuelve el contenido de la cadena respuesta.
	 * @return respuesta.
	 * @see setRespuesta.
	 */
	public String getRespuesta()
	{
		return respuesta;
	}
}
