package test;



import java.util.ArrayList;

import preguntas.PreguntaPila;
import utilidades.EstrategiaPermutacionCaracteres;
import utilidades.EstrategiaPermutaciones;
import utilidades.RespuestaGenerada;

import org.junit.Assert;
import org.junit.Test;

/**
 * Pruebas de la clase PreguntaPila. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaPilaTest {
	/**
	 * Comprueba que realiza correctamente la pila con numeros.
	 */
	@Test
	public void comprobarTestTueInteger(){
		
		PreguntaPila pregunta=new PreguntaPila();
		ArrayList<Integer> comprobar=new ArrayList<Integer>();
		comprobar.add(4);
		comprobar.add(3);
		comprobar.add(2);
		comprobar.add(1);
		comprobar.add(0);
		comprobar.add(9);
		comprobar.add(8);
		comprobar.add(7);
		comprobar.add(6);
		comprobar.add(5);
		 
		Assert.assertTrue(pregunta.comprobarCorrectas(comprobar));
	comprobar.clear();
	
	}
	/**
	 * Comprueba que realiza correctamente la pila con numero.
	 */
	@Test
public void comprobarTestTrueInteger1(){
		
		PreguntaPila pregunta=new PreguntaPila();
		ArrayList<Integer> comprobar=new ArrayList<Integer>();
		comprobar.add(2);
		comprobar.add(5);
		comprobar.add(6);
		comprobar.add(7);
		comprobar.add(4);
		comprobar.add(8);
		comprobar.add(9);
		comprobar.add(3);
		comprobar.add(1);
		comprobar.add(0);
		Assert.assertTrue(pregunta.comprobarCorrectas(comprobar));
		comprobar.clear();
}
	/**
	 * Comprueba que realiza correctamente la pila utilizando una falsa con numeros.
	 */
	@Test
public void comprobarTestFalseInteger(){
	
	PreguntaPila pregunta=new PreguntaPila();
	ArrayList<Integer> comprobar=new ArrayList<Integer>();
		comprobar.add(4);
		comprobar.add(6);
		comprobar.add(8);
		comprobar.add(7);
		comprobar.add(5);
		comprobar.add(3);
		comprobar.add(2);
		comprobar.add(9);
		comprobar.add(0);
		comprobar.add(1);
		Assert.assertFalse(pregunta.comprobarCorrectas(comprobar));
		comprobar.clear();
	}
	/**
	 * Comprueba que realiza correctamente la pila con numeros.
	 */
	@Test
	public void comprobarTestTrueInteger3(){
		
		PreguntaPila pregunta=new PreguntaPila();
		ArrayList<Integer> comprobar=new ArrayList<Integer>();
		comprobar.add(2);
		comprobar.add(1);
		comprobar.add(4);
		comprobar.add(3);
		comprobar.add(6);
		comprobar.add(5);
		comprobar.add(8);
		comprobar.add(7);
		comprobar.add(9);
		comprobar.add(0);
		Assert.assertTrue(pregunta.comprobarCorrectas(comprobar));
	}/**
	 * Comprueba que realiza correctamente la pila con letras.
	 */
	@Test
	public void comprobarTestFalseChar(){
		
		
		RespuestaGenerada respuesta;
		ArrayList<Character> comprobar1=new ArrayList<Character>();
		comprobar1.add('J');
		comprobar1.add('D');
		comprobar1.add('C');
		comprobar1.add('E');
		comprobar1.add('H');
		comprobar1.add('B');
		comprobar1.add('F');
		comprobar1.add('A');
		comprobar1.add('I');
		comprobar1.add('G');
		EstrategiaPermutaciones estrategia=new EstrategiaPermutacionCaracteres();
		respuesta=estrategia.permute(comprobar1, null);
	
		Assert.assertFalse(respuesta.getCorrect());
	}
	/**
	 * Comprueba que realiza correctamente la pila con letras.
	 */
	@Test
	public void comprobarTestTrueChar(){
		
		
		RespuestaGenerada respuesta;
		ArrayList<Character> comprobar1=new ArrayList<Character>();
		comprobar1.add('I');
		comprobar1.add('H');
		comprobar1.add('G');
		comprobar1.add('F');
		comprobar1.add('J');
		comprobar1.add('E');
		comprobar1.add('D');
		comprobar1.add('C');
		comprobar1.add('B');
		comprobar1.add('A');
		EstrategiaPermutaciones estrategia=new EstrategiaPermutacionCaracteres();
		respuesta=estrategia.permute(comprobar1, null);
	
		Assert.assertTrue(respuesta.getCorrect());
	}
	

}
