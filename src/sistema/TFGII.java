package sistema;

import interfaz.FramePrincipal;

import java.util.logging.Logger;

public class TFGII {
	
	/** Logger del programa */
	public final static Logger LOGGER = Logger.getLogger("");
	
	/** Método main del programa */
	public static void main(String args[]){
		Parametros parametros = new Parametros();
		
		FramePrincipal frameGrafos = new FramePrincipal(parametros);
		frameGrafos.repaint();
	}
	
}
