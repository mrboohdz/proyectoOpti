package backend.cotizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import backend.conexion.baseDatos;
import javax.swing.JOptionPane;

public class ListaClientes {
	
	private ArrayList<Cliente> clientes;
	
	//Base de datos
	private ResultSet rs = null; 
	private PreparedStatement ps = null;
	private baseDatos bd = null;
	private Connection con = null;
	
	public ListaClientes() {
		clientes = new ArrayList<Cliente>();
		try {
			bd = new baseDatos();
			con = bd.getConexion();
			
			String SQL = "SELECT * FROM cliente";
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//Vamos creando una cotizacion con cada dato que se tiene, y vamos a ir agregando esta a 
				//la lista de clientes
				Cliente per = new Cliente();
				
				per.setNombre(rs.getString("nombre"));
				per.setDireccion(rs.getString("direccion"));
				per.setNoTelefono(rs.getString("telefono"));
				per.setRfc(rs.getString("rfc"));
				per.setTipoPersona(TipoPersona.valueOf(rs.getString("tipoPersona")));
				per.setRazonSocial(rs.getString("razonSocial"));
				clientes.add(per);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

}
