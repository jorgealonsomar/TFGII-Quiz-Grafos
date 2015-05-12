package modelo.pregunta;

import java.util.ArrayList;

import modelo.Grafo;
import modelo.Semilla;
import util.Texto;

public class PreguntaDeAnchura extends Pregunta {
	
	private ArrayList<Integer> recorridoEnAnchura;
	
	
	/** Constructor de la clase */
	public PreguntaDeAnchura(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido){
		super(nNodos, porcentajeDeArcos, grafoDirigido);
	}
	
	
	@Override
	protected void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido){
		super.generarGrafo(nNodos, porcentajeDeArcos, grafoDirigido);
		recorridoEnAnchura = getGrafo().recorrerEnAnchura(0);
	}
	
	
	@Override
	protected void construirTitulo() {
		titulo = Texto.tituloPregAnchura();
	}
	
	
	@Override
	protected void construirEnunciado() {
		enunciado = Texto.enunciadoPregAnchura();
	}
	

	@Override
	protected void construirParteAResponder() {
		parteAResponder = Texto.pregOrdenacion_ResultadoDeOrdenarGrafo();
		
		//Por cada nodo del resultado de recorrer el grafo:
		for(int i = 0; i < recorridoEnAnchura.size(); i++){
			Integer nodoDelRecorrido = recorridoEnAnchura.get(i);
			
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
			if(i < recorridoEnAnchura.size()-1){ parteAResponder.concatenar(Texto.coma()); }
		}
		parteAResponder.concatenar(Texto.cerrarCorchete());
	}

	
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = Texto.respuestaOrdenacion();
		for(int i = 0; i < recorridoEnAnchura.size(); i++){
			Integer nodoDelRecorrido = recorridoEnAnchura.get(i);
			respuestaCorrecta.concatenar(Texto.nombreDeNodo((Grafo.convertirIndiceEnLetra(nodoDelRecorrido))));
			if(i < recorridoEnAnchura.size()-1){ respuestaCorrecta.concatenar(Texto.coma()); }
		}
		respuestaCorrecta.concatenar(Texto.cerrarCorchete());;
	}


	@Override
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.recorridoEnAnchura, grafoDirigido);
	}
}
