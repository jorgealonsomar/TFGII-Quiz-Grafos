package interfaz;

import java.util.ArrayList;

import javax.swing.JComboBox;

import texto.Idioma;
import texto.Texto;

/**
 * Combo box que permite la selección de entre distintos textos, y que está adaptada
 * para ejecutar cambios de idioma.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class JComboBoxTextos extends JComboBox<String> {
	
	/**
	 * Textos pertenecientes al combo box.
	 */
	ArrayList<Texto> textos = new ArrayList<Texto>();
	
	
	/**
	 * Almacena un nuevo Texto.
	 * @param nuevoTexto
	 *            Nuevo texto a añadir.
	 */
	public void addTexto(Texto nuevoTexto){
		textos.add(nuevoTexto);
	}
	
	
	/**
	 * Convierte los textos contenidos al nuevo idioma.
	 * @param nuevoIdioma
	 *            Nuevo idioma establecido.
	 */
	public void actualizar(Idioma idioma){
		removeAllItems();
		for(Texto texto : textos){
			addItem(texto.getString(idioma));
		}
	}
}