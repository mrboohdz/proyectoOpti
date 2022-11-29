package frontend;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.catalogo.CatalogoCliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class IniciarInterfaz extends JFrame {

	//Se crean los atributos de nuestro Frame
	private JPanel contentPane;
	private JButton btnIniciarSesion;
	private JButton btnCatalogo;
	private CatalogoCliente catalogoCliente;
	private IniciarSesion iniciarSesion;

	public static void main(String[] args) {
		IniciarInterfaz interfaz = new IniciarInterfaz();
		interfaz.setVisible(true);
	}


	public IniciarInterfaz() {
		setTitle("BIENVENIDO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		//Se crean las instancias de los atributos y algunos los inicializamos
		iniciarSesion = new IniciarSesion(this);
		btnIniciarSesion = new JButton("<html>Iniciar Sesi\u00F3n</html>");
		btnCatalogo = new JButton("<html>Inventario</html>");
		
		//Configuramos los botones
		btnIniciarSesion.setBounds(10, 30, 180, 65);
		btnCatalogo.setBounds(244, 30, 180, 65);
		
		/***************************CONNFIGURACION DE BOTONES************************************/
		//BOTON INICIAR SESION
		//Se abre la interfaz de inicio de sesion
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion.setVisible(true);
			}
		});
		
		//BOTON CATALOGO
		//Se abre la interfaz del catalogo
		btnCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				catalogoCliente=new CatalogoCliente();
				catalogoCliente.setVisible(true);
			}
		});
		
		/***************************************************************/

		//Agregar los botones al Frame
		contentPane.add(btnIniciarSesion);
		contentPane.add(btnCatalogo);
	}

}
