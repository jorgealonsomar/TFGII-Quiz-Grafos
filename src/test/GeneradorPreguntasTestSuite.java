package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de tests.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({
	PreguntaInserccionDirectaTest.class,
	PreguntaMonticuloMaximoTest.class,
	PreguntaArbolBinarioBusquedaTest.class,
	PreguntaPilaTest.class,
	PreguntaTablaHashTest.class,
	PreguntaSeleccionDirectaTest.class
})

public class GeneradorPreguntasTestSuite {
	
}

