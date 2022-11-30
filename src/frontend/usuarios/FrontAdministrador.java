package frontend.usuarios;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import frontend.catalogo.CatalogoUsuario;
import frontend.cotizacion.Clientes;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrontAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCatalogo;
	private JButton btnClientes;
	private JButton btnUsuarios;
	private JButton btnProvedores;

	public FrontAdministrador() {
		setTitle("BIENVENIDO ADMINISTRADOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Se crean las instancias de los atributos
		btnCatalogo = new JButton("Ver Cat\u00E1logo");
		btnUsuarios = new JButton("Usuarios");
		btnClientes = new JButton("Clientes");
		btnProvedores = new JButton("Provedores");
		
		//Se configura la apariencia de los botones
		btnCatalogo.setBounds(10, 11, 150, 50);
		btnUsuarios.setBounds(10, 77, 150, 50);
		btnClientes.setBounds(203, 11, 150, 50);
		btnProvedores.setBounds(203, 77, 150, 50);
		
		
		/**************************CONFIGURACION DE BOTONES***********************************/
		
		//BOTON CATALOGO
		//Se abre la interfaz del catalogo para los usuarios, que tiene la capacidad de eliminar, modificar y
		//agregar articulos directamente de la base de datos
		btnCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CatalogoUsuario catalogoUsuario = new CatalogoUsuario();
				catalogoUsuario.setVisible(true);
			}
		});
		
		//BOTON CLIENTES
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes coti = new Clientes();
				coti.setVisible(true);
			}
		});
		
		//BOTON USUARIOS
		//Siendo administrador, se tiene la capacidad de agregar, eliminar o modificar la contrasena de los usuarios
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		
		//BOTON PROVEDORES
		btnProvedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedores proveedores = new Proveedores();
				proveedores.setVisible(true);
			}
		});
		
		/*************************************************************/
		
		//Se agregan los botones a nuestro Frame
		contentPane.add(btnCatalogo);
		contentPane.add(btnUsuarios);
		contentPane.add(btnClientes);
		contentPane.add(btnProvedores);
		

	}
}
