package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Clase con funciones estáticas relacionadas escritura en disco.
 * @author Jorge
 */
public class GestorIO {
	
	/**
	 * Crea un nuevo directorio en la ruta indicada.
	 * @param directorio Ruta donde construir el nuevo directorio.
	 */
	public static void makeDirectorio(File directorio) {
		if (!directorio.exists()) {
			directorio.mkdirs();
			System.out.println("Directorio " + directorio + " creado.");
		}
	}
	
	
	/**
	 * Escribe texto en un archivo.
	 * @param archivo Fichero donde se escribirá el texto.
	 * @param contenido Texto a escribir.
	 */
	public static void escribirEnArchivo(File archivo, String contenido) {
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(
				new FileWriter(archivo, true)));) {
			writer.write(contenido);
			System.out.println("[GestorIO] Escribiendo en el archivo " + archivo);
		} catch (IOException e) { }
	}
	
	
	/**
	 * Elimina el archivo indicado.
	 * @param archivo Archivo a eliminar del disco.
	 */
	public static void eliminarArchivo(File archivo){
	    if (archivo.delete())
	    	System.out.println("[GestorIO] Eliminado el archivo " + archivo + ".");
	     else
	    	 System.out.println("[GestorIO] El archivo " + archivo + " no pudo ser eliminado.");
		
	}
	
	
	/**
	 * Construye una cadena con la fecha y hora actuales, separadas por barras bajas.
	 * Se utiliza como parte del nombre del archivo, con lo que se evita crear dos archivos con el mismo nombre.
	 * @return Cadena con la fecha y hora actuales.
	 */
	public static String construirCadenaFecha() {
		Calendar calendario = Calendar.getInstance();

		String fecha = "_d";
		fecha += calendario.get(Calendar.DAY_OF_MONTH) + "-";
		fecha += (calendario.get(Calendar.MONTH) + 1) + "-";
		fecha += calendario.get(Calendar.YEAR) + "_";

		fecha += "h" + calendario.get(Calendar.HOUR_OF_DAY) + "_";
		fecha += "m" + calendario.get(Calendar.MINUTE) + "_";
		fecha += "s" + calendario.get(Calendar.SECOND) + "";

		return fecha;
	}
	
	
	/**
	 * Indica si el contenido de los dos archivos dados es el idéntico.
	 * @param archivo1
	 *            Primer archivo a comparar.
	 * @param archivo2
	 *            Segundo archivo a comparar.
	 * @return Si el contenido de los dos archivos dados es el idéntico.
	 */
	public static boolean elContenidoDeLosArchivosEsElMismo(File archivo1, File archivo2){
		System.out.println("[GestorIO] archivo1 = " + archivo1);
		System.out.println("[GestorIO] archivo2 = " + archivo2);
	try (
			FileReader fileDeArchivo1 = new FileReader(archivo1);
			FileReader fileDeArchivo2 = new FileReader(archivo2);

			BufferedReader lector1 = new BufferedReader(fileDeArchivo1);
			BufferedReader lector2 = new BufferedReader(fileDeArchivo2);
		){

		String linea_Archivo1 = lector1.readLine();
		String linea_Archivo2 = lector2.readLine();
		
		boolean iguales = true;

		while ((linea_Archivo1 != null) && (linea_Archivo2 != null) && iguales) {

			if (!linea_Archivo1.equals(linea_Archivo2)){
				iguales = false;
			}

			linea_Archivo1 = lector1.readLine();
			linea_Archivo2 = lector2.readLine();
		}

		return ((iguales) && (linea_Archivo1 == null) && (linea_Archivo2 == null));
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
