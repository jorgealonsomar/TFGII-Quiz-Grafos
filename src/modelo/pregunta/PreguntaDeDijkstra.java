package modelo.pregunta;

import modelo.Semilla;
import util.Texto;

public class PreguntaDeDijkstra extends Pregunta {

	/** Constructor de la clase */
	public PreguntaDeDijkstra(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido) {
		super(nNodos, porcentajeDeArcos, grafoDirigido);
	}
	
	
	public PreguntaDeDijkstra(Semilla semilla){
		super(semilla);
	}
	
	
	@Override
	protected void aplicarAlgoritmo() {
		//TODO	
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


	@Override
	public Texto getNombreDeArchivo() {
		return Texto.nombreArchivoPregDijkstra();
	}

}
