package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pregunta de Prim de la clase Orden de selección.
 * @author Jorge Alonso Márquez
 */
public class PreguntaDePrim_OrdenDeSeleccion extends PreguntaDePrim {
	
	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaDePrim_OrdenDeSeleccion(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDePrim_OrdenDeSeleccion(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Construye el enunciado de esta pregunta de Kruskal de Orden de selección.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregPrim_OrdenDeSeleccion();
	}
	
	
	/**
	 * Construye la parte a responder de esta pregunta de Prim de Orden de selección.
	 */
	@Override
	protected void construirParteAResponder() {
		parteAResponder = new Texto("");
		parteAResponder.concatenar(new Texto("\n<table border=\"1\" style=\"width=100%\">"));

		//Primera fila
		parteAResponder.concatenarFilaDeTabla(	Textos_Preguntas.ordenDeSeleccion(),
												Textos_Preguntas.nodo(),
												Textos_Preguntas.predecesor(),
												Textos_Preguntas.pesoDelArco()	);
		
		//Por cada otra fila:
		for(int i = 0; i < arcosDelArbolDeExpansion.size(); i++){
			ArrayList<Texto> campos = new ArrayList<Texto>();
			
			//Campo 0
			campos.add(new Texto(((Integer)(i+1)).toString()));
			campos.get(0).concatenar(new Texto("º"));
			
			//Campo 1
			campos.add(Textos_Preguntas.abrirClausulaMultichoice());
			for(int n = 0; n < getGrafo().getNNodos(); n++){
				if(arcosDelArbolDeExpansion.getNodoDelArco(i).equals(n)){
					campos.get(1).concatenar(Textos_Preguntas.opcionCorrecta100());
				} else {
					campos.get(1).concatenar(Textos_Preguntas.opcionQueResta100());
				}
				campos.get(1).concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(n))));
				if(n < (getGrafo().getNNodos() - 1)){
					campos.get(1).concatenar(new Texto("~"));
				} else {
					campos.get(1).concatenar(new Texto("}"));
				}
			}
			
			//Campo 2
			campos.add(Textos_Preguntas.abrirClausulaMultichoice());
			for(int n = 0; n < getGrafo().getNNodos(); n++){
				if(arcosDelArbolDeExpansion.getPredecesorDelArco(i).equals(n)){
					campos.get(2).concatenar(Textos_Preguntas.opcionCorrecta100());
				} else {
					campos.get(2).concatenar(Textos_Preguntas.opcionQueResta100());
				}
				campos.get(2).concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(n))));
				if(n < (getGrafo().getNNodos() - 1)){
					campos.get(2).concatenar(new Texto("~"));
				} else {
					campos.get(2).concatenar(new Texto("}"));
				}
			}
			
			//Campo 3
			campos.add(Textos_Preguntas.abrirClausulaShortanswer());
			campos.get(3).concatenar(Textos_Preguntas.opcionCorrecta100());
			campos.get(3).concatenar(new Texto(arcosDelArbolDeExpansion.getPesoDelArco(i).toString()));
			campos.get(3).concatenar(new Texto("}"));
			
			parteAResponder.concatenarFilaDeTabla(	campos.get(0),
													campos.get(1),
													campos.get(2),
													campos.get(3)	);
		}
		parteAResponder.concatenar(new Texto("\n</table>"));
	}
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Prim de Orden de
	 * selección.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = new Texto("");
		
		for(int i = 0; i < arcosDelArbolDeExpansion.size(); i++){
			respuestaCorrecta.concatenar(new Texto(Integer.toString(i+1)));
			respuestaCorrecta.concatenar(new Texto("º: ["));
			
			respuestaCorrecta.concatenar(Textos_Preguntas.nodo());
			respuestaCorrecta.concatenar(new Texto(" "));
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(arcosDelArbolDeExpansion.getNodoDelArco(i)))));
			respuestaCorrecta.concatenar(new Texto("] ["));
			respuestaCorrecta.concatenar(Textos_Preguntas.predecesor());
			respuestaCorrecta.concatenar(new Texto(": "));
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(arcosDelArbolDeExpansion.getPredecesorDelArco(i)))));
			respuestaCorrecta.concatenar(new Texto("] ["));
			respuestaCorrecta.concatenar(Textos_Preguntas.pesoDelArco());
			respuestaCorrecta.concatenar(new Texto(": "));
			respuestaCorrecta.concatenar(new Texto(arcosDelArbolDeExpansion.getPesoDelArco(i).toString()));
			respuestaCorrecta.concatenar(new Texto("]\n"));
		}
	}
	
	
	/**
	 * Devuelve el número correspondiente a una pregunta de Prim de Orden de selección.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_PRIM_ORDEN_DE_SELECCION;
	}

	
	/**
	 * Devuelve el número asociado a las preguntas de Orden de selección.
	 * @return Número asociado a la clase de pregunta.
	 */
	@Override
	protected Integer getClaseDePregunta() {
		return PreguntaDePrim.PREGUNTA_ORDEN_DE_SELECCION;
	}
	
}
