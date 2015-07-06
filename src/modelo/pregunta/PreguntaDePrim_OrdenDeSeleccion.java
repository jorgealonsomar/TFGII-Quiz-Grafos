package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

public class PreguntaDePrim_OrdenDeSeleccion extends PreguntaDePrim {
	
	public PreguntaDePrim_OrdenDeSeleccion(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, visualizacionGrafo);
	}
	
	
	public PreguntaDePrim_OrdenDeSeleccion(Semilla semilla){
		super(semilla);
	}
	
	
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregPrim_OrdenDeSeleccion();
	}
	
	
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
	
	
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_PRIM_ORDEN_DE_SELECCION;
	}


	@Override
	protected Integer getTipoDePregunta() {
		return PreguntaDePrim.PREGUNTA_ORDEN_DE_SELECCION;
	}
	
}
