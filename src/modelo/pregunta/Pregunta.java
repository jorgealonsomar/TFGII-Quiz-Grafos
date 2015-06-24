package modelo.pregunta;

import java.util.ArrayList;
import java.util.Random;

import modelo.Semilla;
import modelo.grafo.Grafo;
import modelo.grafo.GrafoDirigido;
import modelo.grafo.GrafoNoDirigido;
import texto.Texto;
import texto.Textos_Preguntas;
import util.Idioma;

public abstract class Pregunta {

	private Grafo grafo;

	protected Texto titulo;
	protected Texto enunciado;
	protected Texto parteAResponder;
	protected Texto respuestaCorrecta;
	
	private VisualizacionGrafo visualizacionGrafo;
	
	private Random randomGenerator;
	
	private Semilla semilla;

	/**
	 * Constructor de la clase. Crea una nueva pregunta con un grafo aleatorio
	 * creado a partir de los parámetros fijados.
	 */
	public Pregunta(Integer nNodos, Double porcentajeDeArcos, boolean esDirigido, boolean esPonderado,
			VisualizacionGrafo visualizacionGrafo) {
		this.visualizacionGrafo = visualizacionGrafo;
		
		//TODO: Añadir semilla
		randomGenerator = new Random();
		
		generarGrafo(nNodos, porcentajeDeArcos, esDirigido, esPonderado);

		construirPregunta(esDirigido);
	}

	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla
	 */
	public Pregunta(Semilla semilla) {
		if (semilla.esDirigido()) {
			grafo = new GrafoDirigido(semilla.getMatrizDeAdyacencia());
		} else {
			grafo = new GrafoNoDirigido(semilla.getMatrizDeAdyacencia());
		}

		construirPregunta(semilla.esDirigido());
	}

	/** (Patrón de diseño Recipe) */
	private void construirPregunta(boolean esDirigido) {
		aplicarAlgoritmo();
		
		construirTitulo();
		construirEnunciado();
		construirParteAResponder();
		construirRespuestaCorrecta();

		generarSemilla(esDirigido);
	}
	

	private void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			boolean esPonderado) {
		if (grafoDirigido) {
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator);
		}
	}
	

	protected abstract void aplicarAlgoritmo();

	protected abstract void construirTitulo();

	protected abstract void construirEnunciado();

	protected abstract void construirParteAResponder();

	protected abstract void construirRespuestaCorrecta();

	protected abstract void generarSemilla(boolean grafoDirigido);

	
	protected void generarSemillaEnFuncionDelTipoDePregunta(
			Integer tipoDePregunta, boolean grafoDirigido) {
		semilla = new Semilla(tipoDePregunta, grafo.getNNodos(),
				grafoDirigido, grafo.getMatrizDeAdyacencia());
	}

	
	public abstract Texto getNombreDeArchivo();

	
	public Grafo getGrafo() {
		return this.grafo;
	}

	
	public String getTitulo(Idioma idioma) {
		return titulo.getString(idioma);
	}

	
	public String getEnunciado(Idioma idioma) {
		return enunciado.getString(idioma);
	}

	
	public String getParteAResponder(Idioma idioma) {
		return parteAResponder.getString(idioma);
	}
	
	
	public Integer getNextRandomInt(Integer bound){
		return randomGenerator.nextInt(bound);
	}
	
	
	/** Código común de construirParteAResponder() para las preguntas de ordenacion */
	protected void resultadoDeOrdenarElGrafo(ArrayList<Integer> recorrido){
		parteAResponder = Textos_Preguntas.pregRecorrido_ResultadoDeRecorrerGrafo();

		// Por cada nodo del resultado de recorrer el grafo:
		for (int i = 0; i < recorrido.size(); i++) {
			Integer nodoDelRecorrido = recorrido.get(i);

			parteAResponder.concatenar(Textos_Preguntas.abrirClausulaMultichoice());

			// Por cada uno de los nodos del grafo:
			for (int nodoDelGrafo = 0; nodoDelGrafo < getGrafo().getNNodos(); nodoDelGrafo++) {
				if (nodoDelGrafo > 0) {
					parteAResponder.concatenar(Textos_Preguntas.tilde());
				}

				if (nodoDelRecorrido.equals(nodoDelGrafo)) {
					parteAResponder.concatenar(Textos_Preguntas.opcionCorrecta100());
					parteAResponder.concatenar(Textos_Preguntas.nombreDeNodo((Grafo
							.convertirIndiceEnLetra(nodoDelGrafo))));
					parteAResponder.concatenar(Textos_Preguntas.comentarioAcierto());
				} else {
					parteAResponder.concatenar(Textos_Preguntas.opcionQueResta100());
					parteAResponder.concatenar(Textos_Preguntas.nombreDeNodo((Grafo
							.convertirIndiceEnLetra(nodoDelGrafo))));
					parteAResponder.concatenar(Textos_Preguntas.comentarioError());
				}

			}
			parteAResponder.concatenar(Textos_Preguntas.cerrarClausula());
			if (i < recorrido.size() - 1) {
				parteAResponder.concatenar(Textos_Preguntas.coma());
			}
		}
	}
	
	
	/** Código común de construirRespuestaCorrecta() para las preguntas de ordenacion */
	protected void respuestaCorrecta(ArrayList<Integer> recorrido){
		respuestaCorrecta = Textos_Preguntas.respuestaCorrectaEs();
		for (int i = 0; i < recorrido.size(); i++) {
			Integer nodoDelRecorrido = recorrido.get(i);
			respuestaCorrecta.concatenar(Textos_Preguntas.nombreDeNodo((Grafo.convertirIndiceEnLetra(nodoDelRecorrido))));
			if (i < recorrido.size() - 1) {
				respuestaCorrecta.concatenar(Textos_Preguntas.coma());
			}
		}
		respuestaCorrecta.concatenar(Textos_Preguntas.cerrarCorchete());
	}
	
	
	public String getRespuestaCorrecta(Idioma idioma) {
		return respuestaCorrecta.getString(idioma);
	}
	

	public String getCodigoSemilla() {
		return semilla.toString();
	}

	
	public String getTextoPreguntaParaMostrarPorPantalla(Idioma idioma) {
		String textoPregunta = "";
		textoPregunta += "--------------------------------------------------------------";
		textoPregunta += "\n" + getTitulo(idioma);
		textoPregunta += "\n\n" + getEnunciado(idioma);
		switch(visualizacionGrafo){
		case MATRIZ_DE_ADYACENCIA:
			textoPregunta += "\n\n" + getGrafo().toTablaMatrizDeAdyacencia();	
			break;
		case LISTA_DE_ADYACENCIA:
			textoPregunta += "\n\n" + getGrafo().toTablaListaDeAdyacencia();	
			break;
		case GRAFO_VISUAL:
			textoPregunta += "\n\n" + getGrafo().toGrafoVisual();	
			break;
		}
		if (idioma == Idioma.ESP) {
			textoPregunta += "\n" + "(semilla: ";
		} else {
			textoPregunta += "\n" + "(seed: ";
		}
		textoPregunta += getCodigoSemilla() + ")";
		textoPregunta += "\n\n" + getRespuestaCorrecta(idioma);
		textoPregunta += "\n\n\n";

		return textoPregunta;
	}
	

	public String getTextoPreguntaXml(Idioma idioma) {
		String textoPregunta = "\n\t<question type=\"cloze\">";
		textoPregunta += "\n\t\t<name><text>" + Texto.quitarCaracteresExtranos(getTitulo(idioma)) + "</text>";
		textoPregunta += "\n\t\t</name>";
		textoPregunta += "\n\t\t<questiontext>";
		textoPregunta += "\n\t\t\t<text><![CDATA[";
		textoPregunta += "\n\t\t\t" + Texto.adaptarCaracteresAXml(getEnunciado(idioma));
		switch(visualizacionGrafo){
		case MATRIZ_DE_ADYACENCIA:
			textoPregunta += "\n</p>" + getGrafo().toTablaMatrizDeAdyacenciaHtml();
			break;
		case LISTA_DE_ADYACENCIA:
			textoPregunta += "\n</p>" + getGrafo().toTablaListaDeAdyacenciaHtml();
			break;
		case GRAFO_VISUAL:
			textoPregunta += "\n</p>" + getGrafo().toGrafoVisualHtml();
			break;
		}
		textoPregunta += "\n</p>\t\t\t" + "(semilla: " + Texto.adaptarCaracteresAXml(getCodigoSemilla()) + ")";
		textoPregunta += "\n</p>\t\t\t" + Texto.adaptarCaracteresAXml(getParteAResponder(idioma));
		textoPregunta += "\n\t\t\t]]></text>";
		textoPregunta += "\n\t\t</questiontext>";
		textoPregunta += "\n\t\t\t<generalfeedback>";
		textoPregunta += "\n\t\t\t<text></text>";
		textoPregunta += "\n\t\t</generalfeedback>";
		textoPregunta += "\n\t\t<shuffleanswers>0</shuffleanswers>";
		textoPregunta += "\n\t</question>";

		return textoPregunta;
	}

}
