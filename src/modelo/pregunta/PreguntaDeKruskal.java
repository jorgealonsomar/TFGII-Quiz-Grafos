package modelo.pregunta;

import modelo.Semilla;
import texto.Texto;
import texto.Textos_Archivos;

public class PreguntaDeKruskal extends Pregunta {

	/** Constructor de la clase */
	public PreguntaDeKruskal(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, true, visualizacionGrafo);
	}
	
	
	public PreguntaDeKruskal(Semilla semilla){
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
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.algoritmoDeKruskal, grafoDirigido);
	}


	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregKruskal();
	}

}
