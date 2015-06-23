package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaTopologica;
import texto.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaTopologica extends PestanaDePregunta {

	public PestanaDePreguntaTopologica(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
		
		deshabilitarGrafoDirigido(true);
		setParametrosSelectorPorcentajeArcos(5, 2, 1);
	}

	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaTopologica(getNumNodos(), getPorcentajeArcos(), getVisualizacionGrafo());
	}

}
