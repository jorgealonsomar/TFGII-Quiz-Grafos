package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.Parametros;
import sistema.Ruta;
import texto.Textos_Interfaz;
import util.GestorIO;
import util.Idioma;

@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {

	/** Panel correspondiente a este frame */
	private JPanel panelDeLaVentana;

	/** Parámetros que está empleando el programa */
	private Parametros parametros;

	/** Barra del menú superior */
	private BarraMenu barraMenu;
	
	/** Panel central, donde se realiza la selección de parámetros */
	private PanelCentral panelCentral;
//	/** Panel que emplea pestañas para separar los distintos tipos de preguntas */
//	private JTabbedPane panelTabulado;

	/** Botón de selección de idioma */
	private JLabel imgIdioma;
	
	/**
	 * Botón de selección del directorio donde se guardarán las preguntas que se
	 * generen
	 */
	private JButton botonElegirDirectorio;

	/**
	 * Ventana de texto con nombre del directorio donde se guardarán las
	 * preguntas que se generen
	 */
	private JTextField ventanaTextoDirectorio;

	/** Área donde se irán mostrando las preguntas generadas */
	private AreaPreguntas areaPreguntas;

	/** Anchura del frame */
	private final int ANCHO = 1200;

	/** Altura del frame */
	private final int ALTO = 800;

	/** Constructor de la clase */
	public FramePrincipal(Parametros parametros) {
		this.parametros = parametros;
		
		setTitle("TFGII Generador de preguntas de Algoritmia");
		setBounds(0, 0, ANCHO, ALTO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		construirPanelDeLaVentana();
		construirBarraMenu();
		construirAreaPreguntas();
		construirSelectorDeDirectorio();
		construirPanelCentral();
		construirBotonIdioma();

		presentarTrasCambioDeIdioma();
	}

	/**
	 * Imprime una pregunta por el área de Preguntas. La imprime también a
	 * archivo.
	 */
	public void imprimePregunta(String textoPreguntaPorPantalla, String textoPreguntaXml,
			String nombreArchivo) {
		String separador = System.getProperty("file.separator");
		String rutaDirectorio = ventanaTextoDirectorio.getText();
		File directorio = new File(rutaDirectorio);

		// Si el directorio seleccionado existe
		if (directorio.exists()) {
			String rutaArchivo = rutaDirectorio + separador + nombreArchivo
					+ GestorIO.construirCadenaFecha() + ".xml";

			// Imprimir la pregunta en el archivo especificado
			GestorIO.escribirEnArchivo(new File(rutaArchivo), textoPreguntaXml);

			// Imprimir la pregunta por el área de preguntas
			areaPreguntas.addTexto(textoPreguntaPorPantalla);

		} else if (rutaDirectorio.equals("")) {
			// Imprimir la pregunta por el área de preguntas
			areaPreguntas.addTexto(textoPreguntaPorPantalla);
		} else {
			JOptionPane.showMessageDialog(null, Textos_Interfaz.errorDirectorioNoExiste()
					.getString(parametros.getIdioma()), "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	/** */
	public void importarSemilla() {
		System.err.println("[FramePrincipal] Importar semilla pendiente de implementar bien");
		
//		String codigoConsigna = JOptionPane.showInputDialog(this,
//				Textos_BarraMenu.introduzcaSemilla().getString(parametros.getIdioma()),
//				Textos_BarraMenu.menuArchivo_ImportarSemilla().getString(parametros.getIdioma()),
//				JOptionPane.PLAIN_MESSAGE);
//
//		try {
//			// Recuperar la consigna a partir de su código
//			Semilla consigna = new Semilla(codigoConsigna);
//
//			Pregunta pregunta = null;
//			if (consigna.getTipoPregunta() == Semilla.recorridoEnProfunidad) {
//				pregunta = new PreguntaDeProfundidad(consigna);
//			} else if (consigna.getTipoPregunta() == Semilla.recorridoEnAnchura) {
//				pregunta = new PreguntaDeAnchura(consigna);
//			} else if (consigna.getTipoPregunta() == Semilla.clasificacionTopologica) {
//				pregunta = new PreguntaTopologica(consigna);
//			} else if (consigna.getTipoPregunta() == Semilla.algoritmoDeDijkstra) {
//				pregunta = new PreguntaDeDijkstra(consigna);
//			} else if (consigna.getTipoPregunta() == Semilla.algoritmoDeKruskal) {
//				pregunta = new PreguntaDeKruskal(consigna);
//			} else if (consigna.getTipoPregunta() == Semilla.algoritmoDePrim) {
//				pregunta = new PreguntaDePrim(consigna);
//			}
//
//			imprimePregunta(pregunta);
//		} catch (ConsignaException excepcion) {
//			JOptionPane.showMessageDialog(null,
//					Textos_Interfaz.errorSemillaIncorrecta().getString(parametros.getIdioma()), "Error",
//					JOptionPane.WARNING_MESSAGE);
//		}
	}
	

	/** Construye el jPanel correspondiente a este frame */
	private void construirPanelDeLaVentana() {
		panelDeLaVentana = new JPanel();
//		panelDeLaVentana.setBounds(0, 0, ANCHO - 50, ALTO - 50);
//		panelDeLaVentana.setPreferredSize(new Dimension(ANCHO - 50, ALTO - 50));
		panelDeLaVentana.setBackground(Color.GRAY);
		panelDeLaVentana.setVisible(true);
		panelDeLaVentana.setLayout(new BorderLayout());
		setContentPane(panelDeLaVentana);
	}
	

	/** Construye la barra del menú superior */
	private void construirBarraMenu() {
		barraMenu = new BarraMenu(this, parametros.getIdioma());
		panelDeLaVentana.add(barraMenu, BorderLayout.PAGE_START);
	}
	
	
	/**
	 * Construye el panel inferior correspondiente a la selección del directorio
	 * donde se guardarán las preguntas que se creen
	 */
	private void construirSelectorDeDirectorio() {
		JPanel panelSelectorDeDirectorio = new JPanel();
//		panelSelectorDeDirectorio.setBounds(0, 0, ANCHO, ALTO);
		panelSelectorDeDirectorio.setBackground(Color.GRAY);
		panelSelectorDeDirectorio.setVisible(true);
		panelSelectorDeDirectorio.setLayout(new BorderLayout());
		add(panelSelectorDeDirectorio);
		panelDeLaVentana.add(panelSelectorDeDirectorio, BorderLayout.PAGE_END);
		
		botonElegirDirectorio = new JButton("");
		botonElegirDirectorio.addActionListener(new ElegirDirectorioListener());
		panelSelectorDeDirectorio.add(botonElegirDirectorio, BorderLayout.LINE_START);

		ventanaTextoDirectorio = new JTextField("");
		panelSelectorDeDirectorio.add(ventanaTextoDirectorio, BorderLayout.CENTER);
	}
	

	/** Construye el panel central */
	private void construirPanelCentral() {
		panelCentral = new PanelCentral(areaPreguntas, this);
		panelDeLaVentana.add(panelCentral, BorderLayout.CENTER);
	}
	

	/** Construye el área donde se irán mostrando las preguntas generadas */
	private void construirAreaPreguntas() {
		areaPreguntas = new AreaPreguntas();
		add(areaPreguntas, BorderLayout.LINE_END);
	}
	

	/** Construye la barra del menú superior */
	private void construirBotonIdioma() {
		imgIdioma = new JLabel();
		imgIdioma.setIcon(new ImageIcon(getClass().getResource(Ruta.IMAGENES + "idiomaEsp.png")));
		panelDeLaVentana.add(imgIdioma, BorderLayout.LINE_START);

		imgIdioma.addMouseListener(new CambiarIdiomaListener());
	}

	/** Reescribe los textos tras cambiar la configuración del idioma */
	private void presentarTrasCambioDeIdioma() {
		Idioma nuevoIdioma = parametros.getIdioma();

		// Barra Menú
		barraMenu.reescribirTextos(nuevoIdioma);

		// Imagen de elección de idioma
		if (nuevoIdioma == Idioma.ESP) {
			imgIdioma.setIcon(new ImageIcon(getClass().getResource(Ruta.IMAGENES + "idiomaEsp.png")));

		} else {
			imgIdioma.setIcon(new ImageIcon(getClass().getResource(Ruta.IMAGENES + "idiomaEng.png")));
		}
		imgIdioma.setToolTipText(Textos_Interfaz.botonSeleccionDeIdioma().getString(nuevoIdioma));

		// Panel central
		panelCentral.presentarTrasCambioDeIdioma(nuevoIdioma);

		// Panel de selección de directorio
		botonElegirDirectorio.setText(Textos_Interfaz.botonElegirDirectorio().getString(nuevoIdioma));
		botonElegirDirectorio.setToolTipText(Textos_Interfaz.tipTextElegirDirectorio().getString(nuevoIdioma));
		ventanaTextoDirectorio.setToolTipText(Textos_Interfaz.tipTextElegirDirectorio().getString(nuevoIdioma));

		// Área de preguntas
		areaPreguntas.presentarTrasCambioDeIdioma(nuevoIdioma);

		repaint();
	}
	
	

	private class CambiarIdiomaListener extends MouseAdapter {

		/** Cambia al siguiente idioma y repinta la ventana */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			parametros.switchIdioma();
			presentarTrasCambioDeIdioma();
		}

	}
	
	
	
	private class ElegirDirectorioListener implements ActionListener {

		/**
		 * Abre un selector de directorio. Tras elegirlo, esa ruta se guarda en
		 * la ventana de texto de selección de directorio
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser selector = new JFileChooser();
			selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int eleccion = selector.showOpenDialog(null);
			if (eleccion == JFileChooser.APPROVE_OPTION) {
				File archivo = selector.getSelectedFile();
				ventanaTextoDirectorio.setText(archivo.toString());
			}
		}

	}

}
