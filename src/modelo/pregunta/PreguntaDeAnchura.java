package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

public class PreguntaDeAnchura extends Pregunta {

	private ArrayList<Integer> recorridoEnAnchura;

	/** Constructor de la clase */
	public PreguntaDeAnchura(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, false, visualizacionGrafo);
	}

	
	public PreguntaDeAnchura(Semilla semilla) {
		super(semilla);
	}

	
	@Override
	protected void aplicarAlgoritmo() {
		recorridoEnAnchura = getGrafo().recorrerEnAnchura(0);
	}

	
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregAnchura();
	}

	
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregAnchura();
	}

	
	@Override
	protected void construirParteAResponder() {
		resultadoDeOrdenarElGrafo(recorridoEnAnchura);
	}

	
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta(recorridoEnAnchura);
	}

	
	@Override
	protected Integer getNumPregunta() {
		return Semilla.RECORRIDO_EN_ANCHURA;
	}
	
	
	@Override
	protected boolean esPonderado() {
		return false;
	}


	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregAnchura();
	}

}
