package interfaz;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Ventana que muestra información sobre los creadores de la aplicación.
 * @author Pablo Dobarco García y Jesús Javier Rodríguez Terrados.
 */
public class VAcercaDe {
	/**
	 * JDialog de la ventana.
	 */
	JDialog frame = null;

	/**
	 * Método principal de la clase.
	 * @param args Argumentos de entrada.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAcercaDe window = new VAcercaDe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Método que inicializa la ventana.
	 */
	public VAcercaDe() {
		initialize();
	}

	/**
	 * Método que contiene la configuración de la ventana.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setBounds(700, 300, 362, 478);
		frame.setTitle("Acerca de...");
		frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelImagen = new JPanel();
		panelImagen.setBounds(62, 13, 230, 267);
		frame.getContentPane().add(panelImagen);
		
		JLabel img = new JLabel(" ");		
		ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/logoUBU.png")));
		img.setIcon(image);
		img.setSize(135,135);
		img.setLocation(550,20);
		img.setVisible(true);
		panelImagen.add(img); 
		
		JLabel lblNewLabel = new JLabel("Ingeniería Técnica en Informática de Gestión");
		lblNewLabel.setBounds(12, 280, 330, 29);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblJessJavierRodrguez = new JLabel("Jesús Javier Rodríguez Terrados");
		lblJessJavierRodrguez.setBounds(12, 337, 330, 16);
		lblJessJavierRodrguez.setHorizontalAlignment(SwingConstants.CENTER);
		lblJessJavierRodrguez.setFont(new Font("Garamond", Font.BOLD, 14));
		frame.getContentPane().add(lblJessJavierRodrguez);
		
		JLabel lblPabloDobarco = new JLabel("Pablo Dobarco García");
		lblPabloDobarco.setBounds(12, 351, 330, 16);
		lblPabloDobarco.setFont(new Font("Garamond", Font.BOLD, 14));
		lblPabloDobarco.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblPabloDobarco);
		
		JLabel lblAlumnos = new JLabel("Alumnos:");
		lblAlumnos.setBounds(12, 322, 330, 16);
		lblAlumnos.setFont(new Font("Garamond", Font.BOLD, 14));
		frame.getContentPane().add(lblAlumnos);
		
		JLabel lblTutores = new JLabel("Tutores:");
		lblTutores.setBounds(12, 379, 330, 16);
		lblTutores.setFont(new Font("Garamond", Font.BOLD, 14));
		frame.getContentPane().add(lblTutores);
		
		JLabel lblCarlosLpezNozal = new JLabel("Carlos López Nozal");
		lblCarlosLpezNozal.setBounds(12, 411, 330, 16);
		lblCarlosLpezNozal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarlosLpezNozal.setFont(new Font("Garamond", Font.BOLD, 14));
		frame.getContentPane().add(lblCarlosLpezNozal);
		
		JLabel lblJuanJosRodrguez = new JLabel("Juan José Rodríguez Díez");
		lblJuanJosRodrguez.setBounds(12, 395, 330, 16);
		lblJuanJosRodrguez.setHorizontalAlignment(SwingConstants.CENTER);
		lblJuanJosRodrguez.setFont(new Font("Garamond", Font.BOLD, 14));
		frame.getContentPane().add(lblJuanJosRodrguez);
		
		JLabel lblCurso = new JLabel("Curso 2012/2013");
		lblCurso.setBounds(12, 308, 330, 16);
		lblCurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurso.setFont(new Font("Garamond", Font.BOLD, 15));
		frame.getContentPane().add(lblCurso);
	}
}