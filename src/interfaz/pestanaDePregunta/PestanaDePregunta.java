package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
	
	private JLabel txtNumPreguntas = new JLabel(Textos_Interfaz.textoNumPreguntas().esp());
	private final int MIN_PREGUNTAS = 1;
	private final int MAX_PREGUNTAS = 15;
	private JSlider selectorNumPreguntas = new JSlider(MIN_PREGUNTAS, MAX_PREGUNTAS, 1);
	
	private JLabel txtNumNodos = new JLabel(Textos_Interfaz.textoNumNodos().esp());
	private final int MIN_NODOS = 1;
	private final int MAX_NODOS = 10;
	private JSlider selectorNumNodos = new JSlider(MIN_NODOS, MAX_NODOS, 5);
	
	private JLabel txtPorcentajeArcos = new JLabel(Textos_Interfaz.textoPorcentajeArcos().esp());
	private JSlider selectorPorcentajeArcos = new JSlider(0, 100, 50);
	
	private JLabel txtGrafoDirigido = new JLabel(Textos_Interfaz.textoGrafoDirigido().esp());
	private JCheckBox checkBoxGrafoDirigido = new JCheckBox();
	
	private JLabel txtVisualizacionGrafo = new JLabel(Textos_Interfaz.textoVisualizacionGrafo().esp());
	private JComboBoxVisualizacionGrafo comboBoxVisualizacionGrafo = new JComboBoxVisualizacionGrafo();
	
	private JLabel txtTipoPregunta = new JLabel(Textos_Interfaz.textoTipoDePregunta().esp());
	private JComboBoxTextos comboBoxTipoPregunta = new JComboBoxTextos();
	
	private JButton botonGenerarPregunta = new JButton(Textos_Interfaz.botonGenerarPregunta().esp());
	/** Botón de importar semillas */
	private JButton botonImportarSemilla = new JButton(Textos_Interfaz.botonImportarSemilla().esp());
	
	
	/** Constructor */
	public PestanaDePregunta(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame) {
		this.nombreDeLaPestana = nombreDeLaPestana;
		this.frame = frame;
		
		panelTabulado.addTab(getNombreDeLaPestana(Idioma.ESP), null, this, getNombreDeLaPestana(Idioma.ESP));
		panelTabulado.setMnemonicAt(panelTabulado.getTabCount()-1, teclaMnemotecnica);
		
		añadirSelectores();
		
		añadirBotones();
		
//		JLabel contenido = new JLabel(nombreDeLaPestana.getString(Idioma.ESP));
//		contenido.setHorizontalAlignment(JLabel.CENTER);
//		add(contenido);
		
		setLayout(new GridLayout(0, 2));
	}
	
	
	public String getNombreDeLaPestana(Idioma idioma){
		return nombreDeLaPestana.getString(idioma);
	}
	
	
	public int getNumPreguntas(){
		return (Integer)selectorNumPreguntas.getValue();
	}
	
	
	public int getNumNodos(){
		return (Integer)selectorNumNodos.getValue();
	}
	
	
	public Double getPorcentajeArcos(){
		//Valor del 1 al 100
		Integer valorEntero = selectorPorcentajeArcos.getValue();
		return ((double)valorEntero/100);
	}
	
	
	public Integer getTipoDePregunta(){
		return comboBoxTipoPregunta.getSelectedIndex();
	}
	
	
	public void setParametrosSelectorPorcentajeArcos(int maximo, int valorActual, int espaciado){
		selectorPorcentajeArcos.setMaximum(maximo);
		selectorPorcentajeArcos.setValue(valorActual);
		selectorPorcentajeArcos.setMajorTickSpacing(espaciado);
		selectorPorcentajeArcos.setMinorTickSpacing(espaciado);
	}
	
	
	public void setVisibleTipoPregunta(boolean flag){
		txtTipoPregunta.setVisible(flag);
		comboBoxTipoPregunta.setVisible(flag);
	}
	
	
	public void addTipoPregunta(Texto tipoPregunta){
		comboBoxTipoPregunta.addTexto(tipoPregunta);
	}
	
	
	public void deshabilitarGrafoDirigido(boolean dejarMarcado){
		checkBoxGrafoDirigido.setSelected(true);
		
		txtGrafoDirigido.setEnabled(false);
		checkBoxGrafoDirigido.setEnabled(false);
	}
	
	
	public boolean isDirigido(){
		return checkBoxGrafoDirigido.isSelected();
	}
	
	
	public VisualizacionGrafo getVisualizacionGrafo(){
		return comboBoxVisualizacionGrafo.getClaseDeVisualizacion();
	}
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void reaccionarACambioDeIdioma(Idioma nuevoIdioma){
		idioma = nuevoIdioma;
		
		botonGenerarPregunta.setText(Textos_Interfaz.botonGenerarPregunta().getString(nuevoIdioma));
		botonImportarSemilla.setText(Textos_Interfaz.botonImportarSemilla().getString(nuevoIdioma));
		
		txtNumPreguntas.setText(Textos_Interfaz.textoNumPreguntas().getString(nuevoIdioma));
		selectorNumPreguntas.setToolTipText(Textos_Interfaz.tipTextNumPreguntas().getString(nuevoIdioma));
		
		txtNumNodos.setText(Textos_Interfaz.textoNumNodos().getString(nuevoIdioma));
		selectorNumNodos.setToolTipText(Textos_Interfaz.tipTextNumNodos().getString(nuevoIdioma));
		
		txtPorcentajeArcos.setText(Textos_Interfaz.textoPorcentajeArcos().getString(nuevoIdioma));
		txtPorcentajeArcos.setToolTipText(Textos_Interfaz.tipTextPorcentajeArcos().getString(nuevoIdioma));
		selectorPorcentajeArcos.setToolTipText(Textos_Interfaz.tipTextPorcentajeArcos().getString(nuevoIdioma));
		
		txtGrafoDirigido.setText(Textos_Interfaz.textoGrafoDirigido().getString(nuevoIdioma));
		
		txtVisualizacionGrafo.setText(Textos_Interfaz.textoVisualizacionGrafo().getString(nuevoIdioma));
		comboBoxVisualizacionGrafo.actualizar(nuevoIdioma);
		
		txtTipoPregunta.setText(Textos_Interfaz.textoTipoDePregunta().getString(nuevoIdioma));
		comboBoxTipoPregunta.actualizar(nuevoIdioma);
		
		botonGenerarPregunta.setToolTipText(Textos_Interfaz.tipTextBotonGenerarPregunta().getString(nuevoIdioma));
		botonImportarSemilla.setToolTipText(Textos_Interfaz.tipTextBotonImportarSemilla().getString(nuevoIdioma));
	}

	
	private void añadirSelectores(){
		add(txtNumPreguntas, BorderLayout.CENTER);		
		selectorNumPreguntas.setPaintTicks(true);
		selectorNumPreguntas.setPaintLabels(true);
		selectorNumPreguntas.setMajorTickSpacing(1);
		selectorNumPreguntas.setMinorTickSpacing(1);
		add(selectorNumPreguntas);
		
		add(txtNumNodos, BorderLayout.CENTER);
		selectorNumNodos.setPaintTicks(true);
		selectorNumNodos.setPaintLabels(true);
		selectorNumNodos.setMajorTickSpacing(1);
		selectorNumNodos.setMinorTickSpacing(1);
		add(selectorNumNodos);
		
		add(txtPorcentajeArcos, BorderLayout.CENTER);
		selectorPorcentajeArcos.setPaintTicks(true);
		selectorPorcentajeArcos.setPaintLabels(true);
		selectorPorcentajeArcos.setMajorTickSpacing(20);
		selectorPorcentajeArcos.setMinorTickSpacing(10);
		add(selectorPorcentajeArcos);
		
		add(txtGrafoDirigido, BorderLayout.CENTER);
		add(checkBoxGrafoDirigido);
		
		add(txtVisualizacionGrafo, BorderLayout.CENTER);
		add(comboBoxVisualizacionGrafo);
		
		add(txtTipoPregunta, BorderLayout.CENTER);
		add(comboBoxTipoPregunta);
		setVisibleTipoPregunta(false);
	}
	
	
	private void añadirBotones() {
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
			for(int i = 0; i < getNumPreguntas(); i++){
				pregunta = generarPregunta();
				
				textoPreguntaPorPantalla += pregunta.getTextoPreguntaParaMostrarPorPantalla(idioma);
				textoPreguntaXml += pregunta.getTextoPreguntaXml(idioma);
			}
			
			textoPreguntaXml += "\n</quiz>";
			String nombreArchivo = pregunta.getNombreDeArchivo().getString(idioma);
			
			frame.imprimePregunta(textoPreguntaPorPantalla, textoPreguntaXml, nombreArchivo);
		}
		
	}
	
	
	
	private class ImportarSemillaListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.importarSemilla();
		}
	}
	
	
	
	private class JComboBoxTextos extends JComboBox<String> {
		ArrayList<Texto> textos = new ArrayList<Texto>();
		
		public void addTexto(Texto nuevoTexto){
			textos.add(nuevoTexto);
		}
		
		public void actualizar(Idioma idioma){
			removeAllItems();
			for(Texto texto : textos){
				addItem(texto.getString(idioma));
			}
		}
	}
	
	
	
	private class JComboBoxVisualizacionGrafo extends JComboBoxTextos {
		
		/** Constructor de la clase */
		public JComboBoxVisualizacionGrafo(){
			addTexto(Textos_Interfaz.visualizacionMatrizAdyacencia());
			addTexto(Textos_Interfaz.visualizacionListaAdyacencia());
			addTexto(Textos_Interfaz.visualizacionGrafoVisual());
		}

		public VisualizacionGrafo getClaseDeVisualizacion(){
			switch(getSelectedIndex()){
			case 0:
				return VisualizacionGrafo.MATRIZ_DE_ADYACENCIA;
			case 1:
				return VisualizacionGrafo.LISTA_DE_ADYACENCIA;
			case 2: default:
				return VisualizacionGrafo.GRAFO_VISUAL;
			}
		}
	}
}
