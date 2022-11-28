package frontend.usuarios;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.catalogo.CatalogoUsuario;
import frontend.cotizacion.Clientes;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrontUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCatalogo;
	private JButton btnClientes;

	public FrontUsuario() {
		setLocationRelativeTo(null);
		setTitle("BIENVENIDO USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 114);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCatalogo = new JButton("Catalogo");
		btnClientes = new JButton("Clientes");
		
		btnCatalogo.setBounds(10, 11, 150, 50);
		btnClientes.setBounds(198, 11, 150, 50);
		
		/*************************************************************/
		//BOTON CATALOGO
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
		
		/*************************************************************/
		
		contentPane.add(btnCatalogo);
		contentPane.add(btnClientes);
		
	}

}
