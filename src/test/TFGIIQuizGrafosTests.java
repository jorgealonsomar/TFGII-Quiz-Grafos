package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite que contiene todos los tests relativos al proyecto.
 * @author Jorge Alonso Márquez
 */
@RunWith(Suite.class)
@SuiteClasses({ PreguntaTest.class, SemillaTest.class, GrafoTest.class })
public class TFGIIQuizGrafosTests { }
