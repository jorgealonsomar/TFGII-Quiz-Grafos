package interfaz;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

/**
 * Ventana de carga de la aplicación.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class PantallaCargando extends JWindow {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Estructura de la ventana.
	 */
	BorderLayout borderLayout1 = new BorderLayout();
	/**
	 * Imagen.
	 */
  JLabel imageLabel = new JLabel();
  /**
   * JPanel.
   */
  JPanel southPanel = new JPanel();
  /**
   * Estructura inferior.
   */
  FlowLayout southPanelFlowLayout = new FlowLayout();
  /**
   * Barra de progreso.
   */
  JProgressBar progressBar = new JProgressBar();
  /**
   * Imagen.
   */
  ImageIcon imageIcon;

  /**
   * Pantalla de carga.
   * @param imageIcon Icono.
   */
  public PantallaCargando(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
    dibujaVentana();
  }

  /**
   * Método que dibuja la ventana.
   */
  public void dibujaVentana() {
    imageLabel.setIcon(imageIcon);
    this.getContentPane().setLayout(borderLayout1);
    southPanel.setLayout(southPanelFlowLayout);
    southPanel.setBackground(Color.BLACK);
    this.getContentPane().add(imageLabel, BorderLayout.CENTER);
    this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    southPanel.add(progressBar, null);
    this.pack();
  }

  /**
   * Establece el progreso máximo.
   * @param maxProgress Progreso máximo.
   */
  public void setProgresoMax(int maxProgress)
  {
    progressBar.setMaximum(maxProgress);}

  /**
   * Establece el progreso.
   * @param progress Progreso.
   */
  public void setProgreso(int progress)
  {
    final int progreso = progress;
        progressBar.setValue(progreso);}

  /**
   * Establece el progreso con un mensaje.
   * @param message Mensaje.
   * @param progress Progreso.
   */
  public void setProgreso(String message, int progress)
  {
    final int progreso = progress;
    final String theMessage = message;
    setProgreso(progress);
    progressBar.setValue(progreso);
    setMessage(theMessage);  }

  /**
   * Establece el mensaje.
   * @param message Mensaje.
   */
  private void setMessage(String message)
 {
    if (message==null){
      message = "";
      progressBar.setStringPainted(false);}
    else{
      progressBar.setStringPainted(true);}

    progressBar.setString(message); }

  /**
   * Establece la velocidad de carga.
   */
public void velocidadDeCarga(){
    for (int i = 0; i <= 100; i++)
    {
      for (long j=0; j<5000000; ++j)
      {

      }
     setProgreso("" + i, i); 
   
   }
    dispose();}

}


/**
 * Clase principal de carga de la aplicación.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
class PantallaCargandoMain {

	/**
	 * Pantalla.
	 */
	  PantallaCargando screen;

	  /**
	   * Método principal de la clase.
	   */
	  public PantallaCargandoMain() {
	    inicioPantalla();
		screen.velocidadDeCarga();
	  }

	  /**
	   * Inicialización de la pantalla.
	   */
	  private void inicioPantalla() {
		ImageIcon myImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/pantCarga.png")));

	    screen = new PantallaCargando(myImage);
	    screen.setLocationRelativeTo(null);
	    screen.setProgresoMax(100);
	    screen.setVisible(true);
	  }

	  /**
	   * Main de la clase.
	   * @param args Argunmentos de entrada.
	   */
	  public static void main(String[] args)
	  {
	    new PantallaCargandoMain();
	  }
	}