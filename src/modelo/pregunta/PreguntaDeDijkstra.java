package modelo.pregunta;

import modelo.Semilla;

public class PreguntaDeDijkstra extends Pregunta {

	/** Constructor de la clase */
	public PreguntaDeDijkstra(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido) {
		super(nNodos, porcentajeDeArcos, grafoDirigido);
	}

	@Override
	protected void construirTitulo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void construirEnunciado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void construirParteAResponder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void construirRespuestaCorrecta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.algoritmoDeDijkstra, grafoDirigido);
	}

}
