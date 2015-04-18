package interfaz;

import java.awt.EventQueue;
import java.awt.Toolkit;


import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;

import javax.swing.SwingConstants;

import preguntas.Preguntas;
import utilidades.FileUtilities;

/**
 * Ventana inicial de la aplicación.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class VInicial {
	/**
	 * Logger de la aplicación.
	 */
	private static final Logger logger = LoggerFactory.getLogger(VInicial.class);
	/**
	 * JFrame de la ventana.
	 */
	private JFrame frame;
	/**
	 * Campo de texto del directorio.
	 */
    private JTextField textDirectorio;
    /**
	 * Variable para idioma.
	 */
    private  Locale aLocale;
    /**
	 * Variable para recuperar textos según el idioma.
	 */
    ResourceBundle messages;

    /**
	 * Método principal de la clase.
	 * @param args Argumentos de entrada.
	 */
	public static void main(String[] args) {
		new PantallaCargandoMain();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					FileUtilities.copiarFicherosPropiedades();
					Preguntas.ponerPropiedades("idiomaAplicacion","0");
					VInicial window = new VInicial();
					window.frame.setVisible(true);
					logger.info("Aplicacion iniciada");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Método que inicializa la ventana.
	 */
	public VInicial() {
		initialize();
	}

	/**
	 * Método que contiene la configuración de la ventana.
	 */
	private void initialize() {
		aLocale = new Locale ("es", "ES");
		messages = ResourceBundle.getBundle ("interfaz.idiomas.MessagesBundle", aLocale);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(650, 400, 450, 249);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(messages.getString ("titulo"));
		
		frame.setIconImage (Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/miniUBU.png")));

		textDirectorio = new JTextField();
		textDirectorio.setEditable(false);
		textDirectorio.setBounds(141, 80, 257, 25);
		frame.getContentPane().add(textDirectorio);
		textDirectorio.setColumns(10);
		
		final JLabel lblSeleccionDirect = new JLabel();
		lblSeleccionDirect.setText(messages.getString ("elegir"));
		lblSeleccionDirect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionDirect.setBounds(12, 24, 418, 16);
		frame.getContentPane().add(lblSeleccionDirect);
		
		final JButton btnBotonExplorar = new JButton();
		btnBotonExplorar.setText(messages.getString ("explorar"));
		btnBotonExplorar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    	JFileChooser selector = new JFileChooser();		
		    	selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
		    	int opcion = selector.showOpenDialog(null);
		    	if (opcion == JFileChooser.APPROVE_OPTION) {		
			
		    		File archivo = selector.getSelectedFile();
		    		textDirectorio.setText(archivo.toString());
		    		try {
		    			Preguntas.ponerPropiedades("ruta",archivo.toString());
		    			logger.info("Ruta seleccionada");
		    		} catch (IOException e) {
		    			JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					}
		    	}
		    	
		    	}
			});			
		btnBotonExplorar.setBounds(38, 79, 97, 25);
		frame.getContentPane().add(btnBotonExplorar);
		
		
		
		final JButton btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
	
		btnAceptar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/ok.png"))));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textDirectorio.getText().equals("")){
						JOptionPane.showMessageDialog(null,messages.getString ("elegir"),"", JOptionPane.WARNING_MESSAGE);
				}
				else{
					logger.info("Ruta correcta");
					VGenDePreguntas window = new VGenDePreguntas();
					window.ventana.setVisible(true);
					frame.setVisible(false);
				}
			}
		});

		btnAceptar.setBounds(87, 146, 110, 25);
		frame.getContentPane().add(btnAceptar);
		
		final JButton btnSalir = new JButton();
		btnSalir.setText(messages.getString ("salir"));
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);

		btnSalir.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/no.png"))));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=0;
		        n = JOptionPane.showOptionDialog(frame,messages.getString ("confirmacionSalir"),
		        		"", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE, 
		                null, new String[]{messages.getString ("si"),messages.getString ("no")}, "default");
		     
		    
		        if(n==0)
		        {
		        	logger.info("Fin de la Aplicacion");
		        	System.exit(0);
		        }
		        else
		        {
		        	logger.info("Operación de salir cancelada");
		        	return;
		        }
		        	
			}
		});

		btnSalir.setBounds(245, 146, 110, 25);
		frame.getContentPane().add(btnSalir);
		
		JButton btnSpa = new JButton();
		btnSpa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/spa2.png"))));
		btnSpa.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				aLocale = new Locale ("es", "Es");
				cambioIdioma(lblSeleccionDirect, btnBotonExplorar, btnAceptar, btnSalir);	
				logger.info("Cambio idioma a español");
				try {
					Preguntas.ponerPropiedades("idiomaAplicacion","0");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}				
				
			}
		});	
		btnSpa.setBounds(389, 188, 22, 17);
		frame.getContentPane().add(btnSpa);
		
		JButton btnEng = new JButton();
		btnEng.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/eng2.png"))));
		btnEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 aLocale = new Locale ("en", "En");
				 cambioIdioma(lblSeleccionDirect, btnBotonExplorar, btnAceptar, btnSalir);
				 logger.info("Cambio idioma a ingles");
				 try {
					Preguntas.ponerPropiedades("idiomaAplicacion","1");
					}catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}	
			}
		});		
		btnEng.setBounds(414, 188, 22, 17);
		frame.getContentPane().add(btnEng);
							
	}
	/**
	 * Método que cambia el idioma de los elementos de la ventana.
	 * @param lblSeleccionDirect Cambia el texto de selección de directorio.
	 * @param btnBotonExplorar Cambia el texto del botón explorar.
	 * @param btnAceptar Cambia el texto del botón aceptar.
	 * @param btnSalir Cambia el texto del botón salir.
	 */
	public void cambioIdioma(JLabel lblSeleccionDirect, AbstractButton btnBotonExplorar, AbstractButton btnAceptar, AbstractButton btnSalir){
		messages = ResourceBundle.getBundle ("interfaz.idiomas.MessagesBundle", aLocale);
		frame.setTitle(messages.getString ("titulo"));
		lblSeleccionDirect.setText(messages.getString ("elegir"));
		btnBotonExplorar.setText(messages.getString ("explorar"));
		btnAceptar.setText(messages.getString ("aceptar"));
		btnSalir.setText(messages.getString ("salir"));
	}
	
	
}
