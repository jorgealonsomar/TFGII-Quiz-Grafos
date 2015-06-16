package modelo;

public class Semilla {
	
	private Integer tipoPregunta;
	
	public static final Integer recorridoEnProfunidad = 0;
	public static final Integer recorridoEnAnchura = 1;
	public static final Integer clasificacionTopologica = 2;
	public static final Integer algoritmoDeDijkstra = 3;
	public static final Integer algoritmoDePrim = 4;
	public static final Integer algoritmoDeKruskal = 5;
	
	/** Valor correspondiente al n�mero de nodos.
	 * Se empieza a contar desde el 0, por lo que podr� tomar valores desde el 0 (1 nodo)
	 * hasta el 9 (10 nodos) */
	private Integer valorNNodos;
	
	private boolean esDirigido;
	
	private Integer[][] matrizDeAdyacencia;
	
	
	/** Constructor. Construye una nueva semilla */
	public Semilla(Integer tipoPregunta, Integer nNodos, boolean esDirigido, Integer[][] matrizDeAdyacencia){
		this.tipoPregunta = tipoPregunta;
		this.valorNNodos = nNodos-1;
		this.esDirigido = esDirigido;
		this.matrizDeAdyacencia = matrizDeAdyacencia;
	}
	
	
	/** Constructor. Recupera una vieja semilla a partir de su c�digo */
	public Semilla(String codigo) throws ConsignaException {
		try{
			
			Integer valorAux;
			
			//Primer car�cter. Corresponde al tipo de pregunta
			valorAux = Integer.parseInt(codigo.substring(0, 1));
			
			if(0 <= valorAux && valorAux <= 5){
				this.tipoPregunta = valorAux;
			} else {
				throw new ConsignaException("El valor del primer car�cter de la semilla debe estar entre en 0 y el 5");
			}
			
			
			//Segundo car�cter. Corresponde al valorNNodos (al n�mero de nodos del grafo - 1) 
			this.valorNNodos = Integer.parseInt(codigo.substring(1, 2));
			//(cualquier valor ser� v�lido, ya que puede tomar como valor un n�mero del 0 al 9)
			
			
			//El tercer car�cter indica si el grafo es o no dirigido
			if(codigo.substring(2, 3).equals("0")){
				this.esDirigido = false;
			} else {
				this.esDirigido = true;
			}
			
			//Los caracteres restantes corresponden a los valores de la matriz de adyacencia
			
			//Inicializar la matriz de adyacencia
			matrizDeAdyacencia = new Integer[getNNodos()][getNNodos()];
			for(int i = 0; i < matrizDeAdyacencia.length; i++){
				for(int j = 0; j < matrizDeAdyacencia[i].length; j++){
					matrizDeAdyacencia[i][j] = 0;
				}
			}
				
			//Por cada fila de la matriz de adyacencia:
			int f = 0;
			for(int i = 3; i < codigo.length(); i = i+3){
				Integer valorEntero = Integer.parseInt(codigo.substring(i, i+3));
				String cadenaBinaria = Integer.toBinaryString(valorEntero);
				
				//Se asegura que el valor de cadenaBinaria hasta que tenga una cantidad nNodos de d�gitos
				while(cadenaBinaria.length() < (valorNNodos+1)){
					cadenaBinaria = "0" + cadenaBinaria;
				}
				
				for(int c = 0; c < matrizDeAdyacencia[f].length; c++){
					matrizDeAdyacencia[f][c] = Integer.parseInt(cadenaBinaria.substring(c, c+1));
				}
				
				f++;
			}
			
		}catch(Exception excepcion){
			throw new ConsignaException("El formato del c�digo usado para generar la semilla es err�neo.");
		}
	}
	
	
	
	/** Devuelve el c�digo de correspondiente a la semilla */
	@Override
	public String toString(){
		String cadena = "";
		
		cadena += tipoPregunta;
		cadena += valorNNodos;
		
		if(esDirigido){
			cadena += "1";
		} else {
			cadena += "0";
		}
		
		//Por cada fila de la matriz de adyacencia:
		for(int f = 0; f < matrizDeAdyacencia.length; f++){
			//Se convierten los valores de esa fila en un n�mero 
			
			Integer valorFila = 0;
			int exponente = matrizDeAdyacencia.length-1;
			for(int c = 0; c < matrizDeAdyacencia[f].length; c++){
				
				valorFila += (int) (matrizDeAdyacencia[f][c]*(Math.pow(2, exponente)));
				
				exponente--;
			}
			
			//Se asegura que el valor de esta fila hasta que sea una cadena de 3 d�gitos
			String cadenaFila = valorFila.toString();
			while(cadenaFila.length() < 3){
				cadenaFila = "0" + cadenaFila;
			}
			cadena += cadenaFila;
		}
		
		return cadena;
	}
	
	
	public Integer getTipoPregunta(){
		return tipoPregunta;
	}
	
	
	/** Devuelve el n�mero de nodos que tiene grafo asociado a esta semilla. */
	public Integer getNNodos(){
		return valorNNodos+1;
	}
	
	
	public boolean esDirigido(){
		return esDirigido;
	}
	
	
	public Integer[][] getMatrizDeAdyacencia(){
		return matrizDeAdyacencia;
	}
	
}
