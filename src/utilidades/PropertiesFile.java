package utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import preguntas.Preguntas;

/**
 * La clase ArchivosPropiedades.
 */
public class PropertiesFile {
	
	/** El fichero. */
	FileInputStream file = null;
	
	 /**
 	 * Obtiene las propiedades del fichero properties.
 	 *
 	 * @return the properties
 	 */
	public Properties getProperties() 
	  {
	        try
	        {
	        	String curDir = System.getProperty("user.dir");

	        	file = new FileInputStream(curDir+Preguntas.obtenerSeparador()+"Configuracion"+Preguntas.obtenerSeparador()+"parametros.properties");
	         
	            Properties properties = new Properties();
	            
	            properties.load( file );
	        
	            if (!properties.isEmpty()) 
	            {                
	                return properties;
	            } else {
	                return null;
	            }
	        } catch (IOException ex) {
	            return null;
	        }
	   }
 	
 	
}
