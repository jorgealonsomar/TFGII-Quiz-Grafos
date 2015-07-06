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
	
	
	/**
	 * Devuelve el texto correspondiente a la universidad y el grado, utilizado en el Acerca de.
	 * @return Texto correspondiente a la universidad y el grado.
	 */
	public static Texto acercaDe_UniversidadYGrado(){
		return new Texto("Universidad de Burgos. Grado en Ingeniería Informática.",
				"University of Burgos. Bachelor's Degree in Informatics Engineering.");
	}
	
	
	/**
	 * Devuelve el texto correspondiente al autor, utilizado en el Acerca de.
	 * @return Texto correspondiente al autor.
	 */
	public static Texto acercaDe_Autor(){
		return new Texto("Autor: Jorge Alonso Márquez.",
				"Author: Jorge Alonso Márquez.");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a los tutores, utilizado en el Acerca de.
	 * @return Texto correspondiente a los tutores.
	 */
	public static Texto acercaDe_Tutores(){
		return new Texto("Tutores: Juan José Rodríguez Díez y Carlos López Nozal.",
				"Tutors: Juan José Rodríguez Díez & Carlos López Nozal.");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a la fecha de entrega, utilizado en el Acerca de.
	 * @return Texto correspondiente a la fecha de entrega.
	 */
	public static Texto acercaDe_FechaDeEntrega(){
		return new Texto("Entregado a julio de 2015.",
				"Submitted at date: July, 2015.");
	}
	
	
}
