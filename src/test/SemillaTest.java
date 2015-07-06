package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import modelo.Semilla;
import modelo.SemillaException;
import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeProfundidad;
import modelo.pregunta.VisualizacionGrafo;

import org.junit.Test;

/**
 * Casos de test relativos a las semillas.
 * @author Jorge Alonso Márquez
 */
public class SemillaTest {
	
	/**
	 * Tests que evalúan la construcción nuevas de semillas.
	 */
	@Test
	public void construirSemilla() {
		Integer numPregunta = Semilla.RECORRIDO_EN_PROFUNDIDAD;
		Integer nNodos = 3;
		boolean esDirigido = false;
		Double porcentajeDeArcos = 0.55;
		VisualizacionGrafo visualizacionGrafo = new VisualizacionGrafo(true, false, false);
		Integer tipoDePregunta = 0;
		String seedDelRandom = new Long(System.currentTimeMillis()).toString();
		
		// Crear nueva semilla
		Semilla semilla = new Semilla(numPregunta, nNodos, esDirigido, porcentajeDeArcos,
				visualizacionGrafo, tipoDePregunta, seedDelRandom);

		// El tipo de pregunta debe ser 0
		assertEquals(new Integer(0), semilla.getNumPregunta());

		// El número de nodos debe ser 3
		assertEquals(new Integer(3), semilla.getNNodos());

		// El grafo no debe ser dirigido
		assertEquals(false, semilla.isDirigido());

		// El porcentaje de arcos debe ser 0.55
		assertEquals(new Double(0.55), semilla.getPorcentajeDeArcos());
		
		// El modo de visualización debe ser matriz de adyacencia		
		assertEquals(true, semilla.getVisualizacionGrafo().isMatrizDeAdyacencia());
		
		// El modo de visualización no debe ser lista de adyacencia		
		assertEquals(false, semilla.getVisualizacionGrafo().isListaDeAdyacencia());
		
		// El modo de visualización no debe ser visual		
		assertEquals(false, semilla.getVisualizacionGrafo().isGrafoVisual());
		
		// El tipo de pregunta debe ser 0
		assertEquals(new Integer(0), semilla.getClaseDePregunta());
		
		//La cadena representativa de la semilla debe empezar por 02005540
		assertEquals("02005540", semilla.toString().substring(0, 8));
		
		// La seed del random debe ser igual a que fue dada
		assertEquals(seedDelRandom, semilla.getSeedDelRandom());
	}
	
	
	/**
	 * Tests que evalúan la recuperación de semillas a partir de su código.
	 */
	@Test
	public void recuperarSemilla() {
		Semilla semilla = null;
		String codigoSemilla = "020055401435673973843";

		// Recuperar la semilla a partir de su código
		try {
			semilla = new Semilla(codigoSemilla);
		} catch (SemillaException e) {
			fail(e.getMensajeDelError());
		}
			
			// El tipo de pregunta debe ser de recorrido en profundidad
			assertEquals(Semilla.RECORRIDO_EN_PROFUNDIDAD, semilla.getNumPregunta());
			
			// El número de nodos debe ser 3
			assertEquals(new Integer(3), semilla.getNNodos());
			
			// El grafo no debe ser dirigido
			assertEquals(false, semilla.isDirigido());

			// El porcentaje de arcos debe ser 0.55
			assertEquals(new Double(0.55), semilla.getPorcentajeDeArcos());
			
			// El modo de visualización debe ser matriz de adyacencia		
			assertEquals(true, semilla.getVisualizacionGrafo().isMatrizDeAdyacencia());
			
			// El modo de visualización no debe ser lista de adyacencia		
			assertEquals(false, semilla.getVisualizacionGrafo().isListaDeAdyacencia());
			
			// El modo de visualización no debe ser visual		
			assertEquals(false, semilla.getVisualizacionGrafo().isGrafoVisual());
			
			// El tipo de pregunta debe ser 0
			assertEquals(new Integer(0), semilla.getClaseDePregunta());
			
			//La cadena representativa de la semilla debe empezar por 02005540
			assertEquals("02005540", semilla.toString().substring(0, 8));
			
			// La seed del random debe ser 1435673973843
			assertEquals("1435673973843", semilla.getSeedDelRandom().toString());
			
	}
	
	
	/**
	 * Tests que evalúan la generación de excepciones durante la recuperación de una pregunta a partir
	 * de un código erróneo.
	 */
	@Test
	public void recuperarSemillaErronea() {

		// Los valores de las pos. 0 y 1 no es necesario probarlos,
		// pues su rango es [0~9] por lo que cualquier valor entero será correcto

		// El valor de la pos. 2, correspondiente al booleano que indica si es dirigido, no
		// es necesario probar si fallará, pues el programa simplemente entiende como positivo
		// cualquier valor distinto de cero
		
		// Valor de las pos. 3, 4 y 5. Se introducirá un valor para porcentaje de arcos
		// demasiado alto (mayor de 100):
		try {
			new Semilla("020" + "101" + "001435673973843");
			fail("Debió saltar una excepción.");
		} catch (SemillaException e) { }
		
		// Valor de la pos. 6. Se introducirá un valor para el modo de visualización
		// demasiado alto (mayor de 7):
		try {
			new Semilla("020055" + "8" + "01435673973843");
			fail("Debió saltar una excepción.");
		} catch (SemillaException e) { }
		
		// Valor de la pos. 7. Se introducirá un valor para el tipo de pregunta
		// demasiado alto (mayor de 2):
		try {
			new Semilla("0200550" + "3" + "1435673973843");
			fail("Debió saltar una excepción.");
		} catch (SemillaException e) { }		
		
		
		// Longitud de la cadena incorrecta
		try {
			new Semilla("55");
			fail("Debió saltar una excepción.");
		} catch (SemillaException e) { }
		
		//Introducción de un valor no numérico
		try {
			new Semilla("A20055001435673973843");
			fail("Debió saltar una excepción.");
		} catch (SemillaException e) { }
		
	}
	
	
	/**
	 * Tests que evalúan si el código de semilla dado por una pregunta corresponde al código
	 * de la semilla usada para la recuperación de esa pregunta.
	 */
	@Test
	public void semillaDeLaPregunta() {
		Pregunta pregunta = null;
		Semilla semilla = null;
		try {
			semilla = new Semilla("020055001435673973843");
			pregunta = new PreguntaDeProfundidad(semilla);
		} catch (SemillaException e) {
			System.err.println("[SemillaTest] " + e.getMensajeDelError());
		}
		
		assertEquals("020055001435673973843", semilla.toString());
		
		//La semilla de la pregunta debe seguir siendo 020055001435673973843
		assertEquals("020055001435673973843", pregunta.getSemilla().toString());
		
	}
	
	
	/**
	 * Test que evalúan el funcionamiento de la clase VisualizacionGrafo
	 */
	@Test
	public void visualizacionGrafo(){
		VisualizacionGrafo visual;

		visual = new VisualizacionGrafo(true, true, true);
		//Su código debe ser 7 (111)
		assertEquals("7", visual.getCodigo());
		
		visual = new VisualizacionGrafo(true, false, false);
		//Su código debe ser 4 (100)
		assertEquals("4", visual.getCodigo());
		
		visual = new VisualizacionGrafo("2"); //(010)
		//Debe de representarse como matriz de adyacencia
		assertFalse(visual.isMatrizDeAdyacencia());
		//Debe de representarse como lista de adyacencia
		assertTrue(visual.isListaDeAdyacencia());
		//Debe de representarse visualmente
		assertFalse(visual.isGrafoVisual());
		
		visual = new VisualizacionGrafo("0"); //(000)
		//Debe de representarse como matriz de adyacencia
		assertFalse(visual.isMatrizDeAdyacencia());
		//No debe de representarse como lista de adyacencia
		assertFalse(visual.isListaDeAdyacencia());
		//No debe de representarse visualmente
		assertFalse(visual.isGrafoVisual());
		
		try {
			Semilla semilla = new Semilla("020055401435673973843");
			//Debe de representarse como matriz de adyacencia
			assertTrue(semilla.getVisualizacionGrafo().isMatrizDeAdyacencia());
			//No debe de representarse como lista de adyacencia
			assertTrue(semilla.getVisualizacionGrafo().isMatrizDeAdyacencia());
			//No debe de representarse visualmente
			assertTrue(semilla.getVisualizacionGrafo().isMatrizDeAdyacencia());
		} catch (SemillaException e) {
			System.err.println("[SemillaTest] " + e.getMensajeDelError());
		}
		
	}

}
