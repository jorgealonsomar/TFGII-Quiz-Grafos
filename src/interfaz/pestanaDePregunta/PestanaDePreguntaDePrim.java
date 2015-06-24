package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

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
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
		
		setVisibleTipoPregunta(true);
		addTipoPregunta(Textos_Preguntas.tipoPreguntaPrim_ArcosDelArbolDeExpansion());
		addTipoPregunta(Textos_Preguntas.tipoPregunta_OrdenDeSeleccion());
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		switch(getTipoDePregunta()){
		case PreguntaDePrim.PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION:
			return new PreguntaDePrim_ArcosDelArbolDeExpansion(getNumNodos(), getPorcentajeArcos(),
					isDirigido(), getVisualizacionGrafo());
		case PreguntaDePrim.PREGUNTA_ORDEN_DE_SELECCION: default:
			return new PreguntaDePrim_OrdenDeSeleccion(getNumNodos(), getPorcentajeArcos(),
					isDirigido(), getVisualizacionGrafo());
		}
	}

}
