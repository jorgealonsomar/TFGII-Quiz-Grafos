package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import texto.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeAnchura extends PestanaDePregunta {

	public PestanaDePreguntaDeAnchura(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, frame);
	}

	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaDeAnchura(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}

}
