package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeProfundidad;
import util.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeProfundidad extends PestanaDePregunta {

	public PestanaDePreguntaDeProfundidad(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaDeProfundidad(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}

}
