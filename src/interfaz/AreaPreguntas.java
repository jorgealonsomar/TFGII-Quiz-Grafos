package interfaz;

import javax.swing.JTextArea;

import modelo.pregunta.Pregunta;
import util.Idioma;

@SuppressWarnings("serial")
public class AreaPreguntas extends JTextArea {
	
	public void imprimirPregunta(Pregunta pregunta, Idioma idioma){
		setText(getText() + "--------------------------------------------------------------");
		setText(getText() + "\n" + pregunta.getTitulo(idioma));
		setText(getText() + "\n\n" + pregunta.getEnunciado(idioma));
		setText(getText() + "\n\n" + pregunta.getGrafo().toString());
		setText(getText() + "\n\n" + pregunta.getParteAResponder(idioma));
		setText(getText() + "\n\n" + pregunta.getRespuestaCorrecta(idioma));
		setText(getText() + "\n\n\n");
	}
}
