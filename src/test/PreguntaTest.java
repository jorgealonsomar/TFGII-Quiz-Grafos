package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import interfaz.FramePrincipal;

import java.io.File;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import modelo.pregunta.PreguntaDeProfundidad;
import modelo.pregunta.VisualizacionGrafo;

import org.junit.Test;

import sistema.GestorIO;
import sistema.Ruta;
import texto.Idioma;
import texto.Textos_Preguntas;

/**
 * Casos de test relativos a las preguntas.
 * @author Jorge Alonso Márquez
 */
public class PreguntaTest {
	
	/**
	 * Test que evalúa si las preguntas se generan de forma correcta, comprobando si sus
	 * textos son los que deberían generar.
	 */
	@Test
	public void textosDeLasPreguntas() {
		Integer nNodos = 5;
		Double porcentajeDeArcos = 1.00;
		boolean esDirigido = true;

		Pregunta preguntaDeAnchura = new PreguntaDeAnchura(nNodos, porcentajeDeArcos, esDirigido,
				new VisualizacionGrafo(true, false, false));

		assertEquals(preguntaDeAnchura.getTitulo(Idioma.ESP),
				Textos_Preguntas.tituloPregAnchura().getString(Idioma.ESP));
		assertEquals(preguntaDeAnchura.getTitulo(Idioma.ENG),
				Textos_Preguntas.tituloPregAnchura().getString(Idioma.ENG));

		assertEquals(
				preguntaDeAnchura.getEnunciado(Idioma.ESP),
				Textos_Preguntas.enunciadoPregAnchura().getString(Idioma.ESP));
	}
	
	
	
	/**
	 * Test que evalúa si el archivo generado por una pregunta es similar a aquél creado al recuperar
	 * esa misma pregunta a través de su semilla.
	 */
	@Test
	public void comparacionDeArchivos() {
		Integer nNodos = 4;
		Double porcentajeDeArcos = 0.50;
		boolean esDirigido = false;
		
		FramePrincipal framePrincipal = new FramePrincipal();
		String rutaPruebas = Ruta.PRUEBAS;
		GestorIO.makeDirectorio(new File(rutaPruebas));
		framePrincipal.setTextoDirectorio(rutaPruebas);
		
		String nombreArchivoOriginal = "test_Original.xml";
		String nombreArchivoRecuperado = "test_Recuperada.xml";
		
		File rutaArchivoOriginal = new File(rutaPruebas + File.separator + nombreArchivoOriginal);
		File rutaArchivoRecuperado = new File(rutaPruebas + File.separator + nombreArchivoRecuperado);
		
		GestorIO.eliminarArchivo(rutaArchivoOriginal);
		GestorIO.eliminarArchivo(rutaArchivoRecuperado);
		
		
		//Se crea la pregunta
		Pregunta preguntaOriginal = new PreguntaDeProfundidad(nNodos, porcentajeDeArcos, esDirigido,
				new VisualizacionGrafo(true, false, false));
		
		//Se imprime a archivo
		String textoPreguntaXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		textoPreguntaXml += "\n<quiz>";
		textoPreguntaXml += preguntaOriginal.getTextoPreguntaXml(Idioma.ESP);
		textoPreguntaXml += "\n</quiz>";
		
		framePrincipal.imprimePregunta("", textoPreguntaXml, nombreArchivoOriginal, null);
		
		
		//Se recupera la pregunta
		Pregunta preguntaRecuperada = new PreguntaDeProfundidad(preguntaOriginal.getSemilla());
		
		//Se imprime a archivo
		textoPreguntaXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		textoPreguntaXml += "\n<quiz>";
		textoPreguntaXml += preguntaRecuperada.getTextoPreguntaXml(Idioma.ESP);
		textoPreguntaXml += "\n</quiz>";
		
		framePrincipal.imprimePregunta("", textoPreguntaXml, nombreArchivoRecuperado, null);
		
		
		//Se comprueba que su contenido es similar
		assertTrue(GestorIO.elContenidoDeLosArchivosEsElMismo(rutaArchivoOriginal, rutaArchivoRecuperado));
	}
	

}
