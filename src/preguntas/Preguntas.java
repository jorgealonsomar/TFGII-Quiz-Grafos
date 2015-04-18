package preguntas;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;
import utilidades.PropertiesFile;

/**
 * Clase que obtiene los valores del fichero properties y que contiene la estructura de las preguntas.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public abstract class Preguntas {
	/**
	 * Obtiene el S.O. sobre el que se trabaja.
	 */
	String so = System.getProperty("os.name");
	/**
	 * Obtiene el separador.
	 */
	String separador = System.getProperty("file.separator");
	/**
	 * Crea un fichero.
	 * @param ruta Ruta donde se encuentra el fichero.
	 * @return Fichero.
	 * @throws FileNotFoundException Excepción por fallo al leer el fichero.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static File rutaFichero(String ruta) throws FileNotFoundException, IOException
	{
		
		File file=FileUtilities.createFile(ruta);
		return file;
		
	}
	
	/**
	 * Obtiene las propiedades de valores Integer.
	 * @param propiedad Propiedad que quiere.
	 * @return Propiedad obtenida.
	 */
	public static int obtenerPropiedades(String propiedad)
	{
		Properties properties = new PropertiesFile().getProperties();
		
		String property=properties.getProperty(propiedad);
		return Integer.parseInt(property);
	}
	
	/**
	 * Obtiene las propiedades de valores Double.
	 * @param propiedad Propiedad que quiere.
	 * @return Propiedad obtenida.
	 */
	public static double obtenerPropiedadesDouble(String propiedad)
	{
		Properties properties = new PropertiesFile().getProperties();
		
		String property=properties.getProperty(propiedad);
		return Double.parseDouble(property);
	}
	/**
	 * Obtiene la propiedad de una cadena.
	 * @param propiedad Propiedad que quiero.
	 * @return Propiedad.
	 */
	public static String obtenerPropiedadesString(String propiedad)
	{
		Properties properties = new PropertiesFile().getProperties();
		
		String property=properties.getProperty(propiedad);
		return property;
	}
	
	
	


	
	/**
	 * Asigna un valor a una propiedad.
	 * @param propiedad Propiedad a la que quiero asignar el valor.
	 * @param valor Valor.
	 * @throws FileNotFoundException Excepción por fallo al leer el fichero.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 */
	public static  void ponerPropiedades(String propiedad,String valor) throws FileNotFoundException, IOException
 	{
		Properties properties = new PropertiesFile().getProperties();

		properties.setProperty(propiedad,valor);
		
		String curDir = System.getProperty("user.dir");

   	 
		String archivo=curDir+Preguntas.obtenerSeparador()+"Configuracion"+Preguntas.obtenerSeparador()+"parametros.properties";

		
		properties.store(new FileOutputStream(archivo),null);
	
	
		 
		 
		 
		
	
	}
		 
 	
	
	
	/**
	 * Obtiene la semmilla.
	 * @param seedProperty Semilla que hay en el fichero properties.
	 * @return Semilla.
	 */
	public static int obtenerSemilla(int seedProperty)
	{
		
		int seed;
		if(seedProperty==-1)
			seed= (int) java.lang.Math.round(Math.random()*999999999+0);
		else
			seed=seedProperty;
		
		return seed;
	}
	

	
	/**
	 * Método para escribir en un fichero.
	 * @param fichero Fichero.
	 * @param texto Texto a escribir.
	 */
	public static void escribirFichero(File fichero,String texto)
	{
		 FileUtilities.writeFile(fichero, texto); 
	}
	/**
	 * Escribe la cabecera del fichero.
	 * @param formatos Formato del fichero.
	 * @return Formato.
	 */
	public static FormatosPreguntas empezarFichero(FormatosPreguntas formatos)
	{
		String xmlFormat;

		xmlFormat="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n ";
		xmlFormat=xmlFormat+"<quiz>\r\n\t <question type=\"category\">\r\n\t <category>\r\n\t <text>Estructura de datos</text>\r\n </category>\r\n </question>";
		
	
		formatos.setXml(formatos.getXml(), xmlFormat);
		return formatos;
		
	}
	/**
	 * Parte inicial de las preguntas.
	 * @param tipo Tipo de pregunta.
	 * @param formatos Formatos de la pregunta.
	 * @param seed Semilla.
	 * @param tipoPregunta Tipo de pregunta.
	 * @return Formatos de la pregunta.
	 */
	public static FormatosPreguntas empezarPreguntas(String tipo,FormatosPreguntas formatos, int seed,String tipoPregunta)
	{
		int idioma=obtenerPropiedades("idioma");
		String xmlFormat;
		String giftFormat;
		String question;
		
		
	
		
		String sem;
		idioma=obtenerPropiedades("idioma");
		//System.out.println(idioma);
		if(idioma==0)
			sem=" Semilla=";
		else
			sem=" Seed=";
		xmlFormat=" <question type=\""+tipoPregunta+"\"> \r\n\t <name> \r\n\t <text>"+ tipo + sem +seed +"</text>\r\n</name> \r\n<questiontext format=\"html\">\r\n\t <text>\r\n\t  <![CDATA[<p> ";
		giftFormat="::"+ tipo +sem+seed +"::\r\n";
		question="\n\n"+ tipo +sem+seed+"\n";
		
		
		formatos.setXml(formatos.getXml(), xmlFormat);
		formatos.setGift(formatos.getGift(),giftFormat);
		formatos.setEnunciado(formatos.getEnunciado(), question);
		return formatos;
	}
	
	/**
	 * Escritura de la parte de la respuesta.
	 * @param tam tamaño.
	 * @param formatos Formatos de la pregunta.
	 * @param feedBack Feedback que se muestra.
	 * @param enunciado Enunciado de la pregunta con elementos tipo Integer.
	 * @param res Respuesta.
	 * @param cloze Indica si es formato cloze.
	 * @return Formatos de la pregunta.
	 */
	public static FormatosPreguntas escribirRespuestas(int tam,FormatosPreguntas formatos,String feedBack,ArrayList<Integer>enunciado,ArrayList<Integer>res, boolean cloze)
	{
		String xmlFormat=" ";
		String  giftFormat=" ";
		String question=" ";
		String respuesta="";
		 giftFormat=giftFormat+"{\r\n ";
		 xmlFormat=xmlFormat+" </p> ";
		 xmlFormat=xmlFormat+"<p> ";
		 giftFormat=giftFormat+"=%100%";
		for (int i=0;i<tam;i++)
		 {
			 xmlFormat=xmlFormat+"{1:MULTICHOICE:";
			 if(res.get(i)!=null)
				 giftFormat=giftFormat+res.get(i)+" ";
			 else
				 giftFormat=giftFormat+"\\* ";
			 if(res.get(i)!=null)
			 {
				 feedBack="El resultado correcto es el número "+res.get(i);
				 respuesta=respuesta+res.get(i)+" ";
				 question=question+res.get(i)+" ";
			 }else
			 {
				 feedBack="El resultado correcto es el número "+res.get(i);
				 respuesta=respuesta+"*"+" ";
				 question=question+"*"+" ";
			 }
			 for(int j=0;j<enunciado.size();j++)
			 {
				 if(j==0)
				 {
					 if(enunciado.get(j)!=res.get(i))
						 xmlFormat=xmlFormat+"~";
					 else
						 xmlFormat=xmlFormat+"=";
				 }
				 else
				 {
					 xmlFormat=xmlFormat+"~";
					 if(enunciado.get(j)==res.get(i))
						 xmlFormat=xmlFormat+"=";
				 }
				if(enunciado.get(j)!=null)
					xmlFormat=xmlFormat+enunciado.get(j)+"#"+feedBack;
				else
					xmlFormat=xmlFormat+"*"+"#"+feedBack;
				 
			 }
			 xmlFormat=xmlFormat+"} ";
	
		 }
		
		if(!cloze)
			xmlFormat="";
		
		 formatos.setXml(formatos.getXml(), xmlFormat);
		 formatos.setGift(formatos.getGift(),giftFormat);
		formatos.setEnunciado(formatos.getEnunciado(),question);
		formatos.setRespuesta(formatos.getRespuesta(),respuesta);
		return formatos;
	}
	/**
	 * Escritura de la parte de la respuesta.
	 * @param tam tamaño.
	 * @param formatos Formatos de la pregunta.
	 * @param feedBack Feedback que se muestra.
	 * @param enunciado Enunciado de la pregunta con elementos tipo Character.
	 * @param res Respuesta.
	 * @param cloze Indica si es formato cloze.
	 * @return Formatos de la pregunta.
	 */
	public static FormatosPreguntas escribirRespuestasChar(int tam,FormatosPreguntas formatos,String feedBack,ArrayList<Character>enunciado,ArrayList<Character>res, boolean cloze)
	{
		String xmlFormat=" ";
		String  giftFormat=" ";
		String question=" ";
		String respuesta="";
		 xmlFormat=xmlFormat+"<p> ";
		 giftFormat=giftFormat+"=%100%";
		for (int i=0;i<tam;i++)
		 {
			 xmlFormat=xmlFormat+"{1:MULTICHOICE:";
			 if(res.get(i)!=null)
				 giftFormat=giftFormat+res.get(i)+" ";
			 else
				 giftFormat=giftFormat+"* ";
			 feedBack="El resultado correcto es la letra "+res.get(i);
			 respuesta=respuesta+res.get(i)+" ";
			 question=question+res.get(i)+" ";
			 for(int j=0;j<enunciado.size();j++)
			 {
				 if(j==0)
				 {
					 if(enunciado.get(j)!=res.get(i))
						 xmlFormat=xmlFormat+"~";
					 else
						 xmlFormat=xmlFormat+"=";
				 }
				 else
				 {
					 xmlFormat=xmlFormat+"~";
					 if(enunciado.get(j)==res.get(i))
						 xmlFormat=xmlFormat+"=";
				 }
				if(enunciado.get(j)!=null)
					xmlFormat=xmlFormat+enunciado.get(j)+"#"+feedBack;
				else
					xmlFormat=xmlFormat+"*"+"#"+feedBack;
				 
			 }
			 xmlFormat=xmlFormat+"} ";
	
		 }
		if(!cloze)
			xmlFormat="";
		 formatos.setXml(formatos.getXml(), xmlFormat);
		 formatos.setGift(formatos.getGift(),giftFormat);
		formatos.setEnunciado(formatos.getEnunciado(),question);
		formatos.setRespuesta(formatos.getRespuesta(),respuesta);
		res.clear();
		return formatos;
	}
	
	/**
	 * Parte final de la pregunta.
	 * @param formatos Formatos de la pregunta.
	 * @param fileXml Fichero para el formato xml.
	 * @param fileGift Fichero para el formato GIFT.
	 */
	public static void finalizarEnunciado(FormatosPreguntas formatos,File fileXml,File fileGift)
	{
		formatos.setGift(formatos.getGift(),"}\r\n\r\n");
		formatos.setXml(formatos.getXml()," </p> </p>]]>\r\n</text> \r\n");	
		
	}
	
	/**
	 * Obtiene el separador.
	 * @return Separador.
	 */
	public static String obtenerSeparador()
	{
		
		 String separador = System.getProperty("file.separator");
		 return separador;
		
	}
	
	
	/**
	 * Parte final de la pregunta xml.
	 * @param formatos Formatos de la pregunta.
	 * @param fileXml Fichero para el formato xml.
	 */
	public static void finalizarEnunciadoXml(FormatosPreguntas formatos,File fileXml)
	{
		formatos.setXml(formatos.getXml()," </p> </p>]]>\r\n</text> \r\n");	
		
	}
	/**
	 * Parte final de la pregunta GIFT.
	 * @param formatos Formatos de la pregunta.
	 * @param fileGift Fichero para el formato GIFT.
	 */
	public static void finalizarEnunciadoGift(FormatosPreguntas formatos,File fileGift)
	{
		formatos.setGift(formatos.getGift(),"}\r\n\r\n");
		
	}
	/**
	 * Finaliza la pregunta A.
	 * @param formatos Formato.
	 * @param retroalimentacion Retroalimentación.
	 * @param retro Retroalimentación.
	 */
	public static void finalizarPreguntaA(FormatosPreguntas formatos,boolean retroalimentacion,String retro)
	{
		formatos.setXml(formatos.getXml()," \r\n </questiontext>\r\n<generalfeedback format=\"html\"> \r\n" );
		if(retroalimentacion)
		{
			formatos.setXml(formatos.getXml(),retro);	
		}
		formatos.setXml(formatos.getXml(),	"\r\n</generalfeedback>");
		
	}
	/**
	 * Finaliza la pregunta B.
	 * @param formatos Formato.
	 */
	public static void finalizarPreguntaB(FormatosPreguntas formatos)
	{
		formatos.setXml(formatos.getXml()," \r\n 	<answer fraction=\"100\" format=\"moodle_auto_format\"> \r\n <text> "+formatos.getRespuesta()+" </text>\r\n <feedback format=\"html\">\r\n	 <text></text>\r\n    </feedback>\r\n  </answer>\r\n");
		formatos.setRespuesta("", "");

	}
	/**
	 * Finaliza la pregunta.
	 * @param formatos Formato.
	 */
	public static void finalizarPregunta(FormatosPreguntas formatos)
	{
		formatos.setXml(formatos.getXml()," \r\n </question>\r\n");
	
	}
	/**
	 * Finaliza el documento.
	 * @param formatos Formatos.
	 * @param fileXml Fichero xml.
	 * @param fileGift Fichero GIFT.
	 */
	public static void finalizarDocumentos(FormatosPreguntas formatos,File fileXml,File fileGift)
	{
		formatos.setXml(formatos.getXml(),"</quiz>\r\n");
		escribirFichero(fileXml, formatos.getXml()); 
		escribirFichero(fileGift,formatos.getGift());
		Logger.getLogger("logger").log(Level.INFO,formatos.getEnunciado()+"\n");
	}
	/**
	 * Método para obtener datos.
	 * @param hashSize Tamaño de la tabla hash.
	 * @param formatos Formatos de la pregunta.
	 * @param enunciado Enunciado de la pregunta con elementos tipo Integer.
	 * @return Formatos de la pregunta.
	 */
	public static FormatosPreguntas obtenerDatos(int hashSize,FormatosPreguntas formatos, ArrayList<Integer>enunciado)
	{
		 for(int i=0;i<hashSize;i++)
		 {
			formatos.setXml(formatos.getXml(),  enunciado.get(i)+" ");
			formatos.setGift(formatos.getGift(), enunciado.get(i)+" ");
			formatos.setEnunciado(formatos.getEnunciado(),  enunciado.get(i)+" ");
		 }
		return formatos;
	}
	/**
	 * Método para obtener datos sin imágenes.
	 * @param hashSize Tamaño de la tabla hash.
	 * @param formatos Formatos de la pregunta.
	 * @param enunciado Enunciado de la pregunta con elementos tipo Integer.
	 * @return Formatos de la pregunta.
	 */
	public static FormatosPreguntas obtenerDatosSinImagen(int hashSize,FormatosPreguntas formatos, ArrayList<Integer>enunciado)
	{
		 for(int i=0;i<hashSize;i++)
		 {
			
			formatos.setGift(formatos.getGift(), enunciado.get(i)+" ");
			formatos.setEnunciado(formatos.getEnunciado(),  enunciado.get(i)+" ");
		 }
		return formatos;
	}
	/**
	 * Método para obtener datos.
	 * @param hashSize Tamaño de la tabla hash.
	 * @param formatos Formatos de la pregunta.
	 * @param enunciado Enunciado de la pregunta con elementos tipo Character.
	 * @return Formatos de la pregunta.
	 */
	public static FormatosPreguntas obtenerDatosChar(int hashSize,FormatosPreguntas formatos, ArrayList<Character>enunciado)
	{
		 for(int i=0;i<hashSize;i++)
		 {
			formatos.setXml(formatos.getXml(),  enunciado.get(i)+" ");
			formatos.setGift(formatos.getGift(), enunciado.get(i)+" ");
			formatos.setEnunciado(formatos.getEnunciado(),  enunciado.get(i)+" ");
		 }
		return formatos;
	}
	
	
	
}




