package texto;

/**
 * Texto usado en alguna parte de la aplicación. Contiene la cadena tanto en espanol
 * como en inglés, para poder usarla de una u otra forma según se necesite.
 * @author Jorge Alonso Márquez
 */
public class Texto {
	
	/**
	 * Versión en espanol del texto.
	 */
	private String textoSpa = "";
	
	/**
	 * Versión en inglés del texto.
	 */
	private String textoEng = "";
	
	
	/**
	 * Constructor de la clase con textos diferenciados para cada idioma
	 * @param textoSpa
	 *            Versión en espanol del texto.
	 * @param textoEng
	 *            Versión en inglés del texto.
	 */
	public Texto(String textoSpa, String textoEng){
		this.textoSpa = textoSpa;
		this.textoEng = textoEng;
	}
	
	
	/**
	 * Constructor de la clase con un texto universal para todo idioma.
	 * @param textoUniversal
	 *            Versión universal del texto.
	 */
	public Texto(String textoUniversal){
		this.textoSpa = textoUniversal;
		this.textoEng = textoUniversal;
	}
	
	
	/**
	 * Devuelve el texto en idioma espanol.
	 * @return Versión en espanol del texto.
	 */
	public String esp(){
		return textoSpa;
	}
	
	
	/**
	 * Devuelve el texto en idioma inglés.
	 * @return Versión en inglés del texto.
	 */
	public String eng(){
		return textoEng;
	}
	
	
	/**
	 * Devuelve el texto en el idioma indicado.
	 * @param idioma
	 *            Idioma en el que se requiere el texto.
	 * @return Texto en el idioma indicado.
	 */
	public String getString(Idioma idioma){
		if(idioma == Idioma.ENG){
			return eng();
		} else {
			return esp();
		}
	}
	
	
	/**
	 * Concatena el texto dado a continuación de éste.
	 * @param textoAConcatenar
	 *            Texto que se quiere a continuación de éste.
	 */
	public void concatenar(Texto textoAConcatenar){
		textoSpa += textoAConcatenar.esp();
		textoEng += textoAConcatenar.eng();
	}
	
	
	/**
	 * Concatena a continuación de este texto los campos dados. Incluye las
	 * etiquetas correspondientes a una fila de una tabla xml, con cada campo en
	 * una de sus columnas.
	 * @param campos
	 *            Campos correspondientes a cada columna de esa fila de la
	 *            tabla.
	 */
	public void concatenarFilaDeTabla(Texto... campos) {
		concatenar(new Texto("\n\t<tr>"));
		for(Texto campo : campos){
			concatenar(new Texto("\n\t\t<td>"));
			concatenar(campo);
			concatenar(new Texto("</td>"));
		}
		concatenar(new Texto("\n\t</tr>"));
	}
	
	
	/**
	 * Tranforma los caracteres de una cadena dada de forma que las vocales tildadas y 
	 * las enes se tranformen en sus equivalentes válidos para xml.
	 * @param cadena Cadena a transformar.
	 * @return Cadena transformada.
	 */
	public static String adaptarCaracteresAXml(String cadena){
		cadena = cadena.replace("á", "&aacute");
		cadena = cadena.replace("é", "&eacute");
		cadena = cadena.replace("í", "&iacute");
		cadena = cadena.replace("ó", "&oacute");
		cadena = cadena.replace("ú", "&uacute");
		
		cadena = cadena.replace("Á", "&Aacute");
		cadena = cadena.replace("É", "&Eacute");
		cadena = cadena.replace("Í", "&Iacute");
		cadena = cadena.replace("Ó", "&Oacute");
		cadena = cadena.replace("Ú", "&Uacute");
		
		cadena = cadena.replace("ñ", "&ntilde");
		cadena = cadena.replace("Ñ", "&Ntilde");
		
		return cadena;
	}

	
	/**
	 * Retira algunos de los caracteres ajenos a UTF-8.
	 * Esto evita los problemas que se dan al importar en moodle un archivo que emplee
	 * dichos caracteres en su título, ya que los elementos tales como "&aacute" no se aceptan
	 * como válidos en el apartado de Título.
	 * @param cadena Cadena a transformar.
	 * @return Cadena transformada.
	 */
	public static String quitarCaracteresExtranos(String cadena) {
		cadena = cadena.replace("á", "a");
		cadena = cadena.replace("é", "e");
		cadena = cadena.replace("í", "i");
		cadena = cadena.replace("ó", "o");
		cadena = cadena.replace("ú", "u");
		
		cadena = cadena.replace("Á", "A");
		cadena = cadena.replace("É", "E");
		cadena = cadena.replace("Í", "I");
		cadena = cadena.replace("Ó", "O");
		cadena = cadena.replace("Ú", "U");
		
		cadena = cadena.replace("ñ", "n");
		cadena = cadena.replace("Ñ", "N");
		
		return cadena;
	}
	
	
	/**
	 * Anade a la cadena por la izquierda una cantidad de los caracteres dados tal que la cadena
	 * alcance el tamano objetivo.
	 * @param cadena
	 *            Cadena a modificar.
	 * @param caracter
	 *            Carácter a anadir a la cadena.
	 * @param tamanoObjetivo
	 *            Tamano con el que ha de quedar la cadena.
	 */
	public static String anadirCaracteresPorLaIzquierda(String cadena, char caracter, int tamanoObjetivo){
		while( (tamanoObjetivo - cadena.length()) > 0) {
			cadena = caracter + cadena;
			}

		return cadena;
	}
	
	
}
