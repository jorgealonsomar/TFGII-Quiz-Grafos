package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import texto.Textos_BarraMenu;
import util.Idioma;

@SuppressWarnings("serial")
public class BarraMenu extends JMenuBar {

	private FramePrincipal frame;

	private final JMenu menuArchivo;
	private JMenuItem opcionImportarSemilla;
	private JMenuItem opcionSalir;

	private final JMenu menuAyuda;
	private JMenuItem opcionAcercaDe;

	
	/** Constructor de la clase */
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
	

	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void reescribirTextos(Idioma nuevoIdioma) {

		menuArchivo.setText(Textos_BarraMenu.menuArchivo().getString(nuevoIdioma));
		opcionImportarSemilla.setText(Textos_BarraMenu.menuArchivo_ImportarSemilla()
				.getString(nuevoIdioma));
		opcionSalir.setText(Textos_BarraMenu.menuArchivo_Salir().getString(nuevoIdioma));

		menuAyuda.setText(Textos_BarraMenu.menuAyuda().getString(nuevoIdioma));
		opcionAcercaDe.setText(Textos_BarraMenu.menuAyuda_AcercaDe().getString(nuevoIdioma));
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
			frame.importarSemilla();
		}
	}
	
	
	
	private class AcercaDeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO
		}
	}
	
}
