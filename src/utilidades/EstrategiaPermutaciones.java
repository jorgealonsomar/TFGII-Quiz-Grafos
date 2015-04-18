package utilidades;

import java.util.ArrayList;
import java.util.Random;


/**
 * Clase EstrategiaPermutaciones.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public interface EstrategiaPermutaciones {
	/**
	 * Permutación de los datos.
	 * @param array Array de caracteres de la respuesta.
	 * @param rnd Parámetro random.
	 * @return Respuesta
	 */
	public RespuestaGenerada permute(ArrayList<Character> array,Random rnd );

	
}
