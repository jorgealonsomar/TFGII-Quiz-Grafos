package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pregunta de Dijkstra de la clase Distancias más cortas.
 * @author Jorge Alonso Márquez
 */
public class PreguntaDeDijkstra_DistanciasMasCortas extends PreguntaDeDijkstra {
	
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
	public PreguntaDeDijkstra_DistanciasMasCortas(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDeDijkstra_DistanciasMasCortas(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Construye el enunciado de esta pregunta de Dijkstra de Distancias más cortas.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregDijkstra_DistanciasMasCortas();	
	}
	
	
	/**
	 * Construye la parte a responder de esta pregunta de Dijkstra de Distancias más cortas.
	 */
	@Override
	protected void construirParteAResponder() {
		parteAResponder = new Texto("");
		for(Integer i = 1; i < distanciasAlNodoOrigen.size(); i++){
			parteAResponder.concatenar(Textos_Preguntas.distanciaDesdeElNodoA());
			parteAResponder.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(i))));
			parteAResponder.concatenar(new Texto(": "));
			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaShortanswer());
			parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
			
			Integer distancia = distanciasAlNodoOrigen.get(i);
			if(distancia > 100000 || distancia < -100000){ 
				parteAResponder.concatenar(new Texto("i"));
			} else {
				parteAResponder.concatenar(new Texto(distanciasAlNodoOrigen.get(i).toString()));
			}
			
			
			parteAResponder.concatenar(new Texto("}.\n</p>"));
		}
	}
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Dijkstra de Distancias
	 * más cortas.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = new Texto("");
		
		for (int i = 1; i < distanciasAlNodoOrigen.size(); i++) {
			Integer distancia = distanciasAlNodoOrigen.get(i);
			String textoDistancia;
			if(distancia > 100000 || distancia < -100000){ 
				textoDistancia = "i";
			} else {
				textoDistancia = distancia.toString();
			}
			
			respuestaCorrecta.concatenar(Textos_Preguntas.distanciaDesdeElNodoA());
			respuestaCorrecta.concatenar(Textos_Preguntas.nombreDeNodo((Grafo.convertirIndiceEnLetra(i))));
			respuestaCorrecta.concatenar(new Texto(": " + textoDistancia + "."));
			respuestaCorrecta.concatenar(new Texto("\n"));

		}
		
	}
	
	/**
	 * Devuelve el número correspondiente a una pregunta de Dijkstra de Distancias más cortas.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_DIJKSTRA_DISTANCIAS_MAS_CORTAS;
	}
	
	
	/**
	 * Devuelve el número asociado a las preguntas de Distancias más cortas.
	 * @return Número asociado a la clase de pregunta.
	 */
	@Override
	protected Integer getClaseDePregunta() {
		return PreguntaDeDijkstra.PREGUNTA_DISTANCIAS_MAS_CORTAS;
	}
	

}
