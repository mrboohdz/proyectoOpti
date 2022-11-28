package frontend.catalogo;


import javax.swing.*;

import backend.catalogo.Articulo;
import backend.catalogo.Catalogo;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
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
	
	private Catalogo cat;
		
	public CatalogoUsuario() {
		setTitle("CATÁLOGO DE PRODUCTOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(864,458);
		setLocationRelativeTo(null); 
		setResizable(false);
		getContentPane().setLayout(null); 
		
		//Ingresamos el panel de productos que usamos tambien en la interfaz de vista para el cliente
		panelProductos = new PanelProductos(this);
		cat = new Catalogo();
		
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
		lblInfoPrecio.setBounds(512, 102, 46, 14);
		lblInfoStock.setBounds(512, 127, 46, 14);
		txtInfoNombre.setBounds(568, 49, 266, 20);
		txtInfoID.setEditable(false);
		txtInfoID.setBounds(568, 74, 133, 20);
		txtInfoPrecio.setBounds(568, 99, 133, 20);
		txtInfoStock.setBounds(568, 124, 133, 20);
		btnModificar.setBounds(512, 169, 89, 23);
		btnEliminar.setBounds(745, 169, 89, 23);
		btnModificar.setToolTipText("<html>Selecciona algun articulo de la tabla y hazle modificaciones, \r\n<br>al terminar presiona aqui</html>");
		btnAgregar.setBounds(633, 169, 89, 23);
		btnAgregar.setToolTipText("<html>Puedes crear un nuevo art\u00EDculo desde cero o tomar otro como base, <br>al finalizar de llenar los datos, presiona aqui</html>");
		btnLimpiar.setBounds(633, 203, 89, 23);
	
		/********************************ACCIONES DE BOTONES******************************************/
		
		//BOTON AGREGAR
		//Cuando se presiona agregar, se valida si los datos fueron ingresados correctamente, en caso de que algun campo
		//este vacio, no nos deja continuar, si esta todo correcto, se crea un nuevo articulo que se envia al catalogo
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtInfoNombre.getText();
				String precio = txtInfoPrecio.getText();
				String stock = txtInfoStock.getText();
				
				if("".equals(nombre) || "".equals(precio) || "".equals(stock))
					JOptionPane.showMessageDialog(null, "Campo vacío");
				else {
					Articulo art = new Articulo();
					art.setNombre(nombre);
					art.setPrecio(Float.parseFloat(precio));
					art.setStock(Integer.parseInt(stock));
					
					int input = JOptionPane.showConfirmDialog(null, "¿Desea agregar "+art.getNombre()+"?");
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
					int input = JOptionPane.showConfirmDialog(null, "¿Esta seguro de elimiar el articulo?");
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
	
	public void LlenarTabla(Articulo art) {
		txtInfoID.setText(String.valueOf(art.getId()));
		txtInfoNombre.setText(art.getNombre());
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
