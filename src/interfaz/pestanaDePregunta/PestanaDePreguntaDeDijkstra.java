package interfaz.pestanaDePregunta;

import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeDijkstra;
import modelo.pregunta.PreguntaDeDijkstra_DistanciasMasCortas;
import modelo.pregunta.PreguntaDeDijkstra_OrdenDeSeleccion;
import modelo.pregunta.PreguntaDeDijkstra_RutaMasCorta;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pestaña del panel tabulado de preguntas correspondiente a las preguntas de Algoritmo de Dijkstra.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class PestanaDePreguntaDeDijkstra extends PestanaDePregunta {
	
	/**
	 * Constructor de la clase.
	 * Establece el selector de clase de pregunta visible y le añade las tres posibles clases de pregunta de
	 * Algoritmo de Dijkstra.
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
	public PestanaDePreguntaDeDijkstra(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, frame, panelCentral);
		
		setVisibleClasePregunta(true);
		addClasePregunta(Textos_Preguntas.tipoPreguntaDijkstra_DistanciasMasCortas());
		addClasePregunta(Textos_Preguntas.tipoPreguntaDijkstra_RutaMasCorta());
		addClasePregunta(Textos_Preguntas.tipoPregunta_OrdenDeSeleccion());
	}
	
	
	/**
	 * Genera una nueva pregunta de Dijkstra. Puede ser de Distancias más cortas, de Ruta más corta o de Orden de
	 * selección, según esté seleccionado.
	 * @return Nueva pregunta generada.
	 */
	@Override
	protected Pregunta generarPregunta() {
		switch(getClaseDePregunta()){
		case PreguntaDeDijkstra.PREGUNTA_DISTANCIAS_MAS_CORTAS:
			return new PreguntaDeDijkstra_DistanciasMasCortas(	panelCentral.getNumNodos(),
																panelCentral.getPorcentajeArcos(),
																panelCentral.isDirigido(), 
																panelCentral.getVisualizacionGrafo());
		case PreguntaDeDijkstra.PREGUNTA_RUTA_MAS_CORTA:
			return new PreguntaDeDijkstra_RutaMasCorta(	panelCentral.getNumNodos(),
														panelCentral.getPorcentajeArcos(),
														panelCentral.isDirigido(), 
														panelCentral.getVisualizacionGrafo());
		case PreguntaDeDijkstra.PREGUNTA_ORDEN_DE_SELECCION: default:
			return new PreguntaDeDijkstra_OrdenDeSeleccion(	panelCentral.getNumNodos(),
															panelCentral.getPorcentajeArcos(),
															panelCentral.isDirigido(), 
															panelCentral.getVisualizacionGrafo());
		}
		
	}

}
