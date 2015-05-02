package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import util.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaDeAnchura extends PestanaDePregunta {

	public PestanaDePreguntaDeAnchura(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, JTextField ventanaTextoDirectorio) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, ventanaTextoDirectorio);
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaDeAnchura(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}


	@Override
	protected String getNombreArchivo() {
		return Texto.nombreArchivoPregAnchura().getString(idioma);
	}

}
