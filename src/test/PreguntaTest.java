package test;

import static org.junit.Assert.assertEquals;
import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import modelo.pregunta.VisualizacionGrafo;

import org.junit.Test;

import texto.Textos_Preguntas;
import util.Idioma;

public class PreguntaTest {

	@Test
	public void preguntaDeAnchura() {
		Integer nNodos = 5;
		Double porcentajeDeArcos = 1.00;
		boolean esDirigido = true;

		Pregunta preguntaDeAnchura = new PreguntaDeAnchura(nNodos, porcentajeDeArcos, esDirigido,
				VisualizacionGrafo.MATRIZ_DE_ADYACENCIA);

//		assertEquals(preguntaDeAnchura.getGrafo(), new GrafoDirigido(nNodos, porcentajeDeArcos, false));

		assertEquals(preguntaDeAnchura.getTitulo(Idioma.ESP),
				Textos_Preguntas.tituloPregAnchura().getString(Idioma.ESP));
		assertEquals(preguntaDeAnchura.getTitulo(Idioma.ENG),
				Textos_Preguntas.tituloPregAnchura().getString(Idioma.ENG));

		assertEquals(
				preguntaDeAnchura.getEnunciado(Idioma.ESP),
				Textos_Preguntas.enunciadoPregAnchura().getString(Idioma.ESP));
	}

}
