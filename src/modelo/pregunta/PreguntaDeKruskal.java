package modelo.pregunta;

import modelo.Semilla;

public class PreguntaDeKruskal extends Pregunta {

	/** Constructor de la clase */
	public PreguntaDeKruskal(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido) {
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
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.algoritmoDeKruskal, grafoDirigido);
	}

}
