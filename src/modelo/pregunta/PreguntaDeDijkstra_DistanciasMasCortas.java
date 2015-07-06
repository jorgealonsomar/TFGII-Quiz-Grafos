package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

public class PreguntaDeDijkstra_DistanciasMasCortas extends PreguntaDeDijkstra {

	public PreguntaDeDijkstra_DistanciasMasCortas(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
	}
	
	
	public PreguntaDeDijkstra_DistanciasMasCortas(Semilla semilla){
		super(semilla);
	}
	
	
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregDijkstra_DistanciasMasCortas();	
	}
	
	
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


	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_DIJKSTRA_DISTANCIAS_MAS_CORTAS;
	}


	@Override
	protected Integer getTipoDePregunta() {
		return PreguntaDeDijkstra.PREGUNTA_DISTANCIAS_MAS_CORTAS;
	}
	

}
