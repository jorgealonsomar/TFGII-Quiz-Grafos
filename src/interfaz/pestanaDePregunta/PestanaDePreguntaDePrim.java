package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;
import interfaz.PanelCentral;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDePrim;
import modelo.pregunta.PreguntaDePrim_ArcosDelArbolDeExpansion;
import modelo.pregunta.PreguntaDePrim_OrdenDeSeleccion;
import texto.Texto;
import texto.Textos_Preguntas;

@SuppressWarnings("serial")
public class PestanaDePreguntaDePrim extends PestanaDePregunta {

	public PestanaDePreguntaDePrim(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, FramePrincipal frame, PanelCentral panelCentral) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame, panelCentral);
		
//		deshabilitarGrafoDirigido(false);
		setVisibleTipoPregunta(true);
		addTipoPregunta(Textos_Preguntas.tipoPregunta_ArcosDelArbolDeExpansion());
		addTipoPregunta(Textos_Preguntas.tipoPregunta_OrdenDeSeleccion());
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		switch(getTipoDePregunta()){
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
