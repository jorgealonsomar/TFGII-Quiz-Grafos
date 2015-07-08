package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.GrafoNoDirigido;
import modelo.grafo.ListaDeArcos;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

/**
 * Pregunta abstracta de Kruskal.
 * @author Jorge Alonso Márquez
 */
public abstract class PreguntaDeKruskal extends Pregunta {
	
	/**
	 * Valor numérico correspondiente a las preguntas de la clase Arcos del árbol de expansión.
	 */
	public static final int PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION = 0;
	
	/**
	 * Valor numérico correspondiente a las preguntas de la clase Orden de selección.
	 */
	public static final int PREGUNTA_ORDEN_DE_SELECCION = 1;
	
	/**
	 * Arcos pertenecientes al árbol de expansión resultante de haber aplicado el algoritmo de
	 * Kruskal.
	 */
	protected ListaDeArcos arcosArbolDeExpansion;
	

	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaDeKruskal(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, false, true, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDeKruskal(Semilla semilla){
		super(semilla);
	}	
	
	
	/**
	 * Aplica sobre el grafo el algoritmo que corresponde a las preguntas de Kruskal
	 * (el algoritmo de Kruskal).
	 */
	@Override
	protected void aplicarAlgoritmo() {
		arcosArbolDeExpansion = ((GrafoNoDirigido)getGrafo()).algoritmoDeKruskal();
	}
	
	
	/**
	 * Construye el título de esta pregunta de Kruskal.
	 */
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregKruskal();
	}
	
	
	/**
	 * Construye el enunciado de esta pregunta de Kruskal.
	 */
	@Override
	protected abstract void construirEnunciado();
	
	
	/**
	 * Construye la parte a responder de esta pregunta de Kruskal.
	 */
	@Override
	protected abstract void construirParteAResponder();
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Kruskal.
	 */
	@Override
	protected abstract void construirRespuestaCorrecta();
	
	
	/**
	 * Indica que los grafos usados en las preguntas de Kruskal son ponderados.
	 * @return Si el grafo asociado a la pregunta es o no ponderado.
	 */
	@Override
	protected boolean esPonderado() {
		return true;
	}
	

	/**
	 * Devuelve el nombre de archivo de las preguntas de Kruskal.
	 * @return Nombre de archivo de la pregunta.
	 */
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregKruskal();
	}

}
