package modelo;

public class Arco {
	private Integer nodoOrigen;
	private Integer nodoDestino;
	
	
	/** Constructor de la clase */
	public Arco(Integer nodoOrigen, Integer nodoDestino){
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
	}
	
	
	public Integer getNodoOrigen(){
		return nodoOrigen;
	}
	
	
	public Integer getNodoDestino(){
		return nodoDestino;
	}
}
