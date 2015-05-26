package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import modelo.Consigna;
import modelo.ConsignaException;

import org.junit.Test;

public class SemillaTest {

	@Test
	public void construirSemilla() {
		Integer nNodos = 3;
		boolean esDirigido = false;
		Integer[][] matrizDeAdyacencia = new Integer[][]{new Integer[]{0,1,1}, new Integer[]{1,0,1}, new Integer[]{1,1,0}};
		
		//Crear nueva semilla
		Consigna semilla = new Consigna(Consigna.recorridoEnProfunidad, nNodos, esDirigido, matrizDeAdyacencia);
		
		//El tipo de pregunta debe ser 0
		assertEquals(semilla.getTipoPregunta(), (Integer)0);
		
		//El número de nodos debe ser 3
		assertEquals(semilla.getNNodos(), (Integer)3);
		
		//El grafo no debe ser dirigido
		assertEquals(semilla.esDirigido(), false);
		
		//La matriz de adyacencia debe ser {{0,1,1}{1,0,1}{1,1,0}}
		assertEquals(semilla.getMatrizDeAdyacencia()[0][0], (Integer)0);
		assertEquals(semilla.getMatrizDeAdyacencia()[0][1], (Integer)1);
		assertEquals(semilla.getMatrizDeAdyacencia()[0][2], (Integer)1);
		
		assertEquals(semilla.getMatrizDeAdyacencia()[1][0], (Integer)1);
		assertEquals(semilla.getMatrizDeAdyacencia()[1][1], (Integer)0);
		assertEquals(semilla.getMatrizDeAdyacencia()[1][2], (Integer)1);
		
		assertEquals(semilla.getMatrizDeAdyacencia()[2][0], (Integer)1);
		assertEquals(semilla.getMatrizDeAdyacencia()[2][1], (Integer)1);
		assertEquals(semilla.getMatrizDeAdyacencia()[2][2], (Integer)0);
		
		assertEquals(semilla.toString(), "020003005006");
	}
	
	
	@Test
	public void recuperarSemilla() {
		String codigoSemilla = "030003013006008";
		
		//Recuperar la semilla a partir de su código
		try{
			Consigna semilla = new Consigna(codigoSemilla);
		
			assertEquals(semilla.getTipoPregunta(), Consigna.recorridoEnProfunidad);
			assertEquals(semilla.getNNodos(), (Integer)4);
			assertEquals(semilla.esDirigido(), false);
			
			//La matriz de adyacencia debe ser {{0,0,1,1}{1,1,0,1}{0,1,1,0}{1,0,0,0}}
			assertEquals(semilla.getMatrizDeAdyacencia()[0][0], (Integer)0);
			assertEquals(semilla.getMatrizDeAdyacencia()[0][1], (Integer)0);
			assertEquals(semilla.getMatrizDeAdyacencia()[0][2], (Integer)1);
			assertEquals(semilla.getMatrizDeAdyacencia()[0][3], (Integer)1);
			
			assertEquals(semilla.getMatrizDeAdyacencia()[1][0], (Integer)1);
			assertEquals(semilla.getMatrizDeAdyacencia()[1][1], (Integer)1);
			assertEquals(semilla.getMatrizDeAdyacencia()[1][2], (Integer)0);
			assertEquals(semilla.getMatrizDeAdyacencia()[1][3], (Integer)1);
	
			assertEquals(semilla.getMatrizDeAdyacencia()[2][0], (Integer)0);
			assertEquals(semilla.getMatrizDeAdyacencia()[2][1], (Integer)1);
			assertEquals(semilla.getMatrizDeAdyacencia()[2][2], (Integer)1);
			assertEquals(semilla.getMatrizDeAdyacencia()[2][3], (Integer)0);
			
			assertEquals(semilla.getMatrizDeAdyacencia()[3][0], (Integer)1);
			assertEquals(semilla.getMatrizDeAdyacencia()[3][1], (Integer)0);
			assertEquals(semilla.getMatrizDeAdyacencia()[3][2], (Integer)0);
			assertEquals(semilla.getMatrizDeAdyacencia()[3][3], (Integer)0);
		
		} catch (ConsignaException e){
			fail(e.getMensajeDelError());
		}
	}
		
		
	@Test
	public void recuperarSemillaErronea() {
			//Valor de la pos. 0 (correspondiente al Tipo de Pregunta) demasiado alto
			try{
				new Consigna("730003013006008");
				fail("Debió saltar una excepción.");
			} catch (ConsignaException e){ }
			
			//El valor de la pos. 1, correspondiente al valorNNodos no es necesario probarlo,
			//pues su rango es [0~9] por lo que cualquier valor entero será correcto
			
			//El valor de la pos. 2, correspondiente al booleano que indica si es dirigido, no
			//es necesario probarlo, pues el programa simplemente entiende como positivo cualquier
			//valor distinto de cero
			
			//Longitud de la cadena incorrecta
			try {
				new Consigna("55");
				fail("Debió saltar una excepción.");
			} catch (ConsignaException e) { }
	}

}
