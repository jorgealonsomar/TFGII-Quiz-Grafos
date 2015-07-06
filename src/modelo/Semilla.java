package modelo;

import modelo.pregunta.VisualizacionGrafo;

/**
 * Clase que contiene la información necesaria para volver a construir una pregunta.
 * Puede convertirse exportarse en forma de código numérico.
 * @author Jorge Alonso Márquez
 */
public class Semilla {
	
	/**
	 * Número correspondiente al tipo y clase de la pregunta.
	 */
	private Integer numPregunta;
	
	/**
	 * Número correspondiente a las preguntas de recorrido en profundidad.
	 */
	public static final Integer RECORRIDO_EN_PROFUNDIDAD = 0;
	
	/**
	 * Número correspondiente a las preguntas de recorrido en anchura.
	 */
	public static final Integer RECORRIDO_EN_ANCHURA = 1;
	
	/**
	 * Número correspondiente a las preguntas de clasificación topológica.
	 */
	public static final Integer CLASIFICACION_TOPOLOGICA = 2;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Dijkstra de la clase Distancias más cortas.
	 */
	public static final Integer ALGORITMO_DE_DIJKSTRA_DISTANCIAS_MAS_CORTAS = 3;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Dijkstra de la clase Ruta más corta.
	 */
	public static final Integer ALGORITMO_DE_DIJKSTRA_RUTA_MAS_CORTA = 4;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Dijkstra de la clase Orden de selección.
	 */
	public static final Integer ALGORITMO_DE_DIJKSTRA_ORDEN_DE_SELECCION = 5;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Prim de la clase Arcos del ábol de expansión.
	 */
	public static final Integer ALGORITMO_DE_PRIM_ARCOS_DEL_ARBOL_DE_EXPANSION = 6;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Prim de la clase Orden de selección.
	 */
	public static final Integer ALGORITMO_DE_PRIM_ORDEN_DE_SELECCION = 7;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Kruskal de la clase Arcos del ábol de expansión.
	 */
	public static final Integer ALGORITMO_DE_KRUSKAL_ARCOS_DEL_ARBOL_DE_EXPANSION = 8;
	
	/**
	 * Número correspondiente a las preguntas del algoritmo de Kruskal de la clase Orden de selección.
	 */
	public static final Integer ALGORITMO_DE_KRUSKAL_ORDEN_DE_SELECCION = 9;
	
	
	/**
	 * Valor correspondiente al número de nodos.
	 * Se empieza a contar desde el 0, por lo que podrá tomar valores desde el 0 (1 nodo) hasta el 9 (10 nodos).
	 */
	private Integer valorNNodos;
	
	/**
	 * Indica si el grafo es o no dirigido.
	 */
	private boolean esDirigido;
	
	/**
	 * Porcentaje de arcos con el que se ha de crear el grafo.	
	 */
	private Double porcentajeDeArcos;
	
	/**
	 * Tipo de visualización con la que se representa el grafo.
	 */
	private VisualizacionGrafo visualizacionGrafo;
	
	/**
	 * Clase de la pregunta.
	 */
	private Integer claseDePregunta;
	
	/**
	 * Seed que se le da al random durante la generación de la pregunta. Esto consigue que los valores
	 * generados "de forma aleatoria" sean en realidad los mismos cada vez.
	 */
	private String seedDelRandom;
	
	
	/**
	 * Constructor. Construye una nueva semilla.
	 * @param numPregunta Número asociado a la pregunta.
	 * @param nNodos Número de nodos que tiene el grafo asociado a la pregunta.
	 * @param esDirigido Si el grafo es o no dirigido.
	 * @param porcentajeDeArcos Porcentaje de arcos que tiene el grafo asociado a la pregunta.
	 * @param visualizacionGrafo Modo de visualización del grafo de la pregunta.
	 * @param claseDePregunta Clase de pregunta.
	 * @param seedDelRandom Seed que se le da al random durante la generación de la pregunta.
	 */
	public Semilla(Integer numPregunta, Integer nNodos, boolean esDirigido, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo, Integer claseDePregunta, String seedDelRandom){
		this.numPregunta = numPregunta;
		this.valorNNodos = nNodos-1;
		this.esDirigido = esDirigido;
		this.porcentajeDeArcos = porcentajeDeArcos;
		this.visualizacionGrafo = visualizacionGrafo;
		this.claseDePregunta = claseDePregunta;
		this.seedDelRandom = seedDelRandom;
	}
	
	
	/**
	 * Constructor. Crea una semilla a partir de su código.
	 * @param codigo
	 *            Código numérico equivalente a la semilla.
	 * @throws SemillaException
	 *             Excepción relativa a las semillas.
	 */
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
				this.claseDePregunta = valorAux;
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
	
	
	/**
	 * Devuelve el código correspondiente a la semilla.
	 * @return Código correspondiente a la semilla.
	 */
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
		cadena += claseDePregunta;
		
		//Seed del random
		cadena += seedDelRandom;
		
		return cadena;
	}
	
	
	/**
	 * Devuelve el número de la pregunta.
	 * @return Número correspondiente a la pregunta.
	 */
	public Integer getNumPregunta(){
		return numPregunta;
	}
	
	
	/**
	 * Devuelve el número de nodos que tiene grafo asociado a la pregunta de esta semilla.
	 * @return Número de nodos del grafo asociado.
	 */
	public Integer getNNodos(){
		return valorNNodos+1;
	}
	
	
	/**
	 * Indica si el grafo asociado a la pregunta de esta semilla es o no dirigido.
	 * @return Si el grafo asociado es dirigido.
	 */
	public boolean isDirigido(){
		return esDirigido;
	}
	
	
	/**
	 * Devuelve el porcentaje de arcos que tiene grafo asociado a la pregunta de esta semilla.
	 * @return Porcentaje de arcos del grafo asociado.
	 */
	public Double getPorcentajeDeArcos(){
		return porcentajeDeArcos;
	}
	
	
	/**
	 * Devuelve el modo de visualización por el que se representará al grafo asociado a la pregunta de esta semilla.
	 * @return Método de visualización del grafo asociado.
	 */
	public VisualizacionGrafo getVisualizacionGrafo(){
		return visualizacionGrafo;
	}
	
	
	/**
	 * Devuelve la clase de la pregunta de esta semilla.
	 * @return Clase de la pregunta.
	 */
	public Integer getClaseDePregunta(){
		return claseDePregunta;
	}
	
	
	/**
	 * Devuelve la seed que se utiliza para la construcción del random empleado en la generación de la pregunta.
	 * @return Seed del random empleado para la generación de la pregunta.
	 */
	public String getSeedDelRandom(){
		return seedDelRandom;
	}
	
}
