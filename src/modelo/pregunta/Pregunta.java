package modelo.pregunta;

import java.util.ArrayList;
import java.util.Random;

import modelo.Semilla;
import modelo.grafo.Grafo;
import modelo.grafo.GrafoDirigido;
import modelo.grafo.GrafoNoDirigido;
import modelo.grafo.ListaDeArcos;
import texto.Texto;
import texto.Textos_Preguntas;
import util.Idioma;

public abstract class Pregunta {

	protected Grafo grafo;

	protected Texto titulo;
	protected Texto enunciado;
	protected Texto parteAResponder;
	protected Texto respuestaCorrecta;

	protected Random randomGenerator;
	
	private Long seedDelRandom;
	private VisualizacionGrafo visualizacionGrafo;
	
	private Semilla semilla;

	/**
	 * Constructor de la clase. Crea una nueva pregunta con un grafo aleatorio
	 * creado a partir de los parámetros fijados.
	 */
	public Pregunta(Integer nNodos, Double porcentajeDeArcos, boolean esDirigido, boolean esPonderado,
			VisualizacionGrafo visualizacionGrafo) {
		seedDelRandom = System.currentTimeMillis();
		
		construirPregunta(nNodos, porcentajeDeArcos, esDirigido, esPonderado, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla
	 */
	public Pregunta(Semilla semilla) {
		seedDelRandom = Long.parseLong(semilla.getSeedDelRandom());
		
		construirPregunta(semilla.getNNodos(), semilla.getPorcentajeDeArcos(), semilla.isDirigido(), esPonderado(),
				semilla.getVisualizacionGrafo());
	}
	
	
	private void construirPregunta(Integer nNodos, Double porcentajeDeArcos, boolean esDirigido, boolean esPonderado,
			VisualizacionGrafo visualizacionGrafo){
		this.visualizacionGrafo = visualizacionGrafo;
		
		randomGenerator = new Random(seedDelRandom);
		
		generarGrafo(nNodos, porcentajeDeArcos, esDirigido, esPonderado);
		

		aplicarAlgoritmo();
		
		construirTitulo();
		construirEnunciado();
		construirParteAResponder();
		construirRespuestaCorrecta();
		
		
		semilla = new Semilla(getNumPregunta(), nNodos, esDirigido, porcentajeDeArcos, visualizacionGrafo,
				getTipoDePregunta(), seedDelRandom.toString());
		
	}
	

	protected void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			boolean esPonderado) {
		if (grafoDirigido) {
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator, false);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator, false);
		}
	}
	

	protected abstract void aplicarAlgoritmo();

	protected abstract void construirTitulo();

	protected abstract void construirEnunciado();

	protected abstract void construirParteAResponder();

	protected abstract void construirRespuestaCorrecta();

	protected abstract Integer getNumPregunta();
	
	/** Devuelve 0 por defecto (preguntas en las que no se puede escoger más que un tipo de pregunta).
	 * Las preguntas que devuelvan otros valores deben heredar esta función */
	protected Integer getTipoDePregunta(){
		return 0;
	}
	
	protected abstract boolean esPonderado();

	
	public abstract Texto getNombreDeArchivo();

	
	public Grafo getGrafo() {
		return this.grafo;
	}
	
	
	public Semilla getSemilla(){
		return semilla;
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
		textoPregunta += "\n</p>\t\t\t" + "(semilla: " +
				Texto.adaptarCaracteresAXml(getCodigoSemilla()) + ")";
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
	
	
	/** Parte común de la aplicación del algoritmo para  las preguntas de Arcos del Árbol de Expansión
	 * de Prim y Kruskal. */
	protected ListaDeArcos aplicarAlgoritmo_PreguntaArcosDelArbolDeExpansion(
			ListaDeArcos arcosAPreguntar) {
		
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
		return arcosAPreguntar;
	}
	
	
	/** Parte común de la construcción de la Parte a Responder de las preguntas de Arcos del Árbol
	 * de Expansión de Prim y Kruskal. */
	protected void construirParteAResponder_PreguntaArcosDelArbolDeExpansion(
			ListaDeArcos arcosAPreguntar, ListaDeArcos listaDeArcos) {
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
	
	
	/** Parte común de la construcción de la Respuesta Correcta de las preguntas de Arcos del Árbol
	 * de Expansión de Prim y Kruskal. */
	protected void construirRespuestaCorrecta_PreguntaArcosDelArbolDeExpansion(
			ListaDeArcos arcosAPreguntar, ListaDeArcos listaDeArcos) {
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
