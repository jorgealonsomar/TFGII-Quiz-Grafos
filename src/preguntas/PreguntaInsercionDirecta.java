package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;


/**
 * Generador de preguntas de inserción directa.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaInsercionDirecta extends Preguntas {
	/**
	 * Array que contendrá los datos.
	 */
	private static ArrayList<Integer>insDir;
	/**
	 * Array auxiliar.
	 */
	private static ArrayList<Integer>insDir2;
	/**
	 * Array auxiliar.
	 */
	private static ArrayList<Integer>original;
	/**
	 * Array que contiene el resultado.
	 */
	private static ArrayList<Integer>arrayObjetivo;
	/**
	 * Pasos del proceso.
	 */
	private static int step=0;
	/**
	 * Paso concreto que va a pedir en la solución.
	 */
	private static int pasoAbuscar;
	/**
	 * Cadena con pasos intermedios.
	 */
	private static String txtPasos="";
	/**
	 * Cadena que contiene el separador.
	 */
	static String separador=Preguntas.obtenerSeparador();
	/**
	 * Informa del idioma en que se solicita la pregunta.
	 */
	static int idioma=obtenerPropiedades("idioma");
	/**
	 * Cadena donde se guardará la retroalimentación.
	 */
	private static String retroalimentacion="";
	/**
	 * Variable que indica si se puede resolver.
	 */
	private static boolean resolver=false;
	
	
	/**
	 * Método para generar preguntas de inserción directa.
	 * @param numQuestions Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static void preguntaInsercion(int numQuestions) throws FileNotFoundException, IOException
	{
		FormatosPreguntas formatos=new FormatosPreguntas();
		Random rnd = new Random();				
			
		int seed;
		int seedProperty;
		int iDSize;
				
		String feedBack="";
		
		String ruta = Preguntas.obtenerPropiedadesString("ruta");
		ruta=ruta+separador+"InsercionDirecta";
		File directorio=new File(ruta);

		FileUtilities.crearDirectorio(directorio);
		File fileXml=null;
		File fileGift=null;
		
		Preguntas.empezarFichero(formatos);
		String filenameGift=ruta+separador+"PreguntaInsercionDirectaGift_"+FileUtilities.obtenerFecha()+".txt";
		String filenameXml=ruta+separador+"PreguntaInsercionDirectaXml_"+FileUtilities.obtenerFecha()+".xml";
		fileXml = rutaFichero(filenameXml);
		fileGift = rutaFichero(filenameGift);
	
		iDSize=obtenerPropiedades("IDSize");
		idioma=obtenerPropiedades("idioma");
		seedProperty=obtenerPropiedades("seedID");

		String enun1=null;
		String enun2=null;
		String enun3=null;
		String enun4=null;

		if(idioma==0)
		{
			enun1=obtenerPropiedadesString("iDEnun1Esp");
			enun2=obtenerPropiedadesString("dEnun2Esp");	
			enun3=obtenerPropiedadesString("dEnun3Esp");
			enun4=obtenerPropiedadesString("dEnun4Esp");
		}
		if(idioma==1)
		{
			enun1=obtenerPropiedadesString("iDEnun1Eng");
			enun2=obtenerPropiedadesString("dEnun2Eng");
			enun3=obtenerPropiedadesString("dEnun3Eng");
			enun4=obtenerPropiedadesString("dEnun4Eng");
		}
		int contar=0;	
		boolean continuar=true;
		while(continuar)
		{
			seed=obtenerSemilla(seedProperty);
			rnd.setSeed(seed);
					
			contar++;
			if (contar==numQuestions)
				continuar=false;
		
			if(idioma==0)
				formatos=empezarPreguntas("Pregunta inserción directa. ", formatos,  seed,"cloze");
			else
				formatos=empezarPreguntas("Insertion sorting question. ", formatos,  seed,"cloze");
 
			ArrayList<Integer>aux= new ArrayList<Integer>();
			for(int i=0;i<99;i++)
				aux.add(i);		 	
			Collections.shuffle(aux, rnd);			 

			insDir= new ArrayList<Integer>();
			insDir2= new ArrayList<Integer>();
			original= new ArrayList<Integer>();
			for(int i=0;i<iDSize;i++)
			{
				insDir.add(i, aux.get(i));					
				insDir2.add(i, aux.get(i));
				original.add(i, aux.get(i));
			}		
		 		 
			insercionDirecta(insDir);	
    	
		
				 
			formatos.setXml(formatos.getXml(),enun1+"  ");
			formatos.setGift(formatos.getGift(),enun1+"  ");
			formatos.setEnunciado(formatos.getEnunciado(),enun1+"  ");
			formatos= obtenerDatos(iDSize,formatos,original);
		 
			txtPasos="";
			insDir=insDir2;
			pasoAbuscar= 3+(rnd.nextInt(step-2));
			resolver=true;
			insercionDirecta(insDir);	
		

			formatos.setXml(formatos.getXml(),"</p>"+enun2+ pasoAbuscar+enun3 +txtPasos);
			formatos.setGift(formatos.getGift(),"\r\n"+enun2+ pasoAbuscar+ " \r\t  "+enun3 +txtPasos);
			formatos.setEnunciado(formatos.getEnunciado(),"\n "+ enun2 +" "+ pasoAbuscar+ enun3+"\n"+txtPasos+enun4);
	

			formatos=escribirRespuestas(iDSize,formatos, feedBack,original,arrayObjetivo,true);
			finalizarEnunciado(formatos, fileXml,fileGift );
			finalizarPreguntaA(formatos,true,retroalimentacion );	
			resolver=false;
			finalizarPregunta(formatos );
			arrayObjetivo.clear();
			aux.clear();
			insDir.clear();
			step=0;
			pasoAbuscar=0;
			retroalimentacion="";

		}

		finalizarDocumentos( formatos, fileXml, fileGift);
			
	}


	/**
	 * Realiza el proceso de inserción directa.
	 * @param insDir Array con los datos.
	 * @return Array con el resultado.
	 */
	public static  ArrayList<Integer> insercionDirecta(ArrayList<Integer>insDir){
		
	
		int aux, cambios=0,cambiosAnt=0;
		step=0;
		boolean pasado1 = false;
		boolean pasado2 = false;
		boolean interc=false;
		boolean solFinal=false;
		int solFi=2;
		retroalimentacion="<text> <![CDATA[";
		for(int i=1; i<insDir.size(); i++)
		{			
			aux=(Integer)insDir.get(i);
			int j=i-1;
			
			while(j>=0 && (Integer)insDir.get(j)>aux)
			{
				insDir.set(j+1, insDir.get(j));
				j--;
				cambios++;				
			}
			insDir.set(j+1, aux);
			
			if(cambios!=cambiosAnt){
				step++;
				cambiosAnt=cambios;
				interc=true;
			}
			
		
			if((step==1 && !pasado1)|| (step==2 && !pasado2))
			{
				if(step==1)
					pasado1=true;
				if(step==2)
					pasado2=true;
				if(idioma==0)
					txtPasos=txtPasos+"--" +" Intercambio "+ step + "--";
				if(idioma==1)	
					txtPasos=txtPasos+"--" +" Exchange "+ step + "--";
					
				txtPasos=txtPasos+insDir.toString();						
				
				txtPasos=txtPasos+"\r\n";
				
			}
			if(resolver && interc==true )
			{
				if(step==pasoAbuscar)
				{	
					solFi--;
					solFinal=true;
					if(idioma==0)
						retroalimentacion=retroalimentacion+" RESPUESTA CORRECTA: ";
					else
						retroalimentacion=retroalimentacion+" CORRECT ANSWER: ";
				}
				if(solFi>0)
				{
					retroalimentacion=retroalimentacion+"<table border=\"3\" align=\"center\"><tbody>\n<tr>\n";
					for(int k=0;k<insDir.size();k++)
					{
						retroalimentacion=retroalimentacion+"<td>"+insDir.get(k)+"</td>";
					}
					retroalimentacion=retroalimentacion+"</tr>\r\n </tbody>\r\n</table>\r\n";
					interc=false;
					if(solFinal==true)
						solFi--;
					solFinal=false;
				}
			}
			if(step==pasoAbuscar)
			{
				arrayObjetivo= new ArrayList<Integer>();
				for(int x=0; x<insDir.size(); x++)
					arrayObjetivo.add(x, insDir.get(x));				
			}			
		}
		if(resolver)
			retroalimentacion=retroalimentacion+"</table>]]></text>";
		return arrayObjetivo;
	}	
	
	/**
	 * Obtiene el paso a buscar.
	 * @param paso Paso a buscar.
	 */
	public void setPasoAbuscar(int paso){
		pasoAbuscar=paso;
	}
	
	/**
	 * Introduce datos en un array.
	 * @param array Array.
	 */
	public void setinsDir(ArrayList<Integer>array){
		for(int i=0;i<array.size();i++)	{
			insDir.add(array.get(i));
		}
	}
	
	/**
	 * Devuelve un array.
	 * @return Array objetivo.
	 */
	public ArrayList<Integer> getarrayObjetivo(){
		return arrayObjetivo; 
	}
}
