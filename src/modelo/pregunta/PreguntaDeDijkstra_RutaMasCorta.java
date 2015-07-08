package modelo.pregunta;

import java.util.ArrayList;
import java.util.Collections;

import modelo.Semilla;
import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pregunta de Dijkstra de la clase Ruta más corta.
 * @author Jorge Alonso Márquez
 */
public class PreguntaDeDijkstra_RutaMasCorta extends PreguntaDeDijkstra {
	
	/**
	 * Nodo objetivo cuyo camino y distancia al Nodo A se ha de hallar.
	 */
	protected Integer nodoObjetivo;
	
	
	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param grafoDirigido
	 *            Si el grafo es dirigido.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaDeDijkstra_RutaMasCorta(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDeDijkstra_RutaMasCorta(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Aplica sobre el grafo la parte del algoritmo de Dijkstra que corresponde a las preguntas de la clase
	 * Ruta más corta.
	 */
	@Override
	protected void aplicarAlgoritmo() {
		super.aplicarAlgoritmo();
		
		//El nodo objetivo será un número aleatorio del grafo, excepto el nodo A  
		nodoObjetivo = getNextRandomInt( (getGrafo().getMatrizDeAdyacencia().length - 1) ) + 1;
		
		//Trazar camino del nodo origen al nodo objetivo
		caminoAlNodoObjetivo = new ArrayList<Integer>();
		caminoAlNodoObjetivo.add(nodoObjetivo);
	
		while(true){
			Integer nodoPrevio = resultadosDijkstra.getNodoPrevio(
					caminoAlNodoObjetivo.get(caminoAlNodoObjetivo.size()-1));
			if(nodoPrevio != null){
				caminoAlNodoObjetivo.add(nodoPrevio);	
			} else {
				break;
			}
		}
		Collections.reverse(caminoAlNodoObjetivo);
		
	}
	

	/**
	 * Construye el enunciado de esta pregunta de Dijkstra de Ruta más corta.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregDijkstra_RutaMasCorta(Grafo.convertirIndiceEnLetra(nodoObjetivo));
	}
	
	
	/**
	 * Construye la parte a responder de esta pregunta de Dijkstra de Ruta más corta.
	 */
	@Override
	protected void construirParteAResponder() {
		parteAResponder = new Texto("");
		
		//Ruta
		parteAResponder.concatenar(Textos_Preguntas.laRutaEs());
		
		//Realizar cada multichoice
		for(int r = 0; r < caminoAlNodoObjetivo.size(); r++){
			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaMultichoice());
			for(int n = 0; n < getGrafo().getNNodos(); n++){
				if(caminoAlNodoObjetivo.get(r).equals(n)){
					parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());	
				} else {
					parteAResponder.concatenar(Textos_Preguntas.opcionQueResta100());
				}
				parteAResponder.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(n))));
				if (n < getGrafo().getNNodos() - 1) {
					parteAResponder.concatenar(new Texto("~"));
				} else {
					parteAResponder.concatenar(new Texto("} "));
				}
			}
			if (r < caminoAlNodoObjetivo.size() - 1) {
				parteAResponder.concatenar(new Texto("-> "));
			}
		}
		
		//Distancia
		parteAResponder.concatenar(new Texto("\n</p>"));
		parteAResponder.concatenar(Textos_Preguntas.laDistanciaEs());
		parteAResponder.concatenar(Textos_Preguntas.abrirClausulaShortanswer());
		parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
		parteAResponder.concatenar(new Texto(distanciasAlNodoOrigen.get(nodoObjetivo).toString()));
		parteAResponder.concatenar(new Texto("}"));
	}
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Dijkstra de Ruta más corta.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = Textos_Preguntas.laRutaEs();

		for (int i = 0; i < caminoAlNodoObjetivo.size(); i++) {
			Integer nodo = caminoAlNodoObjetivo.get(i);
			
			respuestaCorrecta.concatenar(Textos_Preguntas.nombreDeNodo((Grafo.convertirIndiceEnLetra(nodo))));
			if (i < caminoAlNodoObjetivo.size() - 1) {
				respuestaCorrecta.concatenar(new Texto(" -> "));
			}
			
		}
		
		respuestaCorrecta.concatenar(new Texto("\n"));
		respuestaCorrecta.concatenar(Textos_Preguntas.laDistanciaEs());
		respuestaCorrecta.concatenar(new Texto(distanciasAlNodoOrigen.get(nodoObjetivo).toString()));
	}
	
	
	/**
	 * Devuelve el número correspondiente a una pregunta de Dijkstra de Ruta más corta.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_DIJKSTRA_RUTA_MAS_CORTA;
	}
	
	
	/**
	 * Devuelve el número asociado a las preguntas de Ruta más corta.
	 * @return Número asociado a la clase de pregunta.
	 */
	@Override
	protected Integer getClaseDePregunta() {
		return PreguntaDeDijkstra.PREGUNTA_RUTA_MAS_CORTA;
	}
	
}
