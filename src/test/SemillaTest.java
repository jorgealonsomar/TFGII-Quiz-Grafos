package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import modelo.Semilla;
import modelo.SemillaException;
import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeProfundidad;
import modelo.pregunta.VisualizacionGrafo;

import org.junit.Test;

public class SemillaTest {

	@Test
	public void construirSemilla() {
		Integer numPregunta = Semilla.RECORRIDO_EN_PROFUNDIDAD;
		Integer nNodos = 3;
		boolean esDirigido = false;
		Double porcentajeDeArcos = 0.55;
		VisualizacionGrafo visualizacionGrafo = VisualizacionGrafo.MATRIZ_DE_ADYACENCIA;
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
		assertEquals(VisualizacionGrafo.MATRIZ_DE_ADYACENCIA, semilla.getVisualizacionGrafo());
		
		// El tipo de pregunta debe ser 0
		assertEquals(new Integer(0), semilla.getTipoDePregunta());
		
		//La cadena representativa de la semilla debe empezar por 02005500
		assertEquals(semilla.toString().substring(0, 8), "02005500");
		
		// La seed del random debe ser igual a que fue dada
		assertEquals(seedDelRandom, semilla.getSeedDelRandom());
	}
	
	
	@Test
	public void recuperarSemilla() {
		Semilla semilla = null;
		String codigoSemilla = "020055001435673973843";

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
			assertEquals(VisualizacionGrafo.MATRIZ_DE_ADYACENCIA, semilla.getVisualizacionGrafo());
			
			// El tipo de pregunta debe ser 0
			assertEquals(new Integer(0), semilla.getTipoDePregunta());
			
			//La cadena representativa de la semilla debe empezar por 02005500
			assertEquals(semilla.toString().substring(0, 8), "02005500");
			
			// La seed del random debe ser 1435673973843
			assertEquals("1435673973843", semilla.getSeedDelRandom().toString());

	}
	
	
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
		// demasiado alto (mayor de 2):
		try {
			new Semilla("020055" + "3" + "01435673973843");
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
	
	

	@Test
	public void semillaDeLaPregunta() {
		Pregunta pregunta = null;
		Semilla semilla = null;
		try {
			semilla = new Semilla("020055001435673973843");
			pregunta = new PreguntaDeProfundidad(semilla);
		} catch (SemillaException e) { }
		
		assertEquals("020055001435673973843", semilla.toString());
		
		//La semilla de la pregunta debe seguir siendo 020055001435673973843
		assertEquals("020055001435673973843", pregunta.getSemilla().toString());
		
	}

}
