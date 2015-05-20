package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeDijkstra;
import util.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeDijkstra extends PestanaDePregunta {

	public PestanaDePreguntaDeDijkstra(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaDeDijkstra(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}
	
}
