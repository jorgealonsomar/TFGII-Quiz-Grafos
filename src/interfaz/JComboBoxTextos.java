package interfaz;

import java.util.ArrayList;

import javax.swing.JComboBox;

import texto.Texto;
import util.Idioma;

@SuppressWarnings("serial")
public class JComboBoxTextos extends JComboBox<String> {
	ArrayList<Texto> textos = new ArrayList<Texto>();
	
	public void addTexto(Texto nuevoTexto){
		textos.add(nuevoTexto);
	}
	
	public void actualizar(Idioma idioma){
		removeAllItems();
		for(Texto texto : textos){
			addItem(texto.getString(idioma));
		}
	}
}