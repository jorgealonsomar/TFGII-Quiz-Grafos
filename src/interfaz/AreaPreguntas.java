package interfaz;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AreaPreguntas extends JTextArea {

	public void addTexto(String nuevoTexto) {
		setText(getText() + nuevoTexto);
	}
}
