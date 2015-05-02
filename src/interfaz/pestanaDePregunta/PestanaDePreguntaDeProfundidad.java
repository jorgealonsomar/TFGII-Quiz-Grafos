package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeProfundidad;
import util.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeProfundidad extends PestanaDePregunta {

	public PestanaDePreguntaDeProfundidad(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, JTextField ventanaTextoDirectorio) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, ventanaTextoDirectorio);
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaDeProfundidad(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}


	@Override
	protected String getNombreArchivo() {
		return Texto.nombreArchivoPregProfundidad().getString(idioma);
	}
	


}
