package frontend.cotizacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.cotizacion.Cotizacion;
import backend.cotizacion.ListaCotizaciones;
import backend.cotizacion.Persona;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clientes extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ListaCotizaciones listaCotizaciones;
	private DefaultTableCellRenderer center = new DefaultTableCellRenderer();
	private JScrollPane scroll;
	private JTable table;
	private JLabel lblResponsable;
	private JLabel lblPersona;
	private JLabel lblInformacin;
	private JLabel lblDireccin;
	private JLabel lblEmpresa;
	private JLabel lblTelefono;
	private JLabel lblRfc;
	private JTextField txtResponsable;
	private JTextField txtTipoPersona;
	private JTextField txtRFC;
	private JTextField txtTelefono;
	private JTextField txtEmpresa;
	private JTextField txtDireccion;
	private JButton btnLimpiar;
	private JButton btnRecargar;
	
	public Clientes() {
		setTitle("CLIENTES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		listaCotizaciones = new ListaCotizaciones();
		
		table = new JTable();
		scroll = new JScrollPane(table);
		CrearTabla(listaCotizaciones.getCotizaciones());
		lblResponsable = new JLabel("Responsable:");
		lblPersona = new JLabel("Persona:");
		lblRfc = new JLabel("RFC:");
		lblTelefono = new JLabel("Telefono:");
		lblEmpresa = new JLabel("Empresa:");
		lblDireccin = new JLabel("Direcci\u00F3n:");
		lblInformacin = new JLabel("Informaci\u00F3n");
		txtResponsable = new JTextField();
		txtTipoPersona = new JTextField();
		txtRFC = new JTextField();
		txtTelefono = new JTextField();
		txtEmpresa = new JTextField();
		txtDireccion = new JTextField();
		btnLimpiar = new JButton("Limpiar");
		btnRecargar = new JButton("Recargar");
		
		btnRecargar.setBounds(500, 234, 89, 23);
		scroll.setBounds(10, 11, 480, 250);
		scroll.setVisible(true);
		lblInformacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblResponsable.setBounds(500, 55, 80, 14);
		lblPersona.setBounds(500, 80, 80, 14);
		lblRfc.setBounds(500, 105, 80, 14);
		lblTelefono.setBounds(500, 130, 80, 14);
		lblEmpresa.setBounds(500, 155, 80, 14);
		lblDireccin.setBounds(500, 180, 80, 14);
		lblInformacin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInformacin.setBounds(500, 11, 339, 23);
		txtResponsable.setEditable(false);
		txtResponsable.setBounds(590, 52, 249, 20);
		contentPane.add(txtResponsable);
		txtResponsable.setColumns(10);
		txtTipoPersona.setEditable(false);
		txtTipoPersona.setColumns(10);
		txtTipoPersona.setBounds(590, 77, 249, 20);
		txtRFC.setEditable(false);
		txtRFC.setColumns(10);
		txtRFC.setBounds(590, 102, 249, 20);
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(590, 127, 249, 20);
		txtEmpresa.setEditable(false);
		txtEmpresa.setColumns(10);
		txtEmpresa.setBounds(590, 152, 249, 20);
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(590, 177, 249, 46);
		btnLimpiar.setBounds(750, 234, 89, 23);
		
		
		/********************************************************************/
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				String id = table.getValueAt(fila, 0).toString();
				
				Cotizacion cot = listaCotizaciones.CrearInformacion(id);
				Persona res = cot.getResponsable();
				
				txtResponsable.setText(res.getNombre());
				txtTipoPersona.setText(cot.getTipoPersona().toString());
				txtRFC.setText(res.getRfc());
				txtTelefono.setText(res.getNoTelefono());
				txtEmpresa.setText(cot.getEmpresa());
				txtDireccion.setText(res.getDireccion());
				
				
			}
		});
		
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaCotizaciones = new ListaCotizaciones();
				CrearTabla(listaCotizaciones.getCotizaciones());
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		
		
		/********************************************************************/
		
		contentPane.add(scroll);
		contentPane.add(lblResponsable);
		contentPane.add(lblPersona);
		contentPane.add(lblRfc);
		contentPane.add(lblTelefono);
		contentPane.add(lblEmpresa);
		contentPane.add(lblDireccin);
		contentPane.add(lblInformacin);
		contentPane.add(txtTipoPersona);
		contentPane.add(txtRFC);
		contentPane.add(txtTelefono);
		contentPane.add(txtEmpresa);
		contentPane.add(txtDireccion);
		contentPane.add(btnLimpiar);
		contentPane.add(btnRecargar);
		
		
	}
	
	public void CrearTabla(ArrayList<Cotizacion> coti) {
		
		DefaultTableModel modelo =new  DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Responsable", "Persona","Teléfono", "Dirección" }
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
			
			for(Cotizacion temp :coti) {
				Object[] fil = new Object[5];
						fil[0]=temp.getIdCotizacion();
						fil[1]=temp.getResponsable().getNombre();
						fil[2]=temp.getTipoPersona().toString();
						fil[3]=temp.getResponsable().getNoTelefono();
						fil[4]=temp.getResponsable().getDireccion();
						
						modelo.addRow(fil);
			}		
			
			table.getColumnModel().getColumn(0).setPreferredWidth(5);
			center.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(center);
	}
	
	public void Limpiar() {
		txtResponsable.setText(null);
		txtTipoPersona.setText(null);
		txtRFC.setText(null);
		txtTelefono.setText(null);
		txtEmpresa.setText(null);
		txtDireccion.setText(null);
	}
}
