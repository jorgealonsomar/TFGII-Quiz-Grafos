package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.GrafoNoDirigido;
import modelo.grafo.ListaDeArcos;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

public abstract class PreguntaDeKruskal extends Pregunta {
	
	public static final int PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION = 0;
	public static final int PREGUNTA_ORDEN_DE_SELECCION = 1;
	
	protected ListaDeArcos arcosArbolDeExpansion;
	
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
		arcosArbolDeExpansion = ((GrafoNoDirigido)getGrafo()).algoritmoDeKruskal();
	}
	

	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregKruskal();
	}
	
	
	@Override
	protected abstract void construirEnunciado();
	
	
	@Override
	protected abstract void construirParteAResponder();
	
	
	@Override
	protected abstract void construirRespuestaCorrecta();

	@Override
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.algoritmoDeKruskal, grafoDirigido);
	}


	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregKruskal();
	}

}
