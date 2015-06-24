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
	
	protected ListaDeArcos listaDeArcos;
	
	/** Constructor de la clase */
	public PreguntaDePrim(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, true, visualizacionGrafo);
	}
	
	
	public PreguntaDePrim(Semilla semilla) {
		super(semilla);
	}
	
	
	@Override
	protected void aplicarAlgoritmo() {
		listaDeArcos = ((GrafoNoDirigido)getGrafo()).algoritmoDePrim();
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
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.algoritmoDePrim, grafoDirigido);
	}
	
	
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregPrim();
	}
	
}
