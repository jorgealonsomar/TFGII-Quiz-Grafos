package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.ListaDeArcos;
import texto.Textos_Preguntas;

public class PreguntaDeKruskal_ArcosDelArbolDeExpansion extends PreguntaDeKruskal {

	private ListaDeArcos arcosAPreguntar;
	
	public PreguntaDeKruskal_ArcosDelArbolDeExpansion(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, visualizacionGrafo);
	}
	
	
	public PreguntaDeKruskal_ArcosDelArbolDeExpansion(Semilla semilla){
		super(semilla);
	}
	
	
	@Override
	protected void aplicarAlgoritmo() {
		super.aplicarAlgoritmo();
		
		arcosAPreguntar = aplicarAlgoritmo_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar);
	}
	
	
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregPrim_ArcosDelArbolDeExpansion();
	}
	
	
	@Override
	protected void construirParteAResponder() {
		construirParteAResponder_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar, arcosArbolDeExpansion);
	}
	
	
	@Override
	protected void construirRespuestaCorrecta() {
		construirRespuestaCorrecta_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar, arcosArbolDeExpansion);
	}
	
	
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_KRUSKAL_ARCOS_DEL_ARBOL_DE_EXPANSION;
	}


	@Override
	protected Integer getTipoDePregunta() {
		return PreguntaDeKruskal.PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION;
	}
	
}
