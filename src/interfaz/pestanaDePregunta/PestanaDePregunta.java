package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
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
import modelo.pregunta.VisualizacionGrafo;
import texto.Texto;
import texto.Textos_Interfaz;
import util.Idioma;

@SuppressWarnings("serial")
public abstract class PestanaDePregunta extends JPanel {
	
	private Texto nombreDeLaPestana;
	
	protected Idioma idioma = Idioma.ESP;
	
	/** Frame que contiene el gui del programa */
	private FramePrincipal frame;
	
	/** Panel con las opciones generales */
	protected PanelCentral panelCentral;
	
	private JLabel txtTipoPregunta = new JLabel(Textos_Interfaz.textoTipoDePregunta().esp());
	private JComboBoxTextos comboBoxTipoPregunta = new JComboBoxTextos();
	
	private JButton botonGenerarPregunta = new JButton(Textos_Interfaz.botonGenerarPregunta().esp());
	/** Botón de importar semillas */
	private JButton botonImportarSemilla = new JButton(Textos_Interfaz.botonImportarSemilla().esp());
	
	
	/** Constructor */
	public PestanaDePregunta(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame, PanelCentral panelCentral) {
		this.nombreDeLaPestana = nombreDeLaPestana;
		this.frame = frame;
		this.panelCentral = panelCentral;
		
		panelTabulado.addTab(getNombreDeLaPestana(Idioma.ESP), null, this, getNombreDeLaPestana(Idioma.ESP));
		panelTabulado.setMnemonicAt(panelTabulado.getTabCount()-1, teclaMnemotecnica);
		
		anadirSelectores();
		
		anadirBotones();
		
		setLayout(new GridLayout(0, 2));
	}
	
	
	public String getNombreDeLaPestana(Idioma idioma){
		return nombreDeLaPestana.getString(idioma);
	}
	
	
	public Integer getTipoDePregunta(){
		return comboBoxTipoPregunta.getSelectedIndex();
	}
	
	
	public void setVisibleTipoPregunta(boolean flag){
		txtTipoPregunta.setVisible(flag);
		comboBoxTipoPregunta.setVisible(flag);
	}
	
	
	public void addTipoPregunta(Texto tipoPregunta){
		comboBoxTipoPregunta.addTexto(tipoPregunta);
	}
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void reaccionarACambioDeIdioma(Idioma nuevoIdioma){
		idioma = nuevoIdioma;
		
		botonGenerarPregunta.setText(Textos_Interfaz.botonGenerarPregunta().getString(nuevoIdioma));
		botonImportarSemilla.setText(Textos_Interfaz.botonImportarSemilla().getString(nuevoIdioma));
		
		txtTipoPregunta.setText(Textos_Interfaz.textoTipoDePregunta().getString(nuevoIdioma));
		comboBoxTipoPregunta.actualizar(nuevoIdioma);
		
		botonGenerarPregunta.setToolTipText(Textos_Interfaz.tipTextBotonGenerarPregunta().getString(nuevoIdioma));
		botonImportarSemilla.setToolTipText(Textos_Interfaz.tipTextBotonImportarSemilla().getString(nuevoIdioma));
	}

	
	private void anadirSelectores(){
		add(txtTipoPregunta, BorderLayout.CENTER);
		add(comboBoxTipoPregunta);
		setVisibleTipoPregunta(false);
	}
	
	
	private void anadirBotones() {
		botonGenerarPregunta.addActionListener(new GenerarPreguntaListener());
		add(botonGenerarPregunta, BorderLayout.CENTER);
		
		botonImportarSemilla.addActionListener(new ImportarSemillaListener());
		add(botonImportarSemilla, BorderLayout.CENTER);
	}
	
	
	/** Método que se llama al pulsar el botón de Generar Pregunta */
	protected abstract Pregunta generarPregunta();
	
	
	
	private class GenerarPreguntaListener implements ActionListener {
		
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
			String nombreArchivo = pregunta.getNombreDeArchivo().getString(idioma);
			
			BufferedImage imagenVisual = null;
			if(panelCentral.getVisualizacionGrafo() == VisualizacionGrafo.GRAFO_VISUAL){
				imagenVisual = pregunta.getGrafo().toGrafoVisual();
			}
			
			frame.imprimePregunta(textoPreguntaPorPantalla, textoPreguntaXml, nombreArchivo, imagenVisual);
		}
		
	}
	
	
	
	private class ImportarSemillaListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.importarSemilla();
		}
	}
	
	
}
