package interfaz;

import interfaz.pestanaDePregunta.PestanaDePregunta;
import interfaz.pestanaDePregunta.PestanaDePreguntaDeAnchura;
import interfaz.pestanaDePregunta.PestanaDePreguntaDeDijkstra;
import interfaz.pestanaDePregunta.PestanaDePreguntaDeKruskal;
import interfaz.pestanaDePregunta.PestanaDePreguntaDePrim;
import interfaz.pestanaDePregunta.PestanaDePreguntaDeProfundidad;
import interfaz.pestanaDePregunta.PestanaDePreguntaTopologica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.pregunta.VisualizacionGrafo;
import texto.Idioma;
import texto.Textos_Interfaz;

/**
 * Panel que ocupa el centro de la aplicación.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class PanelCentral extends JPanel {
	
	/**
	 * Panel tabulado. Tiene una pestaña para cada uno de los distintos tipos de pregunta.
	 */
	private JTabbedPane panelTabulado;
	
	/**
	 * Idioma en el que se está ejecutando actualmente la aplicación.
	 */
	private Idioma idioma = Idioma.ESP;
	
	
	/**
	 * Etiqueta relativa al selector del número de preguntas.
	 */
	private JLabel txtNumPreguntas = new JLabel(Textos_Interfaz.textoNumPreguntas().esp());
	
	/**
	 * Mínimo valor que puede tomar el selector del número de preguntas.
	 */
	private final int MIN_PREGUNTAS = 1;
	
	/**
	 * Máximo valor que puede tomar el selector del número de preguntas.
	 */
	private final int MAX_PREGUNTAS = 15;
	
	/**
	 * Selector del número de preguntas a generar.
	 */
	private JSlider selectorNumPreguntas = new JSlider(MIN_PREGUNTAS, MAX_PREGUNTAS, 1);
	
	
	/**
	 * Etiqueta relativa al selector del número de nodos.
	 */
	private JLabel txtNumNodos = new JLabel(Textos_Interfaz.textoNumNodos().esp());
	
	/**
	 * Mínimo valor que puede tomar el selector del número de nodos.
	 */
	private final int MIN_NODOS = 2;
	
	/**
	 * Máximo valor que puede tomar el selector del número de nodos.
	 */
	private final int MAX_NODOS = 10;
	
	/**
	 * Selector del número de nodos que tendrán los grafos.
	 */
	private JSlider selectorNumNodos = new JSlider(MIN_NODOS, MAX_NODOS, 5);
	
	
	/**
	 * Etiqueta relativa al selector del porcentaje de arcos.
	 */
	private JLabel txtPorcentajeArcos = new JLabel(Textos_Interfaz.textoPorcentajeArcos().esp());
	
	/**
	 * Selector del porcentaje de arcos que tendrán los grafos.
	 */
	private JSlider selectorPorcentajeArcos = new JSlider(0, 100, 50);
	
	/**
	 * Etiqueta relativa al selector de si el grafo es dirigido.
	 */
	private JLabel txtGrafoDirigido = new JLabel(Textos_Interfaz.textoGrafoDirigido().esp());
	
	/**
	 * Selector con el que indicar si el grafo será o no dirigido.
	 */
	private JCheckBox checkBoxGrafoDirigido = new JCheckBox();
	
	
	/**
	 * Etiqueta relativa al selector de visualización del grafo.
	 */
	private JLabel txtVisualizacionGrafo = new JLabel(Textos_Interfaz.textoVisualizacionGrafo().esp());
	
	/**
	 * Selector que permite escoger el modo de visualización del grafo.
	 */
	private JComboBoxVisualizacionGrafo comboBoxVisualizacionGrafo = new JComboBoxVisualizacionGrafo();
	
	
	/**
	 * Constructor de la clase. Construye el panel y los elementos que contiene.
	 * @param frame
	 *            Frame de la aplicación.
	 */
	public PanelCentral(FramePrincipal frame){
		panelTabulado = new JTabbedPane();
		panelTabulado.addChangeListener(new CambioDePestanaListener());
		
		this.setLayout(new GridBagLayout());
	
		// Construir las pestañas, que se añaden al panel tabulado:
		new PestanaDePreguntaDeProfundidad(panelTabulado, Textos_Interfaz.recorridoEnProfundidad(),
				KeyEvent.VK_E, frame, this);
		new PestanaDePreguntaDeAnchura(panelTabulado, Textos_Interfaz.recorridoEnAnchura(),
				KeyEvent.VK_A, frame, this);
		new PestanaDePreguntaTopologica(panelTabulado, Textos_Interfaz.clasificacionTopologica(),
				KeyEvent.VK_T, frame, this);
		new PestanaDePreguntaDeDijkstra(panelTabulado, Textos_Interfaz.algoritmoDeDijkstra(),
				KeyEvent.VK_D, frame, this);
		new PestanaDePreguntaDePrim(panelTabulado, Textos_Interfaz.algoritmoDePrim(), KeyEvent.VK_P,
				frame, this);
		new PestanaDePreguntaDeKruskal(panelTabulado, Textos_Interfaz.algoritmoDeKruskal(),
				KeyEvent.VK_K, frame, this);
		
		anadirElementos();
	}
	
	
	/**
	 * Crea y añade al panel los elementos que éste contiene.
	 */
	private void anadirElementos(){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		
		//Peso de los elementos en lo que respecta a rellenar el espacio del panel:
		c.weightx = 1;
		c.weighty = 1;
		
		c.gridwidth = 1;
		add(txtNumPreguntas, c);
		selectorNumPreguntas.setPaintTicks(true);
		selectorNumPreguntas.setPaintLabels(true);
		selectorNumPreguntas.setMajorTickSpacing(1);
		selectorNumPreguntas.setMinorTickSpacing(1);
		c.gridx = 1;
		add(selectorNumPreguntas, c);
		
		c.gridy++;
		c.gridx = 0;
		add(txtNumNodos, c);
		selectorNumNodos.setPaintTicks(true);
		selectorNumNodos.setPaintLabels(true);
		selectorNumNodos.setMajorTickSpacing(1);
		selectorNumNodos.setMinorTickSpacing(1);
		c.gridx = 1;
		add(selectorNumNodos, c);
		
		c.gridy++;
		c.gridx = 0;
		add(txtPorcentajeArcos, c);
		selectorPorcentajeArcos.setPaintTicks(true);
		selectorPorcentajeArcos.setPaintLabels(true);
		selectorPorcentajeArcos.setMajorTickSpacing(20);
		selectorPorcentajeArcos.setMinorTickSpacing(10);
		c.gridx = 1;
		add(selectorPorcentajeArcos, c);
		
		c.gridy++;
		c.gridx = 0;
		add(txtGrafoDirigido, c);
		c.gridx = 1;
		add(checkBoxGrafoDirigido, c);
		
		c.gridy++;
		c.gridx = 0;
		add(txtVisualizacionGrafo, c);
		c.gridx = 1;
		add(comboBoxVisualizacionGrafo, c);
		
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		this.add(panelTabulado, c);
	}
	
	
	/** 
	 * Reescribe los textos tras cambiar la configuración del idioma.
	 * @param nuevoIdioma
	 *            Nuevo idioma establecido.
	 */
	public void presentarTrasCambioDeIdioma(Idioma nuevoIdioma) {
		this.idioma = nuevoIdioma;
		
		for (int i = 0; i < panelTabulado.getTabCount(); i++) {
			PestanaDePregunta pestanaActual = ((PestanaDePregunta) panelTabulado.getComponentAt(i));

			panelTabulado.setTitleAt(i, pestanaActual.getNombreDeLaPestana(nuevoIdioma));
			panelTabulado.setToolTipTextAt(i, pestanaActual.getNombreDeLaPestana(nuevoIdioma));
			pestanaActual.reaccionarACambioDeIdioma(nuevoIdioma);
		}
		
		txtNumPreguntas.setText(Textos_Interfaz.textoNumPreguntas().getString(nuevoIdioma));
		selectorNumPreguntas.setToolTipText(Textos_Interfaz.tipTextNumPreguntas().getString(nuevoIdioma));
		
		txtNumNodos.setText(Textos_Interfaz.textoNumNodos().getString(nuevoIdioma));
		selectorNumNodos.setToolTipText(Textos_Interfaz.tipTextNumNodos().getString(nuevoIdioma));
		
		reescribirSelectorPorcentajeArcos();
		
		txtGrafoDirigido.setText(Textos_Interfaz.textoGrafoDirigido().getString(nuevoIdioma));
		
		txtVisualizacionGrafo.setText(Textos_Interfaz.textoVisualizacionGrafo().getString(nuevoIdioma));
		comboBoxVisualizacionGrafo.actualizar(nuevoIdioma);
	}
	
	
	/**
	 * Cambia los textos y tip texts del selector de porcentaje de arcos y su correspondiente etiqueta en función
	 * del tipo de pregunta que esté seleccionada en el panel tabulado. 
	 */
	private void reescribirSelectorPorcentajeArcos(){
		//Para preguntas topológicas:
		if(panelTabulado.getSelectedIndex() == 2){
			txtPorcentajeArcos.setText(Textos_Interfaz.textoPosibilidadDeArcos().getString(idioma));
			txtPorcentajeArcos.setToolTipText(Textos_Interfaz.tipTextPosibilidadDeArcos().getString(idioma));
			selectorPorcentajeArcos.setToolTipText(Textos_Interfaz.tipTextPosibilidadDeArcos().getString(idioma));
		}
		//Para el resto de preguntas:
		else {
			txtPorcentajeArcos.setText(Textos_Interfaz.textoPorcentajeArcos().getString(idioma));
			txtPorcentajeArcos.setToolTipText(Textos_Interfaz.tipTextPorcentajeArcos().getString(idioma));
			selectorPorcentajeArcos.setToolTipText(Textos_Interfaz.tipTextPorcentajeArcos().getString(idioma));
		}
	}
	
	
	/**
	 * Devuelve el número de preguntas que se han de generar.
	 * @return Número de preguntas que se han de generar.
	 */
	public int getNumPreguntas(){
		return (Integer)selectorNumPreguntas.getValue();
	}
	
	
	/**
	 * Devuelve el número de nodos que deben tener los grafos.
	 * @return Número de nodos que deben tener los grafos.
	 */
	public int getNumNodos(){
		return (Integer)selectorNumNodos.getValue();
	}
	
	
	/**
	 * Devuelve el portentaje de arcos que deben tener los grafos.
	 * @return Porcentaje de arcos que deben tener los grafos.
	 */
	public Double getPorcentajeArcos(){
		//Valor del 1 al 100
		Integer valorEntero = selectorPorcentajeArcos.getValue();
		return ((double)valorEntero/100);
	}
	
	
	/**
	 * Cambia los parámetros del selector del porcentaje de arcos.
	 * @param maximo
	 *            Valor máximo del selector.
	 * @param valorActual
	 *            Valor seleccionado actualmente.
	 * @param espaciado
	 *            Distancia de separación entre los valores seleccionables.
	 */
	public void setParametrosSelectorPorcentajeArcos(int maximo, int valorActual, int espaciado){
		selectorPorcentajeArcos.setMaximum(maximo);
		selectorPorcentajeArcos.setValue(valorActual);
		selectorPorcentajeArcos.setMajorTickSpacing(espaciado);
		selectorPorcentajeArcos.setMinorTickSpacing(espaciado);
	}
	
	
	/**
	 * Indica si el grafo ha de ser dirigido.
	 * @return Si el grafo ha de ser dirigido.
	 */
	public boolean isDirigido(){
		return checkBoxGrafoDirigido.isSelected();
	}
	
	
	/**
	 * Devuelve el modo de visualización del grafo.
	 * @return Modo de visualización por el que ha de mostrarse el grafo.
	 */
	public VisualizacionGrafo getVisualizacionGrafo(){
		return comboBoxVisualizacionGrafo.getClaseDeVisualizacion();
	}
	
	
	
	/**
	 * Combo box que contiene los distintos modos de visualización posibles para un grafo.
	 * @author Jorge Alonso Márquez
	 */
	private class JComboBoxVisualizacionGrafo extends JComboBoxTextos {
		
		/**
		 * Constructor de la clase
		 */
		public JComboBoxVisualizacionGrafo(){
			addTexto(Textos_Interfaz.visualizacionGrafoVisual());
			addTexto(Textos_Interfaz.visualizacionMatrizAdyacencia());
			addTexto(Textos_Interfaz.visualizacionListaAdyacencia());
		}
		
		
		/**
		 * Devuelve el modo de visualización del grafo seleccionado.
		 * @return Modo de visualización que está seleccionado.
		 */
		public VisualizacionGrafo getClaseDeVisualizacion(){
			switch(getSelectedIndex()){
			case 0: default:
				return VisualizacionGrafo.GRAFO_VISUAL;
			case 1:
				return VisualizacionGrafo.MATRIZ_DE_ADYACENCIA;
			case 2:
				return VisualizacionGrafo.LISTA_DE_ADYACENCIA;
			}
		}
	}
	
	
	
	/**
	 * Listener correspondiente a los cambios de pestaña del panel tabulado de las preguntas.
	 * @author Jorge Alonso Márquez
	 */
	private class CambioDePestanaListener implements ChangeListener {
		
		
		/**
		 * Activa o desactiva el selector de si el grafo es dirigido según el tipo de pestaña
		 * que esté activada: En las preguntas de topología, de Prim y de Kruskal, no se puede
		 * elegir si el grafo es dirigido o no.
		 * @param arg0
		 *            Evento del cambio que activó el listener.
		 */
		@Override
		public void stateChanged(ChangeEvent arg0) {
			
			//Activar/desactivar pestaña de seleccionar si el grafo es dirigido
			switch(panelTabulado.getSelectedIndex()){
			case 2: case 4: case 5: //Pregunta topológica, Prim, Kruskal
				txtGrafoDirigido.setEnabled(false);
				checkBoxGrafoDirigido.setEnabled(false);
				break;
			default:
				txtGrafoDirigido.setEnabled(true);
				checkBoxGrafoDirigido.setEnabled(true);
				break;
			}
			
			reescribirSelectorPorcentajeArcos();
		}
	}

}
