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

//import backend.cotizacion.Persona;
import backend.proveedores.ListaProveedores;
import backend.proveedores.Proveedor;
/*import backend.usuarios.*;
import backend.usuarios.TipoUsuario;
import backend.usuarios.Usuario;*/
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Proveedores extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/*Creamos todos los atributos que tendra nuestro Frame*/
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarId;
	private JTextField txtBuscarProvedor;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtGiro;
	private JTextField txtID;
	private JLabel lblBuscarProvedor;
	private JButton btnBuscarProvedor;
	private JButton btnBuscarId;
	private JButton btnCargar;
	private JLabel lblIdBusqueda;
	private JLabel lblId;
	private JLabel lblDireccion;
	private JLabel lblTelefono;
	private JLabel lblGiro;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private DefaultTableCellRenderer center = new DefaultTableCellRenderer();
	private JScrollPane scroll;
	private JButton btnModificarTelefono;
	private JButton btnModificarDireccion;
	private JButton btnModificarNombre;
	private JButton btnModificarGiro;
	private ListaProveedores listaProveedores;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Proveedores() {
		setTitle("PROVEDORES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*  CREACION DE INSTANCIAS DE LOS ATRIBUTOS E INICIALIZACION  */
		
		table = new JTable();
		scroll = new JScrollPane(table);
		listaProveedores = new ListaProveedores();
		CrearTabla(listaProveedores.getProveedores());
		lblIdBusqueda = new JLabel("ID: ");
		lblBuscarProvedor = new JLabel("Nombre:");
		lblNombre = new JLabel("Nombre:");
		lblId = new JLabel("ID:");
		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblTelefono = new JLabel("Tel\u00E9fono:");
		lblGiro = new JLabel("Giro:");
		txtBuscarId = new JTextField();
		txtBuscarProvedor = new JTextField();
		txtNombre = new JTextField();
		txtDireccion = new JTextField();
		txtID = new JTextField();
		txtTelefono = new JTextField();
		txtGiro = new JTextField();
		btnBuscarId = new JButton("Buscar");
		btnCargar = new JButton("Recargar Tabla");
		btnBuscarProvedor = new JButton("Buscar");
		btnLimpiar = new JButton("Limpiar");
		btnModificarTelefono = new JButton("<html>Modificar Tel\u00E9fono</html>");
		btnModificarTelefono.setEnabled(false);
		btnModificarDireccion = new JButton("<html>Modificar Direcci\u00F3n</html>");
		btnModificarDireccion.setEnabled(false);
		btnModificarNombre = new JButton("<html>Modificar Nombre</html>");
		btnModificarNombre.setEnabled(false);
		btnModificarGiro = new JButton("<html>Modificar Giro</html>");
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");
		
		/*  Se configura cada atributo como queremos que se vea  */
		scroll.setBounds(10, 11, 480, 250);
		scroll.setVisible(true);
		lblIdBusqueda.setBounds(10, 306, 80, 23);
		lblBuscarProvedor.setBounds(10, 340, 80, 23);
		lblNombre.setBounds(520, 30, 100, 14);
		lblId.setBounds(520, 55, 100, 14);
		lblDireccion.setBounds(520, 80, 100, 14);
		lblTelefono.setBounds(520, 107, 100, 14);
		lblGiro.setBounds(520, 124, 100, 33);
		txtBuscarId.setForeground(Color.BLACK);
		txtBuscarId.setBackground(Color.WHITE);
		txtBuscarId.setBounds(107, 306, 250, 23);	
		txtBuscarProvedor.setForeground(Color.BLACK);
		txtBuscarProvedor.setBackground(Color.WHITE);
		txtBuscarProvedor.setBounds(107, 340, 250, 23);
		
		txtNombre.setBounds(600, 27, 200, 20);
		txtNombre.setColumns(10);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(600, 77, 200, 20);
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(600, 52, 100, 20);
		txtTelefono.setBounds(600, 104, 200, 20);
		txtGiro.setBounds(600, 131, 200, 20);
		btnBuscarId.setToolTipText("B\u00FAsqueda por ID");
		btnBuscarId.setBounds(390, 306, 100, 23);
		btnCargar.setToolTipText("Actualiza la tabla");
		btnCargar.setBounds(107, 272, 250, 23);
		btnBuscarProvedor.setToolTipText("B\u00FAsqueda por ID");
		btnBuscarProvedor.setBounds(390, 340, 100, 23);
		btnLimpiar.setBounds(638, 306, 89, 23);
		btnAgregar.setToolTipText("<html>Puedes crear un nuevo art\u00EDculo desde cero o tomar otro como base, <br>al finalizar de llenar los datos, presiona aqui</html>");
		btnAgregar.setBounds(638, 272, 89, 23);
		btnEliminar.setToolTipText("Elimina el articulo elegido");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(750, 272, 89, 23);
		btnModificarTelefono.setBounds(520, 215, 89, 46);
		btnModificarDireccion.setBounds(638, 215, 89, 46);
		btnModificarNombre.setBounds(750, 215, 89, 46);
		btnModificarGiro.setEnabled(false);
		btnModificarGiro.setBounds(520, 272, 89, 46);
		
		/******************************   **************************************/
		//OPCION DE TABLA
		//Se llenan los textos con la informacion que hay en la tabla cada que se presiona una fila
		//Tambien se habilitan los botones de eliminar y todas mas modificaciones
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				
				txtID.setText(table.getValueAt(fila, 0).toString());
				txtNombre.setText(table.getValueAt(fila, 1).toString());
				txtDireccion.setText(table.getValueAt(fila, 2).toString());
				txtTelefono.setText(table.getValueAt(fila, 3).toString());	
				txtGiro.setText(table.getValueAt(fila, 4).toString());
				
				btnEliminar.setEnabled(true);
				btnModificarTelefono.setEnabled(true);
				btnModificarNombre.setEnabled(true);
				btnModificarDireccion.setEnabled(true);
				btnModificarGiro.setEnabled(true);
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
				int temp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el proveedor "+txtNombre.getText()+"?");
				
				if(temp == 0)
					listaProveedores.EliminarProveedor(txtID.getText());
				
				Actualizar();
				Limpiar();
			}
		});
		
		//BOTON MODIFICAR TELÉFONO
		btnModificarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uno = txtTelefono.getText();
				String id = txtID.getText();
				
				//Se valida que hayan datos en las cajas de texto
				if("".equals(uno)) 
					JOptionPane.showMessageDialog(null, "Campos vacíos");
				
				else{
						//En caso de ser asi, se modifica el telefono con los metodos de listaProveedores
						listaProveedores.ModificarTelefono(uno, id);	
						Actualizar();
						Limpiar();
					}
			}
		});
		
		//BOTON MODIFICAR DIRECCION
		btnModificarDireccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String direccion = txtDireccion.getText();
				String id = txtID.getText();
				//Cuando se desea modificar un usuario, se debe primero saber si el campo esta vacio
				if("".equals(direccion))
					JOptionPane.showMessageDialog(null, "Campo vacío");
				else {
					//En casi de que el usuario ya exista, se nos presenta un dialogo avisandolo
					boolean ban = listaProveedores.Confirmar(direccion);
					if(ban == true)
						JOptionPane.showMessageDialog(null, "Usuario Existente");
					else {
						listaProveedores.ModificarDireccion(direccion, id);
						Actualizar();
						Limpiar();
					}
				}
					
			}
		});
		
		//BOTON MODIFICAR NOMBRE
		//Se valida que los campos no esten vavios y luego se envia a ListaProveedores
		btnModificarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String id = txtID.getText();
				
				if("".equals(nombre))
					JOptionPane.showMessageDialog(null, "Campo vacío");
				else {
					listaProveedores.ModificarNombre(nombre, id);
					Actualizar();
					Limpiar();
				}
					
			}
		});
		
		//BOTON MODIFICAR GIRO
		btnModificarGiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String giro = txtGiro.getText();
				String id = txtID.getText();
				
				listaProveedores.ModificarGiro(giro, id);
				Actualizar();
				Limpiar();
			}
		});
		
		//BOTON AGREGAR
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Primero convertimos todas las cajas de texto a texto
				String direccion = txtDireccion.getText();
				String nombre = txtNombre.getText();
				String giro = txtGiro.getText();
				String telefono = txtTelefono.getText();
				
				//Lo primero que vamos a hacer es validar que todos los campos hayan sido llenados
				if("".equals(direccion) || "".equals(nombre) || "".equals(giro) || "".equals(telefono))
					JOptionPane.showMessageDialog(null, "Campos Vacíos");
				else {
					
						//Y por ultimo, vamos a confirmar que el usuario no exista ya
						boolean ban = listaProveedores.Confirmar(nombre);
						if(ban == true)
							JOptionPane.showMessageDialog(null, "Nombre Existente");
						else {
							//Si todo es correcto, creamos un nuevo usuario que mandaremos a nuestra lista
							//para agregarlo
							Proveedor nvo = new Proveedor();
							
							nvo.setNombre(nombre);
							nvo.setDireccion(direccion);
							nvo.setTelefono(telefono);
							nvo.setGiro(giro);
							
							
							int pera = JOptionPane.showConfirmDialog(null, "¿Desea agregar el usuario "+nombre+"?");
							if(pera == 0) 
								listaProveedores.AgregarProveedor(nvo);
							
							Actualizar();
							Limpiar();
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
					CrearTabla(listaProveedores.BuscarID(id));
			}
		});
		
		//BOTON BUSCAR POR USUARIO
		//Siempre se valida primero que el campo de texto no este vacio
		btnBuscarProvedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtBuscarProvedor.getText();
				
				if("".equals(user))
					JOptionPane.showMessageDialog(null, "Campo Vacío");
				else
					CrearTabla(listaProveedores.BuscarProveedor(user));
			}
		});
		
		/********************************************************************/

		/*  Se agregan al Frame  */
		contentPane.add(scroll);
		contentPane.add(btnBuscarId);
		contentPane.add(lblIdBusqueda);
		contentPane.add(txtBuscarId);
		contentPane.add(btnCargar);
		contentPane.add(txtBuscarProvedor);
		contentPane.add(lblBuscarProvedor);
		contentPane.add(btnBuscarProvedor);
		contentPane.add(lblNombre);
		contentPane.add(lblId);
		contentPane.add(lblDireccion);
		contentPane.add(lblTelefono);
		contentPane.add(lblGiro);
		contentPane.add(txtNombre);
		contentPane.add(txtDireccion);
		contentPane.add(txtTelefono);
		contentPane.add(txtGiro);
		contentPane.add(btnLimpiar);
		contentPane.add(btnAgregar);
		contentPane.add(btnEliminar);
		contentPane.add(txtID);
		contentPane.add(btnModificarTelefono);
		contentPane.add(btnModificarDireccion);
		contentPane.add(btnModificarNombre);
		contentPane.add(btnModificarGiro);
		
	}
	
	
	/* Como se debe actualizar la tabla de forma seguida, se crea la funcion que la creara */
	public void CrearTabla(ArrayList<Proveedor> arrayList) {
		DefaultTableModel modelo =new  DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nombre", "Direccion", "Teléfono", "Giro"}
			){
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						Integer.class, String.class, String.class, String.class, String.class
					};
					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				};
			table.setModel(modelo);
			
			for(Proveedor temp :arrayList) {
				Object[] fil = new Object[5];
						fil[0]=temp.getId();
						fil[1]=temp.getNombre();
						fil[2]=temp.getDireccion();
						fil[3]=temp.getTelefono();
						fil[4]=temp.getGiro();
						modelo.addRow(fil);
			}		
			
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			center.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(center);
	}
	
	public void Limpiar() {
		txtNombre.setText(null);
		txtID.setText(null);
		txtDireccion.setText(null);
		txtTelefono.setText(null);
		txtGiro.setText(null);
		btnEliminar.setEnabled(false);
		btnModificarTelefono.setEnabled(false);
		btnModificarNombre.setEnabled(false);
		btnModificarDireccion.setEnabled(false);
		btnModificarGiro.setEnabled(false);
	}
	
	public void Actualizar() {
		listaProveedores = new ListaProveedores();
		CrearTabla(listaProveedores.getProveedores());
	}
}
