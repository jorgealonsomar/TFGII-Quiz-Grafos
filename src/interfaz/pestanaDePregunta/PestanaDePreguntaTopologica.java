package interfaz.pestanaDePregunta;

import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaTopologica;
import texto.Texto;

/**
 * Pestaña del panel tabulado de preguntas correspondiente a las preguntas de clasificación topológica.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class PestanaDePreguntaTopologica extends PestanaDePregunta {
	
	/**
	 * Constructor de la clase.
	 * @param panelTabulado
	 *            Panel tabulado que contiene esta pestaña.
	 * @param nombreDeLaPestana
	 *            Nombre que recibe esta pestaña.
	 * @param teclaMnemotecnica
	 *            Tecla mnemotécnica asociada a esta pestaña.
	 * @param frame
	 *            Frame de la aplicación.
	 * @param panelCentral
	 *            Panel que contiene las opciones generales.
	 */
	public PestanaDePreguntaTopologica(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, frame, panelCentral);
	}
	
	
	/**
	 * Genera una nueva pregunta de topología.
	 * @return Nueva pregunta generada.
	 */
	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaTopologica(	panelCentral.getNumNodos(),
										panelCentral.getPorcentajeArcos(), 
										panelCentral.getVisualizacionGrafo());
	}

}
