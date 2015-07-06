package modelo.grafo;

/**
 * Arco del grafo. Está definido por los nodos de sus dos extremos.
 * @author Jorge Alonso Márquez
 */
public class Arco {
	
	/**
	 * Nodo en el primer extremo del arco.
	 * En grafos dirigidos, se toma como el nodo de origen.
	 */
	private Integer nodoOrigen;
	
	/**
	 * Nodo en el segundo extremo del arco.
	 * En grafos dirigidos, se toma como el nodo de destino.
	 */
	private Integer nodoDestino;
	
	
	/**
	 * Constructor de la clase.
	 * @param nodoOrigen Primer nodo (nodo de origen en grafos dirigidos).
	 * @param nodoDestino Segundo nodo (nodo de destino en grafos dirigidos).
	 */
	public Arco(Integer nodoOrigen, Integer nodoDestino){
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
	}
	
	
	/**
	 * Devuelve el primer nodo (el nodo de origen en grafos dirigidos).
	 * @return Primer nodo (nodo de origen en grafos dirigidos).
	 */
	public Integer getNodoOrigen(){
		return nodoOrigen;
	}
	
	
	/**
	 * Devuelve el segundo nodo (el nodo de destino en grafos dirigidos).
	 * @return Segundo nodo (nodo de destino en grafos dirigidos).
	 */
	public Integer getNodoDestino(){
		return nodoDestino;
	}
}
