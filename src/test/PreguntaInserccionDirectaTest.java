package test;

import java.util.ArrayList;



import org.junit.Assert;
import org.junit.Test;

import preguntas.PreguntaInsercionDirecta;

/**
 * Pruebas de la clase PreguntaInserccionDirecta. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaInserccionDirectaTest {

	/**
	 * Comprueba que realiza correctamente la insercción Directa.
	 */
	@Test
	public void comprobarTestEquals6(){
		
		PreguntaInsercionDirecta insert=new PreguntaInsercionDirecta();
		ArrayList<Integer> array=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int pasoAbuscar=6;
		insert.setPasoAbuscar(pasoAbuscar);
		
		array.add(75);
		array.add(60);
		array.add(11);
		array.add(39);
		array.add(80);
		array.add(12);
		array.add(40);
		array.add(28);
		array.add(23);
		array.add(46);
		array.add(51);
		array.add(5);
		
		array=PreguntaInsercionDirecta.insercionDirecta(array);
		
		correcta.add(11);
		correcta.add(12);
		correcta.add(28);
		correcta.add(39);
		correcta.add(40);
		correcta.add(60);
		correcta.add(75);
		correcta.add(80);
		correcta.add(23);
		correcta.add(46);
		correcta.add(51);
		correcta.add(5);
		
		Assert.assertEquals(array,correcta);
		array.clear();
		correcta.clear();
	}
	
	
}
