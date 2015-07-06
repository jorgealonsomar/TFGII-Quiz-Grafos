package texto;

/**
 * Textos empleados por los distintos elementos de la barra de menú.
 * @author Jorge Alonso Márquez
 */
public class Textos_BarraMenu {
	
	/**
	 * Devuelve el texto del menú Archivo.
	 * @return Texto del menú Archivo.
	 */
	public static Texto menuArchivo(){
		return new Texto("Archivo", "File");
	}
	
	
	/**
	 * Deuvelve el texto de la opción Salir del menú Archivo.
	 * @return Texto de la opción Salir del menú Archivo.
	 */
	public static Texto menuArchivo_Salir(){
		return new Texto("Salir", "Exit");
	}
	
	
	/**
	 * Devuelve el texto de la opción Importar Semilla del menú Archivo.
	 * @return Texto de la opción Importar Semilla del menú Archivo.
	 */
	public static Texto menuArchivo_ImportarSemilla(){
		return new Texto("Importar Pregunta", "Import Question");
	}
	
	
	/**
	 * Devuelve el texto de la opción Acerca de del menú Ayuda.
	 * @return Texto de la opción Acerca de del menú Ayuda.
	 */
	public static Texto menuAyuda_AcercaDe(){
		return new Texto("Acerca de", "About");
	}
	
	
	/**
	 * Devuelve el texto utilizado para la petición de introducir la semilla.
	 * @return Petición de introducir la semilla.
	 */
	public static Texto introduzcaSemilla(){
		return new Texto("Introduzca la semilla de la pregunta: ", "Insert the seed of the question: ");
	}
	
	
	/**
	 * Devuelve el texto del menú Ayuda.
	 * @return Texto del menú Ayuda.
	 */
	public static Texto menuAyuda(){
		return new Texto("Ayuda", "Help");
	}
}
