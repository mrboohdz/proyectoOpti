package frontend.catalogo;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.catalogo.Articulo;
import backend.catalogo.Catalogo;
import backend.cotizacion.Cotizacion;
import frontend.cotizacion.CotizacionInformacion;
import frontend.cotizacion.Cotizaciones;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelProductos extends JPanel {
	private static final long serialVersionUID = 1L;

	//Se crean los elementos del Panel
	private JTable jtProductos;
	private JButton btnBuscarNombre;
	private JButton btnBuscarId;
	private JTextField txtBuscarNombre;
	private JTextField txtBuscarId;
	private JLabel lblNombreBusqueda;
	private JLabel lblIdBusqueda;
	private JButton btnCotizacion;
	private JButton btnCargar;
	private JScrollPane scroll;
	DefaultTableCellRenderer center = new DefaultTableCellRenderer();
	private Catalogo cat;
	@SuppressWarnings("unused")
	private Cotizacion cotizacion;
	private Articulo articulo;

	@SuppressWarnings("unused")
	private CatalogoUsuario cu;
	private CotizacionInformacion cotInfo;
	private Cotizaciones cot;
	@SuppressWarnings("unused")
	private CatalogoCliente catCliente;
	
	/**
	 * @wbp.parser.constructor
	 */
	public PanelProductos(CatalogoUsuario cu) {
		this.cu=cu;
		cat= new Catalogo();
		setLayout(null);
			
		//Creamos todas las Etiquetas, todos los cuadros de texto
		jtProductos = new JTable();
		scroll = new JScrollPane(jtProductos);
		CrearTabla(cat.getArticulos());
		
		btnCargar = new JButton("Recargar Tabla");
		btnBuscarNombre = new JButton("Buscar");
		btnBuscarId = new JButton("Buscar");
		
		lblNombreBusqueda = new JLabel("Nombre: ");
		lblIdBusqueda = new JLabel("ID: ");
		
		txtBuscarNombre = new JTextField();
		txtBuscarId = new JTextField();
		
		btnCotizacion = new JButton("Crear Cotización");
		

		//Configuramos cada etiqueta, cuadros de texto y botones
		
		scroll.setBounds(10, 11, 480, 262);
		scroll.setVisible(true);
		
		btnCargar.setBounds(107, 284, 250, 23);
		btnCargar.setToolTipText("Actualiza la tabla");
		
		lblNombreBusqueda.setBounds(10, 318, 66, 23);
		btnBuscarNombre.setBounds(390, 318, 100, 23);
		btnBuscarNombre.setToolTipText("B\u00FAsqueda por nombre");
		txtBuscarNombre.setBounds(107, 318, 250, 23);
		txtBuscarNombre.setBackground(Color.WHITE);
		txtBuscarNombre.setForeground(Color.BLACK);
		
		
		lblIdBusqueda.setBounds(10, 352, 80, 23);
		txtBuscarId.setBounds(107, 352, 250, 23);
		txtBuscarId.setBackground(Color.WHITE);
		txtBuscarId.setForeground(Color.BLACK);
		btnBuscarId.setBounds(390, 352, 100, 23);
		btnBuscarId.setToolTipText("B\u00FAsqueda por ID");

		btnCotizacion.setLocation(10, 386);
		btnCotizacion.setSize(200, 23);
		
		/*************EN ESTA PARTE VEMOS LO QUE PASA CUANDO PRESIONAN CADA BOTON***********************/

		//BOTON CARGAR
		//Actualiza la tabla
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				CrearTabla(cat.getArticulos());
			}
		});
		
		
		//BOTON BUSCAR (POR NOMBRE)
		//Tenemos la condicional para saber si el recuadro de busqueda esta vacio, si es asi
		//Entonces nos manda un error y carga el catalogo completo
		//En caso de que se mande un texto correctamente
		//Nos van a aparecer los articulos en la tabla, ya que pueden tener el mismo nombre
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cat = new Catalogo();
				String nombre = txtBuscarNombre.getText();
				
				if("".equals(nombre)) {
					JOptionPane.showMessageDialog(null, "Ingresa un Nombre");
					CrearTabla(cat.getArticulos());
				}
				else {
					ArrayList<Articulo> temp = cat.BuscarArticulo(nombre);
					if(temp == null)
						JOptionPane.showMessageDialog(null, "Artículo no encontrado");
					else
						CrearTabla(temp);
				}
					
			}});
		
		
		//BOTON BUSCAR (POR ID)
		//De igual forma tenemos la condicional para saber si el recuadro de texto esta vacio
		//Como el ID del articulo es unico, nos va a ensenar un solor articulo en la tabla, pero
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				String id = txtBuscarId.getText();
				if("".equals(id)) {
					JOptionPane.showMessageDialog(null, "Ingresa un Nombre");
					CrearTabla(cat.getArticulos());
				}
				else 
					CrearTabla(cat.BuscarArticulo(Integer.parseInt(id)));
		}});
		
		//OPCIONES DE TABLA
		//En caso de que se presione un articulo en la tabla, se llena la interfaz de el usuario
		//para hacer modificaciones en los articulos
		jtProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = jtProductos.getSelectedRow();
				String tempId =jtProductos.getValueAt(fila, 0).toString();
				
				int id = Integer.parseInt(tempId);
				Articulo art = cat.CrearArticulo(id);
				cu.LlenarTabla(art);
				
				//cat.CrearArticulo(tempId);
				//cu.LlenarTabla(cat.getArt());
				cu.getBtnEliminar().setEnabled(true);
				cu.getBtnModificar().setEnabled(true);
			}
		});
		
		//CREAR COTIZACION
		btnCotizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cotInfo = new CotizacionInformacion();
				cot = new Cotizaciones(cotInfo);
				cot.setVisible(true);		
				cu.setVisible(false);
				}
		});
		
		/****************************************************/
		//Agregamos todo al panel
		add(scroll);
		add(btnCargar);
		add(lblNombreBusqueda);
		add(txtBuscarNombre);
		add(btnBuscarNombre);
		add(lblIdBusqueda);
		add(txtBuscarId);
		add(btnBuscarId);
		add(btnCotizacion);

	}
	
	//Cree otro constructor para usarlo en las cotizaciones
	// no se de que otra forma utilizarlo pasando la informacion :c
	
	public PanelProductos(Cotizaciones cot) {
		this.cot=cot;
		cotizacion=new Cotizacion();
		cat= new Catalogo();
		setLayout(null);
			
		//Creamos todas las Etiquetas, todos los cuadros de texto
		jtProductos = new JTable();
		scroll = new JScrollPane(jtProductos);
		CrearTabla(cat.getArticulos());
		
		btnCargar = new JButton("Recargar Tabla");
		btnBuscarNombre = new JButton("Buscar");
		btnBuscarId = new JButton("Buscar");
		
		lblNombreBusqueda = new JLabel("Nombre: ");
		lblIdBusqueda = new JLabel("ID: ");
		
		txtBuscarNombre = new JTextField();
		txtBuscarId = new JTextField();
		
		btnCotizacion = new JButton("Crear Cotización");

		//Configuramos cada etiqueta, cuadros de texto y botones
		
		scroll.setBounds(10, 11, 480, 262);
		scroll.setVisible(true);
		
		btnCargar.setBounds(107, 284, 250, 23);
		btnCargar.setToolTipText("Actualiza la tabla");
		
		lblNombreBusqueda.setBounds(10, 318, 66, 23);
		btnBuscarNombre.setBounds(390, 318, 100, 23);
		btnBuscarNombre.setToolTipText("B\u00FAsqueda por nombre");
		txtBuscarNombre.setBounds(107, 318, 250, 23);
		txtBuscarNombre.setBackground(Color.WHITE);
		txtBuscarNombre.setForeground(Color.BLACK);
		
		
		lblIdBusqueda.setBounds(10, 352, 80, 23);
		txtBuscarId.setBounds(107, 352, 250, 23);
		txtBuscarId.setBackground(Color.WHITE);
		txtBuscarId.setForeground(Color.BLACK);
		btnBuscarId.setBounds(390, 352, 100, 23);
		btnBuscarId.setToolTipText("B\u00FAsqueda por ID");

		btnCotizacion.setLocation(10, 386);
		btnCotizacion.setSize(200, 23);
		
		/*************EN ESTA PARTE VEMOS LO QUE PASA CUANDO PRESIONAN CADA BOTON***********************/

		//BOTON CARGAR
		//Actualiza la tabla
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				CrearTabla(cat.getArticulos());
			}
		});
		
		
		//BOTON BUSCAR (POR NOMBRE)
		//Tenemos la condicional para saber si el recuadro de busqueda esta vacio, si es asi
		//Entonces nos manda un error y carga el catalogo completo
		//En caso de que se mande un texto correctamente
		//Nos van a aparecer los articulos en la tabla, ya que pueden tener el mismo nombre
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtBuscarNombre.getText();
				cat = new Catalogo();
				if("".equals(nombre)) {
					JOptionPane.showMessageDialog(null, "Ingresa un Nombre");
					CrearTabla(cat.getArticulos());
				}
				else 
					CrearTabla(cat.BuscarArticulo(nombre));
			}});
		
		
		//BOTON BUSCAR (POR ID)
		//De igual forma tenemos la condicional para saber si el recuadro de texto esta vacio
		//Como el ID del articulo es unico, nos va a ensenar un solor articulo en la tabla, pero
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				String id = txtBuscarId.getText();
				if("".equals(id)) {
					JOptionPane.showMessageDialog(null, "Ingresa un Nombre");
					CrearTabla(cat.getArticulos());
				}
				else 
					CrearTabla(cat.BuscarArticulo(Integer.parseInt(id)));
		}});
		
		//OPCIONES DE TABLA
		//Esto es lo unico diferente en los constructones, al seleccionar la el articulo de la tabla
		//entonces se crea el articulo y se envia a el frame de cotizacion para
		//que se obtenga la informacion e ir creando una nueva tabla 
		jtProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = jtProductos.getSelectedRow();
				String tempId =jtProductos.getValueAt(fila, 0).toString();
				
				int id = Integer.parseInt(tempId);
				Articulo art = cat.CrearArticulo(id);
				setArticulo(art);
			}
		});
		
		
		/****************************************************/
		//Agregamos todo al panel
		add(scroll);
		add(btnCargar);
		add(lblNombreBusqueda);
		add(txtBuscarNombre);
		add(btnBuscarNombre);
		add(lblIdBusqueda);
		add(txtBuscarId);
		add(btnBuscarId);
		add(btnCotizacion);

	}
	
	public PanelProductos(CatalogoCliente catCliente) {
		this.catCliente=catCliente;
		cotizacion=new Cotizacion();
		cat= new Catalogo();
		setLayout(null);
			
		//Creamos todas las Etiquetas, todos los cuadros de texto
		jtProductos = new JTable();
		scroll = new JScrollPane(jtProductos);
		CrearTabla(cat.getArticulos());
		
		btnCargar = new JButton("Recargar Tabla");
		btnBuscarNombre = new JButton("Buscar");
		btnBuscarId = new JButton("Buscar");
		
		lblNombreBusqueda = new JLabel("Nombre: ");
		lblIdBusqueda = new JLabel("ID: ");
		
		txtBuscarNombre = new JTextField();
		txtBuscarId = new JTextField();
		
		btnCotizacion = new JButton("Crear Cotización");

		//Configuramos cada etiqueta, cuadros de texto y botones
		
		scroll.setBounds(10, 11, 480, 262);
		scroll.setVisible(true);
		
		btnCargar.setBounds(107, 284, 250, 23);
		btnCargar.setToolTipText("Actualiza la tabla");
		
		lblNombreBusqueda.setBounds(10, 318, 66, 23);
		btnBuscarNombre.setBounds(390, 318, 100, 23);
		btnBuscarNombre.setToolTipText("B\u00FAsqueda por nombre");
		txtBuscarNombre.setBounds(107, 318, 250, 23);
		txtBuscarNombre.setBackground(Color.WHITE);
		txtBuscarNombre.setForeground(Color.BLACK);
		
		
		lblIdBusqueda.setBounds(10, 352, 80, 23);
		txtBuscarId.setBounds(107, 352, 250, 23);
		txtBuscarId.setBackground(Color.WHITE);
		txtBuscarId.setForeground(Color.BLACK);
		btnBuscarId.setBounds(390, 352, 100, 23);
		btnBuscarId.setToolTipText("B\u00FAsqueda por ID");

		btnCotizacion.setLocation(10, 386);
		btnCotizacion.setSize(200, 23);
				
		/*************EN ESTA PARTE VEMOS LO QUE PASA CUANDO PRESIONAN CADA BOTON***********************/

		//BOTON CARGAR
		//Actualiza la tabla
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				CrearTabla(cat.getArticulos());
			}
		});
		
		
		//BOTON BUSCAR (POR NOMBRE)
		//Tenemos la condicional para saber si el recuadro de busqueda esta vacio, si es asi
		//Entonces nos manda un error y carga el catalogo completo
		//En caso de que se mande un texto correctamente
		//Nos van a aparecer los articulos en la tabla, ya que pueden tener el mismo nombre
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				String nombre = txtBuscarNombre.getText();
				if("".equals(nombre)) {
					JOptionPane.showMessageDialog(null, "Ingresa un Nombre");
					CrearTabla(cat.getArticulos());
				}
				else 
					CrearTabla(cat.BuscarArticulo(nombre));
			}});
		
		
		//BOTON BUSCAR (POR ID)
		//De igual forma tenemos la condicional para saber si el recuadro de texto esta vacio
		//Como el ID del articulo es unico, nos va a ensenar un solor articulo en la tabla, pero
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cat = new Catalogo();
				String id = txtBuscarId.getText();
				if("".equals(id)) {
					JOptionPane.showMessageDialog(null, "Ingresa un Nombre");
					CrearTabla(cat.getArticulos());
				}
				else 
					CrearTabla(cat.BuscarArticulo(Integer.parseInt(id)));
		}});
		
		//CREAR COTIZACION
		btnCotizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cotInfo = new CotizacionInformacion();
				cot = new Cotizaciones(cotInfo);
				cot.setVisible(true);	
				catCliente.setVisible(false);
				}
		});
		
		/****************************************************/
		//Agregamos todo al panel
		add(scroll);
		add(btnCargar);
		add(lblNombreBusqueda);
		add(txtBuscarNombre);
		add(btnBuscarNombre);
		add(lblIdBusqueda);
		add(txtBuscarId);
		add(btnBuscarId);
		add(btnCotizacion);

	}
	//Tomando en cuenta que debemos crear muchas veces la tabla desde 0 para que se limpie
	//creamos una funcion que lo crea, segun lo que se envie
	public void CrearTabla(ArrayList<Articulo> art) {
		DefaultTableModel modelo =new  DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nombre", "Precio", "Stock"}
			){
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						Integer.class, String.class, Float.class, Integer.class
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
			jtProductos.setModel(modelo);
			
			for(Articulo temp :art) {
				Object[] fil = new Object[4];
						fil[0]=temp.getId();
						fil[1]=temp.getNombre();
						fil[2]=temp.getPrecio();
						fil[3]=temp.getStock();
						modelo.addRow(fil);
			}		
			
			jtProductos.getColumnModel().getColumn(0).setPreferredWidth(10);
			center.setHorizontalAlignment(JLabel.CENTER);
			jtProductos.getColumnModel().getColumn(0).setCellRenderer(center);
	}

	public JButton getBtnCotizacion() {
		return btnCotizacion;
	}

	public void setBtnCotizacion(JButton btnCotizacion) {
		this.btnCotizacion = btnCotizacion;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	

}
