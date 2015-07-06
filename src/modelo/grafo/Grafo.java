package modelo.grafo;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import sun.misc.BASE64Encoder;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

/**
 * Grafo asociado a una pregunta de grafos.
 * @author Jorge Alonso Márquez
 */
public abstract class Grafo {

	/**
	 * Número de nodos que componen el grafo.
	 */
	private Integer nNodos;
	
	/**
	 * Variable booleana que define si los arcos del grafo son o no poderados.
	 */
	private boolean esPonderado;
	
	/**
	 * Generador de números aleatorios.
	 */
	Random randomGenerator;
	
	/**
	 * Matriz de adyacencia del grafo.
	 */
	private Integer[][] matrizDeAdyacencia;
	
	
	/**
	 * Constructor de la clase. Construye un nuevo grafo al azar a partir de los parámetros fijados.
	 * @param nNodos
	 *            Número de nodos del grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos con los que se genera el grafo.
	 * @param esPonderado
	 *            Si el grafo es o no ponderado.
	 * @param randomGenerator
	 *            Generador de números aleatorios.
	 * @param grafoSinCiclos
	 *            Si el grafo debe no contener ciclos.
	 */
	public Grafo(Integer nNodos, Double porcentajeDeArcos, boolean esPonderado, Random randomGenerator,
			boolean grafoSinCiclos) {
		this.setNNodos(nNodos);
		this.esPonderado = esPonderado;
		this.randomGenerator = randomGenerator;
		
		// Instanciar la matriz de adyacencia
		matrizDeAdyacencia = new Integer[nNodos][nNodos];
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			for (int j = 0; j < matrizDeAdyacencia[i].length; j++) {
				matrizDeAdyacencia[i][j] = 0;
			}
		}
		
		if(grafoSinCiclos){ //(Preguntas de Recorrido Topológico)
			construirArcosSinCiclos(porcentajeDeArcos);
		} else {
			construirArcosMinimos();
			construirArcosExtra(porcentajeDeArcos);
		}
	}
	
	
	/**
	 * Constructor de la clase. Crea el grafo a partir de una matriz de adyacencia dada.
	 * @param matrizDeAdyacencia
	 *            Matriz de adyacencia del grafo.
	 */
	public Grafo(Integer[][] matrizDeAdyacencia) {
		this.matrizDeAdyacencia = matrizDeAdyacencia;
		this.nNodos = matrizDeAdyacencia.length;
	}
	
	
	/**
	 * Construye un árbol de expansión con un número de aristas mínimo que recorra el grafo.
	 * 
	 * Une un nodo visitado al azar con un nodo no visitado al azar (que queda marcado como visitado),
	 * y repite este proceso hasta haber acabado con todos los nodos no visitados.
	 */
	private void construirArcosMinimos() {

		ArrayList<Integer> nodosNoVisitados = new ArrayList<Integer>(nNodos);
		for (int i = 0; i < nNodos; i++) {
			nodosNoVisitados.add(i);
		}
		ArrayList<Integer> nodosVisitados = new ArrayList<Integer>();

		// Visitamos un nodo inicial
		Integer nodoInicial = nodosNoVisitados.remove(randomGenerator.nextInt(nodosNoVisitados.size()));
		nodosVisitados.add(nodoInicial);

		// Por cada nodo no visitado
		while (!nodosNoVisitados.isEmpty()) {
			// Cogemos un nodo de los visitados
			Integer nodoVisit = nodosVisitados.get(randomGenerator.nextInt(nodosVisitados.size()));

			// Retiramos un nodo de los que aún no han sido visitados
			Integer nodoNoVisit = nodosNoVisitados.remove(randomGenerator.nextInt(nodosNoVisitados.size()));

			// Construimos un arco entre esos dos nodos
			addArco(nodoVisit, nodoNoVisit);
			
			// Marcamos ese segundo nodo como visitado
			nodosVisitados.add(nodoNoVisit);
		}
		
	}
	
	
	/**
	 * Añade de forma aleatoria nuevos arcos al grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de nuevos arcos.
	 */
	protected abstract void construirArcosExtra(Double porcentajeDeArcos);
	
	
	/**
	 * Añade al grafo nuevos arcos al azar de entre los posibles arcos dados.
	 * @param listaDeArcos
	 *            Lista de posibles arcos.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos a añadir.
	 */
	protected void anadirArcosAlAzarDeLaLista(ArrayList<Arco> listaDeArcos,
			Double porcentajeDeArcos) {
		Integer cantidadDeArcosAAnadir = (int) (porcentajeDeArcos * listaDeArcos
				.size());

		// Por cada arco que haya que añadir
		for (int i = 0; i < cantidadDeArcosAAnadir; i++) {
			// Se saca un arco de la lista de posibles arcos de existentes
			Arco arcoAAnadir = listaDeArcos.remove(randomGenerator.nextInt(listaDeArcos.size()));

			// Se añade al grafo ese arco
			addArco(arcoAAnadir.getNodoOrigen(), arcoAAnadir.getNodoDestino());
		}
	}
	
	
	/**
	 * Añade un nuevo arco a la matriz de adyacencia.
	 * @param nodo1
	 *            Nodo a un extremo del arco.
	 * @param nodo2
	 *            Nodo al otro extremo del arco.
	 */
	protected abstract void addArco(Integer nodo1, Integer nodo2);
	
	
	/**
	 * Añade nuevos arcos al grafo de forma que se mantenga acíclico.
	 * @param porcentajeDeArcos
	 *            Porcentaje aproximado de arcos a generar.
	 */
	private void construirArcosSinCiclos(Double porcentajeDeArcos){
		Integer posibilidadDeArco = (int)(porcentajeDeArcos * 100);
		
		//Generamos una lista con los nodos ordenados al azar
		ArrayList<Integer> listaNodos = new ArrayList<Integer>();
		for(int n = 0; n < getNNodos(); n++){
			listaNodos.add(n);
		}
		Collections.shuffle(listaNodos, randomGenerator);
		
		//Construimos solamente arcos que vayan de un nodo anterior a un nodo posterior
		for(int i = 0; i < listaNodos.size(); i++){
			for(int j = (i+1); j < listaNodos.size(); j++){
				
				Integer randomInt = randomGenerator.nextInt(100);
				if(randomInt <= posibilidadDeArco){
					addArco(listaNodos.get(i), listaNodos.get(j));
				}
				
			}
		}
		
	}
	
	
	/**
	 * Devuelve un nuevo valor para un arco.
	 * Si el grafo es ponderado, será un número aleatorio con un valor de entre 1 y 10.
	 * Si no, el peso del arco será 1.
	 * @return
	 */
	protected Integer generarValorDeArco(){
		if(esPonderado){
			//Genera un peso entero para el arco de entre 1 y 10
			Integer nextInt = (randomGenerator.nextInt(10) + 1);
			return nextInt;			
		} else {
			return 1;
		}
	}
	
	
	/**
	 * Recorre en anchura el grafo.
	 * @param nodoInicial
	 *            Nodo desde el que se empieza a recorrer el grafo.
	 * @return Lista con los nodos ordenados
	 */
	public ArrayList<Integer> recorrerEnAnchura(int nodoInicial) {
		ArrayList<Integer> pendientes = new ArrayList<Integer>();
		ArrayList<Integer> recorrido = new ArrayList<Integer>();

		pendientes.add(nodoInicial);

		// Mientras queden nodos pendientes:
		while (!pendientes.isEmpty()) {

			// Se saca el siguiente nodo pendiente
			Integer nodoActual = pendientes.remove(0);

			// Por cada nodo vecino de ese nodo:
			for (Integer nodoVecino : hallarNodosVecinos(nodoActual)) {
				// Si ese vecino aún no ha sido visitado:
				if (!pendientes.contains(nodoVecino)
						&& !recorrido.contains(nodoVecino)) {
					// Ese vecino se añade a la lista de nodos pendientes
					pendientes.add(nodoVecino);
				}
			}

			// Se añade el nodo actual al recorrido
			recorrido.add(nodoActual);
		}

		return recorrido;
	}
	
	
	/**
	 * Recorre en profundidad el grafo.
	 * @param nodoInicial
	 *            Nodo desde el que se empieza a recorrer el grafo.
	 * @return Lista con los nodos ordenados
	 */
	public ArrayList<Integer> recorrerEnProfundidad(int nodoInicial) {
		ArrayList<Integer> recorrido = new ArrayList<Integer>();

		recorrerEnProfundidad_funcionRecursiva(nodoInicial, recorrido);

		return recorrido;
	}
	
	
	/**
	 * Parte recursiva del algoritmo de recorrido en profunidad.
	 * @param nodo
	 *            Nodo actual.
	 * @param nodosRecorridos
	 *            Lista con los nodos que ya han sido recorridos.
	 */
	private void recorrerEnProfundidad_funcionRecursiva(int nodo, ArrayList<Integer> nodosRecorridos) {
		nodosRecorridos.add(nodo);

		for (Integer nodoVecino : hallarNodosVecinos(nodo)) {
			if (!nodosRecorridos.contains(nodoVecino)) {
				recorrerEnProfundidad_funcionRecursiva(nodoVecino,
						nodosRecorridos);
			}
		}

	}
	
	
	/**
	 * Aplica al grafo el algoritmo de Dijkstra.
	 * @param nodoInicial
	 *            Nodo por el que se empieza a aplicar el algoritmo.
	 * @return Resultados del algoritmo.
	 */
	public ResultadosDijkstra algoritmoDeDijkstra(int nodoInicial) {
		ResultadosDijkstra resultadosDijkstra = new ResultadosDijkstra(nNodos);
		ArrayList<Boolean> nodosVisitados = new ArrayList<Boolean>(matrizDeAdyacencia.length);
		for(int n = 0; n < nNodos; n++){
			nodosVisitados.add(false);
		}
		
		//Inicializar distancias
		//Por cada nodo:
		for(int n = 0; n < matrizDeAdyacencia.length; n++){
			if(n == nodoInicial){
				resultadosDijkstra.setDistanciaAlNodoOrigen(n, 0);
			}
		}
		
		
		//Aplicar el algoritmo
		
		//Mientras queden nodos sin visitar
		while(nodosVisitados.contains(false)){
			//Encontrar el nodo con menor distAOrigen
			Integer menorDistanciaAOrigenDeEntreLosNoVisitados = null;
			Integer noVisitadoAMenorDistancia = null;
			
			for(int n = 0; n < matrizDeAdyacencia.length; n++){
				if(nodosVisitados.get(n) == false){
					if(menorDistanciaAOrigenDeEntreLosNoVisitados == null ||
							resultadosDijkstra.getDistanciaAlNodoOrigen(n) < menorDistanciaAOrigenDeEntreLosNoVisitados){
						menorDistanciaAOrigenDeEntreLosNoVisitados = resultadosDijkstra.getDistanciaAlNodoOrigen(n);
						noVisitadoAMenorDistancia = n;
					}
				}
			}
			if(!noVisitadoAMenorDistancia.equals(nodoInicial)){
				resultadosDijkstra.addOrdenDeSeleccion(noVisitadoAMenorDistancia);
			}
			Integer nodoActual = noVisitadoAMenorDistancia;

			//Se marca como leído
			nodosVisitados.set(nodoActual, true);
			
			ArrayList<Integer> nodosVecinos = new ArrayList<Integer>();
			//Hallar los vecinos no visitados de ese nodo:
			for(int v = 0; v < matrizDeAdyacencia[nodoActual].length; v++){
				if(matrizDeAdyacencia[nodoActual][v] > 0						//(si son vecinos)
						&& nodosVisitados.get(v).equals(false)){	//Si el nodo vecino no ha sido visitado
					nodosVecinos.add(v);
				}
			}
			
			
			for(Integer nodoVecino : nodosVecinos){
				
				//Visitar vecino
				//Si (distAOrigen de n + distEntreAmbosNodos) < distAOrigen de v: 
				if( (resultadosDijkstra.getDistanciaAlNodoOrigen(nodoActual) + matrizDeAdyacencia[nodoActual][nodoVecino])
						< resultadosDijkstra.getDistanciaAlNodoOrigen(nodoVecino) ){
					//distAOrigen de v := (distAOrigen de n + distEntreAmbosNodos)
					resultadosDijkstra.setDistanciaAlNodoOrigen(nodoVecino,
							(resultadosDijkstra.getDistanciaAlNodoOrigen(nodoActual) + matrizDeAdyacencia[nodoActual][nodoVecino]));
					//nodoPrevio de v := n
					resultadosDijkstra.setNodoPrevio(nodoVecino, nodoActual);
				}
					
			}
			
		}
		
		return resultadosDijkstra;
	}
	
	
	/**
	 * Encuentra los nodos que son vecinos del nodo dado.
	 * @param nodo
	 *            Nodo cuyos vecinos se quieren conocer.
	 * @return Vecinos del nodo dado.
	 */
	private ArrayList<Integer> hallarNodosVecinos(int nodo) {
		ArrayList<Integer> nodosVecinos = new ArrayList<Integer>();
		for (int vecino_i = 0; vecino_i < nNodos; vecino_i++) {

			// Si se puede llegar del nodo al vecino i:
			if (matrizDeAdyacencia[nodo][vecino_i] != 0) {
				nodosVecinos.add(vecino_i);
			}

		}
		
		return nodosVecinos;
	}
	
	
	/**
	 * Crea una copia de la matriz de adyacencia.
	 * @return Nueva matriz de adyacencia idéntica a la de este grafo.
	 */
	protected Integer[][] clonarMatrizDeAdyacencia(){
		Integer[][] matrizAdyacenciaAux = new Integer[matrizDeAdyacencia.length][matrizDeAdyacencia[0].length];
		for(int i = 0; i < matrizDeAdyacencia.length; i++){
			for(int j = 0; j < matrizDeAdyacencia.length; j++){
				matrizAdyacenciaAux[i][j] = matrizDeAdyacencia[i][j];
			}
		}
		return matrizAdyacenciaAux;
	}
	
	
	/**
	 * Devuelve el número de nodos de este grafo.
	 * @return Número de nodos de este grafo.
	 */
	public Integer getNNodos() {
		return nNodos;
	}
	
	
	/**
	 * Establece el número de nodos de este grafo.
	 * @param nNodos
	 *            Número de nodos de este grafo.
	 */
	public void setNNodos(Integer nNodos) {
		this.nNodos = nNodos;
	}
	
	
	/**
	 * Devuelve la matriz de adyacencia de este grafo.
	 * @return Número de nodos de este grafo.
	 */
	public Integer[][] getMatrizDeAdyacencia() {
		return matrizDeAdyacencia;
	}
	
	
	/**
	 * Devuelve la cadena que representa a este grafo, consistente en una representación del
	 * mismo en forma de matriz de adyacencia.
	 */
	@Override
	public String toString() {
		return toMatrizDeAdyacencia();
	}
	
	
	/**
	 * Devuelve la matriz de adyacencia correspondiente a este grafo.
	 * @return Matriz de adyacencia correspondiente a este grafo.
	 */
	public String toMatrizDeAdyacencia(){
		String cadena = "";
		
		//Fila de cabecera (letras)
		cadena += "  ";
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += (convertirIndiceEnLetra(i) + " ");
		}
		
		//Resto de filas
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += "\n";
			cadena += (convertirIndiceEnLetra(i) + " ");
			for (int j = 0; j < matrizDeAdyacencia[i].length; j++) {
				cadena += (matrizDeAdyacencia[i][j] + " ");
			}
		}
		
		return cadena;
	}
	
	
	/**
	 * Devuelve la matriz de adyacencia correspondiente a este grafo, en un formato
	 * que le permite formar parte de un archivo de moodle xml.
	 * @return Matriz de adyacencia correspondiente a este grafo, adaptada a xml.
	 */
	public String toMatrizDeAdyacenciaHtml(){
		String cadena = "";
		cadena += "<table border=\"1\" style=\"width:100%\">";
		
		//Fila de cabecera (letras)
		cadena += "\n\t<tr>";
		cadena += "\n\t\t<td></td>";
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += "\n\t\t<td>" + convertirIndiceEnLetra(i) + "</td>";
		}
		cadena += "\n\t</tr>";
		
		//Resto de filas
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += "\n\t<tr>";
			cadena += "\n\t\t<td>" + convertirIndiceEnLetra(i) + "</td>";
			for (int j = 0; j < matrizDeAdyacencia[i].length; j++) {
				cadena += "\n\t\t<td>" + matrizDeAdyacencia[i][j] + "</td>";
			}
			cadena += "\n\t</tr>";
		}
		cadena += "\n</table>";
		
		return cadena;
	}
	
	
	/**
	 * Devuelve la lista de adyacencia correspondiente a este grafo.
	 * @return Lista de adyacencia correspondiente a este grafo.
	 */
	public String toListaDeAdyacencia(){
		String cadena = "";
		
		//Por cada fila de la matriz de adyacencia:
		for (int f = 0; f < matrizDeAdyacencia.length; f++) {
			if (f != 0) cadena += "\n";
			cadena += (convertirIndiceEnLetra(f) + ": ");
			
			for(int c = 0; c < matrizDeAdyacencia[f].length; c++) {
				if(matrizDeAdyacencia[f][c] > 0){
					cadena += (convertirIndiceEnLetra(c));
					if (esPonderado) cadena += (" (" + matrizDeAdyacencia[f][c] + ")");
					cadena += ", ";
				}
			}
			//Borrar los dos últimos caracteres (", ")
			cadena = cadena.substring(0, cadena.length()-2);

		}
		return cadena;
	}
	
	
	/**
	 * Devuelve la lista de adyacencia correspondiente a este grafo, en un formato
	 * que le permite formar parte de un archivo de moodle xml.
	 * @return Lista de adyacencia correspondiente a este grafo, adaptada a xml.
	 */
	public String toListaDeAdyacenciaHtml(){
		String cadena = "";
		cadena += "<table border=\"1\" style=\"width:100%\">";
		
		//Por cada fila de la matriz de adyacencia:
		for (int f = 0; f < matrizDeAdyacencia.length; f++) {
			cadena += "\n\t<tr>";
			cadena += "\n\t\t<td>" + convertirIndiceEnLetra(f) + "</td>";
			
			cadena += "\n\t\t<td>";
			String cadenaAdyacentes = "";
			for(int c = 0; c < matrizDeAdyacencia[f].length; c++) {
				if(matrizDeAdyacencia[f][c] > 0){
					cadenaAdyacentes += convertirIndiceEnLetra(c);
					if (esPonderado) cadenaAdyacentes += (" (" + matrizDeAdyacencia[f][c] + ")");
					cadenaAdyacentes += ", ";
				}
			}

			try {
				//Borrar los dos últimos caracteres (", ")
				cadenaAdyacentes = cadenaAdyacentes.substring(0, cadenaAdyacentes.length()-2);
			} catch(Exception e) { }
			
			cadena += cadenaAdyacentes + "</td>";
			cadena += "\n\t</tr>";
			
		}
		cadena += "\n</table>";
		
		return cadena;
	}
	
	
	/**
	 * Devuelve la imagen correspondiente a este grafo.
	 * @return Imagen correspondiente a este grafo.
	 */
	public BufferedImage toGrafoVisual(){
		SparseMultigraph<Integer, String> grafoJung = new SparseMultigraph<>();
		
		for(int f = 0; f < matrizDeAdyacencia.length; f++){
			grafoJung.addVertex(f);
			
			for(int c = 0; c < matrizDeAdyacencia[f].length; c++){
				if(matrizDeAdyacencia[f][c] > 0){
					añadirArcoAlGrafoVisual(grafoJung, f, c);
				}	
			}
		}

		CircleLayout<Integer, String> layoutCircular = new CircleLayout<>(grafoJung);
		layoutCircular.setSize(new Dimension(300, 300));
		BasicVisualizationServer<Integer,String> panelVisualizacionGrafo =
				new BasicVisualizationServer<>(layoutCircular);
		
		EtiquetadorDeNodos etiquetadorDeNodos = new EtiquetadorDeNodos();
        panelVisualizacionGrafo.getRenderContext().setVertexLabelTransformer(etiquetadorDeNodos);
        
        if(esPonderado){
	        EtiquetadorDeArcos etiquetadorDeArcos = new EtiquetadorDeArcos();
	        panelVisualizacionGrafo.getRenderContext().setEdgeLabelTransformer(etiquetadorDeArcos);
        }

		JFrame frame = new JFrame("Grafo visual");
		frame.getContentPane().add(panelVisualizacionGrafo);
		frame.pack();
		frame.setVisible(false);
        
        BufferedImage imagen = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
        Graphics2D pintor = imagen.createGraphics();
        panelVisualizacionGrafo.paint(pintor);
        
        return imagen;
	}
	
	
	/**
	 * Devuelve una cadena en formato moodle que permite la inserción de una imagen en xml.
	 * @return Cadena de inserción de una imagen en xml.
	 */
	public String toGrafoVisualHtml_Insercion(){
		return "<img src=\"@@PLUGINFILE@@/imagenGrafo.png\" alt=\"\"  />";
	}
	
	
	/**
	 * Devuelve una cadena en formato moodle que permite la definición de una imagen en xml, codificada en
	 * BASE64.
	 * @return Cadena de definición de una imagen en xml, codificada en BASE64.
	 */
	public String toGrafoVisualHtml_Definicion(){
		String cadena = "";
		cadena += "<file name=\"imagenGrafo.png\" encoding=\"base64\">";
		cadena += getGrafoVisualEnBASE64();
		cadena += "==</file>"; 
		return cadena;
	}
	
	
	/**
	 * Codifica en BASE64 la imagen correspondiente a este grafo.
	 * @return Código en BASE64 de la imagen correspondiente a este grafo.
	 */
	private String getGrafoVisualEnBASE64(){
		String cadenaDeLaImagen = null;

        try( ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); ) {
        	
        	BufferedImage imagenGrafo = toGrafoVisual();
        	
            ImageIO.write(imagenGrafo, "png", byteArrayOutputStream);
            byte[] imagenEnBytes = byteArrayOutputStream.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            cadenaDeLaImagen = encoder.encode(imagenEnBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return cadenaDeLaImagen;
	}
	
	
	/**
	 * Añade un nuevo arco al modelo del grafo visual.
	 * @param grafoJung
	 *            Modelo del grafo visual.
	 * @param f
	 *            Nodo origen (fila en la matriz de adyacencia).
	 * @param c
	 *            Nodo destino (columna en la matriz de adyacencia).
	 */
	public abstract void añadirArcoAlGrafoVisual(SparseMultigraph<Integer, String> grafoJung, int f, int c);
	
	
	/**
	 * Devuelve la letra correspondiente a un nodo con el índice dado.
	 * Para un nodo con índice 0 será A, para uno con índice 1, será B, etc.
	 * @param indice
	 *            Índice del nodo.
	 * @return Letra correspondiente al nodo.
	 */
	public static char convertirIndiceEnLetra(int indice) {
		return (char) (indice + 65);
	}
	
	
	/**
	 * Compara este grafo con otro objeto.
	 * Devolverá true si ambos son Grafos y tienen una matriz de adyacencia similar.
	 * @param o
	 *            Objeto a comparar con este grafo.
	 * @return Si ese objeto es equivalente a este grafo.
	 */
	@Override
	public boolean equals(Object o) {
		if (this.getClass().equals(o.getClass())
				&& this.toString().equals(o.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * Etiquetador de los nodos del grafo visual.
	 * @author Jorge Alonso Márquez
	 */
	private class EtiquetadorDeNodos extends ToStringLabeller<Integer>{
		
		/**
		 * Etiqueta el nodo con la letra que corresponde a su índice.
		 * @param numeroDelNodo Índice del nodo.
		 * @return Nombre con el que se etiqueta al nodo (su letra).
		 */
		public String transform(Integer numeroDelNodo) {
            return Character.toString(convertirIndiceEnLetra(numeroDelNodo));
        }
	}
	
	
	
	/**
	 * Etiquetador de los arcos del grafo visual.
	 * @author Jorge Alonso Márquez
	 */
	private class EtiquetadorDeArcos extends ToStringLabeller<String>{
		
		/**
		 * Etiqueta el arco con el peso del mismo.
		 * @param nombreDelArco Nombre e Id único del arco.
		 * @return Nombre con el que se etiqueta al arco (su peso).
		 */
		public String transform(String nombreDelArco) {
			Integer indiceHastaDondeCortar = (nombreDelArco.indexOf(':') + 2);
			
			return nombreDelArco.substring(indiceHastaDondeCortar);
		}
		
	}
	
}
