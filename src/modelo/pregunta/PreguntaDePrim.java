package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.GrafoNoDirigido;
import modelo.grafo.ListaDeArcos;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

public abstract class PreguntaDePrim extends Pregunta {
	
	public static final int PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION = 0;
	public static final int PREGUNTA_ORDEN_DE_SELECCION = 1;
	
	protected ListaDeArcos arcosDelArbolDeExpansion;
	
	/** Constructor de la clase */
	public PreguntaDePrim(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, false, true, visualizacionGrafo);
	}
	
	
	public PreguntaDePrim(Semilla semilla) {
		super(semilla);
	}
	
	
	@Override
	protected void aplicarAlgoritmo() {
		arcosDelArbolDeExpansion = ((GrafoNoDirigido)getGrafo()).algoritmoDePrim();
	}
	
	
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregPrim();
	}
	
	
	@Override
	protected abstract void construirEnunciado();
	
	
	@Override
	protected abstract void construirParteAResponder();
	
	
	@Override
	protected abstract void construirRespuestaCorrecta();
	
	
	@Override
	protected boolean esPonderado() {
		return true;
	}
	
	
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregPrim();
	}
	
}
