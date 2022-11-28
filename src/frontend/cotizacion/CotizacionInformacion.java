package frontend.cotizacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.cotizacion.Cotizacion;
import backend.cotizacion.ListaCotizaciones;
import backend.cotizacion.Persona;
import backend.cotizacion.TipoPersona;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CotizacionInformacion extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Creamos todos los atributos que tendra nuestro Frame
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtRFC;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtEmision;
	private JTextField txtVencimiento;
	private JTextField txtRazonSocial;
	private JTextField txtTotal;
	private JButton btnAceptar;
	private JTextField txtEmpresa;
	private JLabel lblRazonSocial;
	private JLabel lblTotal;
	private JLabel lblVencimiento;
	private JLabel lblFechaEmision;
	private JLabel lblEmpresa;
	private JLabel lblDireccion;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblRFC;
	private JLabel lblPersona;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipoPersona;
	private JLabel lblNombre;
	private JLabel lblInformacion;	
	private Cotizacion cot;
	private ListaCotizaciones listCot;
	@SuppressWarnings("unused")
	private Cotizaciones cotFront;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CotizacionInformacion() {
		cot = new Cotizacion();
		cotFront = new Cotizaciones(this);
		listCot = new ListaCotizaciones();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		//Creamos todos los atributos que necesitamos 
		lblInformacion = new JLabel("Formulario de Informacion");
		lblInformacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre = new JLabel("Nombre:*");
		lblRFC = new JLabel("RFC:");
		lblTelefono = new JLabel("Tel\u00E9fono:*");
		lblPersona = new JLabel("Persona: ");
		lblEmail = new JLabel("Email:*");
		lblDireccion = new JLabel("Direcci\u00F3n:*");
		lblFechaEmision = new JLabel("Emisi\u00F3n:");
		lblVencimiento = new JLabel("Vencimiento:");
		txtNombre = new JTextField();
		txtRFC = new JTextField();
		txtEmail = new JTextField();
		txtTelefono = new JTextField();
		txtDireccion = new JTextField();
		txtEmision = new JTextField();
		txtVencimiento = new JTextField();
		lblEmpresa = new JLabel("Empresa:");
		txtEmpresa = new JTextField();
		lblRazonSocial = new JLabel("Raz\u00F3n Social:");
		txtRazonSocial = new JTextField();
		lblTotal = new JLabel("Total:");
		txtTotal = new JTextField();
		cbTipoPersona = new JComboBox();
		btnAceptar = new JButton("Aceptar");
		
		
		//Los personalizamos con la vista que tendran
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacion.setBounds(10, 11, 414, 23);
		lblTelefono.setBounds(10, 162, 80, 14);
		lblNombre.setBounds(10, 62, 80, 14);
		lblRFC.setBounds(10, 112, 80, 14);
		lblPersona.setBounds(10, 87, 80, 14);
		lblEmail.setBounds(10, 137, 46, 14);
		lblDireccion.setBounds(10, 187, 80, 14);
		lblFechaEmision.setBounds(10, 271, 80, 14);
		lblVencimiento.setBounds(248, 274, 80, 14);
		txtNombre.setBounds(100, 59, 324, 20);
		txtNombre.setColumns(10);
		txtRFC.setBounds(100, 109, 324, 20);
		txtRFC.setColumns(10);
		txtEmail.setBounds(100, 134, 324, 20);
		txtEmail.setColumns(10);
		txtTelefono.setBounds(100, 159, 324, 20);
		txtTelefono.setColumns(10);
		txtDireccion.setBounds(100, 184, 324, 20);
		txtDireccion.setColumns(10);
		txtEmision.setEditable(false);
		txtEmision.setBounds(100, 268, 86, 20);
		txtEmision.setColumns(10);
		txtEmision.setText(cot.FechaHoy());
		txtVencimiento.setEditable(false);
		txtVencimiento.setColumns(10);
		txtVencimiento.setBounds(338, 271, 86, 20);
		txtVencimiento.setText(cot.FechaVenc());
		lblEmpresa.setBounds(10, 215, 80, 14);
		txtEmpresa.setToolTipText("OPCIONAL");
		txtEmpresa.setColumns(10);
		txtEmpresa.setBounds(100, 212, 324, 20);
		lblRazonSocial.setBounds(10, 243, 80, 14);
		txtRazonSocial.setToolTipText("OPCIONAL");
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(100, 240, 324, 20);
		lblTotal.setBounds(100, 309, 46, 14);
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(156, 306, 86, 20);
		cbTipoPersona.setModel(new DefaultComboBoxModel(TipoPersona.values()));
		cbTipoPersona.setBounds(100, 83, 142, 22);
		btnAceptar.setBounds(338, 345, 86, 23);
		
		/***************************BOTONES**************************************/
		
		//BOTON ACEPTAR
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//Lo primero es meter la informacion de los cuadros de texto a Stings
				String nombre = txtNombre.getText();
				String tipoPersona = cbTipoPersona.getSelectedItem().toString();
				String rfc = txtRFC.getText();
				String email = txtEmail.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				String empresa = txtEmpresa.getText();
				String razonS = txtRazonSocial.getText();
				String emision = txtEmision.getText();
				String vencimiento = txtVencimiento.getText();
				String total = txtTotal.getText();
				
				//No todas las opciones son obligatorias, por lo que solo verificamos que aquellas obligatorias
				//esten no nulas
				if("".equals(nombre)||"".equals(telefono)||"".equals(email) ||"".equals(direccion))
					JOptionPane.showMessageDialog(null, "Campos faltantes");
				else {
					//Teniendo todos los datos, creamos una nueva cotizacion que tendra todos los datos recaudados
					//Como no podemos guardar arreglos en la base de datos, los articulos no se gardaran
					//Pero si los datos, que es lo que se usa normalmente en campo
					
					cot = new Cotizacion();
					cot.setTipoPersona(TipoPersona.valueOf(tipoPersona));
					cot.setTotal(Float.parseFloat(total));
					cot.setFechaEmision(emision);
					cot.setFechaVencimiento(vencimiento);
					
					Persona persona	= new Persona();
					persona.setNombre(nombre);
					persona.setEmail(email);
					persona.setDireccion(direccion);
					persona.setNoTelefono(telefono);
					persona.setRfc(rfc);
					
					cot.setResponsable(persona);
					cot.setEmpresa(empresa);
					cot.setRazonSocial(razonS);		
					
					//Se envia a la clase que lo guardara en la base de datos
					//La lista de cotizaciones solamente sera visible para los usuarios
					listCot.CrearCotizacion(cot);
					dispose();
				}

			}
		});
		
		/****************************************************************/
		
		
		//Los agregamos a nuestro Frame
		contentPane.add(lblNombre);
		contentPane.add(lblInformacion);
		contentPane.add(lblRFC);
		contentPane.add(lblTelefono);
		contentPane.add(lblPersona);
		contentPane.add(lblEmail);
		contentPane.add(lblDireccion);
		contentPane.add(lblFechaEmision);		
		contentPane.add(lblVencimiento);
		contentPane.add(txtNombre);
		contentPane.add(txtRFC);
		contentPane.add(txtEmail);
		contentPane.add(txtTelefono);
		contentPane.add(txtDireccion);
		contentPane.add(txtEmision);
		contentPane.add(txtVencimiento);
		contentPane.add(lblEmpresa);
		contentPane.add(txtEmpresa);
		contentPane.add(lblRazonSocial);
		contentPane.add(txtRazonSocial);
		contentPane.add(lblTotal);
		contentPane.add(txtTotal);
		contentPane.add(cbTipoPersona);
		contentPane.add(btnAceptar);

	}
	

	public Cotizacion getCot() {
		return cot;
	}

	public void setCot(Cotizacion cot) {
		this.cot = cot;
	}


	public JTextField getTxtTotal() {
		return txtTotal;
	}


	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}
}
