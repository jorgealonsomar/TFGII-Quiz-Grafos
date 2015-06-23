package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeDijkstra;
import modelo.pregunta.PreguntaDeDijkstra_DistanciasMasCortas;
import modelo.pregunta.PreguntaDeDijkstra_OrdenDeSeleccion;
import modelo.pregunta.PreguntaDeDijkstra_RutaMasCorta;
import texto.Texto;
import texto.Textos_Preguntas;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeDijkstra extends PestanaDePregunta {

	public PestanaDePreguntaDeDijkstra(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
		
		setVisibleTipoPregunta(true);
		addTipoPregunta(Textos_Preguntas.tipoPreguntaDijkstra_DistanciasMasCortas());
		addTipoPregunta(Textos_Preguntas.tipoPreguntaDijkstra_RutaMasCorta());
		addTipoPregunta(Textos_Preguntas.tipoPreguntaDijkstra_OrdenDeSeleccion());
	}

	@Override
	protected Pregunta generarPregunta() {
		switch(getTipoDePregunta()){
		case PreguntaDeDijkstra.PREGUNTA_DISTANCIAS_MAS_CORTAS:
			return new PreguntaDeDijkstra_DistanciasMasCortas(getNumNodos(), getPorcentajeArcos(), isDirigido(),
					getVisualizacionGrafo(), getTipoDePregunta());
		case PreguntaDeDijkstra.PREGUNTA_RUTA_MAS_CORTA:
			return new PreguntaDeDijkstra_RutaMasCorta(getNumNodos(), getPorcentajeArcos(), isDirigido(),
					getVisualizacionGrafo(), getTipoDePregunta());
		case PreguntaDeDijkstra.PREGUNTA_ORDEN_DE_SELECCION: default:
			return new PreguntaDeDijkstra_OrdenDeSeleccion(getNumNodos(), getPorcentajeArcos(), isDirigido(),
					getVisualizacionGrafo(), getTipoDePregunta());
		}
		
	}

}
