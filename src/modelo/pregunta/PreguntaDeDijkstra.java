package modelo.pregunta;

import java.util.ArrayList;

import modelo.ResultadosDijkstra;
import modelo.Semilla;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

public abstract class PreguntaDeDijkstra extends Pregunta {
	
	public static final int PREGUNTA_DISTANCIAS_MAS_CORTAS = 0;
	public static final int PREGUNTA_RUTA_MAS_CORTA = 1;
	public static final int PREGUNTA_ORDEN_DE_SELECCION = 2;
	
	protected ResultadosDijkstra resultadosDijkstra;
	protected ArrayList<Integer> distanciasAlNodoOrigen;
	protected ArrayList<Integer> caminoAlNodoObjetivo;
	
	/** Constructor de la clase */
	public PreguntaDeDijkstra(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo, Integer tipoPregunta) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, true, visualizacionGrafo, tipoPregunta);
	}
	
	
	public PreguntaDeDijkstra(Semilla semilla){
		super(semilla);
	}
	
	
	@Override
	protected void aplicarAlgoritmo(){
		resultadosDijkstra = getGrafo().algoritmoDeDijkstra(0);
		
		distanciasAlNodoOrigen = resultadosDijkstra.getDistanciasAlNodoOrigen();
	}
	
	
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregDijkstra();
	}
	
	
	@Override
	protected abstract void construirEnunciado();

	
	@Override
	protected abstract void construirParteAResponder();
	
	
	@Override
	protected abstract void construirRespuestaCorrecta();
	
	
	@Override
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.algoritmoDeDijkstra, grafoDirigido);
	}
	
	
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregDijkstra();
	}

}
