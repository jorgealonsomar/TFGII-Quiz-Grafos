package modelo.pregunta;

import texto.Texto;


/**
 * Clase que indica los distintos modos en los que se ha de visualizar el grafo.
 * @author Jorge Alonso Márquez
 */
public class VisualizacionGrafo {
	
	/**
	 * Indicador de que la clase debe mostrarse como una matriz de adyacencia.
	 */
	private boolean matrizDeAdyacencia;
	
	/**
	 * Indicador de que la clase debe mostrarse como una lista de adyacencia.
	 */
	private boolean listaDeAdyacencia;
	
	/**
	 * Indicador de que la clase debe mostrarse visualmente.
	 */
	private boolean grafoVisual;
	
	
	/**
	 * Constructor de la clase.
	 * Establece los modos de visualización dados.
	 * @param matrizDeAdyacencia
	 *            Si la clase debe mostrarse como una matriz de adyacencia.
	 * @param listadeAdyacencia
	 *            Si la clase debe mostrarse como una lista de adyacencia.
	 * @param grafoVisual
	 *            Si la clase debe mostrarse visualmente.
	 */
	public VisualizacionGrafo(boolean matrizDeAdyacencia, boolean listadeAdyacencia, boolean grafoVisual){
		this.matrizDeAdyacencia = matrizDeAdyacencia;
		this.listaDeAdyacencia = listadeAdyacencia;
		this.grafoVisual = grafoVisual;
	}
	
	
	/**
	 * Constructor de la clase.
	 * Construye la clase a partir de un código. El valor de ese código será un número entre el 0 y el 7,
	 * que traducido a binario indicará los modos seleccionados: El primer dígito por la izda. corresponde
	 * a la matriz de adyacencia; el segundo, a la lista de adyacencia y el tercero, al grafo visual.
	 * 
	 * Ejemplo: 6 (110 en binario) : Matriz de adyacencia y lista de adyacencia, pero no grafo visual.
	 * @param codigo
	 *            Número que contiene codificados los modos de visualización.
	 */
	public VisualizacionGrafo(String codigo){
		String codigoBinario = Integer.toBinaryString(Integer.parseInt(codigo));
		
		//Su tamano debe ser 3
		codigoBinario = Texto.anadirCaracteresPorLaIzquierda(codigoBinario, '0', 3);
		
		if(codigoBinario.substring(0, 1).equals("0")){
			matrizDeAdyacencia = false;
		} else {
			matrizDeAdyacencia = true;
		}
		
		if(codigoBinario.substring(1, 2).equals("0")){
			listaDeAdyacencia = false;
		} else {
			listaDeAdyacencia = true;
		}
		
		if(codigoBinario.substring(2, 3).equals("0")){
			grafoVisual = false;
		} else {
			grafoVisual = true;
		}
		
	}
	
	
	/**
	 * Indica si la clase debe mostrarse como una matriz de adyacencia.
	 * @return si la clase debe mostrarse como una matriz de adyacencia.
	 */
	public boolean isMatrizDeAdyacencia(){
		return matrizDeAdyacencia;
	}
	
	
	/**
	 * Indica si la clase debe mostrarse como una lista de adyacencia.
	 * @return si la clase debe mostrarse como una lista de adyacencia.
	 */
	public boolean isListaDeAdyacencia(){
		return listaDeAdyacencia;
	}
	
	
	/**
	 * Indica si la clase debe mostrarse visualmente.
	 * @return si la clase debe mostrarse visualmente.
	 */
	public boolean isGrafoVisual(){
		return grafoVisual;
	}
	
	
	/**
	 * Codifica la clase. El valor de ese código será un número entre el 0 y el 7,
	 * que traducido a binario indicará los modos seleccionados: El primer dígito por la izda. corresponde
	 * a la matriz de adyacencia; el segundo, a la lista de adyacencia y el tercero, al grafo visual.
	 * 
	 * Ejemplo: 6 (110 en binario) : Matriz de adyacencia y lista de adyacencia, pero no grafo visual.
	 * @return Número que contiene codificados los modos de visualización.
	 */
	public String getCodigo(){
		String codigoBinario = "";
		
		if(matrizDeAdyacencia){
			codigoBinario += "1";
		} else {
			codigoBinario += "0";
		}
		
		if(listaDeAdyacencia){
			codigoBinario += "1";
		} else {
			codigoBinario += "0";
		}
		
		if(grafoVisual){
			codigoBinario += "1";
		} else {
			codigoBinario += "0";
		}
		
		Integer codigoEntero = Integer.parseInt(codigoBinario, 2);
		return codigoEntero.toString(); 
	}
	
	
	
}
