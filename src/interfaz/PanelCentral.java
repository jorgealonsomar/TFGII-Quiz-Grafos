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
import texto.Textos_Interfaz;
import util.Idioma;

@SuppressWarnings("serial")
public class PanelCentral extends JPanel {
	
	private JTabbedPane panelTabulado;
	
	private Idioma idioma = Idioma.ESP;
	
	private JLabel txtNumPreguntas = new JLabel(Textos_Interfaz.textoNumPreguntas().esp());
	private final int MIN_PREGUNTAS = 1;
	private final int MAX_PREGUNTAS = 15;
	private JSlider selectorNumPreguntas = new JSlider(MIN_PREGUNTAS, MAX_PREGUNTAS, 1);
	
	private JLabel txtNumNodos = new JLabel(Textos_Interfaz.textoNumNodos().esp());
	private final int MIN_NODOS = 2;
	private final int MAX_NODOS = 10;
	private JSlider selectorNumNodos = new JSlider(MIN_NODOS, MAX_NODOS, 5);
	
	private JLabel txtPorcentajeArcos = new JLabel(Textos_Interfaz.textoPorcentajeArcos().esp());
	private JSlider selectorPorcentajeArcos = new JSlider(0, 100, 50);
	
	private JLabel txtGrafoDirigido = new JLabel(Textos_Interfaz.textoGrafoDirigido().esp());
	private JCheckBox checkBoxGrafoDirigido = new JCheckBox();
	
	private JLabel txtVisualizacionGrafo = new JLabel(Textos_Interfaz.textoVisualizacionGrafo().esp());
	private JComboBoxVisualizacionGrafo comboBoxVisualizacionGrafo = new JComboBoxVisualizacionGrafo();
	
	
	/** Constructor de la clase */
	public PanelCentral(AreaPreguntas areaPreguntas, FramePrincipal frame){
		panelTabulado = new JTabbedPane();
		panelTabulado.addChangeListener(new CambioDePestanaListener());
		
		this.setLayout(new GridBagLayout());
	
		// Construir las pestañas, que se añaden al panel tabulado:
		new PestanaDePreguntaDeProfundidad(panelTabulado, Textos_Interfaz.recorridoEnProfundidad(),
				KeyEvent.VK_E, areaPreguntas, frame, this);
		new PestanaDePreguntaDeAnchura(panelTabulado, Textos_Interfaz.recorridoEnAnchura(),
				KeyEvent.VK_A, areaPreguntas, frame, this);
		new PestanaDePreguntaTopologica(panelTabulado, Textos_Interfaz.clasificacionTopologica(),
				KeyEvent.VK_T, areaPreguntas, frame, this);
		new PestanaDePreguntaDeDijkstra(panelTabulado, Textos_Interfaz.algoritmoDeDijkstra(),
				KeyEvent.VK_D, areaPreguntas, frame, this);
		new PestanaDePreguntaDePrim(panelTabulado, Textos_Interfaz.algoritmoDePrim(), KeyEvent.VK_P,
				areaPreguntas, frame, this);
		new PestanaDePreguntaDeKruskal(panelTabulado, Textos_Interfaz.algoritmoDeKruskal(),
				KeyEvent.VK_K, areaPreguntas, frame, this);
		
		anadirElementos();
	}
	
	
	private void anadirElementos(){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		
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
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
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
	
	
	public void setParametrosSelectorPorcentajeArcos(int maximo, int valorActual, int espaciado){
		selectorPorcentajeArcos.setMaximum(maximo);
		selectorPorcentajeArcos.setValue(valorActual);
		selectorPorcentajeArcos.setMajorTickSpacing(espaciado);
		selectorPorcentajeArcos.setMinorTickSpacing(espaciado);
	}
	
	
//	public void deshabilitarGrafoDirigido(boolean dejarMarcado){
//		checkBoxGrafoDirigido.setSelected(dejarMarcado);
//		
//		txtGrafoDirigido.setEnabled(false);
//		checkBoxGrafoDirigido.setEnabled(false);
//	}
	
	
	public boolean isDirigido(){
		return checkBoxGrafoDirigido.isSelected();
	}
	
	
	public VisualizacionGrafo getVisualizacionGrafo(){
		return comboBoxVisualizacionGrafo.getClaseDeVisualizacion();
	}
	
	
	private class JComboBoxVisualizacionGrafo extends JComboBoxTextos {
		
		/** Constructor de la clase */
		public JComboBoxVisualizacionGrafo(){
			addTexto(Textos_Interfaz.visualizacionGrafoVisual());
			addTexto(Textos_Interfaz.visualizacionMatrizAdyacencia());
			addTexto(Textos_Interfaz.visualizacionListaAdyacencia());
		}

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
	
	
	
	private class CambioDePestanaListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent arg0) {
			
			//Activar/desactivar pestaña de seleccionar si el grafo es dirigido
			switch(panelTabulado.getSelectedIndex()){
			case 4: case 5: //Pregunta topológica, Prim, Kruskal
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
