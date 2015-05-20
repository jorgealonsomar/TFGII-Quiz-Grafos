package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import modelo.Semilla;
import modelo.SemillaException;
import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import modelo.pregunta.PreguntaDeDijkstra;
import modelo.pregunta.PreguntaDeKruskal;
import modelo.pregunta.PreguntaDePrim;
import modelo.pregunta.PreguntaDeProfundidad;
import modelo.pregunta.PreguntaTopologica;
import util.Idioma;
import util.Texto;

@SuppressWarnings("serial")
public class BarraMenu extends JMenuBar{
	
	private FramePrincipal frame;
	
	private final JMenu menuArchivo;
	private JMenuItem opcionImportarSemilla;
	private JMenuItem opcionSalir;
	
	private final JMenu menuAyuda;
	
	/** Idioma en el que se está ejecutando la aplicación */
	private Idioma idioma;
	
	
	/** Constructor de la clase */
	public BarraMenu(FramePrincipal frame, Idioma idioma){
		this.frame = frame;
		
		//Menú > Archivo
		menuArchivo = new JMenu();
		add(menuArchivo);

		opcionImportarSemilla = new JMenuItem();
		opcionImportarSemilla.addActionListener(new ImportarSemillaListener());
		menuArchivo.add(opcionImportarSemilla);
		
		opcionSalir = new JMenuItem();
		opcionSalir.addActionListener(new SalirListener());
		menuArchivo.add(opcionSalir);
		
		//Menú > Ayuda
		menuAyuda = new JMenu();
		add(menuAyuda);
		
		reescribirTextos(idioma);
	}
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void reescribirTextos(Idioma nuevoIdioma){
		idioma = nuevoIdioma;
		
		menuArchivo.setText(Texto.menuArchivo().getString(nuevoIdioma));
		opcionImportarSemilla.setText(Texto.menuArchivo_ImportarSemilla().getString(nuevoIdioma));
		opcionSalir.setText(Texto.menuArchivo_Salir().getString(nuevoIdioma));
		
		menuAyuda.setText(Texto.menuAyuda().getString(nuevoIdioma));
	}
	
	
	
	private class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	
	
	private class ImportarSemillaListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String codigoSemilla = JOptionPane.showInputDialog(frame, Texto.introduzcaSemilla()
					.getString(idioma), Texto.menuArchivo_ImportarSemilla().getString(idioma),
					JOptionPane.PLAIN_MESSAGE);
			
			try {
				
				//Recuperar la semilla a partir de su código
				Semilla semilla = new Semilla(codigoSemilla);
				
				Pregunta pregunta = null;
				if (semilla.getTipoPregunta() == Semilla.recorridoEnProfunidad){
					pregunta = new PreguntaDeProfundidad(semilla);
				} else if (semilla.getTipoPregunta() == Semilla.recorridoEnAnchura){
					pregunta = new PreguntaDeAnchura(semilla);
				} else if (semilla.getTipoPregunta() == Semilla.clasificacionTopologica){
					pregunta = new PreguntaTopologica(semilla);
				} else if (semilla.getTipoPregunta() == Semilla.algoritmoDeDijkstra){
					pregunta = new PreguntaDeDijkstra(semilla);
				} else if (semilla.getTipoPregunta() == Semilla.algoritmoDeKruskal){
					pregunta = new PreguntaDeKruskal(semilla);
				} else if (semilla.getTipoPregunta() == Semilla.algoritmoDePrim){
					pregunta = new PreguntaDePrim(semilla);
				}
				
				frame.imprimePregunta(pregunta);
				
			} catch(SemillaException excepcion){
				JOptionPane.showMessageDialog(null, Texto.errorSemillaIncorrecta().getString(idioma),
						"Error", JOptionPane.WARNING_MESSAGE);
			}

		}
	}
	
	
	
}
