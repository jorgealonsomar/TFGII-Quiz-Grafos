package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import modelo.Consigna;
import modelo.ConsignaException;
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
	private JMenuItem opcionImportarConsigna;
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

		opcionImportarConsigna = new JMenuItem();
		opcionImportarConsigna.addActionListener(new ImportarConsignaListener());
		menuArchivo.add(opcionImportarConsigna);
		
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
		opcionImportarConsigna.setText(Texto.menuArchivo_ImportarConsigna().getString(nuevoIdioma));
		opcionSalir.setText(Texto.menuArchivo_Salir().getString(nuevoIdioma));
		
		menuAyuda.setText(Texto.menuAyuda().getString(nuevoIdioma));
	}
	
	
	
	private class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	
	
	private class ImportarConsignaListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String codigoConsigna = JOptionPane.showInputDialog(frame, Texto.introduzcaConsigna()
					.getString(idioma), Texto.menuArchivo_ImportarConsigna().getString(idioma),
					JOptionPane.PLAIN_MESSAGE);
			
			try {
				
				//Recuperar la consigna a partir de su código
				Consigna consigna = new Consigna(codigoConsigna);
				
				Pregunta pregunta = null;
				if (consigna.getTipoPregunta() == Consigna.recorridoEnProfunidad){
					pregunta = new PreguntaDeProfundidad(consigna);
				} else if (consigna.getTipoPregunta() == Consigna.recorridoEnAnchura){
					pregunta = new PreguntaDeAnchura(consigna);
				} else if (consigna.getTipoPregunta() == Consigna.clasificacionTopologica){
					pregunta = new PreguntaTopologica(consigna);
				} else if (consigna.getTipoPregunta() == Consigna.algoritmoDeDijkstra){
					pregunta = new PreguntaDeDijkstra(consigna);
				} else if (consigna.getTipoPregunta() == Consigna.algoritmoDeKruskal){
					pregunta = new PreguntaDeKruskal(consigna);
				} else if (consigna.getTipoPregunta() == Consigna.algoritmoDePrim){
					pregunta = new PreguntaDePrim(consigna);
				}
				
				frame.imprimePregunta(pregunta);
				
			} catch(ConsignaException excepcion){
				JOptionPane.showMessageDialog(null, Texto.errorConsignaIncorrecta().getString(idioma),
						"Error", JOptionPane.WARNING_MESSAGE);
			}

		}
	}
	
	
	
}
