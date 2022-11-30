package backend.cotizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import backend.conexion.baseDatos;

//Usaremos esta clase para hacer otra conexion a la base de datos, esta vez en el esquema de
//cotizacion
public class ListaCotizaciones {
	
	private ArrayList<Cotizacion> cotizaciones; 
	
	//Base de datos
	private ResultSet rs = null; 
	private PreparedStatement ps = null;
	private baseDatos bd = null;
	private Connection con = null;
		
	public ListaCotizaciones(){
		cotizaciones = new ArrayList<Cotizacion>();
		try {
			bd = new baseDatos();
			con = bd.getConexion();
			//Se accede a la base de datos para crear la lista de clientes, que viene de aquellos
			//que hicieron una cotizacion.
			//La lista de articulos no se guarda, lamentablemente no se puede ver que se cotizo
			//SELECT idcotizacion, responsable, cliente.tipoPersona, cliente.telefono, cliente.direccion FROM cotizacion LEFT JOIN cliente ON responsable = cliente.nombre 
			String SQL = "SELECT idcotizacion, cotizacion.responsable, cliente.tipoPersona, cliente.telefono, cliente.direccion, cliente.razonSocial, cliente.rfc FROM cotizacion LEFT JOIN cliente ON responsable = cliente.nombre ";
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//Vamos creando una cotizacion con cada dato que se tiene, y vamos a ir agregando esta a 
				//la lista de clientes
				Cotizacion cot = new Cotizacion();
				Cliente per = new Cliente();
				
				cot.setIdCotizacion(rs.getInt("idcotizacion"));
				per.setTipoPersona(TipoPersona.valueOf(rs.getString("tipoPersona")));
				per.setNombre(rs.getString(2));
				per.setDireccion(rs.getString("direccion"));
				per.setNoTelefono(rs.getString("telefono"));
				per.setRfc(rs.getString("rfc"));
				per.setRazonSocial(rs.getString("razonSocial"));
				cot.setResponsable(per);
				
				cotizaciones.add(cot);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	//Recibimos la cotizacion y le vamos sacando los datos para guardarlos en la base de datos
	public void CrearCotizacion(Cotizacion cot) {
		//Se prepara el String para guardar los datos
		String SQL = "INSERT INTO cotizacion(tipoPersona, total, fechaEmision, fechaVencimiento, responsable,"
				+ "empresa, direccion, telefono, rfc, razonSocial) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			bd = new baseDatos();
			con = bd.getConexion();
			//Despues vamos obteniendo los datos de la cotizacion enviada para irla metiendo en la base
			ps = con.prepareStatement(SQL);
			//ps.setString(1, cot.tipoPersona(cot.getTipoPersona()));
			ps.setFloat(2, cot.getTotal());
			ps.setString(3, cot.FechaHoy());
			ps.setString(4, cot.FechaVenc());
			ps.setString(5, cot.getResponsable().getNombre());
			//ps.setString(6, cot.getEmpresa());
			ps.setString(7, cot.getResponsable().getDireccion());
			ps.setString(8, cot.getResponsable().getNoTelefono());
			ps.setString(9, cot.getResponsable().getRfc());
			//ps.setString(10, cot.getRazonSocial());
			
			//La condicional es para saber si la insercion de datos se hizo de forma correcta
			//Si la actualizacion se llevo a cabo correctamente nos dar� un 1
			int res = ps.executeUpdate();
			if(res>0) {
				try {
					bd = new baseDatos();
					con = bd.getConexion();
					//Esto es para obtener el id y que el usuario pueda guardarlo
					int id=0;
					SQL="SELECT idcotizacion FROM cotizacion WHERE responsable = '"+cot.getResponsable().getNombre()+"'";
					ps=con.prepareStatement(SQL);
					rs = ps.executeQuery();
					
					while(rs.next())
						id = rs.getInt("idcotizacion"); 
					
					JOptionPane.showMessageDialog(null, "Cotizacion Guardada\nSu numero de cotizacion es: "+id);
					
				}catch(SQLException e) {
					System.out.println(e);
				}
				
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al guardar");
			
		}catch(SQLException e){
			System.out.println(e);
		}
		
	}

	public Cotizacion CrearInformacion(String id) {
		Cotizacion coti = new Cotizacion();
		
		for(Cotizacion temp : cotizaciones)
			if(temp.getIdCotizacion() == Integer.parseInt(id)) {
				coti = temp;
				break;
			}
		
		return coti;
				
	}
	
	public ArrayList<Cotizacion> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(ArrayList<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}
	
	
}
