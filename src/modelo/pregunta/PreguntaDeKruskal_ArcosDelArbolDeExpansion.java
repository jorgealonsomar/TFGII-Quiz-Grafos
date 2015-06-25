package modelo.pregunta;

import modelo.grafo.ListaDeArcos;
import texto.Textos_Preguntas;

public class PreguntaDeKruskal_ArcosDelArbolDeExpansion extends PreguntaDeKruskal {

	private ListaDeArcos arcosAPreguntar;
	
	public PreguntaDeKruskal_ArcosDelArbolDeExpansion(Integer nNodos, Double porcentajeDeArcos,
			boolean grafoDirigido, VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
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

}
