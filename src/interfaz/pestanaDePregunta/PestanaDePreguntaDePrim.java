package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDePrim;
import texto.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDePrim extends PestanaDePregunta {

	public PestanaDePreguntaDePrim(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaDePrim(getNumNodos(), getPorcentajeArcos(), isDirigido(), getVisualizacionGrafo());
	}

}
