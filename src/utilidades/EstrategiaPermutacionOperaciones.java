package utilidades;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import preguntas.Preguntas;



/**
 * Clase EstrategiaPermutacionOperaciones.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class EstrategiaPermutacionOperaciones implements EstrategiaPermutaciones {

	@Override
	public RespuestaGenerada permute(ArrayList<Character> array, Random rnd) {
	
		boolean flag1=true;
		String texto="";
		int stackSize=array.size();
		Stack<Character> stack = new Stack<Character>();
		int times=0;
		int get=0;
		int taken=0;
		ArrayList<Character>posibles=new ArrayList<Character>();
		int route=1;
		
		int introduced=0;
		char x='A';
		char y;
	
		String feedback="";
		

		if(Preguntas.obtenerPropiedades("idioma")==0)
			feedback="LA SECUENCIA QUE REALIZA ES: \t";
		else
			feedback="SEQUENCE PERFORMED: \t";
		
		while(flag1 )
		{
			route=1;
			times=rnd.nextInt(stackSize-1)+1;;
			
			while(route<=times)
			{
				
				 stack.push(x);
			
				 if(Preguntas.obtenerPropiedades("idioma")==0)
					feedback=feedback+"Se introduce "+x+".\t";
				 else
					feedback=feedback+"Insert "+x+".\t";
				  route++;
				
				  x++;
				  introduced++;
				 
				  if(introduced==stackSize)
				  {
					  
					 flag1=false;
					 break;
				  }
				
			}
			
			get=(int) rnd.nextInt(times)+1;
			while ( get>taken && !stack.empty())
			{
				y=stack.pop();
				posibles.add(y);
	
				if(Preguntas.obtenerPropiedades("idioma")==0)
					feedback=feedback+"Se extrae "+y+".\t";
				else
					feedback=feedback+"Extract "+y+".\t";
				 taken++;
			}
				
			taken=0;
			
		}
		while(!stack.empty())
		{
			y=stack.pop();
			posibles.add(y);

			if(Preguntas.obtenerPropiedades("idioma")==0)
				feedback=feedback+"Se extrae "+y+".\t";
			else
				feedback=feedback+"Extract "+y+".\t";
		}
		for(int k=0;k<posibles.size();k++)
			texto=texto+posibles.get(k)+" ";
		RespuestaGenerada respuesta= new RespuestaGenerada(texto,true,feedback);
		return respuesta;
		
	}

}
