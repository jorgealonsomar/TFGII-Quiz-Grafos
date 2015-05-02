package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import modelo.pregunta.Pregunta;

public class GestorIO {
	
	public static void makeDirectorio(File directorio) {
		if (!directorio.exists()){
			directorio.mkdirs();
			System.out.println("Directorio " + directorio + " creado.");
		}
	}
	
	
	public static void escribirEnArchivo(File archivo, String contenido) {
		try (
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(archivo, true)));
			){
			
			writer.write(contenido);
			System.out.println("[GestorIO] Escribiendo en el arcivo " + archivo);
			
		} catch (IOException e) { };
	}
	
	
	public static void escribirPreguntaEnArchivo(File archivo, Pregunta pregunta, Idioma idioma){
		escribirEnArchivo(archivo, "--------------------------------------------------------------");
		escribirEnArchivo(archivo, pregunta.getTitulo(idioma));
		escribirEnArchivo(archivo, "\n\n");
		escribirEnArchivo(archivo, pregunta.getEnunciado(idioma));
		escribirEnArchivo(archivo, "\n\n");
		escribirEnArchivo(archivo, pregunta.getGrafo().toString());
		escribirEnArchivo(archivo, "\n\n");
		escribirEnArchivo(archivo, pregunta.getParteAResponder(idioma));
		escribirEnArchivo(archivo, "\n\n\n");
	}
	
	
	public static String construirCadenaFecha() {
		Calendar calendario = Calendar.getInstance();
		
		String fecha = "_d";
		fecha += calendario.get(Calendar.DAY_OF_MONTH) + "-";
		fecha += (calendario.get(Calendar.MONTH)+1) + "-";
		fecha += calendario.get(Calendar.YEAR) + "_";
		
		fecha += "h" + calendario.get(Calendar.HOUR_OF_DAY) + "_";
		fecha += "m" + calendario.get(Calendar.MINUTE) + "_";
		fecha += "s" + calendario.get(Calendar.SECOND) + "";

		return fecha;
	}
}
