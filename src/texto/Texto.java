package texto;

import sistema.TFGII;
import util.Idioma;

public class Texto {
	
	private String textoSpa = "";
	private String textoEng = "";
	
	/** Constructor de la clase con textos diferenciados para cada idioma */
	public Texto(String textoSpa, String textoEng){
		this.textoSpa = textoSpa;
		this.textoEng = textoEng;
	}
	
	
	/** Constructor de la clase con un texto universal para todo idioma */
	public Texto(String textoUniversal){
		this.textoSpa = textoUniversal;
		this.textoEng = textoUniversal;
	}
	
	
	/** Devuelve el texto en idioma español */
	public String esp(){
		return textoSpa;
	}
	
	
	/** Devuelve el texto en idioma inglés */
	public String eng(){
		return textoEng;
	}
	
	
	/** Devuelve el texto en el idioma dado */
	public String getString(Idioma idioma){
		if(idioma == Idioma.ESP){
			return esp();
		} else if(idioma == Idioma.ENG){
			return eng();
		} else {
			TFGII.LOGGER.warning("Idioma seleccionado incorrectamente");
			return null;
		}
	}
	
	
	/** Concatena el texto dado a continuación de éste */
	public void concatenar(Texto textoAConcatenar){
		textoSpa += textoAConcatenar.esp();
		textoEng += textoAConcatenar.eng();
	}
	
	
	@Override
	public String toString(){
		return "[Texto] " + esp();
	}
	
	
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

	
	/** Retira algunos de los caracteres ajenos a UTF-8.
	 *  Esto evita los problemas que se dan al importar en moodle un archivo que emplee
	 *  dichos caracteres en su título. */
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
	
}
