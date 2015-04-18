package utilidades;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Random;

import preguntas.Preguntas;



/**
 * Clase EstrategiaPermutacionCaracteres.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */

public class EstrategiaPermutacionCaracteres implements EstrategiaPermutaciones {

	
	@Override
	public RespuestaGenerada permute(ArrayList<Character> array, Random rnd) {
		if (rnd!=null)
			Collections.shuffle(array, rnd);
		String texto="";
		char letter='A';
		
		int position=0;
		
		char ultimaLetra=(char) ('A'+array.size()-1);
		
		boolean bigger=false;
	
		boolean correct=false;
		Deque<Character> stack = new ArrayDeque<Character>();
	
		
		ArrayList<Character>aux=new ArrayList<Character>();
		
		int i=0;
		
		char taken=0;
		
		boolean finish=false;
		String feedback="";
		

		if(Preguntas.obtenerPropiedades("idioma")==0)
			feedback="LA SECUENCIA QUE REALIZA ES: \t";
		else
			feedback="SEQUENCE PERFORMED: \t";
		
		while(!finish)
		{
			
			while(array.get(position)>=letter)
			{
				
				stack.push(letter);

				if(Preguntas.obtenerPropiedades("idioma")==0)
					feedback=feedback+"Se introduce la letra " +letter+"\t";
				else
					feedback=feedback+"Insert letter " +letter+"\t";
				letter++;
				
			}
			
			if(array.get(0)==ultimaLetra)
			{
				
				bigger=true;
			}
			
			for(int j=0;j<array.size();j++)
			{
				if(array.get(j)>array.get(i))
				{
					
					position=j;
					
					if(array.get(position)==ultimaLetra)
					{
					
						bigger=true;
						
					}
					
					break;
				}
			}
			
			while(array.get(position)>array.get(i))
			{
				
				taken=stack.pop();
	
				if(Preguntas.obtenerPropiedades("idioma")==0)
					feedback=feedback+"Se extrae la letra " +taken+"\t";
				else
					feedback=feedback+"Extract letter " +taken+"\t";
				
				aux.add(taken);
				i++;
			}
			position=i;
			
			if(bigger)
			{
				
				while(letter<=ultimaLetra)
				{
					stack.push(letter);
		
					if(Preguntas.obtenerPropiedades("idioma")==0)
						feedback=feedback+"Se introduce la letra " +letter+"\t";
					else
						feedback=feedback+"Insert letter " +letter+"\t";
					letter++;
				}
				
				while(!stack.isEmpty())
				{
					letter=stack.pop();
					aux.add(letter);
		
					if(Preguntas.obtenerPropiedades("idioma")==0)
						feedback=feedback+"Se extrae la letra " +letter+"\t";
					else
						feedback=feedback+"Extract letter " +letter+"\t";
				}
			}
			
			if(stack.isEmpty()&&bigger)
			{
				
				finish=true;
			}
		}
	
	
		if(aux.equals(array))
		
			correct=true;
	
		else
		
			correct=false;
		for(int k=0;k<array.size();k++)
		{
			texto=texto+array.get(k)+" ";
		}
		RespuestaGenerada respuesta=new RespuestaGenerada(texto,correct,feedback);
		return respuesta;
	}

}
	
	
	



