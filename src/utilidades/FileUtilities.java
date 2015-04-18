package utilidades;


import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import preguntas.Preguntas;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Clase FileUtilities . 
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */


/**
 * Crea el directorio.
 * @param directorio Directorio.
 */
public class FileUtilities {
	/**
	 * Crea el directorio.
	 * @param directorio Directorio.
	 */
	public static void crearDirectorio(File directorio)
	{
		if(!directorio.exists())
			directorio.mkdirs();
	}
		
	
	/**
	 * Obtiene la fecha.
	 * @return Fecha
	 */
	public static String  obtenerFecha()
	{
	
		Calendar cal1 = Calendar.getInstance();
	    String fecha=""+cal1.get(Calendar.DATE)+"_"+cal1.get(Calendar.MONTH)
	    +"_"+cal1.get(Calendar.YEAR)+"_"+cal1.get(Calendar.HOUR)
	    +"_"+cal1.get(Calendar.MINUTE)+"_"+cal1.get(Calendar.SECOND);
		  return fecha;
	}
	/**
	 * Crea el fichero.
	 * @param filename Nombre del fichero.
	 * @return file Fichero.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 */
	public static File createFile(String filename) throws IOException, FileNotFoundException
	{
	
		File file=new File(filename);
		return file;
	
	}
	
	/**
	 * Escribe el fichero.
	 * @param file Fichero.
	 * @param question Pregunta.
	 */
	public static void writeFile(File file,String question)
	{
		try{
			
			FileOutputStream fileO= new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fileO, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(question);
			bw.close();
		}catch(IOException e){};
	}

	/**
	 * Codifica la imagen.
	 * @param image Imagen.
	 * @param type Tipo.
	 * @return Imagen codificada.
	 */
	public static String encodeToString(BufferedImage image, String type) {
		String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

	/**
	 * Decodifica la imagen.
	 * @param imageString Imagen codificada.
	 * @return Imagen
	 */
	 public static BufferedImage decodeToImage(String imageString) {

	        BufferedImage image = null;
	        byte[] imageByte;
	        try {
	            BASE64Decoder decoder = new BASE64Decoder();
	            imageByte = decoder.decodeBuffer(imageString);
	            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	            image = ImageIO.read(bis);
	            bis.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return image;
	    }	
	 
	 /**
	  * Método que copia el contenido de un fichero a otro.
	  * @param archivoacopiar Archivo a copiar.
	  * @param archivonuevo Nuevo archivo.
	  * @param agregar_si_no Variable que indica si agragar o no.
	  */
	 public static void copia (String archivoacopiar, String archivonuevo, boolean agregar_si_no)
	 {
		 
		
		 try
		 {
			
		
			 FileInputStream ArchivoOrigen = new FileInputStream(archivoacopiar);
			 BufferedInputStream BufferOrigen = new BufferedInputStream(ArchivoOrigen);
		
			
			 FileOutputStream ArchivoSalida = new FileOutputStream (archivonuevo, agregar_si_no);
			 BufferedOutputStream BufferSalida = new BufferedOutputStream(ArchivoSalida);
			
			 
			 byte [] array = new byte[1000];
		
			 int leidos = BufferOrigen.read(array);
			
			 while (leidos > 0)
			 {
		
				 BufferSalida.write(array,0,leidos);
			
				 leidos=BufferOrigen.read(array);
			 }
		
			
			 BufferOrigen.close();
			 BufferSalida.close();
	
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	 }
		/**
		 * Método que hace un listado de los directorios y ficheros.
		 * @param f Fichero.
		 * @param c Cadena de caracteres.
		 * @param ficheros Array de ficheros.
		 * @return Ficheros existentes.
		 */
		public static ArrayList<File> listarDirectorio(File f, String c,ArrayList<File> ficheros) {
	
			if (f.isDirectory()) 
			{
		
				File s[] = f.listFiles();

				for (int i = 0; i < s.length; i++) {
					if (s[i].isDirectory()) {
			
						listarDirectorio(s[i], c + " ",ficheros);
					}
					else if (s[i].isFile()){
			
						ficheros.add(s[i]);

					}
				}
			
			}
			return ficheros;
		}


		/**
		 * Método que permite la unión de varios ficheros.
		 * @return Creación o no del fichero.
		 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos.
		 */
		public static boolean juntarFicheros() throws IOException
		{
			String carpeta = Preguntas.obtenerPropiedadesString("ruta");
			
			ArrayList<File>ficheros=new ArrayList<File>();
			File f1 = new File(carpeta);
			ficheros= listarDirectorio(f1, "",ficheros);
			
			int pos=0;
			boolean correcta=false;

			File anexion=new File(carpeta+Preguntas.obtenerSeparador()+"Anexion");
			String rutaanexion=anexion+Preguntas.obtenerSeparador()+"anexion1";
			FileUtilities.crearDirectorio(anexion);
			File folderTxt = null;
			File folderXml = null;
			 
			for (int k=0;k<ficheros.size();k++)
			{
				if(ficheros.get(k).getName().indexOf("anexion")==0)
					pos++;
			}
			
			 for(int j=pos; j<ficheros.size();j++)
			 {
				 correcta=true;
				 String extension = "";
				 String fileName=ficheros.get(j).getName();
				 int i = fileName.lastIndexOf('.');
				 if (i > 0) {
				     extension = fileName.substring(i+1);
				 }
				
				 if(extension.equals("xml"))
					 folderXml=new File(rutaanexion+".xml") ;
				 else
					 folderTxt= new File(anexion+Preguntas.obtenerSeparador()+"anexion_"+FileUtilities.obtenerFecha()+".txt");
				 
				 if(ficheros.get(j).isFile())
				 {
					 
					if(extension.equals("xml"))
					 {
						 FileUtilities.copia(ficheros.get(j).getAbsolutePath(), folderXml.getAbsolutePath(), true);
					 } else
						 FileUtilities.copia(ficheros.get(j).getAbsolutePath(), folderTxt.getAbsolutePath(), true);
				 }	 
			 }		 
						
			 String linea;
						
					
							
			 File arch = null;
			 FileReader fr = null;
			 BufferedReader br = null;
			 FileWriter fichero = null;
			 PrintWriter pw = null;
						 
			 try {
						       
				 	arch = new File (anexion+Preguntas.obtenerSeparador()+"anexion1.xml");
				 	if(arch.exists())
					{
				 		fr = new FileReader (arch);
						br = new BufferedReader(fr);
						fichero = new FileWriter(anexion+Preguntas.obtenerSeparador()+"anexion_"+FileUtilities.obtenerFecha()+".xml");
						pw = new PrintWriter(fichero);         
						linea= br.readLine();
						pw.write(linea+"\n");
						linea= br.readLine();
						pw.write(linea+"\n");
						while((linea=br.readLine())!=null)
						{
							if ((linea.indexOf("?")<0) &&( linea.indexOf("quiz")<0) ) 
								pw.write(linea+"\n");
								        	
						}
								      
						pw.write("</quiz>");
						pw.close();
						fr.close();
						arch.delete(); 
					 }
				 } catch(Exception e){
					  e.printStackTrace();
			 }
						
			return correcta;				
		}

	/**
	 * Método que copia el fichero de propiedades.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos. 
	 * @throws FileNotFoundException Excepción por fallo al leer el archivo.
	 * @throws InterruptedException Excepción por interrupción del proceso.
	 */
	 public static   void copiarFicherosPropiedades() 
			 throws IOException, FileNotFoundException, InterruptedException { 

		 	String cadena;
			String curDir = System.getProperty("user.dir");
			File carpeta =new File(curDir+Preguntas.obtenerSeparador()+"Configuracion");
			File fichero=new File(curDir+Preguntas.obtenerSeparador()+"Configuracion"+Preguntas.obtenerSeparador()+"parametros.properties");
			if (!carpeta.exists()||!fichero.exists())
			{
				if (!carpeta.exists())
					carpeta.mkdir();
				 
				InputStream is = FileUtilities.class.getResourceAsStream("/configuracion/parametros.properties");
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				FileOutputStream folder= new FileOutputStream(curDir+Preguntas.obtenerSeparador()+"Configuracion"+Preguntas.obtenerSeparador()+"parametros.properties");
				OutputStreamWriter osw = new OutputStreamWriter(folder, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
					
				while((cadena=br.readLine())!=null)
				{
					bw.write(cadena+"\n");
				}
				
				bw.close();
			 }
			
		}

	}
