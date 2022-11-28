package frontend.cotizacion;

import java.util.ArrayList;
import javax.swing.*;
import backend.catalogo.*;
import backend.cotizacion.Cotizacion;
import frontend.catalogo.PanelProductos;

import javax.swing.table.*;
import java.awt.event.*;


public class Cotizaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Creacion de atributos
	private PanelProductos panelProductos;
	private Cotizacion cot;
	private JTable jtArticulos;
	private JTextField txtCantidad;
	private JLabel lblCantidad;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField txtTotal;
	private JLabel lblTotal;
	private int fila;
	private JButton btnSiguiente;
	private float tot;
	private JScrollPane scroll;
	@SuppressWarnings("unused")
	private CotizacionInformacion cotInfo;
	
	public Cotizaciones(CotizacionInformacion cotInfo) {
		cot = new Cotizacion();
		this.cotInfo=cotInfo;
		
		setTitle("CREA TU COTIZACION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1009,469);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		//Creacion de etiquetas, cajas de texto, botones, la tabla de contenido y el panel de Productos
		lblCantidad = new JLabel("Cantidad:");
		txtCantidad = new JTextField();
		txtCantidad.setToolTipText("Ingrese la cantidad del articulo seleccionado");
		jtArticulos = new JTable();
		scroll = new JScrollPane(jtArticulos);
		scroll.setBounds(628, 11, 350, 262);
		scroll.setVisible(true);
		
		panelProductos = new PanelProductos(this);		
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");
		txtTotal = new JTextField();
		lblTotal = new JLabel("Total:");
		btnSiguiente = new JButton("Siguiente");
		
		
		//Configurar como se ve cada uno de estos atributos, además de establecer su lugar
		jtArticulos.setLocation(628, 29);
		jtArticulos.setSize(350, 244);
		
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(506, 104, 112, 23);
		txtCantidad.setBounds(516, 125, 86, 20);
		txtCantidad.setColumns(10);
		
		panelProductos.setBounds(0, 0, 502, 429);
		panelProductos.getBtnCotizacion().setVisible(false);

		btnAgregar.setBounds(513, 154, 89, 23);
		
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(513, 188, 89, 23);
		
		txtTotal.setEditable(false);
		txtTotal.setBounds(871, 284, 107, 20);
		txtTotal.setColumns(10);
		
		lblTotal.setBounds(815, 287, 46, 14);
		
		btnSiguiente.setToolTipText("No podr\u00E1s hacer modificaciones en tus articulos m\u00E1s adelante");
		btnSiguiente.setBounds(895, 396, 89, 23);
		
		/***************************CONFIGURACION DE BOTONES Y TABLA***********************************************/
		
		//BOTON AGREGAR 
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cantTemp = txtCantidad.getText();
				
				//Primero vamos a evaluar si en la caja de texto de la cantidad no esta vacia 
				if("".equals(cantTemp))
					JOptionPane.showMessageDialog(null, "Inserte cantidad");
				else {
					//Si no lo esta, recordemos que ya se creo el articulo en el Panel de productos, por lo que solamente lo vamos a agregar
					Articulo temp = panelProductos.getArticulo();
					ArrayList<Articulo> art = cot.getListaArticulos();
					
					//Si el arreglo esta vacio directamente lo vamos a agregar
					if(art.size()==0)
						cot.NuevoArticulo(temp,Integer.parseInt(cantTemp));
					else {
						//En caso contrario, vamos a evaluar si ya existe el articulo, 
						int cen =0;

						for(Articulo ola : art) 
							//Si ya existe, nos pide editar la cantidad del articulos que requieres
							if(ola.getId()==temp.getId()) {
								cen=1;
								String cant = JOptionPane.showInputDialog("Articulo ya ingresado, ingrese nueva cantidad:");
								ola.setCantidad(Integer.parseInt(cant));
								ola.CalcularTotal();
								break;
							}
						//usamos un centinela para saber si se encontro, de ser asi, el articulo se modifica
						//sino, se agrega sin mas
						if(cen==1) 
							JOptionPane.showMessageDialog(null, "Articulo modificado");

						else 
							cot.NuevoArticulo(temp,Integer.parseInt(cantTemp));					
					}
					ActualizarTabla(art);
					txtCantidad.setText("");
				}
			}
		});
	
		
		//TABLA
		//Cuando se selecciona un articulo de la tabla, guardamos la fila y ponmos visible el boton de eliminar
		jtArticulos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = jtArticulos.getSelectedRow();
				btnEliminar.setEnabled(true);
			}
			
		});
		
		
		//BOTON ELIMINAR
		//teniendo en cuenta que la fila es el index de el articulo, podemos eliminarlo con este.
		//Se envia a cotizacion para eliminarlo
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este articulo?");
				
				if(input == 0) {
					cot.EliminarArticulo(fila);
					ArrayList<Articulo> art = cot.getListaArticulos();
					ActualizarTabla(art);
				}
			}
		});

		//BOTON SIGUIENTE
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("".equals(txtTotal.getText()))
					JOptionPane.showMessageDialog(null, "Ingresa Articulos");
				else {
					cotInfo.setCot(cot);
					cotInfo.getTxtTotal().setText(String.valueOf(tot));
					cotInfo.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		/**************************************************************************/
		

		//Agregar todo al Frame
		getContentPane().add(lblCantidad);
		getContentPane().add(txtCantidad);
		getContentPane().add(scroll);
		getContentPane().add(panelProductos);
		getContentPane().add(btnAgregar);
		getContentPane().add(btnEliminar);
		getContentPane().add(txtTotal);
		getContentPane().add(lblTotal);
		getContentPane().add(btnSiguiente);

		
	}
	
	//Cada que se agrega, eliminar o modifica un articulo, vamos a hacer la tabla
	public void ActualizarTabla(ArrayList<Articulo> art) {
		tot = 0.0f;
		DefaultTableModel modelo =new  DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID","Nombre", "Cantidad", "Total"}
			){

				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						Integer.class, String.class, Integer.class, Float.class
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
			jtArticulos.setModel(modelo);
			
			//Ademas de ir poniendo los articulos, va haciendo la suma del total, de manera que se actualiza con cada modificacion
			for(Articulo temp :art) {
				Object[] fil = new Object[4];
						fil[0]=temp.getId();						
						fil[1]= temp.getNombre();
						fil[2]= temp.getCantidad();
						fil[3]= temp.getTotal();
						tot+=temp.getTotal();
						modelo.addRow(fil);
			}
			jtArticulos.getColumnModel().getColumn(0).setPreferredWidth(10);
			txtTotal.setText(String.valueOf(tot));
	}
	


	public float getTot() {
		return tot;
	}

	public void setTot(float tot) {
		this.tot = tot;
	}
}
