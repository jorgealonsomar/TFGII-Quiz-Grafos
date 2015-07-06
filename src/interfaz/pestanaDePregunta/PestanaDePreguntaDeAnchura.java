package interfaz.pestanaDePregunta;

import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import texto.Texto;

/**
 * Pestaña del panel tabulado de preguntas correspondiente a las preguntas de recorrido en anchura.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class PestanaDePreguntaDeAnchura extends PestanaDePregunta {
	
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
	public PestanaDePreguntaDeAnchura(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, frame, panelCentral);
	}
	
	
	/**
	 * Genera una nueva pregunta de anchura.
	 * @return Nueva pregunta generada.
	 */
	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaDeAnchura(	panelCentral.getNumNodos(),
										panelCentral.getPorcentajeArcos(),
										panelCentral.isDirigido(), 
										panelCentral.getVisualizacionGrafo() );
	}

}
