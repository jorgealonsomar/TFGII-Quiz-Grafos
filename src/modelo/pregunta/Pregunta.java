package modelo.pregunta;

import modelo.Grafo;
import modelo.GrafoDirigido;
import modelo.GrafoNoDirigido;
import modelo.Semilla;
import util.Idioma;
import util.Texto;

public abstract class Pregunta {
	
	private Grafo grafo;
	
	protected Texto titulo;
	protected Texto enunciado;
	protected Texto parteAResponder;
	protected Texto respuestaCorrecta;
	
	private Semilla semilla;
	
	/** Constructor de la clase.
	 * (Patrón de diseño Recipe) */
	public Pregunta(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido){
		generarGrafo(nNodos, porcentajeDeArcos, grafoDirigido);
		
		construirTitulo();
		construirEnunciado();
		construirParteAResponder();
		construirRespuestaCorrecta();
		
		generarSemilla(grafoDirigido);
	}
	
	
	protected void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido){
		if(grafoDirigido){
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos);
		}
	}
	
	
	protected abstract void construirTitulo();

	
	protected abstract void construirEnunciado();
	
	
	protected abstract void construirParteAResponder();
	
	
	protected abstract void construirRespuestaCorrecta();
	
	
	protected abstract void generarSemilla(boolean grafoDirigido);
	
	
	protected void generarSemillaEnFuncionDelTipoDePregunta(Integer tipoDePregunta, boolean grafoDirigido){
		semilla = new Semilla(tipoDePregunta, grafo.getNNodos(), grafoDirigido, grafo.getMatrizDeAdyacencia());
	}
	
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
	
	
	public String getCodigoSemilla(){
		return semilla.toString();
	}
	
	
	public String getTextoPregunta(Idioma idioma){
		String textoPregunta = "";
		textoPregunta += "--------------------------------------------------------------";
		textoPregunta += "\n" + getTitulo(idioma);
		textoPregunta += "\n\n" + getEnunciado(idioma);
		textoPregunta += "\n\n" + getGrafo().toString();
		textoPregunta += "\n" + "(semilla: " + getCodigoSemilla() + ")";
		textoPregunta += "\n\n" + getParteAResponder(idioma);
		textoPregunta += "\n\n" + getRespuestaCorrecta(idioma);
		textoPregunta += "\n\n\n";
		
		return textoPregunta;
	}
}
