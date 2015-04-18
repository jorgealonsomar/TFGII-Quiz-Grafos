package preguntas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;
import utilidades.ImageUtilities;

/**
 * Generador de pregunta de montículo máximo.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaMonticuloMaximo extends Preguntas {

	
	/**
	 * Método para borrar.
	 * @param numQuestion Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static void preguntaBorrarMonticulo(int numQuestion) throws FileNotFoundException, IOException
	{

		int seed;
		int seedProperty;
		int heapSize;
		int extractionsNumber;
		String separador=Preguntas.obtenerSeparador();
		int numberOfResult=0;
		
		String ruta=obtenerPropiedadesString("ruta");
		ArrayList<Character>heap;
		ArrayList<Character>enunciado;
		ArrayList<Character>aux;
		ArrayList<String> imagenes ;
		String imagen="";
		Random rnd = new Random();
		
		ruta=ruta+separador+"Monticulos"+separador+"Eliminar";
		File directorio=new File(ruta);
		
		FileUtilities.crearDirectorio(directorio);

		char letter='A';
		String feedBack="";
		String m;
		String retroalimentacion="";
		String enun1= null;;
		String enun2 = null;
		String enun3= null;;
		String enun4= null;;
		File fileXml=null;
		File fileGift=null;
		
		FormatosPreguntas formatos=new FormatosPreguntas();
		
		
		Preguntas.empezarFichero(formatos);
		String filenameGift=ruta+separador+"PreguntaMonticuloGift_"+FileUtilities.obtenerFecha()+".txt";
		String filenameXml=ruta+separador+"PreguntaMonticuloXml_"+FileUtilities.obtenerFecha()+".xml";
		fileXml = rutaFichero(filenameXml);
		fileGift = rutaFichero(filenameGift);
		
		int idioma=obtenerPropiedades("idioma");
		if(idioma==0)
		{
			enun1=obtenerPropiedadesString("heapEnun1Espa");
			enun2=obtenerPropiedadesString("heapEnun2Espa");	
			enun3=obtenerPropiedadesString("heapEnun3DelEspa");
			enun4=obtenerPropiedadesString("heapEnun4Espa");
		}
		if(idioma==1)
		{
			enun1=obtenerPropiedadesString("heapEnun1Ingles");
			enun2=obtenerPropiedadesString("heapEnun2Ingles");
			enun3=obtenerPropiedadesString("heapEnun3DelIngles");
			enun4=obtenerPropiedadesString("heapEnun4Ingles");
		}
		int contar=0;	
		boolean continuar=true;
		while(continuar)
		{
			heap= new ArrayList<Character>();
			enunciado= new ArrayList<Character>();
			aux= new ArrayList<Character>();
			imagenes =new ArrayList<String>();
			m="";
			
			heapSize=obtenerPropiedades("heapSize");
			seedProperty=obtenerPropiedades("seedHeap");
			extractionsNumber=obtenerPropiedades("extractionsNumber");
			contar++;
			if (contar==numQuestion)
				continuar=false;
		
			seed=obtenerSemilla(seedProperty);
			rnd.setSeed(seed);

			numberOfResult=heapSize-extractionsNumber;
		
			if(idioma==0)
				formatos=empezarPreguntas("Pregunta eliminar montículo. ", formatos,  seed,"shortanswer");
			else
				formatos=empezarPreguntas("Delete heap question. ", formatos,  seed,"shortanswer");
	
		
		
			for(int i=0;i<26;i++)
				aux.add(i,(char) (letter+i));
			
		 	
			Collections.shuffle(aux, rnd);
		 
			for(int i=0;i<heapSize;i++)
				enunciado.add(i, aux.get(i));
		 
			for(int i=0;i<heapSize;i++)
				heap.add(i, enunciado.get(i));
		 
			 for(int i=heap.size()-1;i>0;i--)
				 heap=restructurar(heap,i);
			 m=construirArbol(heap);
			 imagen= ImageUtilities.utilizarImagenes( ruta+separador+"eliminar.png",m);
			 formatos.setXml(formatos.getXml(),"<img src=\"@@PLUGINFILE@@/eliminar.png\" alt=\"\"  /></p>"+" "+ enun1 +" "+numberOfResult+" "+enun2+" "+extractionsNumber +" "+enun3+" "+enun4);
			 formatos.setGift(formatos.getGift(),enun1 +" "+numberOfResult+" "+enun2+" "+extractionsNumber +" "+enun3+" "+enun4);
			 formatos.setEnunciado(formatos.getEnunciado(),enun1 +" "+numberOfResult+" "+enun2+extractionsNumber +" "+enun3+" "+enun4+"\n");
		
			 
			 formatos= obtenerDatosChar(heapSize,formatos,heap);
		 
			 formatos.setGift(formatos.getGift(),"{\r\n ");
			 formatos.setXml(formatos.getXml()," </p> ");
			 
			 idioma=obtenerPropiedades("idioma");
			 if(idioma==0)
				 formatos.setEnunciado(formatos.getEnunciado(),"\nLa respuesta correcta es:");
			 else
				 formatos.setEnunciado(formatos.getEnunciado(),"\nThe correct answer is:");
			 
			 imagenes.clear();
			 for(int i=0;i<extractionsNumber;i++)
			 {
				String dato=""+heap.get(0);
				heap=extraccion(heap);
				m=construirArbol(heap);
				retroalimentacion=ImageUtilities.imagenRetroalimentacion( i, extractionsNumber,imagenes, retroalimentacion,"eliminar",m,ruta+separador,dato);	
			 }
		 
			 for(int i=0;i<extractionsNumber;i++)
			 {
				 retroalimentacion=retroalimentacion+"<file name=\"eliminar_"+i+".png\" encoding=\"base64\">"+imagenes.get(i)	+	"</file>\r\n " ;
			 } 
		
		 finalizarEnunciadoXml(formatos, fileXml );
		 formatos.setXml(formatos.getXml(),"<file name=\"eliminar.png\" encoding=\"base64\">"+imagen+"</file>");
		 formatos=escribirRespuestasChar( numberOfResult,formatos, feedBack,enunciado,heap,false);

		 finalizarEnunciadoGift(formatos, fileGift );
		 
		 finalizarPreguntaA(formatos,true,retroalimentacion);
		 finalizarPreguntaB(formatos);
		 finalizarPregunta(formatos );
		 heap.clear();
		 retroalimentacion="";
				
	}

	finalizarDocumentos( formatos, fileXml, fileGift);
		 
	
	}
	
	/**
	 * Método para añadir.
	 * @param numQuestions Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static void preguntaAnadirMonticulo(int numQuestions) throws FileNotFoundException, IOException
	{
		int seed;
		int seedProperty;
		int heapSize;
		int addNumber;
		int numberOfResult=0;
		
		ArrayList<Character>heap;
		ArrayList<Character>enunciado;
		ArrayList<Character>aux;
		ArrayList<String> imagenes ;
		ArrayList<Character>ampliacion;
		String imagen;
		String retroalimentacion="";
		String m;
		String separador=Preguntas.obtenerSeparador();
		char letter='A';
		String feedBack="";
		Random rnd = new Random();
		String ruta = Preguntas.obtenerPropiedadesString("ruta");
		ruta=ruta+separador+separador+"Monticulos"+separador+"Anadir";
		File directorio=new File(ruta);

		FileUtilities.crearDirectorio(directorio);
		String enun1= null;
		String enun2= null;
		String enun3 = null; 
		String enun4= null; 

	
	    
	 
		FormatosPreguntas formatos=new FormatosPreguntas();
		
		File fileXml=null;
		File fileGift=null;
		
		Preguntas.empezarFichero(formatos);
		String filenameGift=ruta+separador+"PreguntaAñadirMonticuloGift_"+FileUtilities.obtenerFecha()+".txt";
		String filenameXml=ruta+separador+"PreguntaAñadirMonticuloXml_"+FileUtilities.obtenerFecha()+".xml";
		fileXml = rutaFichero(filenameXml);
		fileGift = rutaFichero(filenameGift);
		

				
		
		int idioma=obtenerPropiedades("idioma");
		if(idioma==0)
		{
			enun1=obtenerPropiedadesString("heapEnun1Espa");
			enun2=obtenerPropiedadesString("heapEnun2Espa");	
			enun3=obtenerPropiedadesString("heapEnun3AddEspa");
			enun4=obtenerPropiedadesString("heapEnun4Espa");
		}
		if(idioma==1)
		{
			enun1=obtenerPropiedadesString("heapEnun1Ingles");
			enun2=obtenerPropiedadesString("heapEnun2Ingles");
			enun3=obtenerPropiedadesString("heapEnun3AddIngles");
			enun4=obtenerPropiedadesString("heapEnun4Ingles");
		}

		
		int contar=0;	
		boolean continuar=true;
		while(continuar)
		{
			heapSize=obtenerPropiedades("heapSizeAdd");
			
			seedProperty=obtenerPropiedades("seedHeapAdd");
			
			addNumber=obtenerPropiedades("addNumber");
			ampliacion= new ArrayList<Character>();
			heap= new ArrayList<Character>();
			enunciado= new ArrayList<Character>();
			aux= new ArrayList<Character>();
			imagenes =new ArrayList<String>();
			m="";
			seed=obtenerSemilla(seedProperty);
			rnd.setSeed(seed);
			
			contar++;
			if (contar==numQuestions)
				continuar=false;
		
			numberOfResult=heapSize+addNumber;
		
			if(idioma==0)
				formatos=empezarPreguntas("Pregunta añadir montículo. ", formatos,  seed,"shortanswer");
			else
				formatos=empezarPreguntas("Add heap question. ", formatos,  seed,"shortanswer");
	
		
			for(int i=0;i<26;i++)
				aux.add(i,(char) (letter+i));
			
		 	
			 Collections.shuffle(aux, rnd);
		 
			 for(int i=0;i<heapSize;i++)
				 enunciado.add(i, aux.get(i));
		 
			 for(int i=heapSize;i<heapSize+addNumber;i++)
				ampliacion.add(aux.get(i));
			 
			 for(int i=0;i<heapSize;i++)
				 heap.add(i, enunciado.get(i));
		
			 for(int i=heap.size()-1;i>0;i--)
				heap=restructurar(heap,i);
			m=construirArbol(heap);
		 	imagen= ImageUtilities.utilizarImagenes(  ruta+separador+"anadir.png",m);
		 	formatos.setXml(formatos.getXml(),"<img src=\"@@PLUGINFILE@@/anadir.png\" alt=\"\" /></p>"+" "+enun1+" "+numberOfResult+" "+enun2+" "+addNumber +" "+enun3+" " );
		 	formatos.setGift(formatos.getGift(),enun1+" "+numberOfResult+" "+enun2+" "+addNumber +" "+enun3+" " );
		 	formatos.setEnunciado(formatos.getEnunciado(),enun1+" "+numberOfResult+" "+enun2+addNumber +" "+enun3+" " );
		
			formatos= obtenerDatosChar(addNumber,formatos,ampliacion);
		 
			formatos.setGift(formatos.getGift(),enun4);
			formatos.setXml(formatos.getXml(),enun4);
			formatos.setEnunciado(formatos.getEnunciado(),enun4+"\n");
		 
		 
		 
			formatos= obtenerDatosChar(heapSize,formatos,heap);
		 
			formatos.setGift(formatos.getGift(),"{\r\n ");
			formatos.setXml(formatos.getXml()," </p> ");
			formatos.setEnunciado(formatos.getEnunciado(),"\nLa respuesta correcta es:");
		 
			for(int i=0;i<addNumber;i++)
			{
				 String dato=""+ampliacion.get(i);
				 heap=adicion(heap,ampliacion.get(i));
				 m=construirArbol(heap);
				 retroalimentacion=ImageUtilities.imagenRetroalimentacion( i, addNumber,imagenes, retroalimentacion,"anadir",m,ruta+separador+separador,dato);	
			 }
			 for(int i=0;i<addNumber;i++)
			 {
				 retroalimentacion=retroalimentacion+"<file name=\"anadir_"+i+".png\" encoding=\"base64\">"+imagenes.get(i)	+	"</file>\r\n " ;
			 } 
		 
			 for(int i=0;i<addNumber;i++)
				enunciado.add(ampliacion.get(i));
		 

			 finalizarEnunciadoXml(formatos, fileXml );
		 
			 formatos.setXml(formatos.getXml(),"<file name=\"anadir.png\" encoding=\"base64\">"+imagen+"</file>");
			 formatos=escribirRespuestasChar( numberOfResult,formatos, feedBack,enunciado,heap,false);

			 finalizarEnunciadoGift(formatos, fileGift );
		 
		 
			 finalizarPreguntaA(formatos,true,retroalimentacion);
			 finalizarPreguntaB(formatos);
			 finalizarPregunta(formatos );
			 heap.clear();
			 retroalimentacion="";
		}
		finalizarDocumentos( formatos, fileXml, fileGift);
	}
	
	
	/**
	 * Método para reestructurar el montículo.
	 * @param heap Array con los datos.
	 * @param pivot Pivote.
	 * @return Array reestructurado.
	 */
	public static ArrayList<Character> restructurar(ArrayList<Character>heap,int pivot)
	{
		
		int aux=heap.size();
		int auxPadre;
		int aux_hijo1,aux_hijo2;
		int maxHijo=0;
		boolean salir=false;		
		
		auxPadre = (pivot-1)/2;
			
		salir=false;
		while(!salir)
		{
			aux_hijo1=(auxPadre*2)+1;
			aux_hijo2=(auxPadre*2)+2;
				
			if(aux>=aux_hijo2)
			{
					
				if(aux>aux_hijo2)
				{
					if(heap.get(aux_hijo1)>heap.get(aux_hijo2))
					{
						maxHijo=aux_hijo1;
					}
					else
					{
						maxHijo=aux_hijo2;
					}
							
				}
					
				else
				{
					maxHijo=aux_hijo1;
				}
				if(heap.get(auxPadre)<heap.get(maxHijo))
				{
					intercambiar(auxPadre,maxHijo,heap);
					auxPadre=maxHijo;
					aux=heap.size();
						
				}
				else
					salir=true;
			}
			else
				salir=true;
				
		}
		return heap;
	}
	
	/**
	 * Método para intercambiar la posición de 2 valores.
	 * @param i Valor 1.
	 * @param k Valor 2.
	 * @param heap Array que contiene los datos.
	 */
	private static void intercambiar(int i, int k,ArrayList<Character> heap){
		char aux = heap.get(i);
		heap.set(i,heap.get(k));
		heap.set(k,aux);
		
	}
	
	/**
	 * Método para la extracción.
	 * @param heap Array que contiene los datos. 
	 * @return Array resultante.
	 */
	public static ArrayList<Character> extraccion(ArrayList<Character>heap)
	{
		char aux;
		aux=heap.get(heap.size()-1);
		heap.set(heap.size()-1, heap.get(0));
		heap.set(0, aux);
		heap.remove(heap.size()-1);
		
		for(int i=heap.size()-1;i>0;i--) 
			heap=restructurar(heap,i);

		return heap;
	}
	
	/**
	 * Método para añadir elementos al mantículo.
	 * @param heap Array que contiene los datos. 
	 * @param anadir Dato a añadir.
	 * @return Array resultante. 
	 */
	public static ArrayList<Character> adicion (ArrayList<Character>heap, char anadir)
	{
		heap.add(anadir);
		
		for(int i=heap.size()-1;i>0;i--) 
			heap=restructurar(heap,i);

		return heap;
	}
	
	/**
	 * Método que permite generar el dibujo del Arbol.
	 * @param heap Array que contiene los datos.
	 * @return Cadena de caracteres con el formato correcto para dibujar el Arbol.
	 */
	public static String construirArbol( ArrayList<Character>heap )
	{
		 boolean acabar=false;
			
		 int bajadas=0;
		 int hijo;
		 int auxPadre;
		 boolean subir=false;
		 auxPadre=0;
		 boolean comprobarHijos=false;
		 String m="";
		 ArrayList<Character> dibujo=new ArrayList<Character>();
		 int contar=0;
		 
		 for(int i=0;i<heap.size();i++)
			 dibujo.add(heap.get(i));
		while(!acabar)
		{
			subir=false;
				
			hijo=auxPadre*2+1;
				
			if(hijo<=dibujo.size()-1)
			{
				if(dibujo.get(hijo)==null)
					hijo=auxPadre*2+2;
			}
				
				
			if(hijo<=dibujo.size()-1)
			{
				if(dibujo.get(auxPadre)!=null)
				{
					for(int a=0;a<bajadas;a++)
						m=m+"\t";
					m=m+dibujo.get(auxPadre);
					dibujo.set(auxPadre, null);
					contar++;
					
					bajadas++;
					m=m+"\n";
				
				}
			}
			if (hijo>dibujo.size()-1)
			{
				for(int a=0;a<bajadas;a++)
					m=m+"\t";
				
				m=m+dibujo.get(auxPadre);
				dibujo.set(auxPadre, null);
				contar++;
			
				m=m+"\n";
				
				auxPadre=(auxPadre-1)/2;
				subir=true;
				while(!comprobarHijos)
				{
					if (contar==dibujo.size())
					{
						acabar=true;
						comprobarHijos=true;
					}
					
					if(((auxPadre*2)+1)<=dibujo.size()-1 && ((auxPadre*2)+2)<=dibujo.size()-1)
					{
						if(dibujo.get((auxPadre*2)+1)==null && dibujo.get((auxPadre*2)+2)==null)
						{
							auxPadre=(auxPadre-1)/2;
							bajadas--;
						}else
							comprobarHijos=true;
					}else if(((auxPadre*2)+1)<=dibujo.size()-1 && ((auxPadre*2)+2)>dibujo.size()-1)
					{
						auxPadre=(auxPadre-1)/2;
						bajadas--;
					}
					else
						comprobarHijos=true;
						
				
				}
				subir=true;
				comprobarHijos=false;
			}
			if(!subir)
				auxPadre=hijo;
			if (contar==dibujo.size())
				acabar=true;

		}
		return m;
	}
	
	
}
