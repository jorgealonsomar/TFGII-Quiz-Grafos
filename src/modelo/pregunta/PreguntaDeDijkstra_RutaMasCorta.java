package modelo.pregunta;

import java.util.ArrayList;
import java.util.Collections;

import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

public class PreguntaDeDijkstra_RutaMasCorta extends PreguntaDeDijkstra {
	
	/** Nodo objetivo cuyo camino y distancia al Nodo A se ha de hallar  */
	protected Integer nodoObjetivo;
	
	public PreguntaDeDijkstra_RutaMasCorta(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
	}
	
	
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
	
	
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregDijkstra_RutaMasCorta(Grafo.convertirIndiceEnLetra(nodoObjetivo));
	}
	
	
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
	
}
