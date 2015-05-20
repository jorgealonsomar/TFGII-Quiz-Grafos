package interfaz.pestanaDePregunta;

import interfaz.AreaPreguntas;
import interfaz.FramePrincipal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

import modelo.pregunta.Pregunta;
import util.Idioma;
import util.Texto;

@SuppressWarnings("serial")
public abstract class PestanaDePregunta extends JPanel {
	
	private Texto nombreDeLaPestana;
	
	protected Idioma idioma = Idioma.ESP;
	
	/** Frame que contiene el gui del programa */
	private FramePrincipal frame;
	
	private JLabel txtNumPreguntas = new JLabel(Texto.textoNumPreguntas().esp());
	private final int MIN_PREGUNTAS = 1;
	private final int MAX_PREGUNTAS = 15;
	private JSpinner selectorNumPreguntas = new JSpinner(new SpinnerNumberModel(1, MIN_PREGUNTAS, MAX_PREGUNTAS, 1));
	
	private JLabel txtNumNodos = new JLabel(Texto.textoNumNodos().esp());
	private final int MIN_NODOS = 1;
	private final int MAX_NODOS = 10;
	private JSpinner selectorNumNodos = new JSpinner(new SpinnerNumberModel(5, MIN_NODOS, MAX_NODOS, 1));
	
	private JLabel txtPorcentajeArcos = new JLabel(Texto.textoPorcentajeArcos().esp());
	private JSlider selectorPorcentajeArcos = new JSlider(0, 100, 50);
	
	private JLabel txtGrafoDirigido = new JLabel(Texto.textoGrafoDirigido().esp());
	private JCheckBox checkBoxGrafoDirigido = new JCheckBox();
	
	private JButton botonGenerarPregunta = new JButton(Texto.botonGenerarPregunta().esp());
	
	public PestanaDePregunta(JTabbedPane panelTabulado, Texto nombreDeLaPestana, int teclaMnemotecnica,
			AreaPreguntas areaPreguntas, FramePrincipal frame) {
		this.nombreDeLaPestana = nombreDeLaPestana;
		this.frame = frame;
		
		panelTabulado.addTab(getNombreDeLaPestana(Idioma.ESP), null, this, getNombreDeLaPestana(Idioma.ESP));
		panelTabulado.setMnemonicAt(panelTabulado.getTabCount()-1, teclaMnemotecnica);
		
		añadirSelectores();
		
		añadirBotonGenerarPregunta();
		
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
	
	
	public boolean isDirigido(){
		return checkBoxGrafoDirigido.isSelected();
	}
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void reaccionarACambioDeIdioma(Idioma nuevoIdioma){
		idioma = nuevoIdioma;
		
		botonGenerarPregunta.setText(Texto.botonGenerarPregunta().getString(nuevoIdioma));
		
		txtNumPreguntas.setText(Texto.textoNumPreguntas().getString(nuevoIdioma));
		txtNumNodos.setText(Texto.textoNumNodos().getString(nuevoIdioma));
		
		txtPorcentajeArcos.setText(Texto.textoPorcentajeArcos().getString(nuevoIdioma));
		txtPorcentajeArcos.setToolTipText(Texto.tipTextPorcentajeArcos().getString(nuevoIdioma));
		selectorPorcentajeArcos.setToolTipText(Texto.tipTextPorcentajeArcos().getString(nuevoIdioma));
	}

	
	private void añadirSelectores(){
		add(txtNumPreguntas, BorderLayout.CENTER);		
		selectorNumPreguntas.setToolTipText("[" + MIN_PREGUNTAS + " ~ " + MAX_PREGUNTAS + "]");
		add(selectorNumPreguntas);
		
		add(txtNumNodos, BorderLayout.CENTER);
		selectorNumNodos.setToolTipText("[" + MIN_NODOS + " ~ " + MAX_NODOS + "]");
		add(selectorNumNodos);
		
		add(txtPorcentajeArcos, BorderLayout.CENTER);
		selectorPorcentajeArcos.setPaintTicks(true);
		selectorPorcentajeArcos.setPaintLabels(true);
		selectorPorcentajeArcos.setMajorTickSpacing(20);
		selectorPorcentajeArcos.setMinorTickSpacing(10);
		add(selectorPorcentajeArcos);
		
		add(txtGrafoDirigido, BorderLayout.CENTER);
		add(checkBoxGrafoDirigido);
	}
	
	
	private void añadirBotonGenerarPregunta() {
		botonGenerarPregunta.addActionListener(new GenerarPreguntaListener());
		add(botonGenerarPregunta, BorderLayout.CENTER);
	}
	
	
	/** Método que se llama al pulsar el botón de Generar Pregunta */
	protected abstract Pregunta generarPregunta();
	
	
	
	private class GenerarPreguntaListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Pregunta pregunta = generarPregunta();
			
			//Por numPreguntas veces:
			for(int i = 0; i < getNumPreguntas(); i++){
				frame.imprimePregunta(pregunta);
			}
			
		}
		
	}
	
}
