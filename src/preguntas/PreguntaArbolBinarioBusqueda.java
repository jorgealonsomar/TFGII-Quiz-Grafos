package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.Random;


import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;
import utilidades.ImageUtilities;

/**
 * Generador de las preguntas relativas a los Arboles binarios de búsqueda.
 * Puede crear 3 tipos distintos de pregunta: añadir nodos, eliminar nodos 
 * por la derecha y eliminar nodos por la izquierda. 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */

public class PreguntaArbolBinarioBusqueda extends Preguntas {
	/**
	 * Cadena que contiene la retroalimentación.
	 */
	private static String retroalimentacion="";
	/**
	 * ArrayList que contiene las imágenes.
	 */
	static ArrayList<String>imagenes=new ArrayList<String>();
	/**
	 * CAdena que contiene la ruta del fichero.
	 */
	static String ruta = Preguntas.obtenerPropiedadesString("ruta");
	/**
	 * Cadena que contiene el separador.
	 */
	static String separador=Preguntas.obtenerSeparador();

	
	
	/**
	 * Método que genera las preguntas de árboles binarios de búsqueda.
	 * @param option Tipo de pregunta a generar.
	 * @param numQuestions Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos. 
	 */
	public static void preguntaArbol( int option,int numQuestions ) throws FileNotFoundException, IOException
	{
		Random rnd = new Random();
		String rutaAnadir=ruta+separador+"Arboles"+separador+"Anadir";
		File directorio=new File(rutaAnadir);
		FileUtilities.crearDirectorio(directorio);
		
		String ruta = Preguntas.obtenerPropiedadesString("ruta");
		
		String rutaDer=ruta+separador+"Arboles"+separador+"EliminarDer";
		directorio=new File(rutaDer);
		FileUtilities.crearDirectorio(directorio);
		

		String rutaIzq=ruta+separador+"Arboles"+separador+"EliminarIzq";
		directorio=new File(rutaIzq);
		FileUtilities.crearDirectorio(directorio);
		
		
		int seed;
		int seedProperty = 0;
		int treeSize = 0;
		int extractionsNumberBST;
		int imagenArbol;
		
		extractionsNumberBST=0;
		int numberOfResult=0;
		ArrayList<Integer> anchura;
		ArrayList<Integer> anchuraRes;
		ArrayList<Integer>nodoAeliminar;
		ArrayList<Integer>newNodes;
		int insertionsNumberBST;
		 String imagen = null;
		
		insertionsNumberBST=0;
		String feedBack="";
		String m ="";
		String filenameGift="";
		 String filenameXml="";
		
		FormatosPreguntas formatos=new FormatosPreguntas();
		File fileXml=null;
		File fileGift=null;
		
			
			if(option==1)
			{
				filenameGift = rutaDer+separador+"PreguntaArbolesBusquedaGiftDer_"+FileUtilities.obtenerFecha()+".txt";
				 filenameXml = rutaDer+separador+"PreguntaArbolesBusquedaXmlDer_"+FileUtilities.obtenerFecha()+".xml";
			}
			if(option==2)
			{
				filenameGift=rutaIzq+separador+"PreguntaArbolesBusquedaGiftIzq_"+FileUtilities.obtenerFecha()+".txt";
				 filenameXml=rutaIzq+separador+"PreguntaArbolesBusquedaXmlIzq_"+FileUtilities.obtenerFecha()+".xml";
			}
			if(option==3)
			{
				 filenameGift=rutaAnadir+separador+"PreguntaArbolesBusquedaAddNodosGift_"+FileUtilities.obtenerFecha()+".txt";
				 filenameXml=rutaAnadir+separador+"PreguntaArbolesBusquedaAddNodosXml_"+FileUtilities.obtenerFecha()+".xml";
			}
			fileXml=rutaFichero(filenameXml);
			fileGift=rutaFichero(filenameGift);
			Preguntas.empezarFichero(formatos);
		
		
		
		String enun1 = null,enun2 = null,enun3 = null,enun4 = null ,enun1Sin=null;
		
		int idioma=obtenerPropiedades("idioma");
		imagenArbol=obtenerPropiedades("imagenArbol");
		if (idioma==0 )
		{
			
			
			enun1Sin=obtenerPropiedadesString("bstEnun1Esp");
			
		enun1=obtenerPropiedadesString("bstEnun1Espimg");
			enun2=obtenerPropiedadesString("bstEnun2Esp");
			enun4=obtenerPropiedadesString("bstEnun4Esp");
		}
		if (idioma==1)
		{
			
			enun1Sin=obtenerPropiedadesString("bstEnun1Eng");
		
			enun1=obtenerPropiedadesString("bstEnun1Engimg");
			enun2=obtenerPropiedadesString("bstEnun2Eng");
			enun4=obtenerPropiedadesString("bstEnun4Eng");
		}
		if(option==1){
			treeSize=obtenerPropiedades("treeDerSize");
			
			seedProperty=obtenerPropiedades("seedBSTDer");
			
			extractionsNumberBST=obtenerPropiedades("extractionsNumberBSTDer");
			 
			if (idioma==0)
				enun3=obtenerPropiedadesString("bstEnun3DerEsp");
			if (idioma==1)
				enun3=obtenerPropiedadesString("bstEnun3RigEng");
			
		}
		if(option==2){
			treeSize=obtenerPropiedades("treeIzqSize");
			
			seedProperty=obtenerPropiedades("seedBSTIzq");
			
			extractionsNumberBST=obtenerPropiedades("extractionsNumberBSTIzq");
			if (idioma==0)
				enun3=obtenerPropiedadesString("bstEnun3IzqEsp");
			if (idioma==1)
				enun3=obtenerPropiedadesString("bstEnun3lefEng");
		}
		
		if(option==3){
			treeSize=obtenerPropiedades("treeAddSize");
			
			seedProperty=obtenerPropiedades("seedBSTAdd");
			
			insertionsNumberBST=obtenerPropiedades("insertionsNumberBST");
			if (idioma==0)
				enun3=obtenerPropiedadesString("bstEnun3AddEsp");
			if (idioma==1)
				enun3=obtenerPropiedadesString("bstEnun3AddEng");
		}
		int contar=0;	
		boolean continuar=true;
		
		
		while(continuar)
		{
			
			m=null;
			imagen = null;
			anchura=new ArrayList<Integer>();
			anchuraRes=new ArrayList<Integer>();
			nodoAeliminar= new ArrayList<Integer>();
			newNodes= new ArrayList<Integer>();
			seed=obtenerSemilla(seedProperty);
			rnd.setSeed(seed);
			contar++;
			
			if (contar==numQuestions)
				continuar=false;
				if(option==1)
					if(idioma==0)
						formatos=empezarPreguntas("Pregunta árbol binario de búsqueda con borrado derecho. ", formatos,  seed,"shortanswer");
					else
						formatos=empezarPreguntas("Binary search tree (by right deletion) question. ", formatos,  seed,"shortanswer");			
				if(option==2)
					if(idioma==0)
						formatos=empezarPreguntas("Pregunta árbol binario de búsqueda con borrado izquierdo. ", formatos,  seed,"shortanswer");
					else
						formatos=empezarPreguntas("Binary search tree (by left deletion) question. ", formatos,  seed,"shortanswer");
				if(option==3)
					if(idioma==0)
						formatos=empezarPreguntas("Pregunta árbol binario de búsqueda: añadir nodos. ", formatos,  seed,"shortanswer");
					else
						formatos=empezarPreguntas("Binary search tree (inserting) question. ", formatos,  seed,"shortanswer");
			 
			
			 ArrayList<Integer>aux= new ArrayList<Integer>();
			 for(int i=0;i<99;i++)
				 aux.add(i);		 	
			 Collections.shuffle(aux, rnd);					
			 
			 
			 
	
			 ArrayList<Integer>tree= new ArrayList<Integer>();
			 for(int i=0;i<treeSize;i++)
				 tree.add(i, aux.get(i));			
			
			 
	
			 ArbolBB arbol = new ArbolBB();				
			 for(int i=0; i<treeSize; i++)
				 arbol.insertar(tree.get(i));			
			
		    anchura= arbol.recorridoEnAnchura(); 
		    if(imagenArbol==0)
			{
			
			    if(option==1)
			    	formatos.setXml(formatos.getXml(),"<img src=\"@@PLUGINFILE@@/imageEliminarDer.png\" alt=\"\"  /></p><p> "+enun1);
			    if(option==2)
			    	formatos.setXml(formatos.getXml(),"<img src=\"@@PLUGINFILE@@/imageEliminarIzq.png\" alt=\"\"  /></p><p> "+enun1);
			    if(option==3)
			    	formatos.setXml(formatos.getXml(),"<img src=\"@@PLUGINFILE@@/imageAnadirNodo.png\" alt=\"\"  /></p><p> "+enun1);
			}
		    else
		    	formatos.setXml(formatos.getXml(),enun1Sin);

			formatos.setGift(formatos.getGift(),enun1Sin+" ");
		    formatos.setEnunciado(formatos.getEnunciado(),enun1Sin+" ");
		    if(imagenArbol==0)
		    	formatos= obtenerDatosSinImagen(anchura.size(),formatos,anchura);
		    else
		    	 formatos= obtenerDatos(anchura.size(),formatos,anchura);
		     
		    formatos.setXml(formatos.getXml(),"</p>\r\n <p>"+enun2);
		    formatos.setGift(formatos.getGift(),"\r\n "+enun2) ;		
		    formatos.setEnunciado(formatos.getEnunciado(),"\n "+enun2);
		   
		    	 formatos.setXml(formatos.getXml(),"  "+enun3+"  "+enun4);
		    	 formatos.setGift(formatos.getGift(),"  "+enun3+"  "+enun4);
		    	 formatos.setEnunciado(formatos.getEnunciado(),"  "+enun3+"  "+enun4);
	
	
		    		
	
		 
		     Collections.shuffle(tree, rnd);	
		     if(option==1 || option==2){	    	 
		    	 for(int i=0; i<extractionsNumberBST; i++)
		    		 nodoAeliminar.add(i, tree.get(i));	
		    	 if(imagenArbol==0)
		 		{
			    	 if(option==1){		
					    	
						 m= construirArbol(convertirArbol(arbol));
						 imagen = ImageUtilities.utilizarImagenes(ruta+separador+"Arboles"+separador+"EliminarDer"+separador+"imageEliminarDer.png",m);
						 
				    }
				    if(option==2){					 
				    	 m= construirArbol(convertirArbol(arbol));
						 imagen = ImageUtilities.utilizarImagenes(ruta+separador+"Arboles"+separador+"EliminarIzq"+separador+"imageEliminarIzq.png",m);
						 
				    }	
		 		}
		    	 formatos= obtenerDatos(extractionsNumberBST,formatos,nodoAeliminar);
		    	 arbol= eliminar(nodoAeliminar,extractionsNumberBST,arbol, option); 
			    numberOfResult=treeSize-extractionsNumberBST;
			    
			      
			 }	  
		     if(option==3){	    	 
		    	 for(int i=treeSize; i<treeSize+insertionsNumberBST; i++)
					 newNodes.add(aux.get(i));	
		    	 if(imagenArbol==0)
			 	{
			    	 m= construirArbol(convertirArbol(arbol));
					 imagen = ImageUtilities.utilizarImagenes(ruta+separador+"Arboles"+separador+"Anadir"+separador+"imageAnadirNodo.png",m);
			 	} 
				 formatos= obtenerDatos(insertionsNumberBST,formatos, newNodes);
		    	 arbol=anadir(newNodes, insertionsNumberBST, arbol);
		    	 numberOfResult=treeSize+insertionsNumberBST;
	
		    	 for(int i=treeSize;i<treeSize+insertionsNumberBST;i++)
						anchura.add(aux.get(i));
		    	 
		    	 anchuraRes= arbol.recorridoEnAnchura(); 
		    	 for(int i=tree.size(), j=0; j<newNodes.size();i++, j++)
					 tree.add(i, newNodes.get(j));
				 anchura=anchuraRes;
				 
				
				 	    	 	  
			  }
		     if(idioma==0)
		    	formatos.setEnunciado(formatos.getEnunciado(),"\nArbol resultante: ");
			else
				formatos.setEnunciado(formatos.getEnunciado(),"\nFinal tree: ");
		   
		    anchuraRes= arbol.recorridoEnAnchura(); 
			finalizarEnunciadoXml(formatos, fileXml );
		    
		    if(option==1)
		    	formatos.setXml(formatos.getXml(),"<file name=\"imageEliminarDer.png\" encoding=\"base64\">"+imagen+"</file>");
		    if(option==2)
		    	formatos.setXml(formatos.getXml(),"<file name=\"imageEliminarIzq.png\" encoding=\"base64\">"+imagen+"</file>");
		    if(option==3)
		    	formatos.setXml(formatos.getXml(),"<file name=\"imageAnadirNodo.png\" encoding=\"base64\">"+imagen+"</file>");
		    formatos=escribirRespuestas( numberOfResult,formatos, feedBack,anchura, anchuraRes,false);
		
			finalizarEnunciadoGift(formatos, fileGift );
		    finalizarPreguntaA(formatos,true,retroalimentacion);
			finalizarPreguntaB(formatos);
			 finalizarPregunta(formatos );
			 
					
		}
				
					finalizarDocumentos( formatos, fileXml, fileGift);
	}
	
	/**
	 * Modifica el formato de un Arbol.
	 * @param arbol Arbol recibido.
	 * @return Arbol modificado
	 */
	public static Arbol convertirArbol(ArbolBB arbol){
		ArrayList<Integer>tree= new ArrayList<Integer>();
		tree=arbol.recorridoEnAnchura();
		Arbol arbol2 = new Arbol();
		for(int i=0; i<tree.size(); i++)
			arbol2.insertarNodo(tree.get(i));
		return arbol2; 
	}
	
	/**
	 * Método que se encarga de llamar a otros métodos encargados de eliminar nodos en función
	 * del tipo de borrado que se solicite.
	 * @param nodoAeliminar Array de nodos a eliminar.
	 * @param extractionsNumberBST Número de nodos a eliminar.
	 * @param arbol Arbol al que se le van a eliminar nodos.
	 * @param option Indica el tipo de borrado que se quiere realizar.
	 * @return Arbol con los nodos ya borrados.
	 * @throws IOException 
	 */
	public static  ArbolBB eliminar(ArrayList<Integer>nodoAeliminar,int extractionsNumberBST, ArbolBB arbol,int option) throws IOException
	{
		retroalimentacion="";
		String m="";
		if (option==1)
		{
			imagenes.clear();
			for(int i=0; i<extractionsNumberBST; i++)
			{
				arbol.eliminar(nodoAeliminar.get(i),option);
				 m= construirArbol(convertirArbol(arbol));
				retroalimentacion=ImageUtilities.imagenRetroalimentacion( i, extractionsNumberBST,imagenes, retroalimentacion,"eliminar_der",m,ruta+separador+"Arboles"+separador+"EliminarDer"+separador,""+nodoAeliminar.get(i));	
	    	 
			}
			
			for(int i=0; i<extractionsNumberBST; i++)
				retroalimentacion=retroalimentacion+"<file name=\"eliminar_der_"+i+".png\" encoding=\"base64\">"+imagenes.get(i)	+	"</file>\r\n " ;
		}
		if (option==2)
		{
			imagenes.clear();
			for(int i=0; i<extractionsNumberBST; i++)
			{
				arbol.eliminar(nodoAeliminar.get(i),option);
				 m= construirArbol(convertirArbol(arbol));
				retroalimentacion=ImageUtilities.imagenRetroalimentacion( i, extractionsNumberBST,imagenes, retroalimentacion,"eliminar_izq",m,ruta+separador+"Arboles"+separador+"EliminarIzq"+separador,""+nodoAeliminar.get(i));	
	    	 
			}
			
			for(int i=0; i<extractionsNumberBST; i++)
				retroalimentacion=retroalimentacion+"<file name=\"eliminar_izq_"+i+".png\" encoding=\"base64\">"+imagenes.get(i)	+	"</file>\r\n " ;
		}
		
		return arbol;
	}
	
	/**
	 * Método para añadir nodos a un Arbol.
	 * @param newNodes Array de nodos que se añadirán.
	 * @param insertionsNumberBST Número de nodos que se van a añadir.
	 * @param arbol Arbol donde se van a añadir.
	 * @return Arbol con los nodos añadidos.
	 * @throws IOException 
	 */
	public static  ArbolBB anadir(ArrayList<Integer>newNodes,int insertionsNumberBST, ArbolBB arbol) throws IOException{		
		String m="";
		imagenes.clear();  
		retroalimentacion="";
		for (int i=0; i<insertionsNumberBST; i++)
		   {
		    	 arbol.insertar(newNodes.get(i));
				 m= construirArbol(convertirArbol(arbol));
				retroalimentacion=ImageUtilities.imagenRetroalimentacion( i, insertionsNumberBST,imagenes, retroalimentacion,"anadir",m,ruta+separador+"Arboles"+separador+"Anadir"+separador,""+newNodes.get(i));
		    	 
		   }	
		for (int i=0; i<insertionsNumberBST; i++)
			retroalimentacion=retroalimentacion+"<file name=\"anadir_"+i+".png\" encoding=\"base64\">"+imagenes.get(i)	+	"</file>\r\n " ;
		 return arbol;
	}
	
	/**
	 * Método que permite generar el dibujo del Arbol.
	 * @param arbol2 Arbol a dibujar.
	 * @return Cadena de caracteres con el formato correcto para dibujar el Arbol.
	 */
	public static String construirArbol(Arbol arbol2){
		String m="";	
		ArrayList<Integer>tree= new ArrayList<Integer>();
		tree=arbol2.recorridoPreorden();
		Stack<String> pilaNulls = new Stack<String>();
		Stack<Integer> pilaNodos = new Stack<Integer>();
		boolean liberarPila=false;		
				
		for(int i=0; i<tree.size(); i++){
			int p= calcProf(arbol2, tree.get(i));
			for(int j=0; j<p; j++){
				m=m+"\t";
			}
			m=m+tree.get(i)+"\n";
			
			
			if(arbol2.tieneHijos(arbol2, tree.get(i))==1){	//1= solo hijo izquierdo.
				String nulls="";
				for(int j=0; j<p; j++){
					nulls=nulls+"\t";
				}
				nulls=nulls+""+"\n";
				pilaNulls.push(nulls);
				pilaNodos.push(tree.get(i));
			}

			
			if(arbol2.tieneHijos(arbol2, tree.get(i))==2){	//2= solo hijo derecho.
				for(int j=0; j<p+1; j++){
					m=m+"\t";
				}
				m=m+""+"\n";
			}
			
			if(arbol2.tieneHijos(arbol2, tree.get(i))==0){//0= no hijos			
				if(!pilaNulls.isEmpty())
					liberarPila =true;				
			}
			if(liberarPila==true && i+1<tree.size()){			
				
				while(!pilaNulls.isEmpty() && pilaNodos.peek()<tree.get(i+1)){
					pilaNodos.pop();				
					m=m+"\t"+pilaNulls.pop();					
				}
			}
			else if(liberarPila==true && i+1==tree.size()){
				while(!pilaNulls.isEmpty()){
					pilaNodos.pop();
					m=m+"\t"+pilaNulls.pop();
				}
			}
			liberarPila=false;
		}
		
		return m;
	}
	
	/**
	 * Método que calcula la profundidad de un nodo.
	 * @param arbol2 Arbol donde se encuentra el nodo.
	 * @param valor Valor del nodo del que quiero conocer la profundidad.
	 * @return Profundidad del nodo.
	 */
	public static int calcProf(Arbol arbol2, int valor){
		int prof=arbol2.profundidad(valor);
		return prof;
	}
	
}
