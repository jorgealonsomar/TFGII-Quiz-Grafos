package interfaz;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.help.HelpBroker;
import javax.help.HelpSet;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import preguntas.PreguntaArbolBinarioBusqueda;
import preguntas.PreguntaInsercionDirecta;
import preguntas.PreguntaMonticuloMaximo;
import preguntas.PreguntaPila;
import preguntas.PreguntaSeleccionDirecta;
import preguntas.PreguntaTablaHash;
import preguntas.Preguntas;
import utilidades.FileUtilities;
import utilidades.FormatosPreguntas;


import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;

/**
 * Ventana principal de la aplicación.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class VGenDePreguntas {
	/**
	 * JFrame de la ventana.
	 */
	JFrame ventana;
	
	/**
	 * Estado de la semilla en la extracción de elementos del montículo.
	 */
	private boolean SemillaMontiExtraer=false;
	/**
	 * Estado de la semilla en la adición de elementos del montículo.
	 */
	private boolean SemillaMontiAdd=false;
	/**
	 * Estado de la semilla en la pila.
	 */
	private boolean SemillaPila=false;
	/**
	 * Estado de la semilla en la tabla hash.
	 */
	private boolean SemillaHash=false;
	/**
	 * Estado de la semilla en inserción directa.
	 */
	private boolean SemillaID=false;
	/**
	 * Estado de la semilla en selección directa.
	 */
	private boolean SemillaSD=false;
	/**
	 * Estado de la semilla en ABB:añadir nodos.
	 */
	private boolean SemillaABBadd=false;
	/**
	 * Estado de la semilla en ABB borrado derecho.
	 */
	private boolean SemillaABBder=false;
	/**
	 * Estado de la semilla en borrado izquierdo.
	 */
	private boolean SemillaABBizq=false;
	/**
	 * Variable para idioma.
	 */
	private  Locale aLocale;
	/**
	 * Variable para recuperar textos según el idioma.
	 */
	private  ResourceBundle messages;
	/**
	 * Logger de la aplicación.
	 */
	private static final Logger logger = LoggerFactory.getLogger(VGenDePreguntas.class);
	
	/**
	 * Método principal de la clase.
	 * @param args Argumentos de entrada.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					VGenDePreguntas window = new VGenDePreguntas();					
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VGenDePreguntas() {
		initialize();
	}

	/**	
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		logger.info("Pantalla Principal ");
		if(Preguntas.obtenerPropiedades("idiomaAplicacion")==0)
			aLocale = new Locale ("es", "ES");
		else
			aLocale = new Locale ("en", "En");
		
		messages = ResourceBundle.getBundle ("interfaz.idiomas.MessagesBundle", aLocale);
		
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.setTitle(messages.getString ("tituloPrincipal"));
		ventana.setBounds(560, 350, 677, 588);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setIconImage (Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/miniUBU.png")));
		ventana.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 231, 26);
		ventana.getContentPane().add(menuBar);
		
		
		final JMenu menuArchivo = new JMenu();
		menuArchivo.setText(messages.getString ("archivo"));
		menuBar.add(menuArchivo);


		final JMenuItem salir = new JMenuItem();
		salir.setText(messages.getString ("salir"));
		
		salir.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/exit.png"))));
	
		salir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    	int n=0;
		        n = JOptionPane.showOptionDialog(ventana,messages.getString ("confirmacionSalir"),
		        		"", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE, 
		                null, new String[]{messages.getString ("si"),messages.getString ("no")}, "default");;
		    
		        if(n==0)
		        {
		        	logger.info("Fin de la aplicacion ");
		        	System.exit(0);
		        }
		        else
		        {
		        	logger.info("Cancelar la accion de salir");
		        	return;
		        }	
		    }
		});  
		
// SELECCION DIRECTORIO		
		final JMenuItem mntmSeleccionarDirectorio = new JMenuItem();
		mntmSeleccionarDirectorio.setText(messages.getString ("cambiarDir"));
		mntmSeleccionarDirectorio.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/dir.png"))));
		
		mntmSeleccionarDirectorio.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    	JFileChooser selector = new JFileChooser();		
		    	selector.setCurrentDirectory(new File(Preguntas.obtenerPropiedadesString("ruta")));
		    	selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
		    	int opcion = selector.showOpenDialog(null);
		    	if (opcion == JFileChooser.APPROVE_OPTION) {		
		    		File archivo = selector.getSelectedFile();
			    	try {
			    		logger.info("Nueva ruta seleccionada ");
			    		Preguntas.ponerPropiedades("ruta",archivo.toString());
			    	} catch (IOException e) {
			    		logger.info("Error al leer el fichero de propiedades ");
			    		JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
			    		e.printStackTrace();
			    	}
		    	}
		    	
		    }
		});
		
		
		menuArchivo.add(mntmSeleccionarDirectorio);
		
		JSeparator separator_1 = new JSeparator();
		menuArchivo.add(separator_1);
		menuArchivo.add(salir);
		
		
		
		final JMenu mnEdicion = new JMenu();
		mnEdicion.setText(messages.getString ("edicion"));
		
		menuBar.add(mnEdicion);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 25, 917, 702);
		ventana.getContentPane().add(tabbedPane);
		
		final JLabel lblModoGeneradorActivo = new JLabel();
		lblModoGeneradorActivo.setText(messages.getString ("modoGenerarPreguntas"));
		lblModoGeneradorActivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModoGeneradorActivo.setVisible(true);
		lblModoGeneradorActivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModoGeneradorActivo.setBounds(293, 0, 366, 26);
		ventana.getContentPane().add(lblModoGeneradorActivo);
		
		final JLabel lblModoRecupActivo = new JLabel();
		lblModoRecupActivo.setText(messages.getString ("modoRecuperarPreguntas"));
		lblModoRecupActivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModoRecupActivo.setVisible(false);
		lblModoRecupActivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModoRecupActivo.setBounds(293, 0, 366, 26);
		ventana.getContentPane().add(lblModoRecupActivo);
		
		
	

		
		
		
		
		final JMenu mnIdioma = new JMenu();
		mnIdioma.setText(messages.getString ("idioma"));
		menuBar.add(mnIdioma);
		
		final JMenu mnCambiarIdiomaPregs = new JMenu();
		mnCambiarIdiomaPregs.setText(messages.getString ("camIdiomaPreg"));
		mnIdioma.add(mnCambiarIdiomaPregs);
		
		final JMenuItem mntmEspaolPregs = new JMenuItem();
		mntmEspaolPregs.setText(messages.getString ("espanol"));
		
		mntmEspaolPregs.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/spa.png"))));
		mntmEspaolPregs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logger.info("cambiar idioma de las preguntas a español ");
					Preguntas.ponerPropiedades("idioma","0");
				}catch (IOException e1) {
					logger.info("Error al leer el fichero de propiedades ");
					JOptionPane.showMessageDialog(null,messages.getString (messages.getString ("errorPropiedades")),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});		
		mnCambiarIdiomaPregs.add(mntmEspaolPregs);
		
		final JMenuItem mntmInglesPregs = new JMenuItem();
		mntmInglesPregs.setText(messages.getString ("ingles"));
		mntmInglesPregs.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/eng.png"))));
		mntmInglesPregs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logger.info("Cambiar idioma preguntas a ingles ");
					Preguntas.ponerPropiedades("idioma","1");
				} catch (IOException e1) {
					logger.info("Error al leer el fichero de propiedades ");
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					
					e1.printStackTrace();
				}
			}
		});
		mnCambiarIdiomaPregs.add(mntmInglesPregs);
		
		final JMenu mnAyuda = new JMenu();
		mnAyuda.setText(messages.getString ("ayuda1"));		
		menuBar.add(mnAyuda);
		
		final JMenuItem mntmManualDeUsuario = new JMenuItem();
		mntmManualDeUsuario.setText(messages.getString ("ayuda2"));		
		mntmManualDeUsuario.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/help.png"))));
		//AÑADIDO JAVAHELP
		try {
			// Carga el fichero de ayuda
						logger.info("Cargando ayuda ");
						//CARGA JAR
						URL url = new URL("jar:file:Generador.jar!/help/help_set.hs");
						//CARGA ECLIPSE
						//File fichero = new File("src/help/help_set.hs");
						//java.net.URL hsURL = fichero.toURI().toURL();

						// Crea el HelpSet y el HelpBroker
						HelpSet helpset = new HelpSet(getClass().getClassLoader(), url);
						HelpBroker hb = helpset.createHelpBroker();
						hb.enableHelpOnButton(mntmManualDeUsuario, "aplicacion", helpset);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		mnAyuda.add(mntmManualDeUsuario);
		
		
		final JMenuItem mntmAcercaDe = new JMenuItem();
		mntmAcercaDe.setText(messages.getString ("acerca"));
		mntmAcercaDe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/about.png"))));
		mntmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				logger.info("Obtener el acerca de.. ");
				VAcercaDe window = new VAcercaDe();
				window.frame.setVisible(true);
				
			}
		});
		
		JSeparator separator = new JSeparator();
		mnAyuda.add(separator);
		mntmAcercaDe.setSelected(true);
		mnAyuda.add(mntmAcercaDe);

		
		//borde de la pantalla que muestra la preguntas
		final TitledBorder borde = new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		borde.setTitle(messages.getString ("pregGeneradas"));
		

//MONTICULOS		
		JPanel pestMont = new JPanel();
		tabbedPane.addTab("", null, pestMont, null);
		tabbedPane.setTitleAt(0, messages.getString ("monticulos"));

		pestMont.setLayout(null);
		
		final JTabbedPane subPanelesMonti = new JTabbedPane(JTabbedPane.TOP);
		subPanelesMonti.setBounds(0, 0, 749, 697);
		pestMont.add(subPanelesMonti);
// MONTICULO EXTRAER MAXIMO		
		JPanel montiExtra = new JPanel();
		subPanelesMonti.addTab("", null, montiExtra, null);
		subPanelesMonti.setTitleAt(0, messages.getString ("extraerMax"));		
		montiExtra.setLayout(null);
		
		final JLabel montiNumPre = new JLabel();
		montiNumPre.setText(messages.getString ("numPreg"));	
		montiNumPre.setBounds(43, 38, 239, 15);
		montiExtra.add(montiNumPre);
		
		final JSpinner contMontiNumPregExtraer = new JSpinner();
		contMontiNumPregExtraer.setToolTipText(messages.getString ("numPregToolTip"));
		contMontiNumPregExtraer.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		contMontiNumPregExtraer.setBounds(340, 34, 45, 22);
		montiExtra.add(contMontiNumPregExtraer);
		
		final JLabel montiNmeroDeElementos = new JLabel();
		montiNmeroDeElementos.setText(messages.getString ("numElem"));		
		montiNmeroDeElementos.setBounds(43, 85, 239, 15);
		montiExtra.add(montiNmeroDeElementos);
		
		final JLabel montiNmeroDeExtracciones = new JLabel();
		montiNmeroDeExtracciones.setText(messages.getString ("numExtMont"));
		
		montiNmeroDeExtracciones.setBounds(43, 131, 239, 15);
		montiExtra.add(montiNmeroDeExtracciones);

		final JSpinner contMontiNumElem = new JSpinner();
		contMontiNumElem.setToolTipText(messages.getString ("montiNumElemToolTip"));
		contMontiNumElem.setModel(new SpinnerNumberModel(5, 5, 20, 1));
		contMontiNumElem.setBounds(340, 81, 45, 22);
		montiExtra.add(contMontiNumElem);
		
		final JSpinner contMontiNumExtra = new JSpinner();
		contMontiNumExtra.setToolTipText(messages.getString ("montiNumExtracToolTip"));
		contMontiNumExtra.setModel(new SpinnerNumberModel(1, 1, 19, 1));
		contMontiNumExtra.setBounds(340, 127, 45, 22);
		montiExtra.add(contMontiNumExtra);

		// ventana log mont extraer				
				JPanel panelTextoMontiExtra = new JPanel();
				panelTextoMontiExtra.setBorder(borde);
				
				panelTextoMontiExtra.setBounds(4, 215, 665, 394);
				montiExtra.add(panelTextoMontiExtra);
				panelTextoMontiExtra.setLayout(null);

				final JTextArea textAreaMontiExtra = new JTextArea();
				textAreaMontiExtra.setEditable(false);
				textAreaMontiExtra.setLineWrap(true);
				textAreaMontiExtra.setWrapStyleWord(true);
				textAreaMontiExtra.setBounds(0, 0, 457, 238);
				montiExtra.add(textAreaMontiExtra);
			    
			    
				
				JScrollPane scrollMontiExtra = new JScrollPane(textAreaMontiExtra);
				scrollMontiExtra.setBounds(8, 18, 459, 261);
				panelTextoMontiExtra.add(scrollMontiExtra);
				
				final JButton buttonLimpMontExtra = new JButton("");
				buttonLimpMontExtra.setToolTipText(messages.getString ("limpiarCuadroPregs"));
				buttonLimpMontExtra.setBounds(451, 0, 16, 16);
				panelTextoMontiExtra.add(buttonLimpMontExtra);
				
				buttonLimpMontExtra.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
				buttonLimpMontExtra.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						textAreaMontiExtra.setText("");
					}
				});
								
				
		final JButton botonMontiExtraer = new JButton();
		botonMontiExtraer.setText(messages.getString ("generar"));
		botonMontiExtraer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				try {
					if(Integer.parseInt(contMontiNumElem.getValue().toString()) > Integer.parseInt(contMontiNumExtra.getValue().toString())){
						Preguntas.ponerPropiedades("heapSize", contMontiNumElem.getValue().toString());	// heapSize = num elem
						Preguntas.ponerPropiedades("extractionsNumber", contMontiNumExtra.getValue().toString());	// nº de extracc
						
						PreguntaMonticuloMaximo.preguntaBorrarMonticulo((int)contMontiNumPregExtraer.getValue());
						logger.info("Generada pregunta de extraer monticulo ");	
						textAreaMontiExtra.setText(textAreaMontiExtra.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
						
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,messages.getString ("paramIncorrect"));	
						logger.info("Paarmetros en extraer monticulos incorrectos ");	
					}
					} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		botonMontiExtraer.setBounds(183, 179, 99, 23);
		montiExtra.add(botonMontiExtraer);
		
		final JTextField txtSemillaMontiExtraer = new JTextField();
		txtSemillaMontiExtraer.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaMontiExtraer.setVisible(false);
		txtSemillaMontiExtraer.setVisible(false);
		txtSemillaMontiExtraer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSemillaMontiExtraer.setText(null); 
			}
		});
		txtSemillaMontiExtraer.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemillaMontiExtraer.setText(messages.getString ("semilla"));
		txtSemillaMontiExtraer.setBounds(43, 179, 116, 22);
		montiExtra.add(txtSemillaMontiExtraer);
		txtSemillaMontiExtraer.setColumns(10);
		
		final JButton btnRecuperarMontiExtraer = new JButton();
		btnRecuperarMontiExtraer.setText(messages.getString ("recuperar"));
		btnRecuperarMontiExtraer.setVisible(false);
		btnRecuperarMontiExtraer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String semilla = txtSemillaMontiExtraer.getText();
				try {
					if(txtSemillaMontiExtraer.getText().length()>0){
						for(int i=0; i<txtSemillaMontiExtraer.getText().length();i++){
							char c = txtSemillaMontiExtraer.getText().charAt(i);
							if(Character.isDigit(c))
								SemillaMontiExtraer=true;
							else{
								SemillaMontiExtraer=false;
								break;
							}									
						}	
					}else
						SemillaMontiExtraer=false;
					
					if(SemillaMontiExtraer){
						if(Integer.parseInt(contMontiNumElem.getValue().toString()) > Integer.parseInt(contMontiNumExtra.getValue().toString())){
							Preguntas.ponerPropiedades("heapSize", contMontiNumElem.getValue().toString());	// heapSize = num elem
							Preguntas.ponerPropiedades("extractionsNumber", contMontiNumExtra.getValue().toString());	// nº de extracc
							Preguntas.ponerPropiedades("seedHeap",semilla);
							
							PreguntaMonticuloMaximo.preguntaBorrarMonticulo(1);
/*Mostrar enun log*/		textAreaMontiExtra.setText(textAreaMontiExtra.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
						
							Preguntas.ponerPropiedades("seedHeap","-1");	
							logger.info("Recuperar Pregunta	");
							JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
							logger.info("Pregunta extraer monticulo recuperada ");	
						}
						else
							JOptionPane.showMessageDialog(null,messages.getString ("paramIncorrect"));
					}else
						JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));					
					txtSemillaMontiExtraer.setText(messages.getString ("semilla"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					logger.info("Error al leer el fichero propiedades ");	
					e1.printStackTrace();
				}
			}
		});
		btnRecuperarMontiExtraer.setBounds(264, 178, 121, 25);
		montiExtra.add(btnRecuperarMontiExtraer);
		
		
		
		
		
		
// MONTICULO AÑADIR ELEMENTOS		
		JPanel montiAdd = new JPanel();
		subPanelesMonti.addTab("", null, montiAdd, null);
		subPanelesMonti.setTitleAt(1, messages.getString ("montiAddElem"));
		montiAdd.setLayout(null);
		
		final JLabel montiExtraNumPre = new JLabel();
		montiExtraNumPre.setText(messages.getString ("numPreg"));	
		montiExtraNumPre.setBounds(43, 38, 230, 15);
		montiAdd.add(montiExtraNumPre);
		
		final JSpinner contMontiNumPregAdd = new JSpinner();
		contMontiNumPregAdd.setToolTipText(messages.getString ("numPregToolTip"));
		contMontiNumPregAdd.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		contMontiNumPregAdd.setBounds(340, 34, 45, 22);
		montiAdd.add(contMontiNumPregAdd);
		
		final JLabel montiNmeroDeElementosAdd = new JLabel();
		montiNmeroDeElementosAdd.setText(messages.getString ("numElem"));		
		montiNmeroDeElementosAdd.setBounds(43, 85, 230, 15);
		montiAdd.add(montiNmeroDeElementosAdd);
		
		final JLabel montiNmeroDeAdds = new JLabel();
		montiNmeroDeAdds.setText(messages.getString ("montiAddElemAdd"));
		montiNmeroDeAdds.setBounds(43, 131, 230, 15);
		montiAdd.add(montiNmeroDeAdds);

		final JSpinner contMontiNumElemAdd = new JSpinner();	//heapSizeAdd
		contMontiNumElemAdd.setToolTipText(messages.getString ("montiIntroNumElem"));
		contMontiNumElemAdd.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		contMontiNumElemAdd.setBounds(340, 81, 45, 22);
		montiAdd.add(contMontiNumElemAdd);
		
		final JSpinner contMontiNumAdd = new JSpinner();	//addNumber
		contMontiNumAdd.setToolTipText(messages.getString ("montiIntroNumAdd"));
		contMontiNumAdd.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		contMontiNumAdd.setBounds(340, 127, 45, 22);
		montiAdd.add(contMontiNumAdd);
		
		// ventana log mont add				
		JPanel panelTextoMontiAdd = new JPanel();
		panelTextoMontiAdd.setBorder(borde);
		panelTextoMontiAdd.setBounds(4, 215, 665, 394);
		montiAdd.add(panelTextoMontiAdd);
		panelTextoMontiAdd.setLayout(null);


		final JTextArea textAreaMontiAdd = new JTextArea();
		textAreaMontiAdd.setWrapStyleWord(true);
		textAreaMontiAdd.setEditable(false);
		textAreaMontiAdd.setLineWrap(true);
		textAreaMontiAdd.setBounds(12, 248, 457, 238);
		montiAdd.add(textAreaMontiAdd);
		
		JScrollPane scrollMontiAdd = new JScrollPane(textAreaMontiAdd);
		scrollMontiAdd.setBounds(8, 18, 459, 261);
		panelTextoMontiAdd.add(scrollMontiAdd);				
		
		final JButton buttonLimpMontAdd = new JButton("");
		buttonLimpMontAdd.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		buttonLimpMontAdd.setBounds(451, 0, 16, 16);
		panelTextoMontiAdd.add(buttonLimpMontAdd);
		buttonLimpMontAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
		buttonLimpMontAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textAreaMontiAdd.setText("");
			}
		});
		
		
		final JButton botonMontiAdd = new JButton();
		botonMontiAdd.setText(messages.getString ("generar"));
		botonMontiAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Preguntas.ponerPropiedades("heapSizeAdd", contMontiNumElemAdd.getValue().toString());	// heapSizeAdd = num elem
					Preguntas.ponerPropiedades("addNumber", contMontiNumAdd.getValue().toString());	// nº de adds
					
					PreguntaMonticuloMaximo.preguntaAnadirMonticulo((int)contMontiNumPregAdd.getValue());
					logger.info("Generada pregunta de añadir monticulo");	
					textAreaMontiAdd.setText(textAreaMontiAdd.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
					
					JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					logger.info("Error al leer el fichero de propiedades");	
				}		
			}
		});

		botonMontiAdd.setBounds(183, 179, 99, 23);
		montiAdd.add(botonMontiAdd);
		
		final JTextField txtSemillaMontiAdd = new JTextField();
		txtSemillaMontiAdd.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaMontiAdd.setVisible(false);
		txtSemillaMontiAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSemillaMontiAdd.setText(null); 
			}
		});
		txtSemillaMontiAdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemillaMontiAdd.setText(messages.getString ("semilla"));
		txtSemillaMontiAdd.setBounds(43, 179, 116, 22);
		montiAdd.add(txtSemillaMontiAdd);
		txtSemillaMontiAdd.setColumns(10);
		
		
		final JButton btnRecuperarMontiAdd = new JButton();
		btnRecuperarMontiAdd.setText(messages.getString ("recuperar"));
		btnRecuperarMontiAdd.setVisible(false);
		btnRecuperarMontiAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String semilla = txtSemillaMontiAdd.getText();
				try {
					if(txtSemillaMontiAdd.getText().length()>0){
						for(int i=0; i<txtSemillaMontiAdd.getText().length();i++){
							char c = txtSemillaMontiAdd.getText().charAt(i);
							if(Character.isDigit(c))
								SemillaMontiAdd=true;
							else{
								SemillaMontiAdd=false;
								break;
							}									
						}
					}else
						SemillaMontiAdd=false;
						
					if(SemillaMontiAdd){
						Preguntas.ponerPropiedades("heapSizeAdd", contMontiNumElemAdd.getValue().toString());	// heapSizeAdd = num elem
						Preguntas.ponerPropiedades("addNumber", contMontiNumAdd.getValue().toString());	// nº de adds
						Preguntas.ponerPropiedades("seedHeapAdd",semilla);
					
						PreguntaMonticuloMaximo.preguntaAnadirMonticulo(1);
						textAreaMontiAdd.setText(textAreaMontiAdd.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
						logger.info("Recuperar pregunta de añadir monticulo ");	
						Preguntas.ponerPropiedades("seedHeapAdd","-1");
						
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
						logger.info("Pregunta de añadir monticulo recuperada correctamente ");	

					}else
						JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	
					txtSemillaMontiAdd.setText(messages.getString ("semilla"));

				} catch (IOException e1) {					
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		btnRecuperarMontiAdd.setBounds(264, 178, 121, 25);
		montiAdd.add(btnRecuperarMontiAdd);
		
		

// PILAS		
		JPanel pestPila = new JPanel();
		tabbedPane.addTab("", null, pestPila, null);
		tabbedPane.setTitleAt(1, messages.getString ("pilas"));
		pestPila.setLayout(null);

		final JLabel PilaNumPre = new JLabel();
		PilaNumPre.setText(messages.getString ("numPreg"));
		PilaNumPre.setBounds(43, 26, 241, 15);
		pestPila.add(PilaNumPre);
		
		final JSpinner contPilaNumPreg = new JSpinner();
		contPilaNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
		contPilaNumPreg.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		contPilaNumPreg.setBounds(341, 22, 45, 22);
		pestPila.add(contPilaNumPreg);
		
		final JLabel tamPila = new JLabel();
		tamPila.setText(messages.getString ("pilaTam"));
		tamPila.setBounds(43, 69, 253, 15);
		pestPila.add(tamPila);
		
		final JSpinner contTamPila = new JSpinner();//stackSize
		contTamPila.setToolTipText(messages.getString ("pilaTamTT"));
		contTamPila.setModel(new SpinnerNumberModel(5, 5, 20, 1));
		contTamPila.setBounds(341, 65, 45, 22);
		pestPila.add(contTamPila);
		
		final JLabel lblPilaNumRespuestas = new JLabel();
		lblPilaNumRespuestas.setText(messages.getString ("pilaNumPosResp"));
		lblPilaNumRespuestas.setBounds(43, 118, 265, 15);
		pestPila.add(lblPilaNumRespuestas);
		
		final JSpinner contPilaNumResp = new JSpinner();
		contPilaNumResp.setToolTipText(messages.getString ("pilaNumOpc"));
		contPilaNumResp.setModel(new SpinnerNumberModel(3, 3, 6, 1));
		contPilaNumResp.setBounds(341, 114, 45, 22);
		pestPila.add(contPilaNumResp);
		
		final JLabel lblProbDeRespuestaCorrecta = new JLabel();
		lblProbDeRespuestaCorrecta.setText(messages.getString ("pilaProbRespOk"));
		lblProbDeRespuestaCorrecta.setBounds(43, 169, 265, 15);
		pestPila.add(lblProbDeRespuestaCorrecta);
	
		final JSpinner contPorcentRespCorrect = new JSpinner();
		contPorcentRespCorrect.setToolTipText(messages.getString ("pilaProbRespOkTT"));
		contPorcentRespCorrect.setModel(new SpinnerNumberModel(0.1,0.1, 0.9, 0.1));
		contPorcentRespCorrect.setBounds(341, 165, 45, 22);
		pestPila.add(contPorcentRespCorrect);
		
		// ventana log pila				
				JPanel panelTextoPila = new JPanel();
				panelTextoPila.setBorder(borde);
				panelTextoPila.setBounds(4, 246, 711, 451);
				pestPila.add(panelTextoPila);
				panelTextoPila.setLayout(null);

				final JTextArea textAreaPila = new JTextArea();
				textAreaPila.setWrapStyleWord(true);
				textAreaPila.setEditable(false);
				textAreaPila.setLineWrap(true);
				textAreaPila.setBounds(12, 248, 457, 238);
				pestPila.add(textAreaPila);
				
				JScrollPane scrollPila = new JScrollPane(textAreaPila);
				scrollPila.setBounds(8, 18, 461, 255);
				panelTextoPila.add(scrollPila);
				
				final JButton buttonLimpPila = new JButton("");
				buttonLimpPila.setToolTipText(messages.getString ("limpiarCuadroPregs"));
				buttonLimpPila.setBounds(453, 0, 16, 16);
				panelTextoPila.add(buttonLimpPila);
				buttonLimpPila.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
				buttonLimpPila.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						textAreaPila.setText("");
						logger.info("Texto de la pregunta borrado ");	
					}
				});
						
		
				
		final JButton botonGenerarPila = new JButton();
		botonGenerarPila.setText(messages.getString ("generar"));
	
		botonGenerarPila.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {	
					
				
						Preguntas.ponerPropiedades("seedStack", "-1");
						Preguntas.ponerPropiedades("stackSize",contTamPila.getValue().toString());
						Preguntas.ponerPropiedades("numberOption",contPilaNumResp.getValue().toString());
					
						Preguntas.ponerPropiedades("correctParameter",contPorcentRespCorrect.getValue().toString());
					
							PreguntaPila.preguntaPila((int)contPilaNumPreg.getValue());	
							logger.info("Generada pregunta pila");	
							textAreaPila.setText(textAreaPila.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

		
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					logger.info("Error al leer el fichero de propiedades ");	
				}
			}
		});
		botonGenerarPila.setBounds(185, 213, 99, 23);
		pestPila.add(botonGenerarPila);
		
		
		final JTextField txtSemillaPila = new JTextField();
		txtSemillaPila.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaPila.setVisible(false);
		txtSemillaPila.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSemillaPila.setText(null);
			}
		});
		txtSemillaPila.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemillaPila.setText(messages.getString ("semilla"));
		txtSemillaPila.setBounds(43, 213, 116, 22);
		pestPila.add(txtSemillaPila);
		txtSemillaPila.setColumns(10);
		
		final JButton btnRecuperarPila = new JButton();
		btnRecuperarPila.setText(messages.getString ("recuperar"));
		btnRecuperarPila.setVisible(false);
		btnRecuperarPila.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String semilla = txtSemillaPila.getText();
				
				try {
					if(txtSemillaPila.getText().length()>0){
						for(int i=0; i<txtSemillaPila.getText().length();i++){
							char c = txtSemillaPila.getText().charAt(i);
							if(Character.isDigit(c))
								SemillaPila=true;
							else{
								SemillaPila=false;
								break;
							}									
						}						
					}else
						SemillaPila=false;
					if(SemillaPila){
					
						
						Preguntas.ponerPropiedades("stackSize",contTamPila.getValue().toString());
						Preguntas.ponerPropiedades("numberOption",contPilaNumResp.getValue().toString());
						Preguntas.ponerPropiedades("correctParameter",contPorcentRespCorrect.getValue().toString());					
						Preguntas.ponerPropiedades("seedStack",semilla);
					
						PreguntaPila.preguntaPila(1);
						if(Integer.parseInt(Preguntas.obtenerPropiedadesString("semillaIncorrectaPila"))==0){
							textAreaPila.setText(textAreaPila.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
							Preguntas.ponerPropiedades("seedStack","-1");	
							JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
							logger.info("Recuperar pregunta de pila  ");	
						}
						
						else{
							JOptionPane.showMessageDialog(null,messages.getString ("mensajeSemillaPilaIncorrecta"));							
							Preguntas.ponerPropiedades("semillaIncorrectaPila", "0");
							logger.info("No se ha recuperdado la pregunta. Semilla incorrecta ");	
						}

						

					}else
						JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	
					txtSemillaPila.setText(messages.getString ("semilla"));
						
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRecuperarPila.setBounds(265, 212, 121, 25);
		pestPila.add(btnRecuperarPila);
		
		
		
// TABLAS HASH		
		JPanel pestHash = new JPanel();
		tabbedPane.addTab("", null, pestHash, null);
		tabbedPane.setTitleAt(2, messages.getString ("tablasHash"));
		pestHash.setLayout(null);
		
		final JLabel HashNumPre = new JLabel();
		HashNumPre.setText(messages.getString ("numPreg"));
		HashNumPre.setBounds(43, 67, 228, 15);
		pestHash.add(HashNumPre);
		
		final JSpinner contHashNumPreg = new JSpinner();
		contHashNumPreg .setToolTipText(messages.getString ("numPregToolTip"));
		contHashNumPreg.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		contHashNumPreg.setBounds(341, 63, 45, 22);
		pestHash.add(contHashNumPreg);
		
		final JLabel tamTablaHash = new JLabel();
		tamTablaHash.setText(messages.getString ("tablaTam"));
		tamTablaHash.setBounds(43, 119, 228, 15);
		pestHash.add(tamTablaHash);
		
		final JSpinner tamHashContad = new JSpinner();
		tamHashContad.setToolTipText(messages.getString ("tablaTamTT"));
		tamHashContad.setModel(new SpinnerNumberModel(5, 5, 20, 1));
		tamHashContad.setBounds(341, 115, 45, 22);
		pestHash.add(tamHashContad);
		
		// ventana log hash				
		JPanel panelTextoHash = new JPanel();
		panelTextoHash.setBorder(borde);
		panelTextoHash.setBounds(4, 246, 711, 451);
		pestHash.add(panelTextoHash);
		panelTextoHash.setLayout(null);

		final JTextArea textAreaHash = new JTextArea();
		textAreaHash.setWrapStyleWord(true);
		textAreaHash.setEditable(false);
		textAreaHash.setLineWrap(true);
		textAreaHash.setBounds(12, 248, 457, 238);
		pestHash.add(textAreaHash);
		
		JScrollPane scrollHash = new JScrollPane(textAreaHash);
		scrollHash.setBounds(8, 18, 461, 255);
		panelTextoHash.add(scrollHash);
		
		final JButton buttonLimpHash = new JButton("");
		buttonLimpHash.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		buttonLimpHash.setBounds(453, 0, 16, 16);
		panelTextoHash.add(buttonLimpHash);
		buttonLimpHash.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
		buttonLimpHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textAreaHash.setText("");
				logger.info("Se ha limpiado las preguntas de tabla HAsh ");	
			}
		});
				
		
		final JButton botonGenerarHash = new JButton();
		botonGenerarHash.setText(messages.getString ("generar"));

		botonGenerarHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Preguntas.ponerPropiedades("hashSize",tamHashContad.getValue().toString());
				
						
					PreguntaTablaHash.preguntaTablaHash((int)contHashNumPreg.getValue());	
					textAreaHash.setText(textAreaHash.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
					logger.info("Generada pregunta de tabla Hash ");	
					JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		});
		botonGenerarHash.setBounds(185, 206, 99, 23);
		pestHash.add(botonGenerarHash);
		
		final JTextField txtSemillaHash = new JTextField();
		txtSemillaHash.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaHash.setVisible(false);
		txtSemillaHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSemillaHash.setText(null); 
			}
		});
		txtSemillaHash.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemillaHash.setText(messages.getString ("semilla"));
		txtSemillaHash.setBounds(43, 206, 116, 22);
		pestHash.add(txtSemillaHash);
		txtSemillaHash.setColumns(10);
		
		final JButton btnRecuperarHash = new JButton();
		btnRecuperarHash.setText(messages.getString ("recuperar"));
		btnRecuperarHash.setVisible(false);
		btnRecuperarHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String semilla = txtSemillaHash.getText();
				try {	
					if(txtSemillaHash.getText().length()>0){
						for(int i=0; i<txtSemillaHash.getText().length();i++){
							char c = txtSemillaHash.getText().charAt(i);
							if(Character.isDigit(c))
								SemillaHash=true;
							else{
								SemillaHash=false;
								break;
							}									
						}
						
					}
					else
						SemillaHash=false;				
					
					
					if(SemillaHash){
						Preguntas.ponerPropiedades("hashSize",tamHashContad.getValue().toString());
						Preguntas.ponerPropiedades("seedHash",semilla);
						PreguntaTablaHash.preguntaTablaHash(1);
						logger.info("Recuperar pregunta de tabla Hash ");	
						textAreaHash.setText(textAreaHash.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

						Preguntas.ponerPropiedades("seedHash","-1");
						
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
						logger.info("Pregunta pila recuperada correctamente ");	
					}else
						JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	

					txtSemillaHash.setText(messages.getString ("semilla"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRecuperarHash.setBounds(265, 205, 121, 25);
		pestHash.add(btnRecuperarHash);
		
			
		
// INSERCION DIRECTA
		
		JPanel pestID = new JPanel();
		tabbedPane.addTab("", null, pestID, null);
		tabbedPane.setTitleAt(3, messages.getString ("id"));
		pestID.setLayout(null);
		
		final JLabel IDNumPre = new JLabel();
		IDNumPre.setText(messages.getString ("numPreg"));
		IDNumPre.setBounds(43, 67, 241, 15);
		pestID.add(IDNumPre);
		
		final JSpinner contIDNumPre = new JSpinner();
		contIDNumPre.setToolTipText(messages.getString ("numPregToolTip"));
		contIDNumPre.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		contIDNumPre.setBounds(341, 63, 45, 22);
		pestID.add(contIDNumPre);
		
		final JLabel IDnumElem = new JLabel();
		IDnumElem.setText(messages.getString ("numElem"));
		IDnumElem.setBounds(43, 119, 241, 15);
		pestID.add(IDnumElem);
		
		final JSpinner contIDNumElem = new JSpinner();//IDSize
		contIDNumElem.setToolTipText(messages.getString ("idNumElemOrdenar"));
		contIDNumElem.setModel(new SpinnerNumberModel(9, 9, 20, 1));
		contIDNumElem.setBounds(341, 115, 45, 22);
		pestID.add(contIDNumElem);
		
		// ventana log ID				
				JPanel panelTextoID = new JPanel();
				panelTextoID.setBorder(borde);
				panelTextoID.setBounds(4, 246, 711, 451);
				pestID.add(panelTextoID);
				panelTextoID.setLayout(null);

				final JTextArea textAreaID = new JTextArea();
				textAreaID.setWrapStyleWord(true);
				textAreaID.setEditable(false);
				textAreaID.setLineWrap(true);
				textAreaID.setBounds(12, 248, 457, 238);
				pestID.add(textAreaID);
				
				JScrollPane scrollID = new JScrollPane(textAreaID);
				scrollID.setBounds(8, 18, 461, 255);
				panelTextoID.add(scrollID);
				
				final JButton buttonLimpID = new JButton("");
				buttonLimpID.setToolTipText(messages.getString ("limpiarCuadroPregs"));
				buttonLimpID.setBounds(453, 0, 16, 16);
				panelTextoID.add(buttonLimpID);
				buttonLimpID.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
				buttonLimpID.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						textAreaID.setText("");
						logger.info("Limpiar preguntas de insercion directa ");	
					}
				});
						
				
		final JButton botonGenerarID = new JButton();
		botonGenerarID.setText(messages.getString ("generar"));
		botonGenerarID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				try {
					Preguntas.ponerPropiedades("IDSize",contIDNumElem.getValue().toString());
					
					
					PreguntaInsercionDirecta.preguntaInsercion((int)contIDNumPre.getValue());
					logger.info("Generada pregunta de inserción directa ");	
					textAreaID.setText(textAreaID.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

					JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					logger.info("Error al utilizar el fichero propiedades ");	
				}				
			}
		});
		botonGenerarID.setBounds(185, 206, 99, 23);
		pestID.add(botonGenerarID);
		
		final JTextField txtSemillaID = new JTextField();
		txtSemillaID.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaID.setVisible(false);
		txtSemillaID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSemillaID.setText(null); 
			}
		});
		txtSemillaID.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemillaID.setText(messages.getString ("semilla"));
		txtSemillaID.setBounds(43, 206, 116, 22);
		pestID.add(txtSemillaID);
		txtSemillaID.setColumns(10);
		
		final JButton btnRecuperarID = new JButton();
		btnRecuperarID.setText(messages.getString ("recuperar"));
		btnRecuperarID.setVisible(false);
		btnRecuperarID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String semilla = txtSemillaID.getText();
				try {
					if(txtSemillaID.getText().length()>0){
						for(int i=0; i<txtSemillaID.getText().length();i++){
							char c = txtSemillaID.getText().charAt(i);
							if(Character.isDigit(c))
								SemillaID=true;
							else{
								SemillaID=false;
								break;
							}									
						}						
					}else
						SemillaID=false;
					
					if(SemillaID){
						Preguntas.ponerPropiedades("IDSize",contIDNumElem.getValue().toString());
						Preguntas.ponerPropiedades("seedID",semilla);
												
				
						PreguntaInsercionDirecta.preguntaInsercion(1);
						textAreaID.setText(textAreaID.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

						Preguntas.ponerPropiedades("seedID","-1");
						logger.info("Recuperar pregunta de inserción directa");	
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
					}else
						JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	

					txtSemillaID.setText(messages.getString ("semilla"));
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRecuperarID.setBounds(265, 205, 121, 25);
		pestID.add(btnRecuperarID);
	
			
		
// SELECCION DIRECTA		
		JPanel pestSD = new JPanel();
		tabbedPane.addTab("", null, pestSD, null);
		tabbedPane.setTitleAt(4, messages.getString ("sd"));
		pestSD.setLayout(null);

		
		final JLabel SDNumPre = new JLabel();
		SDNumPre.setText(messages.getString ("numPreg"));
		SDNumPre.setBounds(43, 67, 241, 15);
		pestSD.add(SDNumPre);
		
		final JSpinner contSDNumPre = new JSpinner();
		contSDNumPre.setToolTipText(messages.getString ("numPregToolTip"));
		contSDNumPre.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		contSDNumPre.setBounds(341, 63, 45, 22);
		pestSD.add(contSDNumPre);
		
		final JLabel SDnumElem = new JLabel();
		SDnumElem.setText(messages.getString ("numElem"));
		SDnumElem.setBounds(43, 119, 241, 15);
		pestSD.add(SDnumElem);
		
		final JSpinner contSDNumElem = new JSpinner();//SDSize
		contSDNumElem.setToolTipText(messages.getString ("sdNumElemOrdenar"));
		contSDNumElem.setModel(new SpinnerNumberModel(9, 9, 20, 1));
		contSDNumElem.setBounds(341, 115, 45, 22);
		pestSD.add(contSDNumElem);
		
		// ventana log SD				
		JPanel panelTextoSD = new JPanel();
		panelTextoSD.setBorder(borde);
		panelTextoSD.setBounds(4, 246, 711, 451);
		pestSD.add(panelTextoSD);
		panelTextoSD.setLayout(null);

		final JTextArea textAreaSD = new JTextArea();
		textAreaSD.setWrapStyleWord(true);
		textAreaSD.setEditable(false);
		textAreaSD.setLineWrap(true);
		textAreaSD.setBounds(12, 248, 457, 238);
		pestSD.add(textAreaSD);
		
		JScrollPane scrollSD = new JScrollPane(textAreaSD);
		scrollSD.setBounds(8, 18, 461, 255);
		panelTextoSD.add(scrollSD);
		
		final JButton buttonLimpSD = new JButton("");
		buttonLimpSD.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		buttonLimpSD.setBounds(453, 0, 16, 16);
		panelTextoSD.add(buttonLimpSD);
		buttonLimpSD.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
		buttonLimpSD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textAreaSD.setText("");
				logger.info("Limpiar de preguntas de seleccion directa ");	
			}
		});
				
		
		final JButton botonGenerarSD = new JButton();
		botonGenerarSD.setText(messages.getString ("generar"));
		botonGenerarSD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Preguntas.ponerPropiedades("SDSize",contSDNumElem.getValue().toString());
					
					
					
					PreguntaSeleccionDirecta.preguntaSeleccion((int)contSDNumPre.getValue());
					logger.info("Generada la pregunta de seleccion directa ");	
					textAreaSD.setText(textAreaSD.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

					JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					logger.info("Error al leer el fichero de propiedades ");	
				}	
			}
		});
		botonGenerarSD.setBounds(185, 206, 99, 23);
		pestSD.add(botonGenerarSD);
		
		final JTextField txtSemillaSD = new JTextField();
		txtSemillaSD.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaSD.setVisible(false);
		txtSemillaSD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSemillaSD.setText(null); 
			}
		});
		txtSemillaSD.setHorizontalAlignment(SwingConstants.CENTER);
		txtSemillaSD.setText(messages.getString ("semilla"));
		txtSemillaSD.setBounds(43, 206, 116, 22);
		pestSD.add(txtSemillaSD);
		txtSemillaSD.setColumns(10);
		
		final JButton btnRecuperarSD = new JButton();
		btnRecuperarSD.setText(messages.getString ("recuperar"));
		btnRecuperarSD.setVisible(false);
		btnRecuperarSD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String semilla = txtSemillaSD.getText();
				try {
					if(txtSemillaSD.getText().length()>0){
						for(int i=0; i<txtSemillaSD.getText().length();i++){
							char c = txtSemillaSD.getText().charAt(i);
							if(Character.isDigit(c))
								SemillaSD=true;
							else{
								SemillaSD=false;
								break;
							}									
						}						
					}else
						SemillaSD=false;
					
					if(SemillaSD){
						Preguntas.ponerPropiedades("SDSize",contSDNumElem.getValue().toString());
						Preguntas.ponerPropiedades("seedSD",semilla);					
						
					
	
						PreguntaSeleccionDirecta.preguntaSeleccion(1);
						textAreaSD.setText(textAreaSD.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

						Preguntas.ponerPropiedades("seedSD","-1");
						logger.info("Recuperar pregunta de seleccion directa ");	
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
					}else
						JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	
					txtSemillaSD.setText(messages.getString ("semilla"));						
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRecuperarSD.setBounds(265, 205, 121, 25);
		pestSD.add(btnRecuperarSD);
		
		
		
// ABB		
		JPanel pestABB = new JPanel();
		tabbedPane.addTab("", null, pestABB, null);
		tabbedPane.setTitleAt(5, messages.getString ("abb"));
		pestABB.setLayout(null);
		
		final JTabbedPane subpanelesABB = new JTabbedPane(JTabbedPane.TOP);
		subpanelesABB.setBounds(0, 0, 715, 750);
		pestABB.add(subpanelesABB);
//ADD---------------	
		JPanel panelAdd = new JPanel();
		subpanelesABB.addTab("", null, panelAdd, null);
		subpanelesABB.setTitleAt(0, messages.getString ("addAnadirNodos"));
		subpanelesABB.setEnabledAt(0, true);
		panelAdd.setLayout(null);
		
		final JLabel AddNumPre = new JLabel();
		AddNumPre.setText(messages.getString ("numPreg"));		
		AddNumPre.setBounds(34, 20, 250, 15);
		panelAdd.add(AddNumPre);
		
		final JLabel AddNodIni = new JLabel();
		AddNodIni.setText(messages.getString ("abbNodInic"));
		AddNodIni.setBounds(34, 60, 250, 15);
		panelAdd.add(AddNodIni);
		
		final JLabel AddNodAdd = new JLabel();
		AddNodAdd.setText(messages.getString ("addNodosAanadir"));
		AddNodAdd.setBounds(34, 100, 250, 15);
		panelAdd.add(AddNodAdd);
		
		final JSpinner contABBAddNumPreg = new JSpinner();
		contABBAddNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
		contABBAddNumPreg.setBounds(341, 20, 45, 22);
		contABBAddNumPreg.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		panelAdd.add(contABBAddNumPreg);
			
			final JSpinner AddContaIni = new JSpinner();//treeAddSize
			AddContaIni.setToolTipText(messages.getString ("abbTamInicTT"));
			AddContaIni.setBounds(341, 60, 45, 22);
			AddContaIni.setModel(new SpinnerNumberModel(5, 5, 15, 1));
			panelAdd.add(AddContaIni);
			
			final JSpinner AddContaAdd = new JSpinner();//insertionsNumberBST
			AddContaAdd.setToolTipText(messages.getString ("addNodosAanadirTT"));
			AddContaAdd.setBounds(341, 100, 45, 22);
			AddContaAdd.setModel(new SpinnerNumberModel(1, 1, 15, 1));
			panelAdd.add(AddContaAdd);
			
			// ventana log abb add				
						JPanel panelTextoABBAdd = new JPanel();
						panelTextoABBAdd.setBorder(borde);
						panelTextoABBAdd.setBounds(4, 215, 665, 394);
						panelAdd.add(panelTextoABBAdd);
						panelTextoABBAdd.setLayout(null);

						final JTextArea textAreaABBAdd = new JTextArea();
						textAreaABBAdd.setWrapStyleWord(true);
						textAreaABBAdd.setEditable(false);
						textAreaABBAdd.setLineWrap(true);
						textAreaABBAdd.setBounds(12, 248, 457, 238);
						panelAdd.add(textAreaABBAdd);
						
						JScrollPane scrollABBAdd = new JScrollPane(textAreaABBAdd);
						scrollABBAdd.setBounds(8, 18, 459, 261);
						panelTextoABBAdd.add(scrollABBAdd);
						
						final JButton buttonLimpABBAdd = new JButton("");
						buttonLimpABBAdd.setToolTipText(messages.getString ("limpiarCuadroPregs"));
						buttonLimpABBAdd.setBounds(453, 0, 16, 16);
						panelTextoABBAdd.add(buttonLimpABBAdd);
						buttonLimpABBAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
						buttonLimpABBAdd.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								textAreaABBAdd.setText("");
							}
						});
						
						final JLabel ABBTipoDeEnunciadoAdd = new JLabel();
						ABBTipoDeEnunciadoAdd.setText(messages.getString ("mostrar"));
						ABBTipoDeEnunciadoAdd.setBounds(34, 140, 250, 16);
						panelAdd.add(ABBTipoDeEnunciadoAdd);
						
						final JComboBox<String> ABBTipoDespAdd = new JComboBox<String>();
						ABBTipoDespAdd.setModel(new DefaultComboBoxModel<String>(new String[] {messages.getString ("imagen"), messages.getString ("recorrido")}));
						ABBTipoDespAdd.setBounds(204, 140, 182, 22);
						panelAdd.add(ABBTipoDespAdd);
						
			
			final JButton botonAdd = new JButton();
			botonAdd.setText(messages.getString ("generar"));
			botonAdd.setBounds(185, 179, 99, 23);
			botonAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Preguntas.ponerPropiedades("treeAddSize",AddContaIni.getValue().toString());
						Preguntas.ponerPropiedades("insertionsNumberBST",AddContaAdd.getValue().toString());

						Preguntas.ponerPropiedades("imagenArbol",Integer.toString(ABBTipoDespAdd.getSelectedIndex()));
						// El ABBaddTipoDesp.getSelectedIndex() manda 0 para la imagen y 1 para rec. en anchura
						PreguntaArbolBinarioBusqueda.preguntaArbol( 3, (int)contABBAddNumPreg.getValue());
						logger.info("Generada la pregunta añadir arboles binarios");	
						textAreaABBAdd.setText(textAreaABBAdd.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
						
						JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			panelAdd.add(botonAdd);
			
			final JTextField txtSemillaABBadd = new JTextField();
			txtSemillaABBadd.setToolTipText(messages.getString ("introSemilla"));
			txtSemillaABBadd.setVisible(false);
			txtSemillaABBadd.setBounds(34, 179, 116, 22);
			txtSemillaABBadd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtSemillaABBadd.setText(null); 
				}
			});
			txtSemillaABBadd.setHorizontalAlignment(SwingConstants.CENTER);
			txtSemillaABBadd.setText(messages.getString ("semilla"));
			panelAdd.add(txtSemillaABBadd);
			txtSemillaABBadd.setColumns(10);
			
			final JButton btnRecuperarABBadd = new JButton();
			btnRecuperarABBadd.setText(messages.getString ("recuperar"));
			btnRecuperarABBadd.setVisible(false);
			btnRecuperarABBadd.setBounds(265, 179, 121, 25);
			btnRecuperarABBadd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String semilla = txtSemillaABBadd.getText();
					try {
						if(txtSemillaABBadd.getText().length()>0){
							for(int i=0; i<txtSemillaABBadd.getText().length();i++){
								char c = txtSemillaABBadd.getText().charAt(i);
								if(Character.isDigit(c))
									SemillaABBadd=true;
								else{
									SemillaABBadd=false;
									break;
								}									
							}						
						}else
							SemillaABBadd=false;
						
						if(SemillaABBadd){
							Preguntas.ponerPropiedades("treeAddSize",AddContaIni.getValue().toString());
							Preguntas.ponerPropiedades("insertionsNumberBST",AddContaAdd.getValue().toString());
							Preguntas.ponerPropiedades("seedBSTAdd",semilla);		
						
							Preguntas.ponerPropiedades("imagenArbol",Integer.toString(ABBTipoDespAdd.getSelectedIndex()));
							PreguntaArbolBinarioBusqueda.preguntaArbol( 3,1);
							textAreaABBAdd.setText(textAreaABBAdd.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
							logger.info("Recuperada pregunta de añadir arboles binarios ");	
							Preguntas.ponerPropiedades("seedBSTAdd","-1");
							JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));

						}else
							JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	
;						txtSemillaABBadd.setText(messages.getString ("semilla"));
							
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			panelAdd.add(btnRecuperarABBadd);
			
			
			
		
					
			
			
			
			
//DER----------------
			JPanel panelDer = new JPanel();
			subpanelesABB.addTab("", null, panelDer, null);
			subpanelesABB.setTitleAt(1,messages.getString ("derBorrarNodos"));
			panelDer.setLayout(null);

			final JLabel DerNumPre = new JLabel();
			DerNumPre.setText(messages.getString ("numPreg"));
			DerNumPre.setBounds(34, 20, 250, 15);
			panelDer.add(DerNumPre);

			final JLabel DerNodIni = new JLabel();
			DerNodIni.setText(messages.getString ("abbNodInic"));
			DerNodIni.setBounds(34, 60, 250, 15);
			panelDer.add(DerNodIni);

			final JLabel DerNodAdd = new JLabel();
			DerNodAdd.setText(messages.getString ("abbNodosABorrar"));
			DerNodAdd.setBounds(34, 100, 250, 15);
			panelDer.add(DerNodAdd);

			final JSpinner contABBDerNumPreg = new JSpinner();
			contABBDerNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
			contABBDerNumPreg.setModel(new SpinnerNumberModel(1, 1, 15, 1));
			contABBDerNumPreg.setBounds(341, 20, 45, 22);
			panelDer.add(contABBDerNumPreg);

			final JSpinner DerContaIni = new JSpinner();//treeDerSize
			 DerContaIni.setToolTipText(messages.getString ("abbTamInicTT"));
			DerContaIni.setModel(new SpinnerNumberModel(10, 10, 30, 1));
			DerContaIni.setBounds(341, 60, 45, 22);
			panelDer.add(DerContaIni);

			final JSpinner DerContaBorr = new JSpinner();//extractionsNumberBSTDer
			DerContaBorr .setToolTipText(messages.getString ("abbNodosABorrarTT"));
			DerContaBorr.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			DerContaBorr.setBounds(341, 100, 45, 22);
			panelDer.add(DerContaBorr);

			// ventana log abb der				
			JPanel panelTextoABBder = new JPanel();
			panelTextoABBder.setBorder(borde);
			panelTextoABBder.setBounds(4, 215, 665, 394);
			panelDer.add(panelTextoABBder);
			panelTextoABBder.setLayout(null);

			final JTextArea textAreaABBder = new JTextArea();
			textAreaABBder.setWrapStyleWord(true);
			textAreaABBder.setEditable(false);
			textAreaABBder.setLineWrap(true);
			textAreaABBder.setBounds(12, 248, 457, 238);
			panelDer.add(textAreaABBder);
			
			JScrollPane scrollABBder = new JScrollPane(textAreaABBder);
			scrollABBder.setBounds(8, 18, 459, 261);
			panelTextoABBder.add(scrollABBder);
			
			final JButton buttonLimpABBder = new JButton("");
			buttonLimpABBder.setToolTipText(messages.getString ("limpiarCuadroPregs"));
			buttonLimpABBder.setBounds(453, 0, 16, 16);
			panelTextoABBder.add(buttonLimpABBder);
			buttonLimpABBder.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
			buttonLimpABBder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					textAreaABBder.setText("");
				}
			});
						
			final JLabel ABBTipoDeEnunciadoDer = new JLabel();
			ABBTipoDeEnunciadoDer.setText(messages.getString ("mostrar"));
			ABBTipoDeEnunciadoDer.setBounds(34, 140, 250, 16);
			panelDer.add(ABBTipoDeEnunciadoDer);
			
			final JComboBox<String> ABBTipoDespDer = new JComboBox<String>();
			ABBTipoDespDer.setModel(new DefaultComboBoxModel<String>(new String[] {messages.getString ("imagen"),messages.getString ("recorrido")}));
			ABBTipoDespDer.setBounds(204, 140, 182, 22);
			panelDer.add(ABBTipoDespDer);
			
			
			final JButton botonDer = new JButton();
			botonDer.setText(messages.getString ("generar"));
			botonDer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						if(Integer.parseInt(DerContaIni.getValue().toString()) > Integer.parseInt(DerContaBorr.getValue().toString())){
							Preguntas.ponerPropiedades("treeDerSize",DerContaIni.getValue().toString());
							Preguntas.ponerPropiedades("extractionsNumberBSTDer",DerContaBorr.getValue().toString());
							
							Preguntas.ponerPropiedades("imagenArbol",Integer.toString(ABBTipoDespDer.getSelectedIndex()));
							PreguntaArbolBinarioBusqueda.preguntaArbol( 1, (int)contABBDerNumPreg.getValue() );

							textAreaABBder.setText(textAreaABBder.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

							JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));

						}
						else
							JOptionPane.showMessageDialog(null,messages.getString ("paramIncorrect"));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}

				}
			});
			botonDer.setBounds(185, 179, 99, 23);
			panelDer.add(botonDer);
			
			final JTextField txtSemillaABBder = new JTextField();
			txtSemillaABBder.setToolTipText(messages.getString ("introSemilla"));
			txtSemillaABBder.setVisible(false);
			txtSemillaABBder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtSemillaABBder.setText(null); 
				}
			});
			txtSemillaABBder.setHorizontalAlignment(SwingConstants.CENTER);
			txtSemillaABBder.setText(messages.getString ("semilla"));
			txtSemillaABBder.setBounds(34, 179, 116, 22);
			panelDer.add(txtSemillaABBder);
			txtSemillaABBder.setColumns(10);
			
			final JButton btnRecuperarABBder = new JButton();
			btnRecuperarABBder.setText(messages.getString ("recuperar"));
	    	btnRecuperarABBder.setVisible(false);
			btnRecuperarABBder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String semilla = txtSemillaABBder.getText();
					try {
						if(txtSemillaABBder.getText().length()>0){
							for(int i=0; i<txtSemillaABBder.getText().length();i++){
								char c = txtSemillaABBder.getText().charAt(i);
								if(Character.isDigit(c))
									SemillaABBder=true;
								else{
									SemillaABBder=false;
									break;
								}									
							}	
						}else
							SemillaABBder=false;
						
						if(SemillaABBder){	
							if(Integer.parseInt(DerContaIni.getValue().toString()) > Integer.parseInt(DerContaBorr.getValue().toString())){
								Preguntas.ponerPropiedades("treeDerSize",DerContaIni.getValue().toString());
								Preguntas.ponerPropiedades("extractionsNumberBSTDer",DerContaBorr.getValue().toString());
								Preguntas.ponerPropiedades("seedBSTDer",semilla);
						
								Preguntas.ponerPropiedades("imagenArbol",Integer.toString(ABBTipoDespDer.getSelectedIndex()));
								PreguntaArbolBinarioBusqueda.preguntaArbol( 1,1);
								textAreaABBder.setText(textAreaABBder.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

								Preguntas.ponerPropiedades("seedBSTDer","-1");

								JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
							}
							else
								JOptionPane.showMessageDialog(null,messages.getString ("paramIncorrect"));
						}else
							JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	

						txtSemillaABBder.setText(messages.getString ("semilla"));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			btnRecuperarABBder.setBounds(265, 179, 121, 25);
			panelDer.add(btnRecuperarABBder);

				
						
//IZQ---------------	
			JPanel panelIzq = new JPanel();
			subpanelesABB.addTab("", null, panelIzq, null);
			subpanelesABB.setTitleAt(2, messages.getString ("izqBorrarNodos"));
			panelIzq.setLayout(null);

			final JLabel IzqNumPre = new JLabel();
			IzqNumPre.setText(messages.getString ("numPreg"));
			IzqNumPre.setBounds(34, 20, 250, 15);
			panelIzq.add(IzqNumPre);

			final JLabel IzqNodIni = new JLabel();
			IzqNodIni.setText(messages.getString ("abbNodInic"));
			IzqNodIni.setBounds(34, 60, 250, 15);
			panelIzq.add(IzqNodIni);

			final JLabel IzqNodAdd = new JLabel();
			IzqNodAdd.setText(messages.getString ("abbNodosABorrar"));
			IzqNodAdd.setBounds(34, 100, 232, 15);
			panelIzq.add(IzqNodAdd);

			final JSpinner contABBIzqNumPreg = new JSpinner();
			contABBIzqNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
			contABBIzqNumPreg.setModel(new SpinnerNumberModel(1, 1, 15, 1));
			contABBIzqNumPreg.setBounds(341, 20, 45, 22);
			panelIzq.add(contABBIzqNumPreg);

			final JSpinner IzqContaIni = new JSpinner();//treeIzqSize
			IzqContaIni.setToolTipText(messages.getString ("abbTamInicTT"));
			IzqContaIni.setModel(new SpinnerNumberModel(10, 10, 30, 1));
			IzqContaIni.setBounds(341, 60, 45, 22);
			panelIzq.add(IzqContaIni);

			final JSpinner IzqContaBorr = new JSpinner();//extractionsNumberBSTIzq
			 IzqContaBorr.setToolTipText(messages.getString ("abbNodosABorrarTT"));
			IzqContaBorr.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			IzqContaBorr.setBounds(341, 100, 45, 22);
			panelIzq.add(IzqContaBorr);
			
			// ventana log abb izq			
						JPanel panelTextoABBizq = new JPanel();
						panelTextoABBizq.setBorder(borde);
						panelTextoABBizq.setBounds(4, 215, 665, 394);
						panelIzq.add(panelTextoABBizq);
						panelTextoABBizq.setLayout(null);

						final JTextArea textAreaABBizq = new JTextArea();
						textAreaABBizq.setWrapStyleWord(true);
						textAreaABBizq.setEditable(false);
						textAreaABBizq.setLineWrap(true);
						textAreaABBizq.setBounds(12, 248, 457, 238);
						panelIzq.add(textAreaABBizq);
						
						JScrollPane scrollABBizq = new JScrollPane(textAreaABBizq);
						scrollABBizq.setBounds(8, 18, 459, 261);
						panelTextoABBizq.add(scrollABBizq);
						
						final JButton buttonLimpABBizq = new JButton("");
						buttonLimpABBizq.setToolTipText(messages.getString ("limpiarCuadroPregs"));
						buttonLimpABBizq.setBounds(453, 0, 16, 16);
						panelTextoABBizq.add(buttonLimpABBizq);
						buttonLimpABBizq.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/clear16.png"))));
						buttonLimpABBizq.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								textAreaABBizq.setText("");
							}
						});
									
						final JLabel ABBTipoDeEnunciadoIzq = new JLabel();
						ABBTipoDeEnunciadoIzq.setText(messages.getString ("mostrar"));
						ABBTipoDeEnunciadoIzq.setBounds(34, 140, 250, 16);
						panelIzq.add(ABBTipoDeEnunciadoIzq);
						
						final JComboBox<String> ABBTipoDespIzq = new JComboBox<String>();
						ABBTipoDespIzq.setModel(new DefaultComboBoxModel<String>(new String[] {messages.getString ("imagen"), messages.getString ("recorrido")}));
						ABBTipoDespIzq.setBounds(204, 140, 182, 22);
						panelIzq.add(ABBTipoDespIzq);

			final JButton botonIzq = new JButton();
			botonIzq.setText(messages.getString ("generar"));
			botonIzq.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						if(Integer.parseInt(IzqContaIni.getValue().toString()) > Integer.parseInt(IzqContaBorr.getValue().toString())){
							Preguntas.ponerPropiedades("treeIzqSize",IzqContaIni.getValue().toString());
							Preguntas.ponerPropiedades("extractionsNumberBSTIzq",IzqContaBorr.getValue().toString());
							
							Preguntas.ponerPropiedades("imagenArbol",Integer.toString(ABBTipoDespIzq.getSelectedIndex()));
							PreguntaArbolBinarioBusqueda.preguntaArbol( 2, (int)contABBIzqNumPreg.getValue());

								textAreaABBizq.setText(textAreaABBizq.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");
							
							JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
						}else
							JOptionPane.showMessageDialog(null,messages.getString ("paramIncorrect"));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			botonIzq.setBounds(185, 179, 99, 23);
			panelIzq.add(botonIzq);
			
			final JTextField txtSemillaABBizq = new JTextField();
			txtSemillaABBizq.setToolTipText(messages.getString ("introSemilla"));
	    	txtSemillaABBizq.setVisible(false);
			txtSemillaABBizq.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtSemillaABBizq.setText(null); 
				}
			});
			txtSemillaABBizq.setHorizontalAlignment(SwingConstants.CENTER);
			txtSemillaABBizq.setText(messages.getString ("semilla"));
			txtSemillaABBizq.setBounds(34, 179, 116, 22);
			panelIzq.add(txtSemillaABBizq);
			txtSemillaABBizq.setColumns(10);
			
			final JButton btnRecuperarABBizq = new JButton();
			btnRecuperarABBizq.setText(messages.getString ("recuperar"));		
	    	btnRecuperarABBizq.setVisible(false);
			btnRecuperarABBizq.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String semilla = txtSemillaABBizq.getText();
					try {
						if(txtSemillaABBizq.getText().length()>0){
							for(int i=0; i<txtSemillaABBizq.getText().length();i++){
								char c = txtSemillaABBizq.getText().charAt(i);
								if(Character.isDigit(c))
									SemillaABBizq=true;
								else{
									SemillaABBizq=false;
									break;
								}									
							}	
						}else
							SemillaABBizq=false;
						
						if(SemillaABBizq){
							if(Integer.parseInt(IzqContaIni.getValue().toString()) > Integer.parseInt(IzqContaBorr.getValue().toString())){
								Preguntas.ponerPropiedades("treeIzqSize",IzqContaIni.getValue().toString());
								Preguntas.ponerPropiedades("extractionsNumberBSTIzq",IzqContaBorr.getValue().toString());
								Preguntas.ponerPropiedades("seedBSTIzq",semilla);
								
								Preguntas.ponerPropiedades("imagenArbol",Integer.toString(ABBTipoDespIzq.getSelectedIndex()));
								PreguntaArbolBinarioBusqueda.preguntaArbol( 2,1);
								textAreaABBizq.setText(textAreaABBizq.getText()+FormatosPreguntas.enunciado.toString()+"\n"+"-------------------------------"+ "\n");

								Preguntas.ponerPropiedades("seedBSTIzq","-1");

								JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
							}else
								JOptionPane.showMessageDialog(null,messages.getString ("paramIncorrect"));
						}else
							JOptionPane.showMessageDialog(null,messages.getString ("mensajeIntroSemilla"));	

						txtSemillaABBizq.setText(messages.getString ("semilla"));												
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,messages.getString ("errorPropiedades"),messages.getString ("alerta"),JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			btnRecuperarABBizq.setBounds(265, 179, 121, 25);
			panelIzq.add(btnRecuperarABBizq);
			
			

///////////////////Mostrar/ocultar opciones dependiendo del modo activo			
			
			
			final JMenuItem mntmGeneradorDePreguntas = new JMenuItem();
			mntmGeneradorDePreguntas.setText(messages.getString ("generarNuevasPreg"));
			
			mntmGeneradorDePreguntas.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/forward.png"))));
			mntmGeneradorDePreguntas.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {
			    	botonMontiExtraer.setVisible(true);montiNumPre.setVisible(true);contMontiNumPregExtraer.setVisible(true);
			    	txtSemillaMontiExtraer.setVisible(false);btnRecuperarMontiExtraer.setVisible(false);	
			    	
			    	botonMontiAdd.setVisible(true);montiExtraNumPre.setVisible(true);contMontiNumPregAdd.setVisible(true);
			    	txtSemillaMontiAdd.setVisible(false);btnRecuperarMontiAdd.setVisible(false);
			    	
			    	botonGenerarPila.setVisible(true);PilaNumPre.setVisible(true);contPilaNumPreg.setVisible(true);
			    	txtSemillaPila.setVisible(false);btnRecuperarPila.setVisible(false);	
			    	
			    	botonGenerarHash.setVisible(true);HashNumPre.setVisible(true);contHashNumPreg.setVisible(true);
			    	txtSemillaHash.setVisible(false);btnRecuperarHash.setVisible(false);	
			    	
			    	botonGenerarID.setVisible(true);IDNumPre.setVisible(true);contIDNumPre.setVisible(true);
			    	txtSemillaID.setVisible(false);btnRecuperarID.setVisible(false);			
			    	
			    	botonGenerarSD.setVisible(true);SDNumPre.setVisible(true);contSDNumPre.setVisible(true);
			    	txtSemillaSD.setVisible(false);btnRecuperarSD.setVisible(false);
			    	
			    	botonAdd.setVisible(true);AddNumPre.setVisible(true);contABBAddNumPreg.setVisible(true);
			    	txtSemillaABBadd.setVisible(false);btnRecuperarABBadd.setVisible(false);
			    	
			    	botonDer.setVisible(true);DerNumPre.setVisible(true);contABBDerNumPreg.setVisible(true);
			    	txtSemillaABBder.setVisible(false);btnRecuperarABBder.setVisible(false);
			    	
			    	botonIzq.setVisible(true);IzqNumPre.setVisible(true);contABBIzqNumPreg.setVisible(true);
			    	txtSemillaABBizq.setVisible(false);btnRecuperarABBizq.setVisible(false);	
			    	
			    	lblModoRecupActivo.setVisible(false);
			    	lblModoGeneradorActivo.setVisible(true);
			    }
			}); 
			mnEdicion.add(mntmGeneradorDePreguntas);
			
			final JMenuItem mntmRecuperarPreguntas = new JMenuItem();
			mntmRecuperarPreguntas.setText(messages.getString ("recupPregs"));
			
			mntmRecuperarPreguntas.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/back.png"))));
			mntmRecuperarPreguntas.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {
			    	botonMontiExtraer.setVisible(false);montiNumPre.setVisible(false);contMontiNumPregExtraer.setVisible(false);
			    	txtSemillaMontiExtraer.setVisible(true);btnRecuperarMontiExtraer.setVisible(true);
			    	
			    	botonMontiAdd.setVisible(false);montiExtraNumPre.setVisible(false);contMontiNumPregAdd.setVisible(false);
			    	txtSemillaMontiAdd.setVisible(true);btnRecuperarMontiAdd.setVisible(true);
			    	
			    	botonGenerarPila.setVisible(false);PilaNumPre.setVisible(false);contPilaNumPreg.setVisible(false);
			    	txtSemillaPila.setVisible(true);btnRecuperarPila.setVisible(true);
			    	
			    	botonGenerarHash.setVisible(false);HashNumPre.setVisible(false);contHashNumPreg.setVisible(false);
			    	txtSemillaHash.setVisible(true);btnRecuperarHash.setVisible(true);
			    	
			    	botonGenerarID.setVisible(false);IDNumPre.setVisible(false);contIDNumPre.setVisible(false);
			    	txtSemillaID.setVisible(true);btnRecuperarID.setVisible(true);
			    	
			    	botonGenerarSD.setVisible(false);SDNumPre.setVisible(false);contSDNumPre.setVisible(false);
			    	txtSemillaSD.setVisible(true);btnRecuperarSD.setVisible(true);

			    	botonAdd.setVisible(false);AddNumPre.setVisible(false);contABBAddNumPreg.setVisible(false);
			    	txtSemillaABBadd.setVisible(true);btnRecuperarABBadd.setVisible(true);

			    	botonDer.setVisible(false);DerNumPre.setVisible(false);contABBDerNumPreg.setVisible(false);
			    	txtSemillaABBder.setVisible(true);btnRecuperarABBder.setVisible(true);

			    	botonIzq.setVisible(false);IzqNumPre.setVisible(false);contABBIzqNumPreg.setVisible(false);
			    	txtSemillaABBizq.setVisible(true);btnRecuperarABBizq.setVisible(true);
			    	
			    	lblModoRecupActivo.setVisible(true);
			    	lblModoGeneradorActivo.setVisible(false);
			    	
			    }
			}); 
			mnEdicion.add(mntmRecuperarPreguntas);
			
			JSeparator separator_2 = new JSeparator();
			mnEdicion.add(separator_2);
			
				
					final JMenuItem mntmUnirArchivos = new JMenuItem();
					mntmUnirArchivos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/unir.png"))));

					mntmUnirArchivos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								boolean correcta;
								correcta=FileUtilities.juntarFicheros();
								if(correcta)
									JOptionPane.showMessageDialog(null,messages.getString ("opRealizOk"));
								else
									JOptionPane.showMessageDialog(null,messages.getString ("opNoRealiz"));
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
						
						}
					});
					mntmUnirArchivos.setText(messages.getString ("guardarPregsEn1Fich"));
					mnEdicion.add(mntmUnirArchivos);

					
					
			// Cambiar idioma de la aplicacion
			final JMenu mnCambiarIdiomaApp = new JMenu();
			mnCambiarIdiomaApp.setText(messages.getString ("camIdiomaApp"));
			mnIdioma.add(mnCambiarIdiomaApp);		
			final JMenuItem mntmInglesApp = new JMenuItem();
			final JMenuItem mntmEspaolApp = new JMenuItem();
			mntmEspaolApp.setText("Español");
			
			mntmEspaolApp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/spa.png"))));
			mntmEspaolApp.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {
		    	
			    	aLocale = new Locale ("es", "Es");
			    	messages = ResourceBundle.getBundle ("interfaz.idiomas.MessagesBundle", aLocale);
			    	cambioGenerales(menuArchivo, salir, mntmSeleccionarDirectorio, mnEdicion,
			    			lblModoGeneradorActivo,lblModoRecupActivo,mnAyuda,mntmManualDeUsuario,
			    			mntmAcercaDe,mnIdioma,mnCambiarIdiomaPregs,mnCambiarIdiomaApp,mntmEspaolPregs,
			    			mntmInglesPregs,mntmGeneradorDePreguntas,mntmRecuperarPreguntas,borde, mntmUnirArchivos);			    	
			    	cambioMontiExtraer(tabbedPane,subPanelesMonti,montiNmeroDeExtracciones,contMontiNumElem,
			    			contMontiNumExtra,montiNumPre, contMontiNumPregExtraer,montiNmeroDeElementos,
			    			buttonLimpMontExtra,botonMontiExtraer,txtSemillaMontiExtraer,btnRecuperarMontiExtraer);
			    	cambioMontiAdd(subPanelesMonti,montiExtraNumPre,contMontiNumPregAdd,montiNmeroDeElementosAdd,
			    			montiNmeroDeAdds,contMontiNumElemAdd,contMontiNumAdd,buttonLimpMontAdd,
			    			botonMontiAdd,txtSemillaMontiAdd,txtSemillaMontiAdd,btnRecuperarMontiAdd,txtSemillaMontiAdd);
			    	cambioPila(tabbedPane,PilaNumPre,contPilaNumPreg,tamPila,contTamPila,lblPilaNumRespuestas,
			    			contPilaNumResp,lblProbDeRespuestaCorrecta,contPorcentRespCorrect,
			    			buttonLimpPila ,botonGenerarPila,txtSemillaPila,txtSemillaPila,btnRecuperarPila,txtSemillaPila);
			    	cambioTablaHash(tabbedPane,HashNumPre,contHashNumPreg ,tamTablaHash,
			    			tamHashContad,buttonLimpHash,botonGenerarHash,
			    			txtSemillaHash,txtSemillaHash,btnRecuperarHash,txtSemillaHash);					    	
			    	cambioID(tabbedPane,IDNumPre,contIDNumPre,IDnumElem,contIDNumElem,buttonLimpID,
			    			botonGenerarID,	txtSemillaID,txtSemillaID,btnRecuperarID,txtSemillaID);
			    	cambioSD(tabbedPane,SDNumPre,contSDNumPre,SDnumElem,contSDNumElem,buttonLimpSD,
			    			botonGenerarSD,txtSemillaSD,txtSemillaSD,btnRecuperarSD,txtSemillaSD);
			    	cambioABBAdd(tabbedPane,ABBTipoDeEnunciadoAdd,  ABBTipoDespAdd,subpanelesABB,AddNumPre,AddNodIni,AddNodAdd,contABBAddNumPreg,AddContaIni,
			    			AddContaAdd,buttonLimpABBAdd,botonAdd,txtSemillaABBadd,txtSemillaABBadd,btnRecuperarABBadd,txtSemillaABBadd);					    						    	
			    	cambioABBder(ABBTipoDeEnunciadoDer,ABBTipoDespDer, subpanelesABB,DerNumPre,DerNodIni,DerNodAdd,contABBDerNumPreg,DerContaBorr ,DerContaIni,
			    			buttonLimpABBder,botonDer,txtSemillaABBder,btnRecuperarABBder,txtSemillaABBder,txtSemillaABBder);					    	
			    	cambioABBizq(ABBTipoDeEnunciadoIzq, ABBTipoDespIzq,subpanelesABB,IzqNumPre,IzqNodIni,IzqNodAdd,contABBIzqNumPreg,IzqContaIni,IzqContaBorr,
			    			buttonLimpABBizq,botonIzq,txtSemillaABBizq,	txtSemillaABBizq,btnRecuperarABBizq,txtSemillaABBizq);
			    }
			}); 
			mnCambiarIdiomaApp.add(mntmEspaolApp);
			
			mntmInglesApp.setText("Inglés");
			
			mntmInglesApp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/eng.png"))));
			mntmInglesApp.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {

			    	aLocale = new Locale ("en", "En");
			    	messages = ResourceBundle.getBundle ("interfaz.idiomas.MessagesBundle", aLocale);			    	
			    	cambioGenerales(menuArchivo, salir, mntmSeleccionarDirectorio, mnEdicion,
			    			lblModoGeneradorActivo,lblModoRecupActivo,mnAyuda,mntmManualDeUsuario,
			    			mntmAcercaDe,mnIdioma,mnCambiarIdiomaPregs,mnCambiarIdiomaApp,mntmEspaolPregs,
			    			mntmInglesPregs,mntmGeneradorDePreguntas,mntmRecuperarPreguntas,borde, mntmUnirArchivos);			    	
			    	cambioMontiExtraer(tabbedPane,subPanelesMonti,montiNmeroDeExtracciones,contMontiNumElem,
			    			contMontiNumExtra,montiNumPre, contMontiNumPregExtraer,montiNmeroDeElementos,
			    			buttonLimpMontExtra,botonMontiExtraer,txtSemillaMontiExtraer,btnRecuperarMontiExtraer);
			    	cambioMontiAdd(subPanelesMonti,montiExtraNumPre,contMontiNumPregAdd,montiNmeroDeElementosAdd,
			    			montiNmeroDeAdds,contMontiNumElemAdd,contMontiNumAdd,buttonLimpMontAdd,
			    			botonMontiAdd,txtSemillaMontiAdd,txtSemillaMontiAdd,btnRecuperarMontiAdd,txtSemillaMontiAdd);
			    	cambioPila(tabbedPane,PilaNumPre,contPilaNumPreg,tamPila,contTamPila,lblPilaNumRespuestas,
			    			contPilaNumResp,lblProbDeRespuestaCorrecta,contPorcentRespCorrect,
			    			buttonLimpPila ,botonGenerarPila,txtSemillaPila,txtSemillaPila,btnRecuperarPila,txtSemillaPila);
			    	cambioTablaHash(tabbedPane,HashNumPre,contHashNumPreg ,tamTablaHash,
			    			tamHashContad,buttonLimpHash,botonGenerarHash,
			    			txtSemillaHash,txtSemillaHash,btnRecuperarHash,txtSemillaHash);					    	
			    	cambioID(tabbedPane,IDNumPre,contIDNumPre,IDnumElem,contIDNumElem,buttonLimpID,
			    			botonGenerarID,	txtSemillaID,txtSemillaID,btnRecuperarID,txtSemillaID);
			    	cambioSD(tabbedPane,SDNumPre,contSDNumPre,SDnumElem,contSDNumElem,buttonLimpSD,
			    			botonGenerarSD,txtSemillaSD,txtSemillaSD,btnRecuperarSD,txtSemillaSD);
			    	cambioABBAdd(tabbedPane,ABBTipoDeEnunciadoAdd,  ABBTipoDespAdd,subpanelesABB,AddNumPre,AddNodIni,AddNodAdd,contABBAddNumPreg,AddContaIni,
			    			AddContaAdd,buttonLimpABBAdd,botonAdd,txtSemillaABBadd,txtSemillaABBadd,btnRecuperarABBadd,txtSemillaABBadd);					    						    	
			    	cambioABBder(ABBTipoDeEnunciadoDer, ABBTipoDespDer, subpanelesABB,DerNumPre,DerNodIni,DerNodAdd,contABBDerNumPreg,DerContaBorr ,DerContaIni,
			    			buttonLimpABBder,botonDer,txtSemillaABBder,btnRecuperarABBder,txtSemillaABBder,txtSemillaABBder);					    	
			    	cambioABBizq(ABBTipoDeEnunciadoIzq, ABBTipoDespIzq,subpanelesABB,IzqNumPre,IzqNodIni,IzqNodAdd,contABBIzqNumPreg,IzqContaIni,IzqContaBorr,
			    			buttonLimpABBizq,botonIzq,txtSemillaABBizq,	txtSemillaABBizq,btnRecuperarABBizq,txtSemillaABBizq);
			    	
			    	}
			});
			mnCambiarIdiomaApp.add(mntmInglesApp);								
	}
	/*
	public void cambiarTodosTextos(){
		
	}*/
	
	/**
	 * Método para el cambio de elementos generales de la aplicación.
	 * @param menuArchivo Cambia el texto de Archivo.
	 * @param salir Cambia el texto de salir.
	 * @param mntmSeleccionarDirectorio Cambia el texto de seleccionar directorio.
	 * @param mnEdicion Cambia el texto de edición.
	 * @param lblModoGeneradorActivo Cambia el texto de modo generador de preguntas.
	 * @param lblModoRecupActivo Cambia el texto de modo recuperación de preguntas.
	 * @param mnAyuda Cambia el texto de ayuda.
	 * @param mntmManualDeUsuario Cambia el texto de manual de usuario.
	 * @param mntmAcercaDe Cambia el texto de Acerca de...
	 * @param mnIdioma Cambia el texto de idioma
	 * @param mnCambiarIdiomaPregs Cambia el texto de idioma de preguntas.
	 * @param mnCambiarIdiomaApp Cambia el texto de idioma de aplicación.
	 * @param mntmEspaolPregs Cambia el texto de Español.
	 * @param mntmInglesPregs Cambia el texto de Inglés.
	 * @param mntmGeneradorDePreguntas Cambia el texto de generador de preguntas.
	 * @param mntmRecuperarPreguntas Cambia el texto de recuperar preguntas.
	 * @param borde Cambia el texto del mensaje que aparece encima de la ventana que muestra las pregutas.
	 * @param mntmUnirArchivos Cambia el texto de la opcion de guardar todas las preguntas juntas.
	 */
	public void cambioGenerales(JMenu menuArchivo, JMenuItem salir, JMenuItem mntmSeleccionarDirectorio, 
			JMenu mnEdicion, JLabel lblModoGeneradorActivo, JLabel lblModoRecupActivo, JMenu mnAyuda, 
			JMenuItem mntmManualDeUsuario, JMenuItem mntmAcercaDe, JMenu mnIdioma, 
			JMenu mnCambiarIdiomaPregs, JMenu mnCambiarIdiomaApp, JMenuItem mntmEspaolPregs, 
			JMenuItem mntmInglesPregs, JMenuItem mntmGeneradorDePreguntas, JMenuItem mntmRecuperarPreguntas, TitledBorder borde,JMenuItem mntmUnirArchivos){
		ventana.setTitle(messages.getString ("tituloPrincipal"));
		menuArchivo.setText(messages.getString ("archivo"));
		salir.setText(messages.getString ("salir"));
	/*	JOptionPane.showConfirmDialog(ventana,messages.getString ("confirmacionSalir"),
					        	    "", JOptionPane.YES_NO_OPTION);*/
		mntmSeleccionarDirectorio.setText(messages.getString ("cambiarDir"));
		mnEdicion.setText(messages.getString ("edicion"));
		lblModoGeneradorActivo.setText(messages.getString ("modoGenerarPreguntas"));
		lblModoRecupActivo.setText(messages.getString ("modoRecuperarPreguntas"));
		mnAyuda.setText(messages.getString ("ayuda1"));	
		mntmManualDeUsuario.setText(messages.getString ("ayuda2"));
		mntmAcercaDe.setText(messages.getString ("acerca"));
		mnIdioma.setText(messages.getString ("idioma"));
		mnCambiarIdiomaPregs.setText(messages.getString ("camIdiomaPreg"));
		mnCambiarIdiomaApp.setText(messages.getString ("camIdiomaApp"));
		mntmEspaolPregs.setText(messages.getString ("espanol"));
		mntmInglesPregs.setText(messages.getString ("ingles"));
		mntmGeneradorDePreguntas.setText(messages.getString ("generarNuevasPreg"));
		mntmRecuperarPreguntas.setText(messages.getString ("recupPregs"));
		borde.setTitle(messages.getString ("pregGeneradas"));
		mntmUnirArchivos.setText(messages.getString ("guardarPregsEn1Fich"));
	}
	/**
	 * Método para el cambio de elementos de extracción de elementos de montículo.
	 * @param tabbedPane Cambia el texto de título de tipo de pregunta.
	 * @param subPanelesMonti Cambia el texto de título de subtipo de pregunta.
	 * @param montiNmeroDeExtracciones Cambia el texto de número de extracciones.
	 * @param contMontiNumElem Cambia el texto del contador del número de elementos.
	 * @param contMontiNumExtra Cambia el texto del contador de número de extracciones.
	 * @param montiNumPre Cambia el texto de número de preguntas.
	 * @param contMontiNumPregExtraer Cambia el texto del contador de número de preguntas. 
	 * @param montiNmeroDeElementos Cambia el texto de número de elementos.
	 * @param buttonLimpMontExtra Cambia el texto de limpiar pantalla.
	 * @param botonMontiExtraer Cambia el texto de generar pregunta.
	 * @param txtSemillaMontiExtraer Cambia el texto de semilla.
	 * @param btnRecuperarMontiExtraer Cambia el texto del botón de recuperar pregunta.
	 */
	public void cambioMontiExtraer(JTabbedPane tabbedPane, JTabbedPane subPanelesMonti, JLabel montiNmeroDeExtracciones, 
			JSpinner contMontiNumElem, JSpinner contMontiNumExtra, JLabel montiNumPre, JSpinner contMontiNumPregExtraer, 
			JLabel montiNmeroDeElementos, JButton buttonLimpMontExtra, JButton botonMontiExtraer, 
			JTextField txtSemillaMontiExtraer, JButton btnRecuperarMontiExtraer){
		tabbedPane.setTitleAt(0, messages.getString ("monticulos"));
		subPanelesMonti.setTitleAt(0, messages.getString ("extraerMax"));
		montiNmeroDeExtracciones.setText(messages.getString ("numExtMont"));
		contMontiNumElem.setToolTipText(messages.getString ("montiNumElemToolTip"));
		contMontiNumExtra.setToolTipText(messages.getString ("montiNumExtracToolTip"));		
		montiNumPre.setText(messages.getString ("numPreg"));
		contMontiNumPregExtraer.setToolTipText(messages.getString ("numPregToolTip"));
		montiNmeroDeElementos.setText(messages.getString ("numElem"));
		buttonLimpMontExtra.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonMontiExtraer.setText(messages.getString ("generar"));
		txtSemillaMontiExtraer.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaMontiExtraer.setText(messages.getString ("semilla"));
		btnRecuperarMontiExtraer.setText(messages.getString ("recuperar"));
	}
	/**
	 * Método para el cambio de elementos de adición de elementos de montículo.
	 * @param subPanelesMonti Cambia el texto de título de subtipo de pregunta.
	 * @param montiExtraNumPre Cambia el texto de número de preguntas.
	 * @param contMontiNumPregAdd Cambia el texto del contador de número de preguntas.
	 * @param montiNmeroDeElementosAdd Cambia el texto de número de elementos.
	 * @param montiNmeroDeAdds Cambia el texto de número de elementos a añadir.
	 * @param contMontiNumElemAdd Cambia el texto del contador de número de elementos.
	 * @param contMontiNumAdd Cambia el texto del contador de número de elementos a añadir.
	 * @param buttonLimpMontAdd Cambia el texto de limpiar pantalla.
	 * @param botonMontiAdd Cambia el texto de generar pregunta.
	 * @param txtSemillaMontiAdd Cambia el texto de semilla.
	 * @param txtSemillaMontiAdd2 Cambia el texto de semilla.
	 * @param btnRecuperarMontiAdd Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaMontiAdd3 Cambia el texto de semilla.
	 */
	public void cambioMontiAdd(JTabbedPane subPanelesMonti, JLabel montiExtraNumPre, 
			JSpinner contMontiNumPregAdd, JLabel montiNmeroDeElementosAdd, JLabel montiNmeroDeAdds, 
			JSpinner contMontiNumElemAdd, JSpinner contMontiNumAdd, JButton buttonLimpMontAdd, 
			JButton botonMontiAdd, JTextField txtSemillaMontiAdd, JTextField txtSemillaMontiAdd2, 
			JButton btnRecuperarMontiAdd, JTextField txtSemillaMontiAdd3){
		subPanelesMonti.setTitleAt(1, messages.getString ("montiAddElem"));
		montiExtraNumPre.setText(messages.getString ("numPreg"));	
		contMontiNumPregAdd.setToolTipText(messages.getString ("numPregToolTip"));
		montiNmeroDeElementosAdd.setText(messages.getString ("numElem"));	
		montiNmeroDeAdds.setText(messages.getString ("montiAddElemAdd"));	
		contMontiNumElemAdd.setToolTipText(messages.getString ("montiIntroNumElem"));
		contMontiNumAdd.setToolTipText(messages.getString ("montiIntroNumAdd"));
		buttonLimpMontAdd.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonMontiAdd.setText(messages.getString ("generar"));
		txtSemillaMontiAdd.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaMontiAdd.setText(messages.getString ("semilla"));
		btnRecuperarMontiAdd.setText(messages.getString ("recuperar"));
		txtSemillaMontiAdd.setText(messages.getString ("semilla"));
	}
	/**
	 * Método para el cambio de elementos de pila.
	 * @param tabbedPane Cambia el texto de título de tipo de pregunta.
	 * @param PilaNumPre Cambia el texto de número de preguntas.
	 * @param contPilaNumPreg Cambia el texto del contador de número de preguntas. 
	 * @param tamPila Cambia el texto del tamaño.
	 * @param contTamPila Cambia el texto del contador de tamaño.
	 * @param lblPilaNumRespuestas Cambia el texto de número de respuestas.
	 * @param contPilaNumResp Cambia el texto del conador del número de respuestas.
	 * @param lblProbDeRespuestaCorrecta Cambia el texto de la probabilidad de que una respuesta sea correcta.
	 * @param contPorcentRespCorrect Cambia el texto del contador de la probabilidad de que una respuesta sea correcta.
	 * @param buttonLimpPila Cambia el texto de limpiar pantalla.
	 * @param botonGenerarPila Cambia el texto de generar pregunta.
	 * @param txtSemillaPila Cambia el texto de semilla.
	 * @param txtSemillaPila2 Cambia el texto de semilla.
	 * @param btnRecuperarPila Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaPila3 Cambia el texto de semilla.
	 */
	public void cambioPila(JTabbedPane tabbedPane, JLabel PilaNumPre, JSpinner contPilaNumPreg, 
			JLabel tamPila, JSpinner contTamPila, JLabel lblPilaNumRespuestas, JSpinner contPilaNumResp, 
			JLabel lblProbDeRespuestaCorrecta, JSpinner contPorcentRespCorrect, JButton buttonLimpPila, 
			JButton botonGenerarPila, JTextField txtSemillaPila, 
			JTextField txtSemillaPila2, JButton btnRecuperarPila, JTextField txtSemillaPila3){
		tabbedPane.setTitleAt(1, messages.getString ("pilas"));
		PilaNumPre.setText(messages.getString ("numPreg"));
		contPilaNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
		tamPila.setText(messages.getString ("pilaTam"));
		contTamPila.setToolTipText(messages.getString ("pilaTamTT"));
		lblPilaNumRespuestas.setText(messages.getString ("pilaNumPosResp"));
		contPilaNumResp.setToolTipText(messages.getString ("pilaNumOpc"));
		lblProbDeRespuestaCorrecta.setText(messages.getString ("pilaProbRespOk"));
		contPorcentRespCorrect.setToolTipText(messages.getString ("pilaProbRespOkTT"));
		buttonLimpPila.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonGenerarPila.setText(messages.getString ("generar"));
		txtSemillaPila.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaPila.setText(messages.getString ("semilla"));
		btnRecuperarPila.setText(messages.getString ("recuperar"));
		txtSemillaPila.setText(messages.getString ("semilla"));
	}
	/**
	 * Método para el cambio de elementos de tabla hash.
	 * @param tabbedPane Cambia el texto de título de tipo de pregunta.
	 * @param HashNumPre Cambia el texto de número de preguntas.
	 * @param contHashNumPreg Cambia el texto del contador de número de preguntas. 
	 * @param tamTablaHash  Cambia el texto del tamaño de la tabla.
	 * @param tamHashContad Cambia el texto del contador del tamaño de la tabla.
	 * @param buttonLimpHash Cambia el texto de limpiar pantalla.
	 * @param botonGenerarHash Cambia el texto de generar pregunta.
	 * @param txtSemillaHash Cambia el texto de semilla.
	 * @param txtSemillaHash2 Cambia el texto de semilla.
	 * @param btnRecuperarHash Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaHash3 Cambia el texto de semilla.
	 */
	public void cambioTablaHash(JTabbedPane tabbedPane, JLabel HashNumPre, JSpinner contHashNumPreg, 
			JLabel tamTablaHash, JSpinner tamHashContad, JButton buttonLimpHash, JButton botonGenerarHash, 
			JTextField txtSemillaHash, JTextField txtSemillaHash2, JButton btnRecuperarHash, JTextField txtSemillaHash3){		
		tabbedPane.setTitleAt(2, messages.getString ("tablasHash"));
		HashNumPre.setText(messages.getString ("numPreg"));
		contHashNumPreg .setToolTipText(messages.getString ("numPregToolTip"));
		tamTablaHash.setText(messages.getString ("tablaTam"));
		tamHashContad.setToolTipText(messages.getString ("tablaTamTT"));
		buttonLimpHash.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonGenerarHash.setText(messages.getString ("generar"));
		txtSemillaHash.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaHash.setText(messages.getString ("semilla"));
		btnRecuperarHash.setText(messages.getString ("recuperar"));
		txtSemillaHash.setText(messages.getString ("semilla"));
	}


	/**
	 * Método para el cambio de elementos de inserción directa.
	 * @param tabbedPane Cambia el texto de título de tipo de pregunta.
	 * @param IDNumPre Cambia el texto de número de preguntas.
	 * @param contIDNumPre Cambia el texto del contador de número de preguntas. 
	 * @param IDnumElem Cambia el texto de número de elementos.
	 * @param contIDNumElem Cambia el texto del contador de número de elementos.
	 * @param buttonLimpID Cambia el texto de limpiar pantalla.
	 * @param botonGenerarID Cambia el texto de generar pregunta.
	 * @param txtSemillaID Cambia el texto de semilla.
	 * @param txtSemillaID3 Cambia el texto de semilla.
	 * @param btnRecuperarID Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaID2 Cambia el texto de semilla.
	 */
	public void cambioID(JTabbedPane tabbedPane, JLabel IDNumPre, JSpinner contIDNumPre, JLabel IDnumElem,
			JSpinner contIDNumElem, JButton buttonLimpID, JButton botonGenerarID, JTextField txtSemillaID, 
			JTextField txtSemillaID3, JButton btnRecuperarID, JTextField txtSemillaID2){
		tabbedPane.setTitleAt(3, messages.getString ("id"));
		IDNumPre.setText(messages.getString ("numPreg"));
		contIDNumPre.setToolTipText(messages.getString ("numPregToolTip"));
		IDnumElem.setText(messages.getString ("numElem"));
		contIDNumElem.setToolTipText(messages.getString ("idNumElemOrdenar"));
		buttonLimpID.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonGenerarID.setText(messages.getString ("generar"));
		txtSemillaID.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaID.setText(messages.getString ("semilla"));
		btnRecuperarID.setText(messages.getString ("recuperar"));
		txtSemillaID.setText(messages.getString ("semilla"));
	}
	/**
	 * Método para el cambio de elementos de selección directa.
	 * @param tabbedPane Cambia el texto de título de tipo de pregunta.
	 * @param SDNumPre Cambia el texto de número de preguntas.
	 * @param contSDNumPre Cambia el texto del contador de número de preguntas. 
	 * @param SDnumElem Cambia el texto de número de elementos.
	 * @param contSDNumElem Cambia el texto del contador de número de elementos.
	 * @param buttonLimpSD Cambia el texto de limpiar pantalla.
	 * @param botonGenerarSD Cambia el texto de generar pregunta.
	 * @param txtSemillaSD Cambia el texto de semilla.
	 * @param txtSemillaSD2 Cambia el texto de semilla.
	 * @param btnRecuperarSD Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaSD3 Cambia el texto de semilla.
	 */
	public void cambioSD(JTabbedPane tabbedPane, JLabel SDNumPre, JSpinner contSDNumPre, JLabel SDnumElem, 
			JSpinner contSDNumElem, JButton buttonLimpSD, JButton botonGenerarSD, JTextField txtSemillaSD, 
			JTextField txtSemillaSD2, JButton btnRecuperarSD, JTextField txtSemillaSD3){
		tabbedPane.setTitleAt(4, messages.getString ("sd"));
		SDNumPre.setText(messages.getString ("numPreg"));
		contSDNumPre.setToolTipText(messages.getString ("numPregToolTip"));
		SDnumElem.setText(messages.getString ("numElem"));
		contSDNumElem.setToolTipText(messages.getString ("sdNumElemOrdenar"));
		buttonLimpSD.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonGenerarSD.setText(messages.getString ("generar"));
		txtSemillaSD.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaSD.setText(messages.getString ("semilla"));
		btnRecuperarSD.setText(messages.getString ("recuperar"));
		txtSemillaSD.setText(messages.getString ("semilla"));
	}
	
	/**
	 * Método para el cambio de elementos de Abb añadir nodos.
	 * @param tabbedPane Cambia el texto de título de tipo de pregunta.
	 * @param AddTipoDeEnunciado Cambia el texto del tipo de enunciado a mostrar.
	 * @param ABBaddTipoDesp Cambia el texto de las opciones de tipo de enunciado.
	 * @param subpanelesABB Cambia el texto de título de subtipo de pregunta.
	 * @param AddNumPre Cambia el texto de número de preguntas.
	 * @param AddNodIni Cambia el texto de número de nodos iniciales.
	 * @param AddNodAdd  Cambia el texto de número de nodos a añadir.
	 * @param contABBAddNumPreg Cambia el texto del contador de número de preguntas. 
	 * @param AddContaIni Cambia el texto del contador de número de nodos iniciales.
	 * @param AddContaAdd Cambia el texto del contador de número de nodos a añadir.
	 * @param buttonLimpABBAdd Cambia el texto de limpiar pantalla.
	 * @param botonAdd Cambia el texto de generar pregunta.
	 * @param txtSemillaABBadd Cambia el texto de semilla.
	 * @param txtSemillaABBadd2 Cambia el texto de semilla.
	 * @param btnRecuperarABBadd Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaABBadd3 Cambia el texto de semilla.
	 */
	public void cambioABBAdd(JTabbedPane tabbedPane, JLabel AddTipoDeEnunciado, JComboBox<String> ABBaddTipoDesp, 
			JTabbedPane subpanelesABB, JLabel AddNumPre, 
			JLabel AddNodIni, JLabel AddNodAdd, JSpinner contABBAddNumPreg, JSpinner AddContaIni, 
			JSpinner AddContaAdd, JButton buttonLimpABBAdd, JButton botonAdd, JTextField txtSemillaABBadd, 
			JTextField txtSemillaABBadd2, JButton btnRecuperarABBadd, JTextField txtSemillaABBadd3){
		tabbedPane.setTitleAt(5, messages.getString ("abb"));
		AddTipoDeEnunciado.setText(messages.getString ("mostrar"));
		ABBaddTipoDesp.setModel(new DefaultComboBoxModel<String>(new String[] {messages.getString ("imagen"), messages.getString ("recorrido")}));
		subpanelesABB.setTitleAt(0, messages.getString ("addAnadirNodos"));
		AddNumPre.setText(messages.getString ("numPreg"));	
		AddNodIni.setText(messages.getString ("abbNodInic"));
		AddNodAdd.setText(messages.getString ("addNodosAanadir"));
		contABBAddNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
		AddContaIni.setToolTipText(messages.getString ("abbTamInicTT"));
		AddContaAdd.setToolTipText(messages.getString ("addNodosAanadirTT"));
		buttonLimpABBAdd.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonAdd.setText(messages.getString ("generar"));
		txtSemillaABBadd.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaABBadd.setText(messages.getString ("semilla"));
		btnRecuperarABBadd.setText(messages.getString ("recuperar"));
		txtSemillaABBadd.setText(messages.getString ("semilla"));
	}
	
	/**
	 * Método para el cambio de elementos de ABB borrado derecho.
	 * @param ABBTipoDeEnunciadoDer Cambia el texto del tipo de enunciado a mostrar.
	 * @param ABBTipoDespDer Cambia el texto de las opciones de tipo de enunciado.
	 * @param subpanelesABB Cambia el texto de título de subtipo de pregunta.
	 * @param DerNumPre Cambia el texto de número de preguntas.
	 * @param DerNodIni Cambia el texto de número de nodos iniciales.
	 * @param DerNodAdd  Cambia el texto de número de nodos a borrar.
	 * @param contABBDerNumPreg Cambia el texto del contador de número de preguntas. 
	 * @param DerContaBorr Cambia el texto del contador de número de nodos a borrar.
	 * @param DerContaIni Cambia el texto del contador de número de nodos iniciales.
	 * @param buttonLimpABBder Cambia el texto de limpiar pantalla.
	 * @param botonDer Cambia el texto de generar pregunta.
	 * @param txtSemillaABBder Cambia el texto de semilla.
	 * @param btnRecuperarABBder Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaABBder2 Cambia el texto de semilla.
	 * @param txtSemillaABBder3 Cambia el texto de semilla.
	 */
	public void cambioABBder(JLabel ABBTipoDeEnunciadoDer, JComboBox<String> ABBTipoDespDer,JTabbedPane subpanelesABB, 
			JLabel DerNumPre, JLabel DerNodIni, JLabel DerNodAdd, JSpinner contABBDerNumPreg, JSpinner DerContaBorr, 
			JSpinner DerContaIni, 	JButton buttonLimpABBder, JButton botonDer, JTextField txtSemillaABBder, 
			JButton btnRecuperarABBder, JTextField txtSemillaABBder2, JTextField txtSemillaABBder3){
		ABBTipoDeEnunciadoDer.setText(messages.getString ("mostrar"));
		ABBTipoDespDer.setModel(new DefaultComboBoxModel<String>(new String[] {messages.getString ("imagen"), messages.getString ("recorrido")}));
		subpanelesABB.setTitleAt(1,messages.getString ("derBorrarNodos"));
		DerNumPre.setText(messages.getString ("numPreg"));
		DerNodIni.setText(messages.getString ("abbNodInic"));
		DerNodAdd.setText(messages.getString ("abbNodosABorrar"));
		contABBDerNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
		DerContaIni.setToolTipText(messages.getString ("abbTamInicTT"));
		DerContaBorr .setToolTipText(messages.getString ("abbNodosABorrarTT"));
		buttonLimpABBder.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonDer.setText(messages.getString ("generar"));
		txtSemillaABBder.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaABBder.setText(messages.getString ("semilla"));
		btnRecuperarABBder.setText(messages.getString ("recuperar"));
		txtSemillaABBder.setText(messages.getString ("semilla"));
	}
	/**
	 * Método para el cambio de elementos de ABB borrado izquierdo.
	 * @param ABBTipoDeEnunciadoIzq Cambia el texto del tipo de enunciado a mostrar.
	 * @param ABBTipoDespIzq Cambia el texto de las opciones de tipo de enunciado.
	 * @param subpanelesABB Cambia el texto de título de subtipo de pregunta.
	 * @param IzqNumPre Cambia el texto de número de preguntas.
	 * @param IzqNodIni Cambia el texto de número de nodos iniciales.
	 * @param IzqNodAdd  Cambia el texto de número de nodos a borrar.
	 * @param contABBIzqNumPreg Cambia el texto del contador de número de preguntas. 
	 * @param IzqContaIni Cambia el texto del contador de número de nodos iniciales.
	 * @param IzqContaBorr Cambia el texto del contador de número de nodos a borrar.
	 * @param buttonLimpABBizq Cambia el texto de limpiar pantalla.
	 * @param botonIzq Cambia el texto de generar pregunta.
	 * @param txtSemillaABBizq Cambia el texto de semilla.
	 * @param txtSemillaABBizq2 Cambia el texto de semilla.
	 * @param btnRecuperarABBizq Cambia el texto del botón de recuperar pregunta.
	 * @param txtSemillaABBizq3 Cambia el texto de semilla.
	 */
	public void cambioABBizq(JLabel ABBTipoDeEnunciadoIzq, JComboBox<String> ABBTipoDespIzq, JTabbedPane subpanelesABB, 
			JLabel IzqNumPre, JLabel IzqNodIni, JLabel IzqNodAdd, JSpinner contABBIzqNumPreg, JSpinner IzqContaIni, 
			JSpinner IzqContaBorr, JButton buttonLimpABBizq, JButton botonIzq, JTextField txtSemillaABBizq, 
			JTextField txtSemillaABBizq2, JButton btnRecuperarABBizq, JTextField txtSemillaABBizq3){
		ABBTipoDeEnunciadoIzq.setText(messages.getString ("mostrar"));
		ABBTipoDespIzq.setModel(new DefaultComboBoxModel<String>(new String[] {messages.getString ("imagen"), messages.getString ("recorrido")}));

		subpanelesABB.setTitleAt(2, messages.getString ("izqBorrarNodos"));
		IzqNumPre.setText(messages.getString ("numPreg"));
		IzqNodIni.setText(messages.getString ("abbNodInic"));
		IzqNodAdd.setText(messages.getString ("abbNodosABorrar"));
		contABBIzqNumPreg.setToolTipText(messages.getString ("numPregToolTip"));
		IzqContaIni.setToolTipText(messages.getString ("abbTamInicTT"));
		IzqContaBorr.setToolTipText(messages.getString ("abbNodosABorrarTT"));
		buttonLimpABBizq.setToolTipText(messages.getString ("limpiarCuadroPregs"));
		botonIzq.setText(messages.getString ("generar"));
		txtSemillaABBizq.setToolTipText(messages.getString ("introSemilla"));
		txtSemillaABBizq.setText(messages.getString ("semilla"));
		btnRecuperarABBizq.setText(messages.getString ("recuperar"));	
		txtSemillaABBizq.setText(messages.getString ("semilla"));
	}
}

















