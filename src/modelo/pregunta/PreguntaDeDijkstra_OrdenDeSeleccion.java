package modelo.pregunta;

import java.util.ArrayList;

import modelo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

public class PreguntaDeDijkstra_OrdenDeSeleccion extends PreguntaDeDijkstra {
	
	private ArrayList<Integer> ordenDeSeleccion;
	
	public PreguntaDeDijkstra_OrdenDeSeleccion(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo, Integer tipoPregunta) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo, tipoPregunta);
	}
	
	
	@Override
	protected void aplicarAlgoritmo(){
		super.aplicarAlgoritmo();
		
		ordenDeSeleccion = resultadosDijkstra.getOrdenDeSeleccion();
	}

	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregDijkstra_OrdenDeSeleccion();
	}

	@Override
	protected void construirParteAResponder() {
		parteAResponder = new Texto("");
		
		parteAResponder.concatenar(new Texto("\n<table border=\"1\" style=\"width=100%\">"));

		//Primera fila
		parteAResponder.concatenar(new Texto("\n\t<tr>"));
		
		parteAResponder.concatenar(new Texto("\n\t\t<td>"));
		parteAResponder.concatenar(Textos_Preguntas.ordenDeSeleccion());
		parteAResponder.concatenar(new Texto("</td>"));
		
		parteAResponder.concatenar(new Texto("\n\t\t<td>"));
		parteAResponder.concatenar(Textos_Preguntas.nodo());
		parteAResponder.concatenar(new Texto("</td>"));
		
		parteAResponder.concatenar(new Texto("\n\t\t<td>"));
		parteAResponder.concatenar(Textos_Preguntas.predecesor());
		parteAResponder.concatenar(new Texto("</td>"));
		
		parteAResponder.concatenar(new Texto("\n\t\t<td>"));
		parteAResponder.concatenar(Textos_Preguntas.distancia());
		parteAResponder.concatenar(new Texto("</td>"));
		
		parteAResponder.concatenar(new Texto("\n\t</tr>"));
		
		//Por cada otra fila:
		for(int i = 0; i < ordenDeSeleccion.size(); i++){
			Integer nodoSeleccion = ordenDeSeleccion.get(i);
			
			parteAResponder.concatenar(new Texto("\n\t<tr>"));
			
			//Primera columna (Orden de selección)
			parteAResponder.concatenar(new Texto("\n\t\t<td>"));
			parteAResponder.concatenar(new Texto(((Integer)(i+1)).toString()));
			parteAResponder.concatenar(new Texto("º</td>"));
			
			//Segunda columna (Nodo)			
			parteAResponder.concatenar(new Texto("\n\t\t<td>"));
			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaMultichoice());
			for(int n = 0; n < getGrafo().getNNodos(); n++){
				if(nodoSeleccion.equals(n)){
					parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
				} else {
					parteAResponder.concatenar(Textos_Preguntas.opcionQueResta100());
				}
				parteAResponder.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(n))));
				if(n < (getGrafo().getNNodos() - 1)){
					parteAResponder.concatenar(new Texto("~"));
				} else {
					parteAResponder.concatenar(new Texto("}"));
				}
			}
			parteAResponder.concatenar(new Texto("</td>"));
			
			//Tercera columna (Predecesor)			
			parteAResponder.concatenar(new Texto("\n\t\t<td>"));
			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaMultichoice());
			for(int n = 0; n < getGrafo().getNNodos(); n++){
				if(resultadosDijkstra.getNodoPrevio(nodoSeleccion).equals(n)){
					parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
				} else {
					parteAResponder.concatenar(Textos_Preguntas.opcionQueResta100());
				}
				parteAResponder.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(n))));
				if(n < (getGrafo().getNNodos() - 1)){
					parteAResponder.concatenar(new Texto("~"));
				} else {
					parteAResponder.concatenar(new Texto("}"));
				}
			}
			parteAResponder.concatenar(new Texto("</td>"));
			
			//Cuarta columna (Distancia)			
			parteAResponder.concatenar(new Texto("\n\t\t<td>"));
			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaShortanswer());
			parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
			parteAResponder.concatenar(new Texto(distanciasAlNodoOrigen.get(nodoSeleccion).toString()));
			parteAResponder.concatenar(new Texto("}"));
			parteAResponder.concatenar(new Texto("</td>"));
			
			parteAResponder.concatenar(new Texto("\n\t</tr>"));
		}
		
		parteAResponder.concatenar(new Texto("\n</table>"));
	}

	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = new Texto("");
		
		for(int i = 0; i < ordenDeSeleccion.size(); i++){
			Integer nodo = ordenDeSeleccion.get(i);
			
			respuestaCorrecta.concatenar(new Texto(((Integer)(i+1)).toString()));
			respuestaCorrecta.concatenar(new Texto("º: ["));
			
			respuestaCorrecta.concatenar(Textos_Preguntas.nodo());
			respuestaCorrecta.concatenar(new Texto(" "));
			respuestaCorrecta.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra(nodo))));
			respuestaCorrecta.concatenar(new Texto("] ["));
			respuestaCorrecta.concatenar(Textos_Preguntas.predecesor());
			respuestaCorrecta.concatenar(new Texto(": "));
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(resultadosDijkstra.getNodoPrevio(nodo)))));
			respuestaCorrecta.concatenar(new Texto("] ["));
			respuestaCorrecta.concatenar(Textos_Preguntas.distancia());
			respuestaCorrecta.concatenar(new Texto(": "));
			respuestaCorrecta.concatenar(new Texto(distanciasAlNodoOrigen.get(nodo).toString()));
			respuestaCorrecta.concatenar(new Texto("]\n"));
		}
	}

}
