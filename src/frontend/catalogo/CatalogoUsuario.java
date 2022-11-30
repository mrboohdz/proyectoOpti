package frontend.catalogo;


import javax.swing.*;

import backend.catalogo.Articulo;
import backend.catalogo.Catalogo;
import backend.proveedores.ListaProveedores;
import backend.proveedores.Proveedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CatalogoUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Creacion de atributos de etiquetas, botones y cajas de texto
	private PanelProductos panelProductos;
	
	private JLabel lblTitulo;
	private JLabel lblInfoNombre;
	private JLabel lblInfoID;
	private JLabel lblInfoPrecio;
	private JLabel lblInfoStock;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnLimpiar;
	private JTextField txtInfoNombre;
	private JTextField txtInfoID;
	private JTextField txtInfoPrecio;
	private JTextField txtInfoStock;
	private JComboBox<String> cbInfoProv;
	
	private Catalogo cat;
		
	@SuppressWarnings("unchecked")
	public CatalogoUsuario() {
		setTitle("CAT�LOGO DE PRODUCTOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(864,479);
		setLocationRelativeTo(null); 
		setResizable(false);
		getContentPane().setLayout(null); 
		
		//Ingresamos el panel de productos que usamos tambien en la interfaz de vista para el cliente
		panelProductos = new PanelProductos(this);
		cat = new Catalogo();
		
		JLabel lblInfoProveedor = new JLabel("Prov:");
		lblInfoProveedor.setBounds(512, 102, 58, 14);
		getContentPane().add(lblInfoProveedor);
		
		cbInfoProv = new JComboBox<String>();
		cbInfoProv.setBounds(578, 96, 133, 22);
		getContentPane().add(cbInfoProv);
		
		//Creacion de etiquetas, cajas de texto y botones
		lblTitulo = new JLabel("DATOS DEL PRODUCTO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoNombre = new JLabel("Nombre:");
		lblInfoID = new JLabel("ID:");
		lblInfoPrecio = new JLabel("Precio: ");
		lblInfoStock = new JLabel("Stock:");
		txtInfoNombre = new JTextField();
		txtInfoID = new JTextField();
		txtInfoPrecio = new JTextField();
		txtInfoStock = new JTextField();
		btnModificar = new JButton("Modificar");
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Elimina el articulo elegido");
		btnAgregar = new JButton("Agregar");
		btnLimpiar = new JButton("Limpiar");
				
		//Configutacion de cada atributo 
		btnEliminar.setEnabled(false);
		btnModificar.setEnabled(false);
		panelProductos.setBounds(0, 0, 502, 429);
		lblTitulo.setBounds(512, 11, 322, 30);
		lblInfoNombre.setBounds(512, 52, 58, 14);
		lblInfoID.setBounds(512, 77, 46, 14);
		lblInfoPrecio.setBounds(512, 126, 46, 14);
		lblInfoStock.setBounds(512, 151, 46, 14);
		txtInfoNombre.setBounds(578, 49, 256, 20);
		txtInfoID.setEditable(false);
		txtInfoID.setBounds(578, 71, 133, 20);
		txtInfoPrecio.setBounds(578, 123, 133, 20);
		txtInfoStock.setBounds(578, 148, 133, 20);
		btnModificar.setBounds(512, 198, 89, 23);
		btnEliminar.setBounds(745, 198, 89, 23);
		btnModificar.setToolTipText("<html>Selecciona algun articulo de la tabla y hazle modificaciones, \r\n<br>al terminar presiona aqui</html>");
		btnAgregar.setBounds(633, 198, 89, 23);
		btnAgregar.setToolTipText("<html>Puedes crear un nuevo art\u00EDculo desde cero o tomar otro como base, <br>al finalizar de llenar los datos, presiona aqui</html>");
		btnLimpiar.setBounds(633, 232, 89, 23);
	
		/********************************ACCIONES DE BOTONES******************************************/
		
		//BOTON AGREGAR
		//Cuando se presiona agregar, se valida si los datos fueron ingresados correctamente, en caso de que algun campo
		//este vacio, no nos deja continuar, si esta todo correcto, se crea un nuevo articulo que se envia al catalogo
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtInfoNombre.getText();
				String precio = txtInfoPrecio.getText();
				String stock = txtInfoStock.getText();
				String proveedor = (String) cbInfoProv.getSelectedItem();
				
				if("".equals(nombre) || "".equals(precio) || "".equals(stock))
					JOptionPane.showMessageDialog(null, "Campo vac�o");
				else {
					Articulo art = new Articulo();
					art.setNombre(nombre);
					art.setPrecio(Float.parseFloat(precio));
					art.setStock(Integer.parseInt(stock));
					art.setProveedor(proveedor);
					int input = JOptionPane.showConfirmDialog(null, "�Desea agregar "+art.getNombre()+"?");
					if(input==0)
						cat.AgregarArticulo(art);
					
					Catalogo cc = new Catalogo();
					panelProductos.CrearTabla(cc.getArticulos());
					Limpiar();	

				}
			}
		});
		
		
		//BOTON ELIMINAR
		//En caso de que no se haya seleccionado ningun articulo, no nos dejara continuar
		//sino, se envia el texto del id a la clase de catalogo
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtInfoID.getText();
				if("".equals(id))
					JOptionPane.showMessageDialog(null, "Selecciona un articulo");
				else {
					int input = JOptionPane.showConfirmDialog(null, "�Esta seguro de elimiar el articulo?");
					if (input==0)
						cat.EliminarArticulo(id);
					
					Catalogo cc = new Catalogo();
					panelProductos.CrearTabla(cc.getArticulos());
					Limpiar();	
				}
			}
		});
		
		
		//BOTON MODIFICAR
		//En caso de que no se haya seleccionado ningun articulo, no nos dejara continuar
		//sino, se crea un articulo nuevo con la nueva informacion, que se enviara a 
		//modificar el articulo en la clase Catalogo
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtInfoID.getText();
				if("".equals(id))
					JOptionPane.showMessageDialog(null, "Selecciona un articulo");
				else {
					Articulo temp = new Articulo();
					temp.setId(Integer.parseInt(id));
					temp.setNombre(txtInfoNombre.getText());
					temp.setProveedor((String) cbInfoProv.getSelectedItem());
					temp.setPrecio(Float.parseFloat(txtInfoPrecio.getText()));
					temp.setStock(Integer.parseInt(txtInfoStock.getText()));
					cat.ModificarArticulo(temp);
					
					Catalogo cc = new Catalogo();
					panelProductos.CrearTabla(cc.getArticulos());
					Limpiar();	
				}
			}
		});
		
		
		//BOTON LIMPIAR
		//unicamente se limpian los cuadros de texto
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		/*****************************************************************************/
	
		
		//Agregar cada atributo a la ventana
		getContentPane().add(panelProductos);
		getContentPane().add(lblTitulo);
		getContentPane().add(lblInfoNombre);
		getContentPane().add(lblInfoID);
		getContentPane().add(lblInfoPrecio);
		getContentPane().add(lblInfoStock);
		getContentPane().add(txtInfoNombre);
		getContentPane().add(txtInfoID);
		getContentPane().add(txtInfoPrecio);
		getContentPane().add(txtInfoStock);
		getContentPane().add(btnModificar);
		getContentPane().add(btnEliminar);
		getContentPane().add(btnAgregar);
		getContentPane().add(btnLimpiar);
		
		
		
	}
	
	public void LlenarProveedores() {
		cbInfoProv.removeAllItems();
		ListaProveedores lp = new ListaProveedores();
		for (Proveedor temp: lp.getProveedores()) {
			cbInfoProv.addItem(temp.getNombre());
		}
	}
	
	public void LlenarTabla(Articulo art) {
		txtInfoID.setText(String.valueOf(art.getId()));
		txtInfoNombre.setText(art.getNombre());
		LlenarProveedores();
		cbInfoProv.setSelectedItem(art.getProveedor());
		txtInfoPrecio.setText(String.valueOf(art.getPrecio()));
		txtInfoStock.setText(String.valueOf(art.getStock()));
	}
	
	public void Limpiar() {
		txtInfoID.setText("");
		txtInfoNombre.setText("");
		txtInfoPrecio.setText("");
		txtInfoStock.setText("");
		
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
}
