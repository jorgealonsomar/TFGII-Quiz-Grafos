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

import texto.Textos_Interfaz;
import util.Idioma;

@SuppressWarnings("serial")
public class AreaPreguntas extends JPanel {
	
//	private JTextArea areaTexto;
	
	private JTextPane panelDeTexto;
    private StyledDocument documentoDelPanel;
	
	private JButton botonLimpiar;
	
	/** Constructor de la clase */
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
	
	
	public void addTexto(String nuevoTexto) {
		try {
			documentoDelPanel.insertString(documentoDelPanel.getLength(), nuevoTexto, null);
		} catch (BadLocationException e) { }
	}
	
	
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
	
	
	public void borrarTexto(){
		panelDeTexto.setText("");
	}
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void presentarTrasCambioDeIdioma(Idioma nuevoIdioma) {
		panelDeTexto.setToolTipText(Textos_Interfaz.tipTextAreaPreguntas().getString(nuevoIdioma));
		
		botonLimpiar.setText(Textos_Interfaz.botonLimpiar().getString(nuevoIdioma));
		botonLimpiar.setToolTipText(Textos_Interfaz.tipTextBotonLimpiar().getString(nuevoIdioma));
	}
	
	
	
	private class LimpiarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			borrarTexto();
		}
		
	}
}