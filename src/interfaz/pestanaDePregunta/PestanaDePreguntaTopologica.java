package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaTopologica;
import texto.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaTopologica extends PestanaDePregunta {

	public PestanaDePreguntaTopologica(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame, panelCentral);
		
//		deshabilitarGrafoDirigido(true);
	}

	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaTopologica(	panelCentral.getNumNodos(),
										panelCentral.getPorcentajeArcos(), 
										panelCentral.getVisualizacionGrafo());
	}

}
