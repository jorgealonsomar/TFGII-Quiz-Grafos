package test;

import java.util.ArrayList;




import org.junit.Assert;
import org.junit.Test;

import preguntas.ArbolBB;


/**
 * Pruebas de la clase PreguntaArbolBinarioBusqueda. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaArbolBinarioBusquedaTest{
	

	/**
	 * Comprueba que realiza correctamente la eliminación por la derecha.
	 */
	@Test
	public void comprobarTestTrueDer(){
		

		ArrayList<Integer> tree=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int treeSize=8;
		ArbolBB arbol = new ArbolBB();
		tree.add(66);
		tree.add(50);
		tree.add(72);
		tree.add(9);
		tree.add(67);
		tree.add(79);
		tree.add(4);
		tree.add(0);
		 for(int i=0; i<treeSize; i++)
			arbol.insertar(tree.get(i));
		
		  arbol.eliminar(50,2); 
		  arbol.eliminar(9,2); 
		  arbol.eliminar(67,2); 

		  
	
		 ArrayList<Integer> resultadoTree = arbol.recorridoEnAnchura();
		 correcta.add(66);
		 correcta.add(4);
		 correcta.add(72);
		 correcta.add(0);
		 correcta.add(79);

		
		 System.out.println(resultadoTree.toString());
		 Assert.assertEquals(resultadoTree,correcta);
		
		tree.clear();
		correcta.clear();
	}
	
	/**
	 * Test que No realiza correctamente la eliminacion por la derecha.
	 */
	@Test
	public void comprobarTestFalseDer(){
		

		ArrayList<Integer> tree=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int treeSize=8;
		ArbolBB arbol = new ArbolBB();
		tree.add(66);
		tree.add(50);
		tree.add(72);
		tree.add(9);
		tree.add(67);
		tree.add(79);
		tree.add(4);
		tree.add(0);
		 for(int i=0; i<treeSize; i++)
			arbol.insertar(tree.get(i));
		
		  arbol.eliminar(50,2); 
		  arbol.eliminar(9,2); 
		  arbol.eliminar(67,2); 

		  
	
		 ArrayList<Integer> resultadoTree = arbol.recorridoEnAnchura();
		 correcta.add(66);
		 correcta.add(4);
		 correcta.add(12);
		 correcta.add(0);
		 correcta.add(79);

		
	
		 Assert.assertNotSame(resultadoTree,correcta);
		
		tree.clear();
		correcta.clear();
	}
	
	/**
	 * Test que  realiza correctamente la eliminacion por la izquierda.
	 */
	@Test
	public void comprobarTestTrueIz(){
		

		ArrayList<Integer> tree=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int treeSize=8;
		ArbolBB arbol = new ArbolBB();
		tree.add(66);
		tree.add(50);
		tree.add(72);
		tree.add(9);
		tree.add(67);
		tree.add(79);
		tree.add(4);
		tree.add(0);
		 for(int i=0; i<treeSize; i++)
			arbol.insertar(tree.get(i));
		
		  arbol.eliminar(50,1); 
		  arbol.eliminar(9,1); 
		  arbol.eliminar(67,1); 

		  

		 ArrayList<Integer> resultadoTree = arbol.recorridoEnAnchura();
		 correcta.add(66);
		 correcta.add(4);
		 correcta.add(72);
		 correcta.add(0);
		 correcta.add(79);

		
		
		 Assert.assertEquals(resultadoTree,correcta);
		
		tree.clear();
		correcta.clear();
	}
	
	/**
	 * Test que No realiza correctamente la eliminacion por la izquierda.
	 */
	@Test
	public void comprobarTestFalseIz(){
		

		ArrayList<Integer> tree=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int treeSize=8;
		ArbolBB arbol = new ArbolBB();
		tree.add(66);
		tree.add(50);
		tree.add(72);
		tree.add(9);
		tree.add(67);
		tree.add(79);
		tree.add(4);
		tree.add(0);
		 for(int i=0; i<treeSize; i++)
			arbol.insertar(tree.get(i));
		
		  arbol.eliminar(50,1); 
		  arbol.eliminar(9,1); 
		  arbol.eliminar(67,1); 

		  
	
		 ArrayList<Integer> resultadoTree = arbol.recorridoEnAnchura();
		 correcta.add(66);
		 correcta.add(4);
		 correcta.add(12);
		 correcta.add(0);
		 correcta.add(79);

		
		
		 Assert.assertNotSame(resultadoTree,correcta);
		
		tree.clear();
		correcta.clear();
	}
	
	/**
	 * Test que  realiza correctamente el añadir.
	 */
	@Test
	public void comprobarTestTrueAdd(){
		

		ArrayList<Integer> tree=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int treeSize=8;
		ArbolBB arbol = new ArbolBB();
		tree.add(17);
		tree.add(13);
		tree.add(46);
		tree.add(23);
		tree.add(58);
		tree.add(43);
		tree.add(70);
		tree.add(91);
		 for(int i=0; i<treeSize; i++)
			arbol.insertar(tree.get(i));
		 arbol.insertar(30);
		 arbol.insertar(94);
		 arbol.insertar(42);
		 arbol.insertar(76);
			
	
		 ArrayList<Integer> resultadoTree = arbol.recorridoEnAnchura();
		 correcta.add(17);
		 correcta.add(13);
		 correcta.add(46);
		 correcta.add(23);
		 correcta.add(58);
		 correcta.add(43);
		 correcta.add(70);
		 correcta.add(30);
		 correcta.add(91);
		 correcta.add(42);
		 correcta.add(76);
		 correcta.add(94);
		 
		
		 Assert.assertEquals(resultadoTree,correcta);
		
		tree.clear();
		correcta.clear();
	}
	
	/**
	 * Test que No realiza correctamente el añadir.
	 */
	@Test
	public void comprobarTestFalseAdd(){
		

		ArrayList<Integer> tree=new ArrayList<Integer>();
		ArrayList<Integer> correcta=new ArrayList<Integer>();
		int treeSize=8;
		ArbolBB arbol = new ArbolBB();
		tree.add(17);
		tree.add(13);
		tree.add(46);
		tree.add(23);
		tree.add(58);
		tree.add(43);
		tree.add(70);
		tree.add(91);
		 for(int i=0; i<treeSize; i++)
			arbol.insertar(tree.get(i));
		 arbol.insertar(30);
		 arbol.insertar(94);
		 arbol.insertar(42);
		 arbol.insertar(76);
			
	
		 ArrayList<Integer> resultadoTree = arbol.recorridoEnAnchura();
		 correcta.add(17);
		 correcta.add(13);
		 correcta.add(46);
		 correcta.add(23);
		 correcta.add(58);
		 correcta.add(43);
		 correcta.add(70);
		 correcta.add(30);
		 correcta.add(91);
		 correcta.add(32);
		 correcta.add(76);
		 correcta.add(94);
		 
		
		 Assert.assertNotSame(resultadoTree,correcta);
		
		tree.clear();
		correcta.clear();
	}
	
	 
	
	
}
