package modelo.pregunta;

import modelo.Consigna;
import modelo.Grafo;
import modelo.GrafoDirigido;
import modelo.GrafoNoDirigido;
import util.Idioma;
import util.Texto;

public abstract class Pregunta {
	
	private Grafo grafo;
	
	protected Texto titulo;
	protected Texto enunciado;
	protected Texto parteAResponder;
	protected Texto respuestaCorrecta;
	
	private Consigna consigna;
	
	/** Constructor de la clase.
	 * Crea una nueva pregunta con un grafo aleatorio creado a partir de los parámetros fijados. */
	public Pregunta(Integer nNodos, Double porcentajeDeArcos, boolean esDirigido){
		generarGrafo(nNodos, porcentajeDeArcos, esDirigido);
		
		construirPregunta(esDirigido);
	}
	
	
	/** Constructor de la clase. 
	 * Recupera una pregunta a partir de una consigna */
	public Pregunta(Consigna consigna){
		if(consigna.esDirigido()){
			grafo = new GrafoDirigido(consigna.getMatrizDeAdyacencia());
		} else {
			grafo = new GrafoNoDirigido(consigna.getMatrizDeAdyacencia());
		}
		
		construirPregunta(consigna.esDirigido());
	}
	
	
	 /** (Patrón de diseño Recipe) */
	private void construirPregunta(boolean esDirigido){
		aplicarAlgoritmo();
		
		construirTitulo();
		construirEnunciado();
		construirParteAResponder();
		construirRespuestaCorrecta();
		
		generarConsigna(esDirigido);
	}
	
	
	private void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido){
		if(grafoDirigido){
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos);
		}
	}
	
	
	protected abstract void aplicarAlgoritmo();
	
	
	protected abstract void construirTitulo();

	
	protected abstract void construirEnunciado();
	
	
	protected abstract void construirParteAResponder();
	
	
	protected abstract void construirRespuestaCorrecta();
	
	
	protected abstract void generarConsigna(boolean grafoDirigido);
	
	
	protected void generarConsignaEnFuncionDelTipoDePregunta(Integer tipoDePregunta, boolean grafoDirigido){
		consigna = new Consigna(tipoDePregunta, grafo.getNNodos(), grafoDirigido, grafo.getMatrizDeAdyacencia());
	}
	
	
	public abstract Texto getNombreDeArchivo();
	
	
	public Grafo getGrafo(){
		return this.grafo;
	}
	

	public String getTitulo(Idioma idioma) {
		return titulo.getString(idioma);
	}


	public String getEnunciado(Idioma idioma) {
		return enunciado.getString(idioma);
	}


	public String getParteAResponder(Idioma idioma) {
		return parteAResponder.getString(idioma);
	}
	
	
	public String getRespuestaCorrecta(Idioma idioma){
		return respuestaCorrecta.getString(idioma);
	}
	
	
	public String getCodigoConsigna(){
		return consigna.toString();
	}
	
	
	public String getTextoPregunta(Idioma idioma){
		String textoPregunta = "";
		textoPregunta += "--------------------------------------------------------------";
		textoPregunta += "\n" + getTitulo(idioma);
		textoPregunta += "\n\n" + getEnunciado(idioma);
		textoPregunta += "\n\n" + getGrafo().toString();
		if (idioma == Idioma.ESP) {
			textoPregunta += "\n" + "(consigna: ";
		} else {
			textoPregunta += "\n" + "(password: ";
		}
		textoPregunta += getCodigoConsigna() + ")";
		textoPregunta += "\n\n" + getParteAResponder(idioma);
		textoPregunta += "\n\n" + getRespuestaCorrecta(idioma);
		textoPregunta += "\n\n\n";
		
		return textoPregunta;
	}
	
	
	public String getTextoPreguntaXml(Idioma idioma){
		String textoPregunta = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		textoPregunta += "\n<quiz>";		
		textoPregunta += "\n\t<question type=\"cloze\">";
		textoPregunta += "\n\t\t<name><text>" + getTitulo(idioma) + "</text>";
		textoPregunta += "\n\t\t</name>";
		textoPregunta += "\n\t\t<questiontext>";
		textoPregunta += "\n\t\t\t<text><![CDATA[";
		textoPregunta += "\n\t\t\t" + Texto.adaptarCaracteresAXml(getEnunciado(idioma));
		textoPregunta += "\n</p>" + getGrafo().toStringTabulado(3, true);
		textoPregunta += "\n</p>\t\t\t" + "(consigna: " + Texto.adaptarCaracteresAXml(getCodigoConsigna()) + ")";
		textoPregunta += "\n</p>\t\t\t" + Texto.adaptarCaracteresAXml(getParteAResponder(idioma));
		textoPregunta += "\n\t\t\t]]></text>";
		textoPregunta += "\n\t\t</questiontext>";
		textoPregunta += "\n\t\t\t<generalfeedback>";
		textoPregunta += "\n\t\t\t<text></text>";
		textoPregunta += "\n\t\t</generalfeedback>";
		textoPregunta += "\n\t\t<shuffleanswers>0</shuffleanswers>";
		textoPregunta += "\n\t</question>";
		textoPregunta += "\n</quiz>";
				
		return textoPregunta;
	}
	
}
