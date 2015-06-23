package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

public class PreguntaDeProfundidad extends Pregunta {

	private ArrayList<Integer> recorridoEnProfundidad;

	/** Constructor de la clase */
	public PreguntaDeProfundidad(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, false, visualizacionGrafo, 1);
	}
	

	public PreguntaDeProfundidad(Semilla semilla) {
		super(semilla);
	}
	

	@Override
	protected void aplicarAlgoritmo() {
		recorridoEnProfundidad = getGrafo().recorrerEnProfundidad(0);
	}
	

	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregProfundidad();
	}
	

	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregProfundidad();
	}
	

	@Override
	protected void construirParteAResponder() {
		resultadoDeOrdenarElGrafo(recorridoEnProfundidad);
	}
	

	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta(recorridoEnProfundidad);
	}
	

	@Override
	protected void generarSemilla(boolean grafoDirigido) {
		super.generarSemillaEnFuncionDelTipoDePregunta(Semilla.recorridoEnProfunidad, grafoDirigido);
	}
	

	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregProfundidad();
	}

}
