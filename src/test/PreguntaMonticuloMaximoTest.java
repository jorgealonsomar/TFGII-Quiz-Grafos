package test;



import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import preguntas.PreguntaMonticuloMaximo;

/**
 * Pruebas de la clase PreguntaMonticuloMaximo. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaMonticuloMaximoTest {
	/**
	 * Comprueba que realiza correctamente tres extracciones.
	 */
	@Test
	public void comprobarTestEqualsExtraccion(){
		

		ArrayList<Character> monticulo=new ArrayList<Character>();
		ArrayList<Character> correcta=new ArrayList<Character>();
		int extractionsNumber=3;
		monticulo.add('Y');
		monticulo.add('X');
		monticulo.add('S');
		monticulo.add('F');
		monticulo.add('V');
		monticulo.add('H');
		monticulo.add('A');
		monticulo.add('D');
		monticulo.add('B');
		monticulo.add('E');
		
		for(int i=monticulo.size()-1;i>0;i--)
				monticulo=PreguntaMonticuloMaximo.restructurar(monticulo,i);
		for(int i=0;i<extractionsNumber;i++)
				monticulo=PreguntaMonticuloMaximo.extraccion(monticulo);

		correcta.add('S');
		correcta.add('F');
		correcta.add('H');
		correcta.add('D');
		correcta.add('E');
		correcta.add('B');
		correcta.add('A');
		
		Assert.assertEquals(monticulo,correcta);
		monticulo.clear();
		correcta.clear();
	}
	/**
	 * Comprueba que realiza correctamente la extraccion con 4 extracciones.
	 */
	@Test
	public void comprobarTestEqualsExtraccion1(){
		

		ArrayList<Character> monticulo=new ArrayList<Character>();
		ArrayList<Character> correcta=new ArrayList<Character>();
		int extractionsNumber=4;
		monticulo.add('X');
		monticulo.add('P');
		monticulo.add('W');
		monticulo.add('L');
		monticulo.add('F');
		monticulo.add('K');
		monticulo.add('T');
		monticulo.add('G');
		monticulo.add('E');
		monticulo.add('B');
		
		for(int i=monticulo.size()-1;i>0;i--)
			monticulo=PreguntaMonticuloMaximo.restructurar(monticulo,i);
		for(int i=0;i<extractionsNumber;i++)
			monticulo=PreguntaMonticuloMaximo.extraccion(monticulo);

		 correcta.add('L');
		 correcta.add('G');
		 correcta.add('K');
		 correcta.add('B');
		 correcta.add('F');
		 correcta.add('E');
		
		 
		Assert.assertEquals(monticulo,correcta);
		monticulo.clear();
		correcta.clear();
	}
	/**
	 * Comprueba que realiza correctamente la extraccion en una determinada posicion.
	 */
	@Test
	public void comprobarTestEqualsSueltoExtraccion(){
		

		ArrayList<Character> monticulo=new ArrayList<Character>();
		ArrayList<Character> correcta=new ArrayList<Character>();
		int extractionsNumber=3;
		monticulo.add('Y');
		monticulo.add('X');
		monticulo.add('S');
		monticulo.add('F');
		monticulo.add('V');
		monticulo.add('H');
		monticulo.add('A');
		monticulo.add('D');
		monticulo.add('B');
		monticulo.add('E');
		
		for(int i=monticulo.size()-1;i>0;i--)
			monticulo=PreguntaMonticuloMaximo.restructurar(monticulo,i);
		 for(int i=0;i<extractionsNumber;i++)
		 {
			monticulo=PreguntaMonticuloMaximo.extraccion(monticulo);
			if(extractionsNumber==1)
				Assert.assertNotSame(monticulo.get(3),'F');
		 }
		 correcta.add('S');
		 correcta.add('F');
		 correcta.add('H');
		 correcta.add('D');
		 correcta.add('E');
		 correcta.add('B');
		 correcta.add('A');
		 
		Assert.assertEquals(monticulo,correcta);
		monticulo.clear();
		correcta.clear();
	}
	/**
	 * Comprueba que realiza correctamente la extraccion en una determinada posicion.
	 */
	@Test
	public void comprobarTestNotEqualsSueltoExtraccion(){
		

		ArrayList<Character> monticulo=new ArrayList<Character>();
		ArrayList<Character> correcta=new ArrayList<Character>();
		int extractionsNumber=3;
		monticulo.add('Y');
		monticulo.add('X');
		monticulo.add('S');
		monticulo.add('F');
		monticulo.add('V');
		monticulo.add('H');
		monticulo.add('A');
		monticulo.add('D');
		monticulo.add('B');
		monticulo.add('E');
		
		 for(int i=monticulo.size()-1;i>0;i--)
				monticulo=PreguntaMonticuloMaximo.restructurar(monticulo,i);
		 for(int i=0;i<extractionsNumber;i++)
		 {
				monticulo=PreguntaMonticuloMaximo.extraccion(monticulo);
				if(extractionsNumber==1)
					Assert.assertNotSame(monticulo.get(3),'B');
		 }
		 correcta.add('S');
		 correcta.add('F');
		 correcta.add('H');
		 correcta.add('D');
		 correcta.add('E');
		 correcta.add('B');
		 correcta.add('A');
		 
		Assert.assertNotSame(monticulo,correcta);
		monticulo.clear();
		correcta.clear();
	}
	/**
	 * Comprueba que realiza correctamente la extraccion.
	 */
	@Test
	public void comprobarTestFalseExtraccion(){
		

		ArrayList<Character> monticulo=new ArrayList<Character>();
		ArrayList<Character> correcta=new ArrayList<Character>();
		int extractionsNumber=3;
		monticulo.add('Y');
		monticulo.add('X');
		monticulo.add('S');
		monticulo.add('F');
		monticulo.add('V');
		monticulo.add('H');
		monticulo.add('A');
		monticulo.add('D');
		monticulo.add('B');
		monticulo.add('E');
		
		 for(int i=monticulo.size()-1;i>0;i--)
				monticulo=PreguntaMonticuloMaximo.restructurar(monticulo,i);
		 for(int i=0;i<extractionsNumber;i++)
				monticulo=PreguntaMonticuloMaximo.extraccion(monticulo);

		 correcta.add('S');
		 correcta.add('D');
		 correcta.add('F');
		 correcta.add('A');
		 correcta.add('H');
		
		 correcta.add('E');
		 correcta.add('B');
		
		 
		Assert.assertNotSame(monticulo,correcta);
		monticulo.clear();
		correcta.clear();
	}
	/**
	 * Comprueba que realiza correctamente añadir valores a un montuculo.
	 */
	@Test
	public void comprobarTestEqualsAdiccion(){
		

		ArrayList<Character> monticulo=new ArrayList<Character>();
		ArrayList<Character> correcta=new ArrayList<Character>();
	
		monticulo.add('Z');
		monticulo.add('T');
		monticulo.add('S');
		monticulo.add('P');
		monticulo.add('N');
		monticulo.add('O');
		monticulo.add('M');
		monticulo.add('G');
		monticulo.add('L');
	
		
		 for(int i=monticulo.size()-1;i>0;i--)
			monticulo=PreguntaMonticuloMaximo.restructurar(monticulo,i);
	
		monticulo=PreguntaMonticuloMaximo.adicion(monticulo, 'V');
		monticulo=PreguntaMonticuloMaximo.adicion(monticulo, 'C');
		monticulo=PreguntaMonticuloMaximo.adicion(monticulo, 'B');

		 correcta.add('Z');
		 correcta.add('V');
		 correcta.add('S');
		 correcta.add('P');
		 correcta.add('T');
		 correcta.add('O');
		 correcta.add('M');
		 correcta.add('G');
		 correcta.add('L');
		 correcta.add('N');
		 correcta.add('C');
		 correcta.add('B');
		 
		
		Assert.assertEquals(monticulo,correcta);
		monticulo.clear();
		correcta.clear();
	}

}
