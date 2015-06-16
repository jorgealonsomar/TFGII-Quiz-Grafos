package texto;

public class Textos_BarraMenu {
	
	/** Texto del menú Archivo */
	public static Texto menuArchivo(){
		return new Texto("Archivo", "File");
	}
	
	
	/** Texto de la opción Salir del menú Archivo */
	public static Texto menuArchivo_Salir(){
		return new Texto("Salir", "Exit");
	}
	
	
	/** Texto de la opción Importar Semilla del menú Archivo */
	public static Texto menuArchivo_ImportarSemilla(){
		return new Texto("Importar Semilla", "Import Seed");
	}
	
	
	/** Texto de la opción Importar Semilla del menú Archivo */
	public static Texto introduzcaSemilla(){
		return new Texto("Introduzca la semilla: ", "Insert the seed: ");
	}
	
	
	/** Texto del menú Ayuda */
	public static Texto menuAyuda(){
		return new Texto("Ayuda", "Help");
	}
}
