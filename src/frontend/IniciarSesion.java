package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.usuarios.ListaUsuarios;
import frontend.usuarios.FrontAdministrador;
import frontend.usuarios.FrontUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private ListaUsuarios listaUsuarios;
	@SuppressWarnings("unused")
	private IniciarInterfaz ini;
	
	public IniciarSesion(IniciarInterfaz ini) {
		this.ini = ini;
		
		listaUsuarios = new ListaUsuarios();

		setTitle("INICIAR SESION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		//Creacion de los atributos
		lblUsuario = new JLabel("Usuario:");
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		txtUsuario = new JTextField();
		txtContrasenia = new JPasswordField();
		JButton btnIngresar = new JButton("Ingresar");
		
		//Configuracion de cada atributo
		lblUsuario.setBounds(10, 20, 100, 14);
		lblContrasenia.setBounds(10, 60, 100, 14);
		txtUsuario.setBounds(120, 17, 250, 20);
		txtUsuario.setColumns(10);
		txtContrasenia.setBounds(120, 57, 250, 20);		
		btnIngresar.setBounds(286, 88, 89, 23);
		
		/*************************CONFIGURACION DE BOTONES***********************************/
		
		//BOTON INGRESAR 
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Tomamos los textos de las cajas de texto
				String usuario = txtUsuario.getText();
				String contrasenia = String.valueOf(txtContrasenia.getPassword());
				
				//Vamos a verificar si las cajas no estan vacias, si lo estan se marca error
				if("".equals(usuario) || "".equals(contrasenia))
					JOptionPane.showMessageDialog(null, "Campo vacío");
				else {
					//Si las cajas no estan vacias, se van a validar los datos en la clase de la lista de usuarios
					listaUsuarios = new ListaUsuarios();
					int temp = listaUsuarios.iniciarSesion(usuario, contrasenia);
					
					//depende del numero que nos de, es el tipo de usuario que ingreso o si el usuario o la contrasena no se encontro
					//por lo que depende del numero, es la interfaz que vamos a correr, o bien, el dialogo de no poder iniciar sesion
					
					if(temp == 0)
						JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos");
					else {
						if(temp==1) {
							FrontUsuario frontUsuario = new FrontUsuario();
							frontUsuario.setVisible(true);
						}
							
						else {
							FrontAdministrador frontAdministrador = new FrontAdministrador();
							frontAdministrador.setVisible(true);
						}
						
						ini.setVisible(false);
						setVisible(false);
						
					}
				}

			}
		});
		
		
		/**********************************************************/
		
		//Agregar los atributos ya configurados al panel
		contentPane.add(lblUsuario);
		contentPane.add(lblContrasenia);
		contentPane.add(txtUsuario);
		contentPane.add(txtContrasenia);
		contentPane.add(btnIngresar);
	}
}
