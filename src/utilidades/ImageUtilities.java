package utilidades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.StructureGraphic.v1.DSTreeParser;
import org.StructureGraphic.v1.DSutils;

import preguntas.Preguntas;

/**
 * Clase ImageUtilities.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 *
 */
public class ImageUtilities {
	/**
	 * Método para utilizar imágenes.
	 * @param file Fichero.
	 * @param m Cadena que contiene la estructura.
	 * @return Imagen.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos. 
	 */
	public static String utilizarImagenes(String file,String m) throws IOException
	{
		String imagen;
		File outputfile = new File(file);
		ImageIO.write( DSutils.fromDS(DSTreeParser.parseSimpleMultiline(m),60,43), "png", outputfile);
		BufferedImage imgpng = ImageIO.read(new File(file));
		imagen = FileUtilities.encodeToString(imgpng,"png");
		 
		imagen = imagen.replaceAll("[\n\r]","");
		outputfile.delete();
		return imagen;
	}
	
	/**
	 * Método que introduce las imágenes en la retroalimentación.
	 * @param i Variable i.
	 * @param pasos Pasos.
	 * @param imagenes Imágenes.
	 * @param retroalimentacion Retroalimentación que recibe.
	 * @param nombre Nombre.
	 * @param m Cadena que contiene la estructura.
	 * @param ruta Ruta del fichero.
	 * @param dato Dato que se pasa.
	 * @return Retroalimentación.
	 * @throws IOException Excepción por motivo de un error en la entrada/salida de datos. 
	 */
	public static String imagenRetroalimentacion(int i,int pasos,ArrayList<String>imagenes,String retroalimentacion,String nombre,String m, String ruta,String dato) throws IOException
	{
		String imagenRetro = utilizarImagenes( ruta+nombre+"_"+i+".png",m);
		imagenes.add(imagenRetro);
		if(i==0)
			retroalimentacion=retroalimentacion+"<text><![CDATA[";
		retroalimentacion=retroalimentacion+"<p> ";
		if(Preguntas.obtenerPropiedades("idioma")==0)
			retroalimentacion=retroalimentacion+"PASO: "+(i+1)+" ELEMENTO: ";
		else
			retroalimentacion=retroalimentacion+"STEP : "+(i+1)+" ELEMENT: ";
		retroalimentacion=retroalimentacion+" "+dato+" </p><p><img src=\"@@PLUGINFILE@@/"+nombre+"_"+i+".png\" alt=\"\">";
		if(i+1==pasos)
		{
			retroalimentacion=retroalimentacion+"</p>]]></text>\r\n";
		}
		else
			retroalimentacion=retroalimentacion+"<p> </p>\r\n";	
		return retroalimentacion;
	}
}
