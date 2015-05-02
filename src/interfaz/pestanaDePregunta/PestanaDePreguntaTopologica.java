package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaTopologica;
import util.Texto;

@SuppressWarnings("serial")
public class PestanaDePreguntaTopologica extends PestanaDePregunta {

	public PestanaDePreguntaTopologica(JTabbedPane panelTabulado, Texto nombreDeLaPestana,
			int teclaMnemotecnica, AreaPreguntas areaPreguntas, JTextField ventanaTextoDirectorio) {
		super(panelTabulado, nombreDeLaPestana, teclaMnemotecnica, areaPreguntas, ventanaTextoDirectorio);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected Pregunta generarPregunta(){
		return new PreguntaTopologica(getNumNodos(), getPorcentajeArcos(), isDirigido());
	}


	@Override
	protected String getNombreArchivo() {
		return Texto.nombreArchivoPregTopologica().getString(idioma);
	}

}
