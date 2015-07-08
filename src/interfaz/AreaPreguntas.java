package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import texto.Idioma;
import texto.Textos_Interfaz;

/**
 * Area donde se muestran las preguntas que se han ido generando.
 * @author Jorge Alonso Márquez
 */
@SuppressWarnings("serial")
public class AreaPreguntas extends JPanel {
	
	/**
	 * Panel de texto donde se muestran las preguntas generadas.
	 */
	private JTextPane panelDeTexto;
	
	/**
	 * Documento de estilo asociado al panel de texto.
	 */
    private StyledDocument documentoDelPanel;
	
    
    /**
     * Botón para limpiar el contenido del panel.
     */
	private JButton botonLimpiar;
	
	/** 
	 * Constructor de la clase.
	 * Crea los componentes y establece los layouts y dimensiones.
	 */
	public AreaPreguntas(){

		panelDeTexto = new JTextPane();
		panelDeTexto.setMargin(new Insets(5, 5, 5, 5));
		panelDeTexto.setEditable(false);
		panelDeTexto.setFont(new Font("Courier New", Font.PLAIN, 12));
		documentoDelPanel = panelDeTexto.getStyledDocument();

		
		setPreferredSize(new Dimension(450, 700));
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());


		JScrollPane scrollAreaPreguntas = new JScrollPane(panelDeTexto);
		scrollAreaPreguntas.setPreferredSize(new Dimension(450, 700));
		scrollAreaPreguntas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(scrollAreaPreguntas, BorderLayout.CENTER);
		
		botonLimpiar = new JButton("Borrar");
		botonLimpiar.addActionListener(new LimpiarListener());
		add(botonLimpiar, BorderLayout.PAGE_END);
		
		presentarTrasCambioDeIdioma(Idioma.ESP);
		
	}
	
	
	/**
	 * Anade un nuevo fragmento de texto al área.
	 * @param nuevoTexto
	 *            Nuevo fragmento de texto a anadir.
	 */
	public void addTexto(String nuevoTexto) {
		try {
			documentoDelPanel.insertString(documentoDelPanel.getLength(), nuevoTexto, null);
		} catch (BadLocationException e) { }
	}
	
	
	/**
	 * Anade una nueva imagen al área.
	 * @param imagen
	 *            Nueva imagen a anadir.
	 */
	public void addImagen(BufferedImage imagen) {
		try {
			Style style = documentoDelPanel.addStyle("StyleName", null);
	        StyleConstants.setIcon(style, new ImageIcon(imagen));
			documentoDelPanel.insertString(documentoDelPanel.getLength(), "texto ignorado", style);
			addTexto("\n\n");
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Borra el contenido del panel.
	 */
	public void limpiar(){
		panelDeTexto.setText("");
	}
	
	
	/**
	 * Reescribe los textos tras cambiar la configuración del idioma.
	 * @param nuevoIdioma
	 *            Nuevo idioma establecido.
	 */
	public void presentarTrasCambioDeIdioma(Idioma nuevoIdioma) {
		panelDeTexto.setToolTipText(Textos_Interfaz.tipTextAreaPreguntas().getString(nuevoIdioma));
		
		botonLimpiar.setText(Textos_Interfaz.botonLimpiar().getString(nuevoIdioma));
		botonLimpiar.setToolTipText(Textos_Interfaz.tipTextBotonLimpiar().getString(nuevoIdioma));
	}
	
	
	
	/**
	 * Listener del botón Limpiar.
	 * @author Jorge Alonso Márquez
	 */
	private class LimpiarListener implements ActionListener {
		
		/**
		 * Limpia el contenido del área.
		 * @param e
		 *            Evento de la acción que activó el listener.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			limpiar();
		}
		
	}
}