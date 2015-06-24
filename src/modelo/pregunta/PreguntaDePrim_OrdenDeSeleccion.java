package modelo.pregunta;

import java.util.ArrayList;

import modelo.grafo.Grafo;
import texto.Texto;
import texto.Textos_Preguntas;

public class PreguntaDePrim_OrdenDeSeleccion extends PreguntaDePrim {
	
	public PreguntaDePrim_OrdenDeSeleccion(Integer nNodos, Double porcentajeDeArcos,
			boolean grafoDirigido, VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
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
		for(int i = 0; i < listaDeArcos.size(); i++){
			ArrayList<Texto> campos = new ArrayList<Texto>();
			
			//Campo 0
			campos.add(new Texto(((Integer)(i+1)).toString()));
			campos.get(0).concatenar(new Texto("º"));
			
			//Campo 1
			campos.add(Textos_Preguntas.abrirClausulaMultichoice());
			for(int n = 0; n < getGrafo().getNNodos(); n++){
				if(listaDeArcos.getNodoDelArco(i).equals(n)){
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
				if(listaDeArcos.getPredecesorDelArco(i).equals(n)){
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
			campos.get(3).concatenar(new Texto(listaDeArcos.getPesoDelArco(i).toString()));
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
		
		for(int i = 0; i < listaDeArcos.size(); i++){
			respuestaCorrecta.concatenar(new Texto(Integer.toString(i+1)));
			respuestaCorrecta.concatenar(new Texto("º: ["));
			
			respuestaCorrecta.concatenar(Textos_Preguntas.nodo());
			respuestaCorrecta.concatenar(new Texto(" "));
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(listaDeArcos.getNodoDelArco(i)))));
			respuestaCorrecta.concatenar(new Texto("] ["));
			respuestaCorrecta.concatenar(Textos_Preguntas.predecesor());
			respuestaCorrecta.concatenar(new Texto(": "));
			respuestaCorrecta.concatenar(new Texto(Character.toString(
					Grafo.convertirIndiceEnLetra(listaDeArcos.getPredecesorDelArco(i)))));
			respuestaCorrecta.concatenar(new Texto("] ["));
			respuestaCorrecta.concatenar(Textos_Preguntas.distancia());
			respuestaCorrecta.concatenar(new Texto(": "));
			respuestaCorrecta.concatenar(new Texto(listaDeArcos.getPesoDelArco(i).toString()));
			respuestaCorrecta.concatenar(new Texto("]\n"));
		}
	}
}
