package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;


import preguntas.PreguntaSeleccionDirecta;
/**
 * Pruebas de la clase PreguntaSeleccionDirecta. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaSeleccionDirectaTest {
	/**
	 * Comprueba que realiza correctamente la seleccion directa.
	 */
	@Test
	public void comprobarTestEqualsSeleccionDirecta7(){
		
		PreguntaSeleccionDirecta select=new PreguntaSeleccionDirecta();
		ArrayList<Integer> array=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int pasoAbuscar=7;
		select.setPasoAbuscar(pasoAbuscar);
		
		array.add(11);
		array.add(14);
		array.add(96);
		array.add(89);
		array.add(28);
		array.add(69);
		array.add(1);
		array.add(79);
		array.add(46);
		array.add(78);
		array.add(40);
		array.add(47);
		
		array=PreguntaSeleccionDirecta.seleccionDirecta(array);
		
		 correcta.add(1);
		 correcta.add(11);
		 correcta.add(14);
		 correcta.add(28);
		 correcta.add(40);
		 correcta.add(46);
		 correcta.add(47);
		 correcta.add(79);
		 correcta.add(69);
		 correcta.add(78);
		 correcta.add(89);
		 correcta.add(96);
		
		Assert.assertEquals(array,correcta);
		array.clear();
		correcta.clear();
	}
}
