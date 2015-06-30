package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.ListaDeArcos;
import texto.Textos_Preguntas;

public class PreguntaDePrim_ArcosDelArbolDeExpansion extends PreguntaDePrim {
	
	private ListaDeArcos arcosAPreguntar;
	
	public PreguntaDePrim_ArcosDelArbolDeExpansion(Integer nNodos, Double porcentajeDeArcos,
			 VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, visualizacionGrafo);
	}
	
	
	public PreguntaDePrim_ArcosDelArbolDeExpansion(Semilla semilla){
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
		construirParteAResponder_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar, arcosDelArbolDeExpansion);
	}
	
	
	@Override
	protected void construirRespuestaCorrecta() {
		construirRespuestaCorrecta_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar, arcosDelArbolDeExpansion);
	}
	
	
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_PRIM_ARCOS_DEL_ARBOL_DE_EXPANSION;
	}


	@Override
	protected Integer getTipoDePregunta() {
		return PreguntaDePrim.PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION;
	}

}
