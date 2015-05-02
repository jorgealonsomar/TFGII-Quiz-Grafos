package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeDijkstra;
import util.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeDijkstra extends PestanaDePregunta {

	public PestanaDePreguntaDeDijkstra(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, JTextField ventanaTextoDirectorio) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, ventanaTextoDirectorio);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaDeDijkstra(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}


	@Override
	protected String getNombreArchivo() {
		return Texto.nombreArchivoPregDijkstra().getString(idioma);
	}
	
	
	
}
