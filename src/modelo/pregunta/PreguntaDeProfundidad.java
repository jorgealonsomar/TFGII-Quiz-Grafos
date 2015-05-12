package modelo.pregunta;

import java.util.ArrayList;

import modelo.Grafo;
import modelo.Semilla;
import util.Texto;

public class PreguntaDeProfundidad extends Pregunta {
	
	private ArrayList<Integer> recorridoEnProfundidad;

	
	/** Constructor de la clase */
	public PreguntaDeProfundidad(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido) {
		super(nNodos, porcentajeDeArcos, grafoDirigido);
	}
	
	
	@Override
	protected void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido){
		super.generarGrafo(nNodos, porcentajeDeArcos, grafoDirigido);
		recorridoEnProfundidad = getGrafo().recorrerEnProfundidad(0);
	}
	

	@Override
	protected void construirTitulo() {
		titulo = Texto.tituloPregProfundidad();
	}

	@Override
	protected void construirEnunciado() {
		enunciado = Texto.enunciadoPregProfundidad();
	}

	@Override
	protected void construirParteAResponder() {
		parteAResponder = Texto.pregOrdenacion_ResultadoDeOrdenarGrafo();
		
		//Por cada nodo del resultado de recorrer el grafo:
		for(int i = 0; i < recorridoEnProfundidad.size(); i++){
			Integer nodoDelRecorrido = recorridoEnProfundidad.get(i);
			
			parteAResponder.concatenar(Texto.abrirClausulaMultichoice());
			
			//Por cada uno de los nodos del grafo:
			for(int nodoDelGrafo = 0; nodoDelGrafo < getGrafo().getNNodos(); nodoDelGrafo++){
				if(nodoDelGrafo > 0){ parteAResponder.concatenar(Texto.tilde()); }
				
				if(nodoDelRecorrido.equals(nodoDelGrafo)){
					parteAResponder.concatenar(Texto.opcionMultichoiceCorrecta100());
					parteAResponder.concatenar(Texto.nombreDeNodo((Grafo.convertirIndiceEnLetra(nodoDelGrafo))));
					parteAResponder.concatenar(Texto.comentarioAcierto());
				} else {
					parteAResponder.concatenar(Texto.opcionMultichoiceQueResta100());
					parteAResponder.concatenar(Texto.nombreDeNodo((Grafo.convertirIndiceEnLetra(nodoDelGrafo))));
					parteAResponder.concatenar(Texto.comentarioError());
				}
					
			}
			parteAResponder.concatenar(Texto.cerrarClausula());
			if(i < recorridoEnProfundidad.size()-1){ parteAResponder.concatenar(Texto.coma()); }
		}
	}
	

	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = Texto.respuestaOrdenacion();
		for(int i = 0; i < recorridoEnProfundidad.size(); i++){
			Integer nodoDelRecorrido = recorridoEnProfundidad.get(i);
			respuestaCorrecta.concatenar(Texto.nombreDeNodo((Grafo.convertirIndiceEnLetra(nodoDelRecorrido))));
			if(i < recorridoEnProfundidad.size()-1){ respuestaCorrecta.concatenar(Texto.coma()); }
		}
		respuestaCorrecta.concatenar(Texto.cerrarCorchete());;
	}


	@Override
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.recorridoEnProfunidad, grafoDirigido);
	}

}
