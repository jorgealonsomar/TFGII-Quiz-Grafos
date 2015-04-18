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
 * Generador de preguntas de selección directa.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaSeleccionDirecta extends Preguntas {
	/**
	 * Array que contendrá los datos.
	 */
	private static ArrayList<Integer>selDir;
	/**
	 * Array auxiliar.
	 */
	private static ArrayList<Integer>selDir2;
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
	private static String separador=Preguntas.obtenerSeparador();
	/**
	 * Cadena donde se guardará la retroalimentación.
	 */
	private static String retroalimentacion="";
	/**
	 * Variable que indica si se puede resolver.
	 */
	private static boolean resolver=false;
	/**
	 * Informa del idioma en que se solicita la pregunta.
	 */
	static int idioma=obtenerPropiedades("idioma");
	
	/**
	 * Método para generar preguntas de selección directa.
	 * @param numQuestions Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static void preguntaSeleccion(int numQuestions) throws FileNotFoundException, IOException
	{
		
		
		FormatosPreguntas formatos=new FormatosPreguntas();
		Random rnd = new Random();
		
		File fileXml=null;
		File fileGift=null;
		int seed;
		int seedProperty;
		int sDSize;
		
		ArrayList<Integer>aux;
		String ruta = Preguntas.obtenerPropiedadesString("ruta");
		ruta=ruta+separador+"SeleccionDirecta";
		File directorio=new File(ruta);

		FileUtilities.crearDirectorio(directorio);
		String feedBack="";
		
		idioma=obtenerPropiedades("idioma");
		sDSize=obtenerPropiedades("SDSize");
		
		seedProperty=obtenerPropiedades("seedSD");
		
	
		
			Preguntas.empezarFichero(formatos);
			String filenameGift=ruta+separador+"PreguntaSeleccionDirectaGift_"+FileUtilities.obtenerFecha()+".txt";
			String filenameXml=ruta+separador+"PreguntaSeleccionDirectaXml_"+FileUtilities.obtenerFecha()+".xml";
			fileXml = rutaFichero(filenameXml);
			fileGift = rutaFichero(filenameGift);
		
	

	
		String enun1=null;
		String enun2=null;
		String enun3=null;
		String enun4=null;

				if(idioma==0)
				{System.out.println("Español");
					enun1=obtenerPropiedadesString("sDEnun1Esp");
					enun2=obtenerPropiedadesString("dEnun2Esp");	
					enun3=obtenerPropiedadesString("dEnun3Esp");
					enun4=obtenerPropiedadesString("dEnun4Esp");
				}
				if(idioma==1)
				{System.out.println("Ingles");
					enun1=obtenerPropiedadesString("sDEnun1Eng");
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
			
			
			retroalimentacion="<text> <![CDATA[";
			contar++;
			if (contar==numQuestions)
				continuar=false;
			
			if(idioma==0)
				formatos=empezarPreguntas("Pregunta selección directa. ", formatos,  seed,"cloze");
			else
				formatos=empezarPreguntas("Selection sorting question. ", formatos,  seed,"cloze");
			
			aux= new ArrayList<Integer>();
			for(int i=0;i<99;i++)
				aux.add(i);		 	
			Collections.shuffle(aux, rnd);				
		 
			selDir= new ArrayList<Integer>();
		 selDir2= new ArrayList<Integer>();
		 original= new ArrayList<Integer>();
		 
		 for(int i=0;i<sDSize;i++){
			 selDir.add(i, aux.get(i));
			 selDir2.add(i, aux.get(i)); 
			 original.add(i, aux.get(i));
		 }	 

	
		
		 seleccionDirecta(selDir);
		 
		 
		 
		 formatos.setXml(formatos.getXml(),enun1+"  ");
		 formatos.setGift(formatos.getGift(),enun1+"  ");
		 formatos.setEnunciado(formatos.getEnunciado(),enun1+"  ");
		 formatos= obtenerDatos(sDSize,formatos,original);
		
		 txtPasos="";
		 selDir=selDir2;
		 pasoAbuscar= 3+(rnd.nextInt(step-2));
		 resolver=true;
		 seleccionDirecta(selDir);
		 
		 formatos.setXml(formatos.getXml(),"</p> "+enun2+ pasoAbuscar+enun3 +" </p><p>"+txtPasos+"");
		 formatos.setGift(formatos.getGift(),"\r\n"+enun2+ pasoAbuscar+" \r\t"+enun3 +txtPasos);
		 formatos.setEnunciado(formatos.getEnunciado(),"\n "+ enun2 + pasoAbuscar+ enun3+"\n"+txtPasos/*+"\n"*/+enun4);
		 
		 
		 System.out.println(arrayObjetivo.toString());
		 formatos=escribirRespuestas(sDSize,formatos, feedBack,original,arrayObjetivo,true);
		 
		 finalizarEnunciado(formatos, fileXml,fileGift );
		 finalizarPreguntaA(formatos,true,retroalimentacion);
		 resolver=false;
		 finalizarPregunta(formatos );
		 arrayObjetivo.clear();
		 aux.clear();
		 selDir.clear();
		 step=0;
		 pasoAbuscar=0;
		 retroalimentacion="";
		 
				
			}

				finalizarDocumentos( formatos, fileXml, fileGift);
		
	}
	
	/**
	 * Realiza el proceso de selección directa.
	 * @param selDir Array con los datos.
	 * @return Array con el resultado.
	 */
	public static ArrayList<Integer> seleccionDirecta(ArrayList<Integer>selDir){
		int cambios=0,cambiosAnt=0;
		boolean pasado1=false;
		boolean pasado2=false;
		boolean interc=false;
		boolean solFinal=false;
		int solFi=2;
		//
		step=0;
		txtPasos="";
		int minimo, aux, posMin;
		for(int i=0; i< selDir.size()-1; i++){			
			minimo=selDir.get(i);
			posMin=i;
			for(int j=i; j<selDir.size(); j++){
				if(minimo>selDir.get(j)){
					minimo=selDir.get(j);
					posMin=j;
					cambios++;
				}

			}			
			aux=selDir.get(i);
			selDir.set(i, selDir.get(posMin)); 
			selDir.set(posMin, aux);
			
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
				txtPasos=txtPasos+selDir.toString();
						
				
				txtPasos=txtPasos+"\r\n";
				
				
			}
			if(resolver && interc==true){
				if(step==pasoAbuscar){	
					solFi--;
					solFinal=true;
					if(idioma==0)
						retroalimentacion=retroalimentacion+" RESPUESTA CORRECTA: ";
					else
						retroalimentacion=retroalimentacion+" CORRECT ANSWER: ";
				}
				if(solFi>0){
					retroalimentacion=retroalimentacion+"<table border=\"3\" align=\"center\"><tbody>\n<tr>\n";
					for(int k=0;k<selDir.size();k++){
						retroalimentacion=retroalimentacion+"<td>"+selDir.get(k)+"</td>";
					}
					retroalimentacion=retroalimentacion+"</tr>\r\n </tbody>\r\n</table>\r\n";
					interc=false;
					if(solFinal==true)
						solFi--;
					solFinal=false;
				}				
			}
			
			if(step==pasoAbuscar){
				arrayObjetivo= new ArrayList<Integer>();
				for(int x=0; x<selDir.size(); x++)
					arrayObjetivo.add(x, selDir.get(x));				
			}
		}
		if(resolver)
			retroalimentacion=retroalimentacion+"]]></text>";
		return arrayObjetivo;
	}	
	
	/**
	 * Obtiene el paso a buscar.
	 * @param paso Paso a buscar.
	 */
	public void setPasoAbuscar(int paso)
	{
		pasoAbuscar=paso;
	}
	
}
