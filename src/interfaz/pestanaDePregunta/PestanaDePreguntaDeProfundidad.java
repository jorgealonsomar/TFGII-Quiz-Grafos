package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeProfundidad;
import texto.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeProfundidad extends PestanaDePregunta {

	public PestanaDePreguntaDeProfundidad(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame, panelCentral);
	}

	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaDeProfundidad(	panelCentral.getNumNodos(),
											panelCentral.getPorcentajeArcos(),
											panelCentral.isDirigido(), 
											panelCentral.getVisualizacionGrafo() );
	}

}
