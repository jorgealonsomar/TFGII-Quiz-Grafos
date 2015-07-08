package modelo.pregunta;

import java.util.ArrayList;
import java.util.Random;

import modelo.Semilla;
import modelo.grafo.Grafo;
import modelo.grafo.GrafoDirigido;
import modelo.grafo.GrafoNoDirigido;
import modelo.grafo.ListaDeArcos;
import texto.Idioma;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pregunta abstracta. Corresponde a una de las posibles preguntas que la aplicación puede generar.
 * @author Jorge Alonso Márquez
 */
public abstract class Pregunta {
	
	/**
	 * Grafo sobre el que se aplica la pregunta.
	 */
	protected Grafo grafo;
	
	/**
	 * Título de la pregunta.
	 */
	protected Texto titulo;
	
	/**
	 * Texto correspondiente al enunciado de la pregunta.
	 */
	protected Texto enunciado;
	
	/**
	 * Texto correspondiente a la parte de la pregunta que se ha de responder.
	 */
	protected Texto parteAResponder;
	
	/**
	 * Texto que indica la respuesta que se espera de la pregunta.
	 */
	protected Texto respuestaCorrecta;
	
	
	/**
	 * Generador de valores aleatorios.
	 */
	protected Random randomGenerator;
	
	/**
	 * Seed que con la que se inicializa el generados de valores aleatorios.
	 */
	private Long seedDelRandom;
	
	/**
	 * Modos de visualización de grafo.
	 */
	private VisualizacionGrafo visualizacionGrafo;
	
	/**
	 * Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	private Semilla semilla;

	
	/**
	 * Constructor de la clase.
	 * Crea una nueva pregunta con un grafo aleatorio creado a partir de los parámetros fijados.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param esDirigido
	 *            Si el grafo será o no dirigido.
	 * @param esPonderado
	 *            Si el grafo será o no ponderado.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public Pregunta(Integer nNodos, Double porcentajeDeArcos, boolean esDirigido, boolean esPonderado,
			VisualizacionGrafo visualizacionGrafo) {
		seedDelRandom = Math.abs(new Random().nextLong());
		
		construirPregunta(nNodos, porcentajeDeArcos, esDirigido, esPonderado, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public Pregunta(Semilla semilla) {
		seedDelRandom = Long.parseLong(semilla.getSeedDelRandom());
		
		construirPregunta(semilla.getNNodos(), semilla.getPorcentajeDeArcos(), semilla.isDirigido(), esPonderado(),
				semilla.getVisualizacionGrafo());
	}
	
	
	/**
	 * Crea una nueva pregunta a partir de los parámetros dados.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param esDirigido
	 *            Si el grafo será o no dirigido.
	 * @param esPonderado
	 *            Si el grafo será o no ponderado.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
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
				getClaseDePregunta(), seedDelRandom.toString());
		
	}
	
	
	/**
	 * Crea el grafo correspondiente a esta pregunta.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param grafoDirigido
	 *            Si el grafo será o no dirigido.
	 * @param esPonderado
	 *            Si el grafo será o no ponderado.
	 */
	protected void generarGrafo(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			boolean esPonderado) {
		if (grafoDirigido) {
			grafo = new GrafoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator, false);
		} else {
			grafo = new GrafoNoDirigido(nNodos, porcentajeDeArcos, esPonderado, randomGenerator, false);
		}
	}
	
	
	/**
	 * Aplica sobre el grafo el algoritmo que corresponde a este tipo de pregunta.
	 */
	protected abstract void aplicarAlgoritmo();
	
	
	/**
	 * Construye el título de la pregunta.
	 */
	protected abstract void construirTitulo();
	
	
	/**
	 * Construye el enunciado de la pregunta.
	 */
	protected abstract void construirEnunciado();
	
	
	/**
	 * Construye la parte de la pregunta que se ha de responder.
	 */
	protected abstract void construirParteAResponder();
	
	
	/**
	 * Construye el texto que indica la respuesta correcta de la pregunta.
	 */
	protected abstract void construirRespuestaCorrecta();
	
	
	/**
	 * Devuelve el número correspondiente a este tipo de pregunta.
	 */
	protected abstract Integer getNumPregunta();
	
	
	/**
	 * Devuelve el número asociado a la clase de pregunta.
	 * Devuelve 0 por defecto (preguntas en las que no se puede escoger más que un tipo de pregunta).
	 * Las preguntas que devuelvan otros valores sobreescribirán esta función.
	 * @return Número asociado a la clase de pregunta.
	 */
	protected Integer getClaseDePregunta(){
		return 0;
	}
	
	
	/**
	 * Indica si el grafo asociado a la pregunta es o no ponderado.
	 * @return Si el grafo asociado a la pregunta es o no ponderado.
	 */
	protected abstract boolean esPonderado();

	
	/**
	 * Devuelve el nombre de archivo de las preguntas de este tipo.
	 * @return Nombre de archivo de la pregunta.
	 */
	public abstract Texto getNombreDeArchivo();

	
	/**
	 * Devuelve el grafo asociado a la pregunta.
	 * @return
	 */
	public Grafo getGrafo() {
		return this.grafo;
	}
	
	
	/**
	 * Devuelve la semilla correspondiente a la pregunta.
	 * @return Semilla correspondiente a la pregunta.
	 */
	public Semilla getSemilla(){
		return semilla;
	}

	
	/**
	 * Devuelve el título de la pregunta en el idioma dado.
	 * @param idioma
	 *            Idioma en el que se quiere el título.
	 * @return Título de la pregunta.
	 */
	public String getTitulo(Idioma idioma) {
		return titulo.getString(idioma);
	}

	
	/**
	 * Devuelve el enunciado de la pregunta en el idioma dado.
	 * @param idioma
	 *            Idioma en el que se quiere el enunciado.
	 * @return Enunciado de la pregunta.
	 */
	public String getEnunciado(Idioma idioma) {
		return enunciado.getString(idioma);
	}

	
	/**
	 * Devuelve la parte a responder de la pregunta en el idioma dado.
	 * @param idioma
	 *            Idioma en el que se quiere la parte a responder.
	 * @return Parte a responder de la pregunta.
	 */
	public String getParteAResponder(Idioma idioma) {
		return parteAResponder.getString(idioma);
	}
	
	
	/**
	 * Devuelve el texto que indica la respuesta correcta.
	 * @param idioma
	 *            Idioma en el que se quiere la parte a responder.
	 * @return Texto que indica la respuesta correcta.
	 */
	public String getRespuestaCorrecta(Idioma idioma) {
		return respuestaCorrecta.getString(idioma);
	}
	
	
	/**
	 * Devuelve el siguiente entero generado por el generador de valores aleatorios.
	 * El valor estará dentro del intervalo [0, limite).
	 * @param limite
	 *            Valor máximo que puede tomar el entero (no inclusivo).
	 * @return Siguiente entero generado por el generador de valores aleatorios.
	 */
	public Integer getNextRandomInt(Integer limite){
		return randomGenerator.nextInt(limite);
	}
	
	
	/**
	 * Código común de construirParteAResponder() para las preguntas de ordenación.
	 * @param recorrido
	 *            Resultado de recorrer el grafo.
	 */
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
	
	
	/**
	 * Código común de construirRespuestaCorrecta() para las preguntas de ordenación. 
	 * @param recorrido
	 *            Resultado de recorrer el grafo.
	 */
	protected void respuestaCorrecta(ArrayList<Integer> recorrido){
		respuestaCorrecta = Textos_Preguntas.respuestaCorrectaEs();
		for (int i = 0; i < recorrido.size(); i++) {
			Integer nodoDelRecorrido = recorrido.get(i);
			respuestaCorrecta.concatenar(Textos_Preguntas.nombreDeNodo((
					Grafo.convertirIndiceEnLetra(nodoDelRecorrido))));
			if (i < recorrido.size() - 1) {
				respuestaCorrecta.concatenar(Textos_Preguntas.coma());
			}
		}
		respuestaCorrecta.concatenar(Textos_Preguntas.cerrarCorchete());
	}
	
	
	/**
	 * Devuelve el código de la semilla, el cual contiene codificada toda la información de la pregunta.
	 * @return Código de la semilla asociada a la pregunta.
	 */
	public String getCodigoSemilla() {
		return semilla.toString();
	}

	
	/**
	 * Devuelve el texto de la pregunta en formato plano, que se mostrará en el área de preguntas.
	 * @param idioma
	 *            Idioma en el que se quiere el texto.
	 * @return Texto de la pregunta en formato plano.
	 */
	public String getTextoPreguntaParaMostrarPorPantalla(Idioma idioma) {
		String textoPregunta = "";
		textoPregunta += "------------------------------------------------------------";
		textoPregunta += "\n" + getTitulo(idioma);
		textoPregunta += "\n\n" + getEnunciado(idioma);
		if (idioma == Idioma.ESP) {
			textoPregunta += "\n" + "(semilla: ";
		} else {
			textoPregunta += "\n" + "(seed: ";
		}
		textoPregunta += getCodigoSemilla() + ")";
		textoPregunta += "\n\n" + getRespuestaCorrecta(idioma);
		if(visualizacionGrafo.isMatrizDeAdyacencia()){
			textoPregunta += "\n\n" + getGrafo().toMatrizDeAdyacencia();	
		}
		if(visualizacionGrafo.isListaDeAdyacencia()){
			textoPregunta += "\n\n" + getGrafo().toListaDeAdyacencia();	
		}
		textoPregunta += "\n\n\n";

		return textoPregunta;
	}
	
	
	/**
	 * Devuelve el texto de la pregunta en formato moodle-xml, que se imprimirá en el archivo generado.
	 * @param idioma
	 *            Idioma en el que se quiere el texto.
	 * @return Texto de la pregunta en formato moodle-xml.
	 */
	public String getTextoPreguntaXml(Idioma idioma) {
		String textoPregunta = "\n\t<question type=\"cloze\">";
		textoPregunta += "\n\t\t<name>";
		textoPregunta += "\n\t\t\t<text>" + Texto.quitarCaracteresExtranos(getTitulo(idioma))
				+ " (Semilla: " + Texto.adaptarCaracteresAXml(getCodigoSemilla()) + ")</text>";
		textoPregunta += "\n\t\t</name>";
		textoPregunta += "\n\t\t<questiontext>";
		textoPregunta += "\n\t\t\t<text><![CDATA[";
		textoPregunta += "\n\t\t\t" + Texto.adaptarCaracteresAXml(getEnunciado(idioma));
		
		if(visualizacionGrafo.isGrafoVisual()){
			textoPregunta += "\n</p>" + getGrafo().toGrafoVisualHtml_Insercion();
		}
		if(visualizacionGrafo.isMatrizDeAdyacencia()){
			textoPregunta += "\n</p>" + getGrafo().toMatrizDeAdyacenciaHtml();
		}
		if(visualizacionGrafo.isListaDeAdyacencia()){
			textoPregunta += "\n</p>" + getGrafo().toListaDeAdyacenciaHtml();
		}
		
		textoPregunta += "\n</p>\t\t\t" + Texto.adaptarCaracteresAXml(getParteAResponder(idioma));
		textoPregunta += "\n\t\t\t]]></text>";
		if(visualizacionGrafo.isGrafoVisual()){
			textoPregunta += "\n\t\t\t" + getGrafo().toGrafoVisualHtml_Definicion();
		}
		textoPregunta += "\n\t\t</questiontext>";
		textoPregunta += "\n\t\t\t<generalfeedback>";
		textoPregunta += "\n\t\t\t<text></text>";
		textoPregunta += "\n\t\t</generalfeedback>";
		textoPregunta += "\n\t\t<shuffleanswers>0</shuffleanswers>";
		textoPregunta += "\n\t</question>";

		return textoPregunta;
	}
	
	
	/**
	 * Parte común de la aplicación del algoritmo para las preguntas de Arcos del Arbol de Expansión
	 * de Prim y Kruskal.
	 * @param arcosAPreguntar
	 *            Número de arcos por los que se preguntará al alumno.
	 * @return Lista con los arcos creados por el algoritmo.
	 */
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

			//Intentar anadir el nuevo arco
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
	
	
	/**
	 * Parte común de la construcción de la parte a responder para las preguntas de Arcos del Arbol de
	 * Expansión de Prim y Kruskal.
	 * @param arcosAPreguntar
	 *            Número de arcos por los que se preguntará al alumno.
	 * @param listaDeArcos Lista de arcos creados por el algoritmo.
	 */
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
	
	
	/**
	 * Parte común de la construcción del texto que indica la respuesta correctapara las preguntas de
	 * Arcos del Arbol de Expansión de Prim y Kruskal.
	 * @param arcosAPreguntar
	 *            Número de arcos por los que se preguntará al alumno.
	 * @param listaDeArcos Lista de arcos creados por el algoritmo.
	 */
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
