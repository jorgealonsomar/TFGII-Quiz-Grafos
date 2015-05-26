package modelo.pregunta;

import modelo.Consigna;
import util.Texto;

public class PreguntaTopologica extends Pregunta {

	/** Constructor de la clase */
	public PreguntaTopologica(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido) {
		super(nNodos, porcentajeDeArcos, grafoDirigido);
	}
	
	
	public PreguntaTopologica(Consigna semilla){
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
	protected void generarConsigna(boolean grafoDirigido) {
		super.generarConsignaEnFuncionDelTipoDePregunta(Consigna.clasificacionTopologica, grafoDirigido);
	}


	@Override
	public Texto getNombreDeArchivo() {
		return Texto.nombreArchivoPregTopologica();
	}

}
