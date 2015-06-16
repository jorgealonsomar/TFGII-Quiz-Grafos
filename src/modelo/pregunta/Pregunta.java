package modelo.pregunta;

import java.util.ArrayList;

import modelo.Grafo;
import modelo.GrafoDirigido;
import modelo.GrafoNoDirigido;
import modelo.Semilla;
import texto.Texto;
import texto.Textos_Preguntas;
import util.Idioma;

public abstract class Pregunta {

	private Grafo grafo;

	protected Texto titulo;
	protected Texto enunciado;
	protected Texto parteAResponder;
	protected Texto respuestaCorrecta;

	private Semilla consigna;

	/**
	 * Constructor de la clase. Crea una nueva pregunta con un grafo aleatorio
	 * creado a partir de los parámetros fijados.
	 */
	public Pregunta(Integer nNodos, Double porcentajeDeArcos, boolean esDirigido) {
		generarGrafo(nNodos, porcentajeDeArcos, esDirigido);

		construirPregunta(esDirigido);
	}

	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una consigna
	 */
	public Pregunta(Semilla consigna) {
		if (consigna.esDirigido()) {
			grafo = new GrafoDirigido(consigna.getMatrizDeAdyacencia());
		} else {
			grafo = new GrafoNoDirigido(consigna.getMatrizDeAdyacencia());
		}

		construirPregunta(consigna.esDirigido());
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
	

	private void generarGrafo(Integer nNodos, Double porcentajeDeArcos,
			boolean grafoDirigido) {
		if (grafoDirigido) {
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos);
		}
	}
	

	protected abstract void aplicarAlgoritmo();

	protected abstract void construirTitulo();

	protected abstract void construirEnunciado();

	protected abstract void construirParteAResponder();

	protected abstract void construirRespuestaCorrecta();

	protected abstract void generarSemilla(boolean grafoDirigido);

	
	protected void generarConsignaEnFuncionDelTipoDePregunta(
			Integer tipoDePregunta, boolean grafoDirigido) {
		consigna = new Semilla(tipoDePregunta, grafo.getNNodos(),
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
	
	
	/** Código común de construirParteAResponder() para las preguntas de ordenacion */
	protected void resultadoDeOrdenarElGrafo(ArrayList<Integer> recorrido){
		parteAResponder = Textos_Preguntas.pregOrdenacion_ResultadoDeOrdenarGrafo();

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
					parteAResponder.concatenar(Textos_Preguntas.opcionMultichoiceCorrecta100());
					parteAResponder.concatenar(Textos_Preguntas.nombreDeNodo((Grafo
							.convertirIndiceEnLetra(nodoDelGrafo))));
					parteAResponder.concatenar(Textos_Preguntas.comentarioAcierto());
				} else {
					parteAResponder.concatenar(Textos_Preguntas.opcionMultichoiceQueResta100());
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
		respuestaCorrecta = Textos_Preguntas.respuestaOrdenacion();
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
		return consigna.toString();
	}

	
	public String getTextoPreguntaParaMostrarPorPantalla(Idioma idioma) {
		String textoPregunta = "";
		textoPregunta += "--------------------------------------------------------------";
		textoPregunta += "\n" + getTitulo(idioma);
		textoPregunta += "\n\n" + getEnunciado(idioma);
		textoPregunta += "\n\n" + getGrafo().toString();
		if (idioma == Idioma.ESP) {
			textoPregunta += "\n" + "(consigna: ";
		} else {
			textoPregunta += "\n" + "(password: ";
		}
		textoPregunta += getCodigoSemilla() + ")";
		textoPregunta += "\n\n" + getRespuestaCorrecta(idioma);
		textoPregunta += "\n\n\n";

		return textoPregunta;
	}
	

	public String getTextoPreguntaXml(Idioma idioma) {
		String textoPregunta = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		textoPregunta += "\n<quiz>";
		textoPregunta += "\n\t<question type=\"cloze\">";
		textoPregunta += "\n\t\t<name><text>" + Texto.quitarCaracteresExtranos(getTitulo(idioma)) + "</text>";
		textoPregunta += "\n\t\t</name>";
		textoPregunta += "\n\t\t<questiontext>";
		textoPregunta += "\n\t\t\t<text><![CDATA[";
		textoPregunta += "\n\t\t\t"
				+ Texto.adaptarCaracteresAXml(getEnunciado(idioma));
		textoPregunta += "\n</p>" + getGrafo().toStringTabulado(3, true);
		textoPregunta += "\n</p>\t\t\t" + "(consigna: "
				+ Texto.adaptarCaracteresAXml(getCodigoSemilla()) + ")";
		textoPregunta += "\n</p>\t\t\t"
				+ Texto.adaptarCaracteresAXml(getParteAResponder(idioma));
		textoPregunta += "\n\t\t\t]]></text>";
		textoPregunta += "\n\t\t</questiontext>";
		textoPregunta += "\n\t\t\t<generalfeedback>";
		textoPregunta += "\n\t\t\t<text></text>";
		textoPregunta += "\n\t\t</generalfeedback>";
		textoPregunta += "\n\t\t<shuffleanswers>0</shuffleanswers>";
		textoPregunta += "\n\t</question>";
		textoPregunta += "\n</quiz>";

		return textoPregunta;
	}

}
