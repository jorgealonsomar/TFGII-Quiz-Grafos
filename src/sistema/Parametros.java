package sistema;

import util.Idioma;

public class Parametros {
	
	/** Idioma en el que se están mostrando los textos del programa */
	private Idioma idioma = Idioma.ESP;
	
	/** Devuelve el idioma en el que se están mostrando los textos del programa */
	public Idioma getIdioma() {
		return idioma;
	}
	
	/** Establece el idioma en el que se mostrarán los textos del programa */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	/** Cambia el idioma en el que se mostrarán los textos del programa */
	public void switchIdioma() {
		if(idioma == Idioma.ESP){
			idioma = Idioma.ENG;
		} else {
			idioma =Idioma.ESP;
		}
	}
}
