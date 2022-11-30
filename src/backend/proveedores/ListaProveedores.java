package backend.proveedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import backend.conexion.baseDatos;


public class ListaProveedores {
	private ResultSet rs = null; 
	private PreparedStatement ps = null;
	private static baseDatos bd = new baseDatos();
	private static final Connection con = bd.getConexion();
	
	private ArrayList<Proveedor> proveedores;
	
	public ListaProveedores() {
		try {
			//Hacemos una lista de usuarios
			proveedores = new ArrayList<Proveedor>();
			
			//Se hace el string para acceder a la base de datos
			String SQL = "SELECT id, nombre, direccion,telefono, giro FROM proveedores";
			
			//Se preparan los datos para agregarlos a la lists
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			//Llenamos la lista de usuarios
			while(rs.next()) {
				Proveedor temp = new Proveedor();
				
				temp.setId((rs.getInt("id")));
				temp.setNombre(rs.getString("nombre"));
				temp.setTelefono(rs.getString("telefono"));
				temp.setDireccion(rs.getString("direccion"));
				temp.setGiro(rs.getString("giro"));
				
				
				proveedores.add(temp);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	
	

	public void AgregarProveedor(Proveedor nvo) {
		String SQL = "INSERT INTO proveedores(nombre, direccion,telefono, giro) VALUES(?,?,?,?)";
		try {
			//Tomamos cada uno de los componentes del usuario que nos mandaron para agregarlo a la base de datos
			ps = con.prepareStatement(SQL);
			
			ps.setString(1, nvo.getNombre());
			ps.setString(2, nvo.getDireccion());
			ps.setString(3, nvo.getTelefono());
			ps.setString(4, nvo.getGiro());
			
			int res = ps.executeUpdate();
			
			//Unicamente para confirmar que se guardo de forma correcta
			if(res>0)
				JOptionPane.showMessageDialog(null, "Usuario Guardado");
			else
				JOptionPane.showMessageDialog(null, "Error al guardar");
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void ModificarDireccion(String cont, String id) {
		String SQL = "UPDATE proveedores SET direccion =? WHERE id ='"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, cont);
			
			int res = ps.executeUpdate();
			if(res>0)
				JOptionPane.showMessageDialog(null, "Dirección modificada");
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void ModificarNombre(String nombre, String id) {
		String SQL = "UPDATE proveedores SET nombre =? WHERE id ='"+id+"'";
		try {
			
			ps = con.prepareStatement(SQL);
			ps.setString(1, nombre);
			
			int res = ps.executeUpdate();
			if(res>0)
				JOptionPane.showMessageDialog(null, "Nombre modificado");
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	  public void ModificarGiro(String giro, String id) { 
		  String SQL ="UPDATE proveedores SET giro =? WHERE id ='"+id+"'"; 
		  try { 
				  ps = con.prepareStatement(SQL); ps.setString(1, giro);
				  int res = ps.executeUpdate(); 
				  if(res>0) 
					  JOptionPane.showMessageDialog(null, "Giro Modificado"); 
				  else 
					  JOptionPane.showMessageDialog(null,"Error al modificar"); 
		  	}
		  catch(SQLException e) {
			  System.out.println(e); 
			  } 
		  }
	 
	
	public void ModificarTelefono(String tel, String id) {
		String SQL = "UPDATE proveedores SET telefono =? WHERE id = '"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, tel);
			
			int res = ps.executeUpdate();
			
			if(res>0)
				JOptionPane.showMessageDialog(null, "Teléfono Modificado");
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Proveedor> BuscarProveedor(String nombre){
		
		ArrayList<Proveedor> temp = new ArrayList<Proveedor>();
		
		for(Proveedor prueba : proveedores)
			if(prueba.getNombre().equals(nombre))
				temp.add(prueba);
		
		return temp;

	}
	
	public ArrayList<Proveedor> BuscarID(String id){
		int idtemp= Integer.parseInt(id);
		ArrayList<Proveedor> temp = new ArrayList<Proveedor>();
		
		for(Proveedor prueba : proveedores)
			if(prueba.getId()==idtemp)
				temp.add(prueba);
		
		return temp;
	}
	
	public boolean Confirmar(String nuevo) {
		
		int cen = 0;
		
		for(Proveedor temp : proveedores)
			if(temp.getNombre().equals(nuevo)) {
				cen = 1;
				break;
			}
		
		if(cen==1)
			return true;
		else
			return false;
		
	}

	
	public void EliminarProveedor(String id) {
		String SQL = "DELETE FROM proveedores WHERE id = '"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			
			int res = ps.executeUpdate();
			
			if(res>0)
				JOptionPane.showMessageDialog(null, "Proveedor Eliminado");
			else
				JOptionPane.showMessageDialog(null, "Error al Eliminar");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public ArrayList<Proveedor> getProveedores() {
		return proveedores;
	}


	public void setProvedores(ArrayList<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
}
