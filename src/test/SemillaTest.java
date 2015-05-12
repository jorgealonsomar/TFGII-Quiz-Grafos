package test;

import static org.junit.Assert.assertEquals;
import modelo.Semilla;

import org.junit.Test;

public class SemillaTest {

	@Test
	public void construirSemilla() {
		Integer nNodos = 3;
		boolean esDirigido = false;
		Integer[][] matrizDeAdyacencia = new Integer[][]{new Integer[]{0,1,1}, new Integer[]{1,0,1}, new Integer[]{1,1,0}};
		
		//Crear nueva semilla
		Semilla semilla = new Semilla(Semilla.recorridoEnProfunidad, nNodos, esDirigido, matrizDeAdyacencia);
		
		//El tipo de pregunta debe ser 0
		assertEquals(semilla.getTipoPregunta(), (Integer)0);
		
		//El número de nodos debe ser 3
		assertEquals(semilla.getNNodos(), (Integer)3);
		
		//El grafo no debe ser dirigido
		assertEquals(semilla.getByteDirigido(), false);
		
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
		Semilla semilla = new Semilla(codigoSemilla);
		
		assertEquals(semilla.getTipoPregunta(), Semilla.recorridoEnProfunidad);
		assertEquals(semilla.getNNodos(), (Integer)4);
		assertEquals(semilla.getByteDirigido(), false);
		
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
		
	}

}
