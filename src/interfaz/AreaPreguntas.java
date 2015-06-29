package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import texto.Textos_Interfaz;
import util.Idioma;

@SuppressWarnings("serial")
public class AreaPreguntas extends JPanel {
	
	private JTextArea areaTexto;
	
	private JButton botonLimpiar;
	
	/** Constructor de la clase */
	public AreaPreguntas(){
		setPreferredSize(new Dimension(450, 700));
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());
		
		areaTexto = new JTextArea(1, 40);
		areaTexto.setEditable(false);
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setText("");

		JScrollPane scrollAreaPreguntas = new JScrollPane(areaTexto);
		scrollAreaPreguntas.setPreferredSize(new Dimension(450, 700));
		scrollAreaPreguntas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(scrollAreaPreguntas, BorderLayout.CENTER);
		
		botonLimpiar = new JButton("Borrar");
		botonLimpiar.addActionListener(new LimpiarListener());
		add(botonLimpiar, BorderLayout.PAGE_END);
		
		presentarTrasCambioDeIdioma(Idioma.ESP);
	}
	
	
	public void addTexto(String nuevoTexto) {
		areaTexto.setText(areaTexto.getText() + nuevoTexto);
	}
	
	
	public void borrarTexto(){
		areaTexto.setText("");
	}
	
	
	/** Reescribe los textos tras cambiar la configuración del idioma */
	public void presentarTrasCambioDeIdioma(Idioma nuevoIdioma) {
		areaTexto.setToolTipText(Textos_Interfaz.tipTextAreaPreguntas().getString(nuevoIdioma));
		
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