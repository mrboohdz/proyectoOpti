package frontend.usuarios;


import javax.swing.*;
import javax.swing.table.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import backend.cotizacion.Persona;
import backend.usuarios.*;
import backend.usuarios.TipoUsuario;
import backend.usuarios.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/*Creamos todos los atributos que tendra nuestro Frame*/
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarId;
	private JTextField txtBuscarUsuario;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private JPasswordField txtConfirmarContrasenia;
	private JTextField txtID;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPrivilegio;
	private JLabel lblBuscarUsuario;
	private JButton btnBuscarUsuario;
	private JButton btnBuscarId;
	private JButton btnCargar;
	private JLabel lblIdBusqueda;
	private JLabel lblId;
	private JLabel lblUsuario;
	private JLabel lblPrivilegio;
	private JLabel lblContrasea;
	private JLabel lblConfirmarContrasea;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private DefaultTableCellRenderer center = new DefaultTableCellRenderer();
	private JScrollPane scroll;
	private JButton btnModificarContrasenia;
	private JButton btnModificarUsuario;
	private JButton btnModificarNombre;
	private JButton btnModificarPrivilegio;
	private ListaUsuarios listaUsuarios;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Usuarios() {
		setTitle("USUARIOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*  CREACION DE INSTANCIAS DE LOS ATRIBUTOS E INICIALIZACION  */
		
		table = new JTable();
		scroll = new JScrollPane(table);
		listaUsuarios = new ListaUsuarios();
		CrearTabla(listaUsuarios.getUsuarios());
		lblIdBusqueda = new JLabel("ID: ");
		lblBuscarUsuario = new JLabel("Usuario:");
		lblNombre = new JLabel("Nombre:");
		lblId = new JLabel("ID:");
		lblUsuario = new JLabel("Usuario:");
		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblConfirmarContrasea = new JLabel("<html>Confirmar contrase\u00F1a:</html>");
		lblPrivilegio = new JLabel("Privilegio:");
		txtBuscarId = new JTextField();
		txtBuscarUsuario = new JTextField();
		txtNombre = new JTextField();
		txtUsuario = new JTextField();
		txtID = new JTextField();
		txtContrasenia = new JPasswordField();
		txtConfirmarContrasenia = new JPasswordField();
		btnBuscarId = new JButton("Buscar");
		btnCargar = new JButton("Recargar Tabla");
		btnBuscarUsuario = new JButton("Buscar");
		btnLimpiar = new JButton("Limpiar");
		btnModificarContrasenia = new JButton("<html>Modificar Contrase\u00F1a</html>");
		btnModificarContrasenia.setEnabled(false);
		btnModificarUsuario = new JButton("<html>Modificar Usuario</html>");
		btnModificarUsuario.setEnabled(false);
		btnModificarNombre = new JButton("<html>Modificar Nombre</html>");
		btnModificarNombre.setEnabled(false);
		btnModificarPrivilegio = new JButton("<html>Modificar Privilegio</html>");
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");
		
		cbPrivilegio = new JComboBox();
		
		/*  Se configura cada atributo como queremos que se vea  */
		scroll.setBounds(10, 11, 480, 250);
		scroll.setVisible(true);
		lblIdBusqueda.setBounds(10, 306, 80, 23);
		lblBuscarUsuario.setBounds(10, 340, 80, 23);
		lblNombre.setBounds(520, 30, 100, 14);
		lblId.setBounds(520, 55, 100, 14);
		lblUsuario.setBounds(520, 80, 100, 14);
		lblContrasea.setBounds(520, 133, 100, 14);
		lblConfirmarContrasea.setBounds(520, 158, 100, 33);
		lblPrivilegio.setBounds(520, 105, 100, 14);
		txtBuscarId.setForeground(Color.BLACK);
		txtBuscarId.setBackground(Color.WHITE);
		txtBuscarId.setBounds(107, 306, 250, 23);	
		txtBuscarUsuario.setForeground(Color.BLACK);
		txtBuscarUsuario.setBackground(Color.WHITE);
		txtBuscarUsuario.setBounds(107, 340, 250, 23);
		
		txtNombre.setBounds(600, 27, 200, 20);
		txtNombre.setColumns(10);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(600, 77, 200, 20);
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(600, 52, 100, 20);
		txtContrasenia.setBounds(600, 130, 200, 20);
		txtConfirmarContrasenia.setBounds(600, 171, 200, 20);
		btnBuscarId.setToolTipText("B\u00FAsqueda por ID");
		btnBuscarId.setBounds(390, 306, 100, 23);
		btnCargar.setToolTipText("Actualiza la tabla");
		btnCargar.setBounds(107, 272, 250, 23);
		btnBuscarUsuario.setToolTipText("B\u00FAsqueda por ID");
		btnBuscarUsuario.setBounds(390, 340, 100, 23);
		btnLimpiar.setBounds(638, 306, 89, 23);
		btnAgregar.setToolTipText("<html>Puedes crear un nuevo art\u00EDculo desde cero o tomar otro como base, <br>al finalizar de llenar los datos, presiona aqui</html>");
		btnAgregar.setBounds(638, 272, 89, 23);
		btnEliminar.setToolTipText("Elimina el articulo elegido");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(750, 272, 89, 23);
		btnModificarContrasenia.setBounds(520, 215, 89, 46);
		btnModificarUsuario.setBounds(638, 215, 89, 46);
		btnModificarNombre.setBounds(750, 215, 89, 46);
		btnModificarPrivilegio.setEnabled(false);
		btnModificarPrivilegio.setBounds(520, 272, 89, 46);
		
		cbPrivilegio.setModel(new DefaultComboBoxModel(TipoUsuario.values()));
		cbPrivilegio.setBounds(600, 101, 200, 22);
		
		/******************************   **************************************/
		//OPCION DE TABLA
		//Se llenan los textos con la informacion que hay en la tabla cada que se presiona una fila
		//Tambien se habilitan los botones de eliminar y todas mas modificaciones
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				
				txtID.setText(table.getValueAt(fila, 0).toString());
				txtUsuario.setText(table.getValueAt(fila, 1).toString());
				txtNombre.setText(table.getValueAt(fila, 2).toString());		
				
				String priv = table.getValueAt(fila, 3).toString();
				
				int index =0;
				if("USUARIO".equals(priv))
					index = 1;
				
				cbPrivilegio.setSelectedIndex(index);
				btnEliminar.setEnabled(true);
				btnModificarContrasenia.setEnabled(true);
				btnModificarNombre.setEnabled(true);
				btnModificarUsuario.setEnabled(true);
				btnModificarPrivilegio.setEnabled(true);
			}
		});
		
		//BOTON CARGAR
		//Pensando que sea una aplicacion de escritorio donde haya mas de un usuario, se recarga la tabla con este boton
		//de forma que si otro usuario hace modificaciones, se actualice la tabla desde la base de datos
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				Limpiar();
			}
		});
		
		//BOTON LIMPIAR
		//Llama a la funcion limpiar
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		
		
		//BOTON ELIMINAR
		//Se pregunta si se quiere realmente eliminar el usuario, en caso de confirmarlo, se elimina de la base de datos
		//todo gracias a los metodos de lista usuarios
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario "+txtNombre.getText()+"?");
				
				if(temp == 0)
					listaUsuarios.EliminarUsuario(txtID.getText());
				
				Actualizar();
				Limpiar();
			}
		});
		
		//BOTON MODIFICAR CONTRASENIA
		btnModificarContrasenia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uno = String.valueOf(txtContrasenia.getPassword());
				String dos = String.valueOf(txtConfirmarContrasenia.getPassword());
				String id = txtID.getText();
				
				//Se valida que hayan datos en las cajas de texto
				if("".equals(uno) || "".equals(dos)) 
					JOptionPane.showMessageDialog(null, "Campos vacíos");
				else 
					//Despues se valida si las contrasenas son iguales en los campos de validacion
					
					if(uno.equals(dos)) {
						//En caso de ser asi, se modifica la contrasena con los metodos de listaUsuarios
						listaUsuarios.ModificarContrasenia(dos, id);	
						Actualizar();
						Limpiar();
					}
					else
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
			}
		});
		
		//BOTON MODIFICAR USUARIO
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String id = txtID.getText();
				//Cuando se desea modificar un usuario, se debe primero saber si el campo esta vacio
				if("".equals(usuario))
					JOptionPane.showMessageDialog(null, "Campo vacío");
				else {
					//En casi de que el usuario ya exista, se nos presenta un dialogo avisandolo
					boolean ban = listaUsuarios.Confirmar(usuario);
					if(ban == true)
						JOptionPane.showMessageDialog(null, "Usuario Existente");
					else {
						listaUsuarios.ModificarUsuario(usuario, id);
						Actualizar();
						Limpiar();
					}
				}
					
			}
		});
		
		//BOTON MODIFICAR NOMBRE
		//Se valida que los campos no esten vavios y luego se envia a Listausuarios
		btnModificarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String id = txtID.getText();
				
				if("".equals(nombre))
					JOptionPane.showMessageDialog(null, "Campo vacío");
				else {
					listaUsuarios.ModificarNombre(nombre, id);
					Actualizar();
					Limpiar();
				}
					
			}
		});
		
		//BOTON MODIFICAR PRIVILEGIO
		btnModificarPrivilegio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String priv = cbPrivilegio.getSelectedItem().toString();
				String id = txtID.getText();
				
				listaUsuarios.ModificarPrivilegio(priv, id);
				Actualizar();
				Limpiar();
			}
		});
		
		//BOTON AGREGAR
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Primero convertimos todas las cajas de texto a texto
				String usuario = txtUsuario.getText();
				String nombre = txtNombre.getText();
				String priv = cbPrivilegio.getSelectedItem().toString();
				String uno = String.valueOf(txtContrasenia.getPassword());
				String dos = String.valueOf(txtConfirmarContrasenia.getPassword());
				
				//Lo primero que vamos a hacer es validar que todos los campos hayan sido llenados
				if("".equals(usuario) || "".equals(nombre) || "".equals(uno) || "".equals(dos))
					JOptionPane.showMessageDialog(null, "Campos Vacíos");
				else {
					//Despues que la contrasena se haya escrito correctamente en ambos campos
					if(uno.equals(dos)) {
						//Y por ultimo, vamos a confirmar que el usuario no exista ya
						boolean ban = listaUsuarios.Confirmar(usuario);
						if(ban == true)
							JOptionPane.showMessageDialog(null, "Usuario Existente");
						else {
							//Si todo es correcto, creamos un nuevo usuario que mandaremos a nuestra lista
							//para agregarlo
							Usuario nvo = new Usuario();
							Persona temp = new Persona();
							temp.setNombre(nombre);
							nvo.setNombre(temp);
							nvo.setContrasenia(uno);
							nvo.setUsuario(usuario);
							nvo.setTipoUsuario(TipoUsuario.valueOf(priv));
							
							int pera = JOptionPane.showConfirmDialog(null, "¿Desea agregar el usuario "+usuario+"?");
							if(pera == 0) 
								listaUsuarios.AgregarUsuario(nvo);
							
							Actualizar();
							Limpiar();
						}
							
					}else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					}
				}
			}
		});
		
		//BOTON BUSCAR POR ID
		//Siempre se valida primero que el campo de texto no este vacio
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtBuscarId.getText();
				
				if("".equals(id))
					JOptionPane.showMessageDialog(null, "Campo Vacío");
				else
					CrearTabla(listaUsuarios.BuscarID(id));
			}
		});
		
		//BOTON BUSCAR POR USUARIO
		//Siempre se valida primero que el campo de texto no este vacio
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtBuscarUsuario.getText();
				
				if("".equals(user))
					JOptionPane.showMessageDialog(null, "Campo Vacío");
				else
					CrearTabla(listaUsuarios.BuscarUsuario(user));
			}
		});
		
		/********************************************************************/

		/*  Se agregan al Fame  */
		contentPane.add(scroll);
		contentPane.add(btnBuscarId);
		contentPane.add(lblIdBusqueda);
		contentPane.add(txtBuscarId);
		contentPane.add(btnCargar);
		contentPane.add(txtBuscarUsuario);
		contentPane.add(lblBuscarUsuario);
		contentPane.add(btnBuscarUsuario);
		contentPane.add(lblNombre);
		contentPane.add(lblId);
		contentPane.add(lblUsuario);
		contentPane.add(lblContrasea);
		contentPane.add(lblConfirmarContrasea);
		contentPane.add(txtNombre);
		contentPane.add(txtUsuario);
		contentPane.add(txtContrasenia);
		contentPane.add(txtConfirmarContrasenia);
		contentPane.add(lblPrivilegio);
		contentPane.add(cbPrivilegio);
		contentPane.add(btnLimpiar);
		contentPane.add(btnAgregar);
		contentPane.add(btnEliminar);
		contentPane.add(txtID);
		contentPane.add(btnModificarContrasenia);
		contentPane.add(btnModificarUsuario);
		contentPane.add(btnModificarNombre);
		contentPane.add(btnModificarPrivilegio);
		
	}
	
	
	/* Como se debe actualizar la tabla de forma seguida, se crea la funcion que la creara */
	public void CrearTabla(ArrayList<Usuario> user) {
		DefaultTableModel modelo =new  DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Usuario", "Nombre", "Privilegio"}
			){
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						Integer.class, String.class, String.class, String.class
					};
					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				};
			table.setModel(modelo);
			
			for(Usuario temp :user) {
				Object[] fil = new Object[4];
						fil[0]=temp.getId();
						fil[1]=temp.getUsuario();
						fil[2]=temp.getNombre().getNombre();
						fil[3]=temp.getTipoUsuario().toString();
						modelo.addRow(fil);
			}		
			
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
			table.getColumnModel().getColumn(1).setPreferredWidth(20);
			center.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(center);
	}
	
	public void Limpiar() {
		txtNombre.setText(null);
		txtID.setText(null);
		txtUsuario.setText(null);
		txtContrasenia.setText(null);
		txtConfirmarContrasenia.setText(null);
		cbPrivilegio.setSelectedIndex(0);
		btnEliminar.setEnabled(false);
		btnModificarContrasenia.setEnabled(false);
		btnModificarNombre.setEnabled(false);
		btnModificarUsuario.setEnabled(false);
		btnModificarPrivilegio.setEnabled(false);
	}
	
	public void Actualizar() {
		listaUsuarios = new ListaUsuarios();
		CrearTabla(listaUsuarios.getUsuarios());
	}
}
