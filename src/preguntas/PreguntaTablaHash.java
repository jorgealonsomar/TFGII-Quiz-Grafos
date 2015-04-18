package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;


/**
 * Generador de preguntas de tabla hash.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaTablaHash extends Preguntas {
	/**
	 * Cadena de retroalimentación.
	 */
		static String retroalimentacion="<text> <![CDATA[";

	/**
	 * Método preguntaTablaHash.
	 * @param numQuestions Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static  void preguntaTablaHash(int numQuestions) throws FileNotFoundException, IOException
		{
			Random rnd = new Random();
			
			ArrayList<Integer> tablaHash=new ArrayList<Integer>();
			ArrayList<Integer> enunciado=new ArrayList<Integer>();
			ArrayList<Integer> aux=new ArrayList<Integer>();
			
			FormatosPreguntas formatos=new FormatosPreguntas();
			String ruta = Preguntas.obtenerPropiedadesString("ruta");
			String separador=Preguntas.obtenerSeparador();
			ruta=ruta+separador+"TablasHash";
			File directorio=new File(ruta);
			
			FileUtilities.crearDirectorio(directorio);
			File fileXml=null;
			File fileGift=null;
			
			Preguntas.empezarFichero(formatos);
			
			String filenameGift=ruta+separador+"PreguntaTablasHashGift"+FileUtilities.obtenerFecha()+".txt";
			String filenameXml=ruta+separador+"PreguntaTablasHashXml"+FileUtilities.obtenerFecha()+".xml";
			fileXml = rutaFichero(filenameXml);
			fileGift = rutaFichero(filenameGift);
			
			int seed;
			int seedProperty;
			int hashSize;
			
			String feedBack = "";
		
			
			hashSize=obtenerPropiedades("hashSize");
		
			seedProperty=obtenerPropiedades("seedHash");
			
			String enun1= null;
			String enun2= null;
			String enun3 = null; 

			int idioma=obtenerPropiedades("idioma");
			if(idioma==0)
			{
				enun1=obtenerPropiedadesString("hashEnum1Esp");
				enun2=obtenerPropiedadesString("hashEnum2Esp");	
				enun3=obtenerPropiedadesString("hashEnum3Esp");

			}
			if(idioma==1)
			{
				enun1=obtenerPropiedadesString("hashEnum1Eng");
				enun2=obtenerPropiedadesString("hashEnum2Eng");
				enun3=obtenerPropiedadesString("hashEnum3Eng");
			}
			int contar=0;	
			boolean continuar=true;
			while(continuar)
			{
				retroalimentacion="<text> <![CDATA[";
				tablaHash=new ArrayList<Integer>();
				enunciado=new ArrayList<Integer>();
				aux=new ArrayList<Integer>();
				
				seed=obtenerSemilla(seedProperty);
				rnd.setSeed(seed);
				contar++;
				if (contar==numQuestions)
					continuar=false;
				if(idioma==0)
					formatos=empezarPreguntas("Pregunta tabla hash. ", formatos,  seed,"cloze");
				else
					formatos=empezarPreguntas("Hash table question. ", formatos,  seed,"cloze");
				
				for(int i=1;i<99;i++)
					aux.add(i);
			 
			 	Collections.shuffle(aux, rnd);
			 
			 	for(int i=0;i<hashSize-1;i++)
			 		enunciado.add(i,aux.get(i));
			 
			 	formatos.setXml(formatos.getXml(),enun1+" ");
				formatos.setGift(formatos.getGift(),enun1+" ");
				formatos.setEnunciado(formatos.getEnunciado(), enun1+" ");	 
			
				formatos= obtenerDatos(hashSize-1,formatos,enunciado);
			
				formatos.setXml(formatos.getXml(),"</p><p>"+enun2+" " +hashSize+" "+enun3);
				formatos.setGift(formatos.getGift(),"\r\n"+enun2+" " +hashSize+" "+enun3);
				formatos.setEnunciado(formatos.getEnunciado(),"\n"+enun2+" " +hashSize+" "+enun3 );
			 
			
				formatos.setXml(formatos.getXml()," </p> ");
				 
				idioma=obtenerPropiedades("idioma");
				if(idioma==0)
					 formatos.setEnunciado(formatos.getEnunciado(),"\nLa respuesta correcta es:");
				 else
					 formatos.setEnunciado(formatos.getEnunciado(),"\nThe correct answer is:");
				 
		 
			
				tablaHash=tablaHash(enunciado,hashSize);
				enunciado.add(null);
				formatos=escribirRespuestas( hashSize,formatos, feedBack,enunciado,tablaHash,true);
				finalizarEnunciado(formatos, fileXml,fileGift );
				finalizarPreguntaA(formatos,true,retroalimentacion);
				finalizarPregunta(formatos );
				retroalimentacion="";
					
			}
				
			finalizarDocumentos( formatos, fileXml, fileGift);	
	}
	
	/**
	 * Crea la tabla hash.
	 * @param enunciado Enunciado de la pregunta.
	 * @param hashSize Tamaño de la tabla hash.
	 * @return Tabla hash creada.
	 */
	public static ArrayList<Integer> tablaHash(ArrayList<Integer>enunciado, int hashSize)
	{
		ArrayList<Integer> tablaHash=new ArrayList<Integer>();
		int key;
		int modulo;
		int salir=0;
		
		for(int i=0;i<hashSize;i++)
			tablaHash.add(i, null);
		 
		 for(int i=0;i<enunciado.size();i++)
		 {
			 salir=0;
			 key=enunciado.get(i);
			 modulo=key%hashSize;
			 if(tablaHash.get(modulo)!=null)
			 {
				 while(salir==0)
				 {
					 modulo++;
					 if(modulo>hashSize-1)
						 modulo=0;
					 
					 if(tablaHash.get(modulo)==null)
					 {
						 tablaHash.set(modulo,key);
						 salir=1;
					 }
				 }
			 }
			 else
				 tablaHash.set(modulo, key);
			 
			retroalimentacion=retroalimentacion+"<table border=\"3\" align=\"center\" cellpadding=\"20\"><tbody>\n<tr>\n";
			for(int k=0;k<tablaHash.size();k++)
			{
				retroalimentacion=retroalimentacion+"<td>";
				if(tablaHash.get(k)==null)
					retroalimentacion=retroalimentacion+"__";
				else
					retroalimentacion=retroalimentacion+tablaHash.get(k);
		
				retroalimentacion=retroalimentacion+"</td>";
			}
			retroalimentacion=retroalimentacion+"</tr>\r\n </tbody>\r\n</table>\r\n";

		 }
		 retroalimentacion=retroalimentacion+"]]></text>";
		 return tablaHash;
	}

}
