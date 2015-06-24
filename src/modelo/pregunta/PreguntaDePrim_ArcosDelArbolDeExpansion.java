package modelo.pregunta;

import modelo.grafo.Grafo;
import modelo.grafo.ListaDeArcos;
import texto.Texto;
import texto.Textos_Preguntas;

public class PreguntaDePrim_ArcosDelArbolDeExpansion extends PreguntaDePrim {
	
	ListaDeArcos arcosAPreguntar;
	
	public PreguntaDePrim_ArcosDelArbolDeExpansion(Integer nNodos, Double porcentajeDeArcos,
			boolean grafoDirigido, VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, visualizacionGrafo);
	}
	
	
	@Override
	protected void aplicarAlgoritmo() {
		super.aplicarAlgoritmo();
		
		Integer nArcosAPreguntar = 5;
		
		Integer extremo1 = null;
		Integer extremo2 = null;
		
		boolean numeroDeIntentosExcesivo = false;
		Integer numeroDeIntentos = 0;
		Integer preguntasConstruidas = 0;
		
		arcosAPreguntar = new ListaDeArcos();
		
		while (preguntasConstruidas < nArcosAPreguntar && !numeroDeIntentosExcesivo){
			numeroDeIntentos++;
			extremo1 = getNextRandomInt(getGrafo().getNNodos());
			
			boolean seguir = true;
			while(seguir){
				extremo2 = getNextRandomInt(getGrafo().getMatrizDeAdyacencia().length);
				if(!extremo1.equals(extremo2)){ //Los dos extremos del nodo no pueden ser el mismo
					seguir = false;
				}
			}

			//Intentar añadir el nuevo arco
			if(arcosAPreguntar.addArco(extremo1, extremo2, 1)){
				preguntasConstruidas++;
			}
			
			//Si se han intentado generar pregunta durante más de 1000 veces, se entenderá que
			//no existen tantos posibles arcos como preguntas se quieren formular, y se detendrá
			if(numeroDeIntentos > 1000){
				numeroDeIntentosExcesivo = true;
			}
		}
	}
	
	
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregPrim_ArcosDelArbolDeExpansion();
	}
	
	
	@Override
	protected void construirParteAResponder() {
		parteAResponder = new Texto("");
		parteAResponder.concatenar(new Texto("\n<table border=\"1\" style=\"width=100%\">"));
		
		//Para cada fila
		for(int i = 0; i < arcosAPreguntar.size(); i++){
			parteAResponder.concatenar(new Texto("\n\t<tr>"));
			
			//Primera columna
			parteAResponder.concatenar(new Texto("\n\t\t<td>"));
			parteAResponder.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra
					(arcosAPreguntar.getNodoDelArco(i)))));
			parteAResponder.concatenar(new Texto("&mdash;"));	//Raya larga
			parteAResponder.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra
					(arcosAPreguntar.getPredecesorDelArco(i)))));
			parteAResponder.concatenar(new Texto("</td>"));
			
			//Segunda columna
			parteAResponder.concatenar(new Texto("\n\t\t<td>"));
			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaMultichoice());
			//Opción 'Pertenece'
			if(listaDeArcos.contieneElArco(
					arcosAPreguntar.getNodoDelArco(i), arcosAPreguntar.getPredecesorDelArco(i))){
				parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
			} else {
				parteAResponder.concatenar(Textos_Preguntas.opcionQueResta100());
			}
			parteAResponder.concatenar(Textos_Preguntas.pertenece());
			parteAResponder.concatenar(new Texto("~"));
			//Opción 'Pertenece'
			if(listaDeArcos.contieneElArco(
					arcosAPreguntar.getNodoDelArco(i), arcosAPreguntar.getPredecesorDelArco(i))){
				parteAResponder.concatenar(Textos_Preguntas.opcionQueResta100());
			} else {
				parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
			}
			parteAResponder.concatenar(Textos_Preguntas.noPertenece());
			
			parteAResponder.concatenar(new Texto("}"));
			parteAResponder.concatenar(new Texto("</td>"));
			
			parteAResponder.concatenar(new Texto("\n\t</tr>"));
		}
		parteAResponder.concatenar(new Texto("\n</table>"));
	}
	
	
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta = new Texto("");
		
		for(int i = 0; i < arcosAPreguntar.size(); i++){
			respuestaCorrecta.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra
					(arcosAPreguntar.getNodoDelArco(i)))));
			respuestaCorrecta.concatenar(new Texto("-"));
			respuestaCorrecta.concatenar(new Texto(Character.toString(Grafo.convertirIndiceEnLetra
					(arcosAPreguntar.getPredecesorDelArco(i)))));
			respuestaCorrecta.concatenar(new Texto(" ("));
			if(listaDeArcos.contieneElArco(
					arcosAPreguntar.getNodoDelArco(i), arcosAPreguntar.getPredecesorDelArco(i))){
				respuestaCorrecta.concatenar(Textos_Preguntas.pertenece());
			} else {
				respuestaCorrecta.concatenar(Textos_Preguntas.noPertenece());
			}
			respuestaCorrecta.concatenar(new Texto(")\n"));
		}
		
	}

}
