package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import modelo.grafo.Grafo;
import modelo.grafo.GrafoNoDirigido;
import modelo.grafo.ListaDeArcos;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pregunta de Kruskal de la clase Orden de selección.
 * @author Jorge Alonso Márquez
 */
public class PreguntaDeKruskal_OrdenDeSeleccion extends PreguntaDeKruskal {
	
	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaDeKruskal_OrdenDeSeleccion(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDeKruskal_OrdenDeSeleccion(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Construye el enunciado de esta pregunta de Kruskal de Orden de selección.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregKruskal_OrdenDeSeleccion();
	}
	
	
	/**
	 * Construye la parte a responder de esta pregunta de Kruskal de Orden de selección.
	 */
	@Override
	protected void construirParteAResponder() {
		parteAResponder = new Texto("");
		parteAResponder.concatenar(new Texto("\n<table border=\"1\" style=\"width=100%\">"));

		//Primera fila
		parteAResponder.concatenarFilaDeTabla(	Textos_Preguntas.ordenDeSeleccion(),
												Textos_Preguntas.arco()	);
		
		//Por cada otra fila:
		for(int a = 0; a < arcosArbolDeExpansion.size(); a++){
			ArrayList<Texto> campos = new ArrayList<Texto>();
			
			//Campo 0
			campos.add(new Texto(((Integer)(a+1)).toString()));
			campos.get(0).concatenar(new Texto("º"));
			
			//Campo 1
			campos.add(Textos_Preguntas.abrirClausulaMultichoice());
			
			ListaDeArcos arcosDelGrafo = ((GrafoNoDirigido)getGrafo()).listarArcosDelGrafo();
			Integer nArcos = arcosDelGrafo.size();
			
			//Por cada arco del grafo:
			for(int i = 0; i < nArcos; i++){
				Integer indiceArcoActual = arcosDelGrafo.getIndiceArcoConMenorPeso();
				
				if( arcosDelGrafo.getExtremoMenor(indiceArcoActual).equals(
						arcosArbolDeExpansion.getExtremoMenor(a))
						&& arcosDelGrafo.getExtremoMayor(indiceArcoActual).equals(
						arcosArbolDeExpansion.getExtremoMayor(a)) ){
					campos.get(1).concatenar(Textos_Preguntas.opcionCorrecta100());
				} else {
					campos.get(1).concatenar(Textos_Preguntas.opcionQueResta100());
				}
				
				campos.get(1).concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(
						arcosDelGrafo.getExtremoMenor(indiceArcoActual)))));
				campos.get(1).concatenar(new Texto("&mdash;"));
				campos.get(1).concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(
						arcosDelGrafo.getExtremoMayor(indiceArcoActual)))));
				
				if(i < (nArcos - 1)){
					campos.get(1).concatenar(new Texto("~"));
				} else {
					campos.get(1).concatenar(new Texto("}"));
				}
				
				arcosDelGrafo.retirarArco(indiceArcoActual);
			}
			
			parteAResponder.concatenarFilaDeTabla(campos.get(0), campos.get(1));
		}
		parteAResponder.concatenar(new Texto("\n</table>"));
	}
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Kruskal de Orden de
	 * selección.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = new Texto("");
		
		for(int i = 0; i < arcosArbolDeExpansion.size(); i++){
			respuestaCorrecta.concatenar(new Texto(Integer.toString(i+1)));
			respuestaCorrecta.concatenar(new Texto("º: ["));
			
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(arcosArbolDeExpansion.getExtremoMenor(i)))));
			respuestaCorrecta.concatenar(new Texto("-"));
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(arcosArbolDeExpansion.getExtremoMayor(i)))));
			respuestaCorrecta.concatenar(new Texto("]\n"));
		}
	}
	
	
	/**
	 * Devuelve el número correspondiente a una pregunta de Kruskal de Orden de selección.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_KRUSKAL_ORDEN_DE_SELECCION;
	}
	
	
	/**
	 * Devuelve el número asociado a las preguntas de Orden de selección.
	 * @return Número asociado a la clase de pregunta.
	 */
	@Override
	protected Integer getClaseDePregunta() {
		return PreguntaDeKruskal.PREGUNTA_ORDEN_DE_SELECCION;
	}
	
}
