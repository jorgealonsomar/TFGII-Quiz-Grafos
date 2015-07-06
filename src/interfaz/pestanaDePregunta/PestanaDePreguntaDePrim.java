package interfaz.pestanaDePregunta;

import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDePrim;
import modelo.pregunta.PreguntaDePrim_ArcosDelArbolDeExpansion;
import modelo.pregunta.PreguntaDePrim_OrdenDeSeleccion;
import texto.Texto;
import texto.Textos_Preguntas;

/**
 * Pestaña del panel tabulado de preguntas correspondiente a las preguntas de Algoritmo de Prim.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class PestanaDePreguntaDePrim extends PestanaDePregunta {
	
	/**
	 * Constructor de la clase.
	 * Establece el selector de clase de pregunta visible y le añade las dos posibles clases de pregunta de
	 * Algoritmo de Prim.
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
	public PestanaDePreguntaDePrim(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, frame, panelCentral);
		
		setVisibleClasePregunta(true);
		addClasePregunta(Textos_Preguntas.tipoPregunta_ArcosDelArbolDeExpansion());
		addClasePregunta(Textos_Preguntas.tipoPregunta_OrdenDeSeleccion());
	}
	
	
	/**
	 * Genera una nueva pregunta de Prim. Puede ser de Arcos del árbol de expansión o de Orden de selección,
	 * según esté seleccionado.
	 * @return Nueva pregunta generada.
	 */
	@Override
	protected Pregunta generarPregunta(){
		switch(getClaseDePregunta()){
		case PreguntaDePrim.PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION:
			return new PreguntaDePrim_ArcosDelArbolDeExpansion(	panelCentral.getNumNodos(),
																panelCentral.getPorcentajeArcos(),
																panelCentral.getVisualizacionGrafo());
		case PreguntaDePrim.PREGUNTA_ORDEN_DE_SELECCION: default:
			return new PreguntaDePrim_OrdenDeSeleccion(	panelCentral.getNumNodos(),
														panelCentral.getPorcentajeArcos(),
														panelCentral.getVisualizacionGrafo());
		}
	}

}
