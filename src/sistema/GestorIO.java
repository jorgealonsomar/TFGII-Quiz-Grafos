package sistema;

import java.io.BufferedWriter;
import java.io.File;
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
			System.out.println("[GestorIO] Escribiendo en el arcivo " + archivo);
		} catch (IOException e) { }
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
}
