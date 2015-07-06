package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import texto.Idioma;
import texto.Textos_BarraMenu;

/**
 * Barra del menú superior
 * @author Jorge
 */
@SuppressWarnings("serial")
public class BarraMenu extends JMenuBar {
	
	/**
	 * Frame principal de la aplicación.
	 */
	private FramePrincipal frame;
	
	
	/**
	 * Menú "Archivo".
	 */
	private final JMenu menuArchivo;
	
	/**
	 * Opción "Importar Semilla", del menú Archivo.
	 */
	private JMenuItem opcionImportarSemilla;
	
	/**
	 * Opción "Salir", del menú Archivo.
	 */
	private JMenuItem opcionSalir;
	
	
	/**
	 * Menú "Ayuda".
	 */
	private final JMenu menuAyuda;
	
	/**
	 * Opción "Acerca de", del menú de Ayuda.
	 */
	private JMenuItem opcionAcercaDe;

	
	/**
	 * Constructor de la clase.
	 * Crea la barra y sus elementos.
	 * @param frame
	 *            Frame principal de la aplicación
	 * @param idioma
	 *            Idioma actual de la aplicación
	 */
	public BarraMenu(FramePrincipal frame, Idioma idioma) {
		this.frame = frame;

		// Menú > Archivo
		menuArchivo = new JMenu();
		add(menuArchivo);

		opcionImportarSemilla = new JMenuItem();
		opcionImportarSemilla.addActionListener(new ImportarSemillaListener());
		menuArchivo.add(opcionImportarSemilla);

		opcionSalir = new JMenuItem();
		opcionSalir.addActionListener(new SalirListener());
		menuArchivo.add(opcionSalir);

		// Menú > Ayuda
		menuAyuda = new JMenu();
		add(menuAyuda);
		
		opcionAcercaDe = new JMenuItem();
		opcionAcercaDe.addActionListener(new AcercaDeListener());
		menuAyuda.add(opcionAcercaDe);
		

		reescribirTextos(idioma);
	}
	

	/**
	 * Reescribe los textos tras cambiar la configuración del idioma.
	 * @param nuevoIdioma
	 *            Nuevo idioma establecido.
	 */
	public void reescribirTextos(Idioma nuevoIdioma) {

		menuArchivo.setText(Textos_BarraMenu.menuArchivo().getString(nuevoIdioma));
		opcionImportarSemilla.setText(Textos_BarraMenu.menuArchivo_ImportarSemilla()
				.getString(nuevoIdioma));
		opcionSalir.setText(Textos_BarraMenu.menuArchivo_Salir().getString(nuevoIdioma));

		menuAyuda.setText(Textos_BarraMenu.menuAyuda().getString(nuevoIdioma));
		opcionAcercaDe.setText(Textos_BarraMenu.menuAyuda_AcercaDe().getString(nuevoIdioma));
	}
	

	
	/**
	 * Listener de la opción Salir.
	 * @author Jorge Alonso Márquez
	 */
	private class SalirListener implements ActionListener {
		
		/**
		 * Cierra la aplicación.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	
	/**
	 * Listener de la opción Importar semilla.
	 * @author Jorge Alonso Márquez
	 */
	private class ImportarSemillaListener implements ActionListener {
		
		/**
		 * Abre la ventana de importar semilla.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.importarSemilla();
		}
	}
	
	
	
	/**
	 * Listener de la opción Acerca de.
	 * @author Jorge Alonso Márquez
	 */
	private class AcercaDeListener implements ActionListener {
		
		/**
		 * Muestra información sobre la aplicación.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "Universidad de Burgos. Grado en Ingeniería Informática."
					+ "\nAutor: Jorge Alonso Márquez."
					+ "\nTutores: Juan José Rodríguez Díez y Carlos López Nozal."
					+ "\n\tEntregado a julio de 2015.",
					"Acerca de", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
