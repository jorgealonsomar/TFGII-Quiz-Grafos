package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import preguntas.PreguntaTablaHash;

/**
 * Pruebas de la clase PreguntaTablaHash. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaTablaHashTest {
	/**
	 * Comprueba que realiza correctamente la tabla Hash.
	 */
	@Test
	public void comprobarTestEquals(){
		
		
		ArrayList<Integer> array=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		
	
		array.add(49);
		array.add(88);
		array.add(80);
		array.add(70);
		array.add(15);
		array.add(26);
		array.add(36);
		
		
		array=PreguntaTablaHash.tablaHash(array,9);
		
		 correcta.add(70);
		 correcta.add(26);
		 correcta.add(36);
		 correcta.add(null);
		 correcta.add(49);
		 correcta.add(null);
		 correcta.add(15);
		 correcta.add(88);
		 correcta.add(80);
		
		
		Assert.assertEquals(array,correcta);
		array.clear();
		correcta.clear();
	}
}
