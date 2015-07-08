package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Semilla;
import modelo.SemillaException;
import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;
import modelo.pregunta.PreguntaDeDijkstra_DistanciasMasCortas;
import modelo.pregunta.PreguntaDeDijkstra_OrdenDeSeleccion;
import modelo.pregunta.PreguntaDeDijkstra_RutaMasCorta;
import modelo.pregunta.PreguntaDeKruskal_ArcosDelArbolDeExpansion;
import modelo.pregunta.PreguntaDeKruskal_OrdenDeSeleccion;
import modelo.pregunta.PreguntaDePrim_ArcosDelArbolDeExpansion;
import modelo.pregunta.PreguntaDePrim_OrdenDeSeleccion;
import modelo.pregunta.PreguntaDeProfundidad;
import modelo.pregunta.PreguntaTopologica;
import sistema.GestorIO;
import sistema.Ruta;
import texto.Idioma;
import texto.Textos_BarraMenu;
import texto.Textos_Interfaz;

/**
 * Frame con los elementos de la interfaz de la aplicación.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {

	/** 
	 * Parámetros que está empleando el programa.
	 */
	private Idioma idioma;

	/**
	 * Barra del menú superior.
	 */
	private BarraMenu barraMenu;
	
	/**
	 * Panel central, donde se realiza la selección de parámetros.
	 */
	private PanelCentral panelCentral;

	/**
	 * Botón de selección de idioma.
	 */
	private JLabel imgIdioma;
	
	/**
	 * Botón de selección del directorio donde se guardarán las preguntas que se generen.
	 */
	private JButton botonElegirDirectorio;

	/**
	 * Ventana de texto con nombre del directorio donde se guardarán las preguntas que se generen.
	 */
	private JTextField ventanaTextoDirectorio;

	/**
	 * Area donde se irán mostrando las preguntas generadas.
	 */
	private AreaPreguntas areaPreguntas;

	/**
	 * Anchura del frame.
	 */
	private final int ANCHO = 1200;

	/**
	 * Altura del frame.
	 */
	private final int ALTO = 800;

	
	/**
	 * Constructor de la clase.
	 * Construye el frame y sus componentes.
	 */
	public FramePrincipal() {
		this.idioma = Idioma.ESP;
		
		setIconImage(new ImageIcon(getClass().getResource(Ruta.IMAGENES + "UBU.png")).getImage());
		
		setBounds(0, 0, ANCHO, ALTO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

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
	 * @param textoPreguntaPorPantalla
	 *            Texto a imprimir por pantalla.
	 * @param textoPreguntaXml
	 *            Texto a imprimir a un archivo xml.
	 * @param nombreArchivo
	 *            Nombre del archivo xml.
	 * @param imagenVisual
	 *            Imagen del grafo correspondiente a la pregunta.
	 */
	public void imprimePregunta(String textoPreguntaPorPantalla, String textoPreguntaXml,
			String nombreArchivo, BufferedImage imagenVisual) {
		
		String separador = System.getProperty("file.separator");
		String rutaDirectorio = ventanaTextoDirectorio.getText();
		File directorio = new File(rutaDirectorio);

		// Si el directorio seleccionado existe
		if (directorio.exists()) {
			String rutaArchivo = rutaDirectorio + separador + nombreArchivo;

			// Imprimir la pregunta en el archivo especificado
			GestorIO.escribirEnArchivo(new File(rutaArchivo), textoPreguntaXml);

			// Imprimir la pregunta por el área de preguntas
			areaPreguntas.addTexto(textoPreguntaPorPantalla);
			if(imagenVisual != null) areaPreguntas.addImagen(imagenVisual);

		} else if (rutaDirectorio.equals("")) {
			// Imprimir la pregunta por el área de preguntas
			areaPreguntas.addTexto(textoPreguntaPorPantalla);
			if(imagenVisual != null) areaPreguntas.addImagen(imagenVisual);
		} else {
			JOptionPane.showMessageDialog(null, Textos_Interfaz.errorDirectorioNoExiste()
					.getString(idioma), "Error", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	
	/**
	 * Recupera una pregunta a partir de su semilla, y, si es válida, la imprime.
	 */
	public void importarSemilla() {
		String codigoConsigna = JOptionPane.showInputDialog(this,
				Textos_BarraMenu.introduzcaSemilla().getString(idioma),
				Textos_BarraMenu.menuArchivo_ImportarSemilla().getString(idioma),
				JOptionPane.PLAIN_MESSAGE);

		try {
			// Recuperar la consigna a partir de su código
			Semilla consigna = new Semilla(codigoConsigna);

			Pregunta pregunta = null;
			if (consigna.getNumPregunta() == Semilla.RECORRIDO_EN_PROFUNDIDAD) {
				pregunta = new PreguntaDeProfundidad(consigna);
			} else if (consigna.getNumPregunta() == Semilla.RECORRIDO_EN_ANCHURA) {
				pregunta = new PreguntaDeAnchura(consigna);
			} else if (consigna.getNumPregunta() == Semilla.CLASIFICACION_TOPOLOGICA) {
				pregunta = new PreguntaTopologica(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_DIJKSTRA_DISTANCIAS_MAS_CORTAS) {
				pregunta = new PreguntaDeDijkstra_DistanciasMasCortas(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_DIJKSTRA_RUTA_MAS_CORTA) {
				pregunta = new PreguntaDeDijkstra_RutaMasCorta(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_DIJKSTRA_ORDEN_DE_SELECCION) {
				pregunta = new PreguntaDeDijkstra_OrdenDeSeleccion(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_PRIM_ARCOS_DEL_ARBOL_DE_EXPANSION) {
				pregunta = new PreguntaDePrim_ArcosDelArbolDeExpansion(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_PRIM_ORDEN_DE_SELECCION) {
				pregunta = new PreguntaDePrim_OrdenDeSeleccion(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_KRUSKAL_ARCOS_DEL_ARBOL_DE_EXPANSION) {
				pregunta = new PreguntaDeKruskal_ArcosDelArbolDeExpansion(consigna);
			} else if (consigna.getNumPregunta() == Semilla.ALGORITMO_DE_KRUSKAL_ORDEN_DE_SELECCION) {
				pregunta = new PreguntaDeKruskal_OrdenDeSeleccion(consigna);
			}
			
			//Generar textos correspondientes a la pregunta
			String textoPreguntaXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			textoPreguntaXml += "\n<quiz>";
			String textoPreguntaPorPantalla = "";
			
			textoPreguntaPorPantalla += pregunta.getTextoPreguntaParaMostrarPorPantalla(idioma);
			textoPreguntaXml += pregunta.getTextoPreguntaXml(idioma);
			
			textoPreguntaXml += "\n</quiz>";
			String nombreArchivo = pregunta.getNombreDeArchivo().getString(idioma)
					+ GestorIO.construirCadenaFecha() + ".xml";
			
			BufferedImage imagenVisual = null;
			if(panelCentral.getVisualizacionGrafo().isGrafoVisual()){
				imagenVisual = pregunta.getGrafo().toGrafoVisual();
			}
			
			//Imprimir la pregunta
			imprimePregunta(textoPreguntaPorPantalla, textoPreguntaXml, nombreArchivo, imagenVisual);
			
		} catch (SemillaException excepcion) {
			JOptionPane.showMessageDialog(null,
					Textos_Interfaz.errorSemillaIncorrecta().getString(idioma), "Error",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	

	/**
	 * Construye la barra del menú superior.
	 */
	private void construirBarraMenu() {
		barraMenu = new BarraMenu(this, idioma);
		getContentPane().add(barraMenu, BorderLayout.PAGE_START);
	}
	
	
	/**
	 * Construye el panel inferior correspondiente a la selección del directorio donde se guardarán
	 * las preguntas que se creen.
	 */
	private void construirSelectorDeDirectorio() {
		JPanel panelSelectorDeDirectorio = new JPanel();
		panelSelectorDeDirectorio.setBackground(Color.GRAY);
		panelSelectorDeDirectorio.setVisible(true);
		panelSelectorDeDirectorio.setLayout(new BorderLayout());
		add(panelSelectorDeDirectorio);
		getContentPane().add(panelSelectorDeDirectorio, BorderLayout.PAGE_END);
		
		botonElegirDirectorio = new JButton("");
		botonElegirDirectorio.addActionListener(new ElegirDirectorioListener());
		panelSelectorDeDirectorio.add(botonElegirDirectorio, BorderLayout.LINE_START);

		ventanaTextoDirectorio = new JTextField("");
		panelSelectorDeDirectorio.add(ventanaTextoDirectorio, BorderLayout.CENTER);
	}
	

	/**
	 * Construye el panel central.
	 */
	private void construirPanelCentral() {
		panelCentral = new PanelCentral(this);
		getContentPane().add(panelCentral, BorderLayout.CENTER);
	}
	

	/**
	 * Construye el área donde se van mostrando las preguntas generadas
	 */
	private void construirAreaPreguntas() {
		areaPreguntas = new AreaPreguntas();
		add(areaPreguntas, BorderLayout.LINE_END);
	}
	

	/**
	 * Construye la barra del menú superior.
	 */
	private void construirBotonIdioma() {
		imgIdioma = new JLabel();
		imgIdioma.setIcon(new ImageIcon(getClass().getResource(Ruta.IMAGENES + "idiomaEsp.png")));
		getContentPane().add(imgIdioma, BorderLayout.LINE_START);

		imgIdioma.addMouseListener(new CambiarIdiomaListener());
	}
	
	
	/**
	 * Reescribe los textos tras cambiar la configuración del idioma.
	 */
	private void presentarTrasCambioDeIdioma() {
		Idioma nuevoIdioma = idioma;
		
		setTitle(Textos_Interfaz.tituloVentana().getString(nuevoIdioma));
		
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

		// Area de preguntas
		areaPreguntas.presentarTrasCambioDeIdioma(nuevoIdioma);

		repaint();
	}
	
	
	/**
	 * Cambia el idioma en el que se muestran los textos del programa.
	 */
	public void switchIdioma() {
		if(idioma == Idioma.ESP){
			idioma = Idioma.ENG;
		} else {
			idioma =Idioma.ESP;
		}
	}
	
	
	/**
	 * Establece la ruta del directorio donde se guardarán las preguntas creadas.
	 */
	public void setTextoDirectorio(String rutaDirectorio){
		ventanaTextoDirectorio.setText(rutaDirectorio);
	}
	
	
	/**
	 * Listener del botón de idioma.
	 * @author Jorge Alonso Márquez
	 */
	private class CambiarIdiomaListener extends MouseAdapter {

		/**
		 * Cambia al siguiente idioma y repinta la ventana.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			switchIdioma();
			presentarTrasCambioDeIdioma();
		}

	}
	
	
	/**
	 * Listener del botón de Elegir directorio.
	 * @author Jorge Alonso Márquez
	 */
	private class ElegirDirectorioListener implements ActionListener {

		/**
		 * Abre un selector de directorio. Tras elegirlo, esa ruta se guarda en la ventana de
		 * texto de selección de directorio.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser selector = new JFileChooser();
			selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int eleccion = selector.showOpenDialog(null);
			if (eleccion == JFileChooser.APPROVE_OPTION) {
				File archivo = selector.getSelectedFile();
				setTextoDirectorio(archivo.toString());
			}
		}

	}

}
