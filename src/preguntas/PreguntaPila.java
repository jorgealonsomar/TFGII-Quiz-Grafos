package preguntas;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import java.util.Random;


import utilidades.EstrategiaPermutacionCaracteres;
import utilidades.EstrategiaPermutacionOperaciones;
import utilidades.EstrategiaPermutaciones;
import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;
import utilidades.RespuestaGenerada;

/**
 * Clase generadora de preguntas de pilas.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PreguntaPila  extends Preguntas{
	/**
	 * Método preguntaPila.
	 * @param numQuestions Número de preguntas.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static void preguntaPila(int numQuestions) throws FileNotFoundException, IOException
		{
		Random rnd = new Random();
		String separador=Preguntas.obtenerSeparador();
		
		
		FormatosPreguntas formatos=new FormatosPreguntas();
		
		int seed;
		int seedProperty;
		int stackSize;
		int numberOptions;
		int correctNumber;
		int incorrectNumber;
		File fileXml = null;
		File fileGift = null;
		boolean continuar=true;
	
		String ruta = Preguntas.obtenerPropiedadesString("ruta");
		ruta=ruta+separador+"Pilas";
		File directorio=new File(ruta);

		FileUtilities.crearDirectorio(directorio);
		EstrategiaPermutaciones estrategiaCaracteres=new EstrategiaPermutacionCaracteres();
		EstrategiaPermutaciones estrategiaOperaciones=new EstrategiaPermutacionOperaciones();
		
		double correctParameter;
		double score;

		Preguntas.empezarFichero(formatos);
		String filenameGift=ruta+separador+"PreguntaPila_"+FileUtilities.obtenerFecha()+".txt";
		String filenameXml=ruta+separador+"PreguntaPilaXml_"+FileUtilities.obtenerFecha()+".xml";
		fileXml=rutaFichero(filenameXml);
		fileGift=rutaFichero(filenameGift);

		String pregunta="";
		String text="";

		ArrayList<Character> array=new ArrayList<Character>();
		ArrayList<Character> corrects=new ArrayList<Character>();
		ArrayList<Integer>correctPositions=new ArrayList<Integer>();
		
		RespuestaGenerada result;
		RespuestaGenerada options;

		stackSize=obtenerPropiedades("stackSize");
		
		seedProperty=obtenerPropiedades("seedStack");
		
		numberOptions=obtenerPropiedades("numberOption");
		
		
		correctParameter=obtenerPropiedadesDouble("correctParameter");
		String enun1= null;
		String enun2= null;
		String enun3 = null; 

		int idioma=obtenerPropiedades("idioma");
		if(idioma==0)
		{
			enun1=obtenerPropiedadesString("pilaEnum1Esp");
			enun2=obtenerPropiedadesString("pilaEnum2Esp");	
			enun3=obtenerPropiedadesString("pilaEnum3Esp");

		}
		if(idioma==1)
		{
			enun1=obtenerPropiedadesString("pilaEnum1Eng");
			enun2=obtenerPropiedadesString("pilaEnum2Eng");
			enun3=obtenerPropiedadesString("pilaEnum3Eng");
		}
		 correctNumber=0;
		 incorrectNumber=0;
		 int contar=0;	
		 boolean error=false;
		 while(continuar)
		 {
		 
			 contar++;
			 if (contar==numQuestions)
				 continuar=false;
			 do
			 {
				 correctPositions.clear();
				 seed=obtenerSemilla(seedProperty);
				 rnd.setSeed(seed);
				 correctNumber=0;
				 incorrectNumber=0;
				 for(int i=1;i<=numberOptions;i++)
				 {
					 if(correctParameter>=rnd.nextDouble())
					 {
						 correctPositions.add( i);
						 correctNumber++;
					 }
					 else
					 {
						 correctPositions.add(null);
						 incorrectNumber++;
					 }
				
				}
				 if(seedProperty!=-1 && (correctNumber==0 || incorrectNumber==0))
				 {
					 error=true;
				 }
			
			 }while((correctNumber==0 || incorrectNumber==0) && error==false);

			 if(!error)
			 {
				 if(idioma==0)
					 formatos=empezarPreguntas("Pregunta pila. ", formatos,  seed,"multichoice");
				else
					formatos=empezarPreguntas("Stack question. ", formatos,  seed,"multichoice");		
			
				 char lastLetter='A';

		    
				 for(int i=1;i<stackSize;i++)
				 {
				
					 lastLetter++;
				 }
			
			
				 text=enun1+" "+ lastLetter+" "+enun2;
				 
				lastLetter='A';
				
					
				for(int i=0;i<stackSize;i++)
				{
					array.add(  lastLetter++);
				}

				 lastLetter--;

				
				if(correctNumber!=0 && incorrectNumber!=0)
				{

					text=text+enun3;
					formatos.setGift(formatos.getGift(),text+"{\r\n");
					formatos.setEnunciado(formatos.getEnunciado(), text);
				
					formatos.setXml(formatos.getXml(),text+"</p>]]></text>\r\n</questiontext>");
					formatos.setXml(formatos.getXml(),"<generalfeedback format=\"moodle_auto_format\">\r\n\t   <text></text>\r\n   </generalfeedback>\r\n");//<generalfeedback format=\"moodle_auto_format\">\r\n\t   <text></text>\r\n   </generalfeedback>\r\n";
					formatos.setXml(formatos.getXml(),"  <defaultgrade>1.0000000</defaultgrade>\r\n   <penalty>0.3333333</penalty>\r\n <hidden>0</hidden>\r\n   <single>false</single>\r\n <shuffleanswers>true</shuffleanswers>\r\n  <answernumbering>abc</answernumbering>\r\n <correctfeedback format=\"moodle_auto_format\">\r\n\t  <text></text>\r\n   </correctfeedback>\r\n");
					formatos.setXml(formatos.getXml()," <partiallycorrectfeedback format=\"moodle_auto_format\">\r\n\t <text></text> \r\n </partiallycorrectfeedback>\r\n  <incorrectfeedback format=\"moodle_auto_format\">\r\n\t <text></text>\r\n  </incorrectfeedback>\r\n");
					pregunta=pregunta+text+"\n";


					
					score=(double)100/correctNumber;
		
					lastLetter='A';	
					for(int i=1,a=0;i<=numberOptions;i++,a++)
					{
						
						formatos.setEnunciado(formatos.getEnunciado(),""+ lastLetter+") ");
						
						if(correctPositions.get(a)!=null)
						{	
							if (correctNumber==1)
							{
								formatos.setGift(formatos.getGift(),"~%100% ");
								formatos.setXml(formatos.getXml(),"<answer fraction=\""+100+"\" format=\"moodle_auto_format\">");
							}
							else
							{
								formatos.setGift(formatos.getGift(),"~%"+score+"% ");
								formatos.setXml(formatos.getXml(),"<answer fraction=\""+score+"\" format=\"moodle_auto_format\">\r\n\t");
							}
							result=estrategiaOperaciones.permute(array, rnd);
							formatos.setGift(formatos.getGift(),result.getText()+" ");
							formatos.setXml(formatos.getXml(),"<text>"+result.getText()+"</text>\r\n");
						
							formatos.setEnunciado(formatos.getEnunciado(),result.getText()+" ");
							
							
							formatos.setEnunciado(formatos.getEnunciado(),"\n");
							if(result.getCorrect())
								corrects.add( lastLetter);
							formatos.setXml(formatos.getXml(),"<feedback format=\"moodle_auto_format\">\r\n\t  <text> "+result.getFeedback()+"</text>\r\n  </feedback>\r\n </answer>\r\n");
							formatos.setGift(formatos.getGift()," #"+result.getFeedback());
							formatos.setGift(formatos.getGift(),"\r\n");
						}else
						{
							if (correctNumber==1)
							{
								formatos.setGift(formatos.getGift(),"~%-100% ");
								formatos.setXml(formatos.getXml(),"<answer fraction=\"-100 \" format=\"moodle_auto_format\">\r\n\t");
							}
							else
							{
								formatos.setGift(formatos.getGift(),"~%-"+score+"% ");
								formatos.setXml(formatos.getXml(),"<answer fraction=\"-"+score+"\" format=\"moodle_auto_format\">\r\n\t");
							}
							options=estrategiaCaracteres.permute(array, rnd);
							while(options.getCorrect())
							{
								options=estrategiaCaracteres.permute(array, rnd);
							}
									
							formatos.setGift(formatos.getGift(),options.getText()+" ");
							
							formatos.setXml(formatos.getXml(),"<text>"+options.getText()+"</text>\r\n");
							formatos.setEnunciado(formatos.getEnunciado(),options.getText()+" ");
							
							formatos.setEnunciado(formatos.getEnunciado(),"\n");
							formatos.setXml(formatos.getXml(),"<feedback format=\"moodle_auto_format\">\r\n\t  <text> "+options.getFeedback()+"</text>\r\n  </feedback>\r\n </answer>\r\n");
							formatos.setGift(formatos.getGift()," #"+options.getFeedback());
							formatos.setGift(formatos.getGift(),"\r\n");
							
						}				
						formatos.setEnunciado(formatos.getEnunciado(),"");
						lastLetter++;
						
					}
					
		
					idioma=obtenerPropiedades("idioma");
					if(idioma==0)
						formatos.setEnunciado(formatos.getEnunciado(),"\nLa respuesta correcta es:");
					else
						formatos.setEnunciado(formatos.getEnunciado(),"\nThe correct answer is:");
					 
					
					formatos.setEnunciado(formatos.getEnunciado(),corrects.toString()+"\n");
					
				
					
					Preguntas.finalizarEnunciadoGift( formatos,fileGift);
					finalizarPregunta(formatos );

					
				}
				corrects.clear();
				array.clear();
				correctPositions.clear();
			}else
				Preguntas.ponerPropiedades("semillaIncorrectaPila", "1");
		
			
		}

		finalizarDocumentos( formatos, fileXml, fileGift);
		corrects.clear();
		array.clear();
		correctPositions.clear();
	}
	
	
	
	/**
	 * Comprueba si el array pasado es una posible solución cuando sea numérica.
	 *
	 * @param possibleSolution Array con la posible solucion de la pila.
	 * @return true si es correcto, false si no lo es.
	 */
	public  boolean comprobarCorrectas(ArrayList<Integer> possibleSolution)
	{
		int number=0;
		int position=0;
		int lastLetter=0+possibleSolution.size()-1;
		boolean bigger=false;
		
		boolean correct=false;
		Deque<Integer> stack = new ArrayDeque<Integer>();

		ArrayList<Integer>aux=new ArrayList<Integer>();
		
		int i=0;
		int taken=0;
		boolean finish=false;
		while(!finish)
		{
			while(possibleSolution.get(position)>=number)
			{
				stack.push(number);
				number++;
				
			}
		
			if(possibleSolution.get(0)==lastLetter)
			{
				bigger=true;
			}
			for(int j=0;j<possibleSolution.size();j++)
			{
				if(possibleSolution.get(j)>possibleSolution.get(i))
				{
					position=j;
					if(possibleSolution.get(position)==lastLetter)
					{
						bigger=true;
					}
					break;
				}
			}
			while(possibleSolution.get(position)>possibleSolution.get(i))
			{
				taken=stack.pop();
				aux.add(taken);
				i++;
			}
			position=i;
			if(bigger)
			{
				while(number<=lastLetter)
				{
					stack.push(number);
					number++;
				}
				
				while(!stack.isEmpty())
				{
					aux.add(stack.pop());
				}
			}
			if(stack.isEmpty())
			{
				finish=true;
			}
		}
	
		
		
		if(aux.equals(possibleSolution))
		
			correct=true;
		else
			correct=false;
		
		
		return correct;
	}
	
}
