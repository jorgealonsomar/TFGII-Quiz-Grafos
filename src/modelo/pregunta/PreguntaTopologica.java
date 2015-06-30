package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import modelo.grafo.GrafoDirigido;
import modelo.grafo.GrafoNoDirigido;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

public class PreguntaTopologica extends Pregunta {

	private ArrayList<Integer> recorridoTopologico;
	
	/** Constructor de la clase */
	public PreguntaTopologica(Integer nNodos, Double porcentajeDeArcos, VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, true, false, visualizacionGrafo);
	}
	
	
	public PreguntaTopologica(Semilla semilla){
		super(semilla);
	}
	
	
	@Override
	protected void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			boolean esPonderado) {
		if (grafoDirigido) {
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator, true);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator, true);
		}
	}
	
	
	@Override
	protected void aplicarAlgoritmo() {
		recorridoTopologico = ((GrafoDirigido)getGrafo()).recorridoTopologico();
	}
	
	
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregClasificacionTopologica();
	}
	

	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregClasificacionTopologica();
	}
	
	
	@Override
	protected void construirParteAResponder() {
		resultadoDeOrdenarElGrafo(recorridoTopologico);
	}

	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta(recorridoTopologico);
	}
	
	
	@Override
	protected Integer getNumPregunta() {
		return Semilla.CLASIFICACION_TOPOLOGICA;
	}
	
	
	@Override
	protected boolean esPonderado() {
		return false;
	}
	

	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregTopologica();
	}

}
