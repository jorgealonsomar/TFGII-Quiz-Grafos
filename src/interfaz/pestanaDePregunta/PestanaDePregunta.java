package interfaz.pestanaDePregunta;

import interfaz.FramePrincipal;
import interfaz.JComboBoxTextos;
import interfaz.PanelCentral;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.pregunta.Pregunta;
import sistema.GestorIO;
import texto.Idioma;
import texto.Texto;
import texto.Textos_Interfaz;

/**
 * Pestaña del panel tabulado de preguntas. Corresponde a una de los posibles tipos de pregunta a generar.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public abstract class PestanaDePregunta extends JPanel {
	
	/**
	 * Nombre que recibe la pestaña.
	 */
	private Texto nombreDeLaPestana;
	
	/**
	 * Idioma actual de la aplicación.
	 */
	protected Idioma idioma = Idioma.ESP;
	
	/**
	 * Frame de la aplicación.
	 */
	private FramePrincipal frame;
	
	/**
	 * Panel que contiene las opciones generales.
	 */
	protected PanelCentral panelCentral;
	
	
	/**
	 * Etiqueta correspondiente al selector de la clase de pregunta.
	 */
	private JLabel txtClasePregunta = new JLabel(Textos_Interfaz.textoClaseDePregunta().esp());
	
	/**
	 * Selector de la clase de pregunta, dentro de las opciones existentes para las de ese tipo.
	 */
	private JComboBoxTextos comboBoxClasePregunta = new JComboBoxTextos();
	
	
	/**
	 * Botón de generación de preguntas.
	 */
	private JButton botonGenerarPregunta = new JButton(Textos_Interfaz.botonGenerarPregunta().esp());
	
	/**
	 * Botón de importación de semillas.
	 */
	private JButton botonImportarSemilla = new JButton(Textos_Interfaz.botonImportarSemilla().esp());
	
	
	/**
	 * Constructor de la clase.
	 * @param panelTabulado
	 *            Panel tabulado que contiene esta pestaña.
	 * @param nombreDeLaPestana
	 *            Nombre que recibe esta pestaña.
	 * @param teclaMnemotecnica
	 *            Tecla mnemotécnica asociada a esta pestaña.
	 * @param frame
	 *            Frame de la aplicación.
	 * @param panelCentral
	 *            Panel que contiene las opciones generales.
	 */
	public PestanaDePregunta(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			FramePrincipal frame, PanelCentral panelCentral) {
		this.nombreDeLaPestana = nombreDeLaPestana;
		this.frame = frame;
		this.panelCentral = panelCentral;
		
		panelTabulado.addTab(getNombreDeLaPestana(Idioma.ESP), null, this, getNombreDeLaPestana(Idioma.ESP));
		panelTabulado.setMnemonicAt(panelTabulado.getTabCount()-1, teclaMnemotecnica);
		
		anadirSelectores();
		
		anadirBotones();
		
		setLayout(new GridLayout(0, 2));
	}
	
	
	/**
	 * Devuelve el nombre de la pestaña.
	 * 
	 * @param idioma
	 *            Idioma actual de la aplicación.
	 * @return Nombre que recibe la pestaña.
	 */
	public String getNombreDeLaPestana(Idioma idioma){
		return nombreDeLaPestana.getString(idioma);
	}
	
	
	/**
	 * Devuelve la clase de pregunta seleccionada.
	 * @return Clase de la pregunta.
	 */
	public Integer getClaseDePregunta(){
		return comboBoxClasePregunta.getSelectedIndex();
	}
	
	
	/**
	 * Hace visible o invisible el selector de la clase de pregunta.
	 * @param flag Flag que inidica si debe ser visible o invisible.
	 */
	public void setVisibleClasePregunta(boolean flag){
		txtClasePregunta.setVisible(flag);
		comboBoxClasePregunta.setVisible(flag);
	}
	
	
	/**
	 * Añade una clase de pregunta al selector de clase de pregunta.
	 * @param clasePregunta Nombre de la nueva clase de pregunta a añadir.
	 */
	public void addClasePregunta(Texto clasePregunta){
		comboBoxClasePregunta.addTexto(clasePregunta);
	}
	
	
	/**
	 * Reescribe los textos tras cambiar la configuración del idioma.
	 * @param nuevoIdioma
	 *            Nuevo idioma establecido.
	 */
	public void reaccionarACambioDeIdioma(Idioma nuevoIdioma){
		idioma = nuevoIdioma;
		
		botonGenerarPregunta.setText(Textos_Interfaz.botonGenerarPregunta().getString(nuevoIdioma));
		botonImportarSemilla.setText(Textos_Interfaz.botonImportarSemilla().getString(nuevoIdioma));
		
		txtClasePregunta.setText(Textos_Interfaz.textoClaseDePregunta().getString(nuevoIdioma));
		comboBoxClasePregunta.actualizar(nuevoIdioma);
		
		botonGenerarPregunta.setToolTipText(Textos_Interfaz.tipTextBotonGenerarPregunta().getString(nuevoIdioma));
		botonImportarSemilla.setToolTipText(Textos_Interfaz.tipTextBotonImportarSemilla().getString(nuevoIdioma));
	}

	
	/**
	 * Añade a este panel sus selectores.
	 */
	private void anadirSelectores(){
		add(txtClasePregunta, BorderLayout.CENTER);
		add(comboBoxClasePregunta);
		setVisibleClasePregunta(false);
	}
	
	
	/**
	 * Añade a este panel los botones de Generar pregunta y de Importar semilla.
	 */
	private void anadirBotones() {
		botonGenerarPregunta.addActionListener(new GenerarPreguntaListener());
		add(botonGenerarPregunta, BorderLayout.CENTER);
		
		botonImportarSemilla.addActionListener(new ImportarSemillaListener());
		add(botonImportarSemilla, BorderLayout.CENTER);
	}
	
	
	/**
	 * Genera una nueva pregunta. Se llama al pulsar el botón de Generar Pregunta.
	 * @return Nueva pregunta generada.
	 */
	protected abstract Pregunta generarPregunta();
	
	
	
	/**
	 * Listener del botón Generar pregunta.
	 * @author Jorge Alonso Márquez
	 */
	private class GenerarPreguntaListener implements ActionListener {
		
		/**
		 * Genera una nueva pregunta en función de los parámetros seleccionados en esta pestaña y en
		 * el panel central.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String textoPreguntaXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			textoPreguntaXml += "\n<quiz>";
			String textoPreguntaPorPantalla = "";
			Pregunta pregunta = null;
			
			//Por numPreguntas veces:
			for(int i = 0; i < panelCentral.getNumPreguntas(); i++){
				pregunta = generarPregunta();
				
				textoPreguntaPorPantalla += pregunta.getTextoPreguntaParaMostrarPorPantalla(idioma);
				textoPreguntaXml += pregunta.getTextoPreguntaXml(idioma);
			}
			
			textoPreguntaXml += "\n</quiz>";
			String nombreArchivo = pregunta.getNombreDeArchivo().getString(idioma)
					+ GestorIO.construirCadenaFecha() + ".xml";
			
			BufferedImage imagenVisual = null;
			if(panelCentral.getVisualizacionGrafo().isGrafoVisual()){
				imagenVisual = pregunta.getGrafo().toGrafoVisual();
			}
			frame.imprimePregunta(textoPreguntaPorPantalla, textoPreguntaXml, nombreArchivo, imagenVisual);
		}
		
	}
	
	
	
	/**
	 * Listener del botón Importar semilla.
	 * @author Jorge Alonso Márquez
	 */
	private class ImportarSemillaListener implements ActionListener {
		
		/**
		 * Abre la ventana de importar semilla.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.importarSemilla();
		}
	}
	
	
}
