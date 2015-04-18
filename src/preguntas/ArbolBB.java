package preguntas;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Clase que maneja árboles binarios de búsqueda.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class ArbolBB{
	/**
	 * Guarda la raíz del árbol.
	 */
    Integer raiz;
    /**
     * Guarda el sub-árbol izquierdo de la raíz.
     */
    ArbolBB subABizq;
    /**
     * Guarda el sub-árbol derecho de la raíz.
     */
    ArbolBB subABder;
    
    /**
     * Constructor de un árbol vacío.
     */
    public ArbolBB(){
        raiz = null;
        subABizq = null;
        subABder = null;
    }
    
    /**
     * Comprueba si el árbol está vacío.
     * @return true si está vacío, false si no lo está.
     */
    private boolean esVacio(){
        boolean vacio = true;        
        if (raiz != null)
            vacio = false;        
        return vacio;
    }
    
    /**
     * Inserta un nodo en el árbol.
     * @param nuevo Nodo a insertar.
     */
    public void insertar(Integer nuevo){
        if(esVacio()){
            raiz = new Integer(nuevo);
            subABizq = new ArbolBB();
            subABder = new ArbolBB();
        }else{
            if(nuevo < raiz)
                subABizq.insertar(nuevo);
            else if (nuevo > raiz)
                subABder.insertar(nuevo);
        }
    }

    /**
     * Guarda el recorrido en anchura de un árbol.
     * @return Array con el recorrido en achura.
     */
    public ArrayList<Integer> recorridoEnAnchura(){
        Vector<ArbolBB> cola = new Vector<ArbolBB>();
        ArbolBB arbol;
       ArrayList<Integer> anchura=new ArrayList<Integer>();
        cola.add(this);
        while(!cola.isEmpty()){
            arbol = cola.elementAt(0);
            cola.remove(0);
            anchura.add(arbol.raiz);
          
            if (!arbol.subABizq.esVacio()) 
            	cola.add(arbol.subABizq );
            if (!arbol.subABder.esVacio()) 
            	cola.add(arbol.subABder );
        }        
        System.out.println();
        return anchura;
    }
    
    /**
     * Halla el desdendiente derecho.
     * @return Desdendiente derecho.
     */
    public ArbolBB desDer(){ 
    	ArbolBB  res; 
    	if(subABder.esVacio()) 
    		res = this; 
    	else res = subABder.desDer(); 
    	return res; 
    }
    
    /**
     * Halla el desdendiente izquierdo.
     * @return Desdendiente izquierdo.
     */
    public ArbolBB desIzq(){ 
    	ArbolBB  res; 
    	if(subABizq.esVacio()) 
    		res = this; 
    	else res = subABizq.desIzq(); 
    	return res; 
    }    

    /**
     * Indica el estado de la raíz en función de sus hijos.
     * @return Estado.
     */
    private int raizEstado(){
    	int res; 
    	if(subABizq.esVacio()){ 
    		if(subABder.esVacio()) 
    			res = 0; 
    		else res = 2; 
    	}else if(subABder.esVacio()) 
    			res = 1; 
    	else res = 3; 
    	return res; 
    }   

    /**
     * Elimina un dato del árbol.
     * @param d Dato a eliminar.
     * @param tipo Tipo de borrado (derecho o izquierdo).
     * @return null si el árbol estaba vacío.
     */
    public Integer eliminar(Integer d, int tipo){ //tipo=1: borrado izqdo., tipo=2: borrado derecho.
    	Integer res=null; 
    	if(!esVacio()) 
    		if(d.equals(raiz)){
    			res = raiz; 
    			int estado = raizEstado(); 
    			switch(estado){ 
	    			case 0: 
	    				raiz = null; 
	    				subABizq = subABder = null; 
	    				break; 
	    			case 1: 
	    				raiz = subABizq.raiz; 
	    				subABder = subABizq.subABder; 
	    				subABizq = subABizq.subABizq; 
	    				break; 
	    			case 2: 
	    				raiz = subABder.raiz; 
	    				subABizq = subABder.subABizq; 
	    				subABder = subABder.subABder; 
	    				break; 
	    			case 3: 	    		
	    				ArbolBB arbol; 
	    				if(tipo==1)
	    					arbol = subABder.desIzq();	
	    				else
	    					arbol = subABizq.desDer();	
	    				raiz = arbol.raiz; 
	    				arbol.eliminar(raiz, tipo);
    				} 
    			}else{ 
    				res = subABizq.eliminar(d, tipo); 
    				if(res==null) 
    					res = subABder.eliminar(d, tipo); 
    			} 
    	return res; 
    } 
}


/**********************************************************************************************************************/


/**
 * Clase que maneja nodos de un árbol.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados
 */
class NodoArbol {

	/**
	 * Nodo hijo izquierdo de un nodo.
	 */
	NodoArbol nodoizquierdo;
	/**
	 * Valor del nodo.
	 */
	int datos;
	/**
	 * Nodo hijo derecho de un nodo.
	 */
	NodoArbol nododerecho;


	/**
     * Constructor de un nodo.
	 * @param datosNodo Datos del nodo.
	 */
	public NodoArbol(int datosNodo){
		datos = datosNodo;
		nodoizquierdo = nododerecho = null; //el nodo no tiene hijos
	}

	/**
	 * Buscar punto de insercion e inserta un nodo nuevo.
	 * @param valorInsertar Valor a insertar.
	 */
	public synchronized void insertar(int valorInsertar){
		//insertar en subarbol izquierdo
		if (valorInsertar < datos){
			//inserta nuevo nodoarbol
			if (nodoizquierdo == null)
				nodoizquierdo = new NodoArbol(valorInsertar);
			else //continua recorriendo subarbol izquierdo
				nodoizquierdo.insertar(valorInsertar);
		}

		//insertar nodo derecho
		else if(valorInsertar > datos){

		//insertar nuevo nodoarbol
		if (nododerecho == null)
			nododerecho = new NodoArbol(valorInsertar);
		else //continua recorriendo subarbol derecho
			nododerecho.insertar(valorInsertar);
		}
	}
		
}

/**
 * Clase que maneja árboles con otro formato.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados
 */
class Arbol{
	/**
	 * Raiz del árbol.
	 */
	NodoArbol raiz;

	/**
	 * Constructor de un árbol vacío.
	 */
	public Arbol(){
		raiz = null;
	}

	/**
	 * Insertar un nuevo nodo en el árbol.
	 * @param valorInsertar Valor del nodo a insertar.
	 */
	public synchronized void insertarNodo(int valorInsertar){
		if(raiz == null)
			raiz = new NodoArbol(valorInsertar); //crea nodo raiz
		else
			raiz.insertar(valorInsertar); // llama al metodo insertar
	}
		
	/**
	 * Comprueba si un nodo tiene hijos y cuántos.
	 * 0= ninguno.
	 * 1= solo hijo izquierdo.
	 * 2= solo hijo derecho.
	 * 3= 2 hijos.
	 * @param arbol2 Arbol donde está el nodo.
	 * @param valor Valor del nodo.
	 * @return Número de hijos.
	 */
	public int tieneHijos(Arbol arbol2, int valor){
		int tieneIzq=0, tieneDer=0;
		int hijos=0;
		NodoArbol nodo = null;
		nodo=arbol2.raiz;		

		while(valor!=nodo.datos){
			if (valor < nodo.datos)
				nodo=nodo.nodoizquierdo;
			
			else if(valor > nodo.datos)
				nodo=nodo.nododerecho;
		}
		if(valor==nodo.datos){
			if (nodo.nodoizquierdo != null)
				tieneIzq=1;			
			if (nodo.nododerecho != null)
				tieneDer=1;
		}
		
		if(tieneIzq==1){
			if(tieneDer==1)
				hijos= 3;
			else
				hijos= 1;
		}
		else if(tieneDer==1)
			hijos=  2;
		return hijos;					
	}
	
	/**
	 * Recorrido en preorden del árbol.
	 * @return Recorrido en preorden.
	 */
	public synchronized ArrayList<Integer> recorridoPreorden()
	{
		ArrayList<Integer> preorden=new ArrayList<Integer>();
		ayudantePreorden(raiz,preorden);
		return preorden;
	}

	/**
	 * Método recursivo para recorrido en preorden.
	 * @param nodo Nodo del que parto.
	 * @param preorden Array a recorrer.
	 */
	private void ayudantePreorden(NodoArbol nodo, ArrayList<Integer> preorden){
		if (nodo == null)
			return;
		preorden.add(nodo.datos);
		ayudantePreorden(nodo.nodoizquierdo, preorden); //recorre subarbol izquierdo
		ayudantePreorden(nodo.nododerecho, preorden); //recorre subarbol derecho		
	}
	
	/**
	 * Método que calcula la profundidad de un nodo.
	 * @param nodo Nodo del que quiero saber la profundidad. 
	 * @return profundidad del nodo.
	 */
	public int profundidad(int nodo){
		NodoArbol n;
		//System.out.println("valor raiz: "+raiz);
		int prof;
		for(n=raiz, prof=0; n!=null; prof++){
			int c=compara(nodo, n.datos);
			if(c<=0){
				if(c==0)
					return prof;
				n=n.nodoizquierdo;
			}
			else
				n=n.nododerecho;
		}
		return -1;
	}
	
	/**
	 * Compara el valor del nodo con un dato concreto.
	 * @param nodo Nodo a comparar.
	 * @param datos Dato a comparar.
	 * @return resultado de la comparación.
	 */
	public int compara(int nodo, int datos){
		int res=0;
		if(nodo<=datos){
			if(nodo==datos)
				res=0;
			else
				res=-1;
		}
		else
			res=1;
		
		return res;
		
	}
}
	