package modelo;

import modelo.pregunta.VisualizacionGrafo;

public class Semilla {
	
	private Integer numPregunta;
	
	public static final Integer RECORRIDO_EN_PROFUNDIDAD = 0;
	public static final Integer RECORRIDO_EN_ANCHURA = 1;
	public static final Integer CLASIFICACION_TOPOLOGICA = 2;
	public static final Integer ALGORITMO_DE_DIJKSTRA_DISTANCIAS_MAS_CORTAS = 3;
	public static final Integer ALGORITMO_DE_DIJKSTRA_RUTA_MAS_CORTA = 4;
	public static final Integer ALGORITMO_DE_DIJKSTRA_ORDEN_DE_SELECCION = 5;
	public static final Integer ALGORITMO_DE_PRIM_ARCOS_DEL_ARBOL_DE_EXPANSION = 6;
	public static final Integer ALGORITMO_DE_PRIM_ORDEN_DE_SELECCION = 7;
	public static final Integer ALGORITMO_DE_KRUSKAL_ARCOS_DEL_ARBOL_DE_EXPANSION = 8;
	public static final Integer ALGORITMO_DE_KRUSKAL_ORDEN_DE_SELECCION = 9;
	
	/** Valor correspondiente al número de nodos.
	 * Se empieza a contar desde el 0, por lo que podrá tomar valores desde el 0 (1 nodo)
	 * hasta el 9 (10 nodos) */
	private Integer valorNNodos;
	
	private boolean esDirigido;
	
	private Double porcentajeDeArcos;
	
	private VisualizacionGrafo visualizacionGrafo;
	
	private Integer tipoDePregunta;
	
	private String seedDelRandom;
	
	
	/** Constructor. Construye una nueva semilla */
	public Semilla(Integer numPregunta, Integer nNodos, boolean esDirigido, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo, Integer tipoDePregunta, String seedDelRandom){
		this.numPregunta = numPregunta;
		this.valorNNodos = nNodos-1;
		this.esDirigido = esDirigido;
		this.porcentajeDeArcos = porcentajeDeArcos;
		this.visualizacionGrafo = visualizacionGrafo;
		this.tipoDePregunta = tipoDePregunta;
		this.seedDelRandom = seedDelRandom;
	}
	
	
	/** Constructor. Crea una semilla a partir de su código */
	public Semilla(String codigo) throws SemillaException {
		Integer valorAux;
		try{

			//Primer carácter. Corresponde al número de pregunta
			this.numPregunta = Integer.parseInt(codigo.substring(0, 1));
			//(cualquier valor será válido, ya que puede tomar como valor un número del 0 al 9)

			
			//Segundo carácter. Corresponde al valorNNodos (al número de nodos del grafo - 1) 
			this.valorNNodos = Integer.parseInt(codigo.substring(1, 2));
			//(cualquier valor será válido, ya que puede tomar como valor un número del 0 al 9)
			
			
			//Tercer carácter. Indica si el grafo es o no dirigido
			if(codigo.substring(2, 3).equals("0")){
				this.esDirigido = false;
			} else {
				this.esDirigido = true;
			}
			
			//Cuarto, quinto y sexto carácter. Corresponde al porcentaje de arcos
			valorAux = Integer.parseInt(codigo.substring(3, 6));
			
			if(0 <= valorAux && valorAux <= 100){
				this.porcentajeDeArcos = ((double)valorAux)/100;
			} else {
				throw new SemillaException("El valor de los caracteres 4º, 5º y 6º de la semilla debe estar"
						+ "entre en 0 y el 100");
			}
			
			//Séptimo carácter. Indica el modo de visualización
			valorAux = Integer.parseInt(codigo.substring(6, 7));
			
			switch(valorAux){
			case 0:
				this.visualizacionGrafo = VisualizacionGrafo.MATRIZ_DE_ADYACENCIA;
				break;
			case 1:
				this.visualizacionGrafo = VisualizacionGrafo.LISTA_DE_ADYACENCIA;
				break;
			case 2:
				this.visualizacionGrafo = VisualizacionGrafo.GRAFO_VISUAL;
				break;
			default:
				throw new SemillaException("El valor del 7º carácter de la semilla debe estar"
						+ "entre en 0 y el 2");
			}
			
			//Octavo carácter. Indica el tipo de pregunta
			valorAux = Integer.parseInt(codigo.substring(7, 8));
			
			if(0 <= valorAux && valorAux <= 2){
				this.tipoDePregunta = valorAux;
			} else {
				throw new SemillaException("El valor del 8º carácter de la semilla debe estar"
						+ "entre en 0 y el 2");
			}
			
			//Resto de caracteres. Corresponden a la seed del random
			seedDelRandom = codigo.substring( 8, codigo.length() );
			
		}catch(Exception excepcion){
			throw new SemillaException("El formato del código usado para generar la semilla es erróneo.");
		}
	}
	
	
	
	/** Devuelve el código de correspondiente a la semilla */
	@Override
	public String toString(){
		String cadena = "";
		
		//Número de pregunta
		cadena += numPregunta;
		
		//Número de nodos
		cadena += valorNNodos;
		
		//Es dirigido
		if(esDirigido){
			cadena += "1";
		} else {
			cadena += "0";
		}
		
		//Porcentaje de arcos
		String cadenaPorcentajeDeArcos = new Integer((new Double(porcentajeDeArcos*100)).intValue()).toString();
		//Debe ocupar 3 caracteres
		for (int i = 0; i < 3-cadenaPorcentajeDeArcos.length(); i++) {
			cadenaPorcentajeDeArcos = "0" + cadenaPorcentajeDeArcos;
		}
		cadena += cadenaPorcentajeDeArcos;
		
		//Modo de visualización
		switch(visualizacionGrafo){
		case MATRIZ_DE_ADYACENCIA:
			cadena += 0;
			break;
		case LISTA_DE_ADYACENCIA:
			cadena += 1;
			break;
		case GRAFO_VISUAL: default:
			cadena += 2;
			break;
		}
		
		//Tipo de pregunta
		cadena += tipoDePregunta;
		
		//Seed del random
		cadena += seedDelRandom;
		
		return cadena;
	}
	
	
	public Integer getNumPregunta(){
		return numPregunta;
	}
	
	
	/** Devuelve el número de nodos que tiene grafo asociado a esta semilla. */
	public Integer getNNodos(){
		return valorNNodos+1;
	}
	
	
	public boolean isDirigido(){
		return esDirigido;
	}
	
	
	public Double getPorcentajeDeArcos(){
		return porcentajeDeArcos;
	}
	
	
	public VisualizacionGrafo getVisualizacionGrafo(){
		return visualizacionGrafo;
	}
	
	
	public Integer getTipoDePregunta(){
		return tipoDePregunta;
	}
	
	
	public String getSeedDelRandom(){
		return seedDelRandom;
	}
	
}
