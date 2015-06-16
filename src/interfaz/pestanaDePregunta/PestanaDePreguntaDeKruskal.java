package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeKruskal;
import texto.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeKruskal extends PestanaDePregunta {

	public PestanaDePreguntaDeKruskal(JTabbedPane panelTabulado,
			Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica,
				areaPreguntas, frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Pregunta generarPregunta() {
		return new PreguntaDeKruskal(getNumNodos(), getPorcentajeArcos(),
				isDirigido());
	}

}
