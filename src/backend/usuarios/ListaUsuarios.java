package backend.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import backend.conexion.baseDatos;
import backend.cotizacion.Cliente;

public class ListaUsuarios {
	private ResultSet rs = null; 
	private PreparedStatement ps = null;
	private static baseDatos bd = new baseDatos();
	private static final Connection con = bd.getConexion();
	
	private ArrayList<Usuario> usuarios;
	
	public ListaUsuarios() {
		try {
			//Hacemos una lista de usuarios
			usuarios = new ArrayList<Usuario>();
			
			//Se hace el string para acceder a la base de datos
			String SQL = "SELECT idusuario, usuario, nombre,contrasenia, tipoUsuario FROM usuarios";
			
			//Se preparan los datos para agregarlos a la lists
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			//Llenamos la lista de usuarios
			while(rs.next()) {
				Usuario temp = new Usuario();
				Cliente pers = new Cliente();
				
				temp.setId((rs.getInt("idusuario")));
				temp.setUsuario(rs.getString("usuario"));
				pers.setNombre(rs.getString("nombre"));
				temp.setNombre(pers);
				temp.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipoUsuario")));
				temp.setContrasenia(rs.getString("contrasenia"));
				
				
				usuarios.add(temp);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	
	public int iniciarSesion(String user, String contrasenia) {
		Usuario temp = new Usuario();
		
		int cen = 0 ;
		
		for(Usuario prueba : usuarios)
			if(prueba.getUsuario().equals(user)) {
				cen =1 ;
				temp = prueba;
				break;
			}
		
		if(cen == 1) {
			if(contrasenia.equals(temp.getContrasenia())) {
				if(temp.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR))
					cen = 2;
			}else
				cen = 0;
		}
		
		return cen;
	}

	public void AgregarUsuario(Usuario nvo) {
		String SQL = "INSERT INTO usuarios(usuario, contrasenia, nombre, tipoUsuario) VALUES(?,?,?,?)";
		try {
			//Tomamos cada uno de los componentes del usuario que nos mandaron para agregarlo a la base de datos
			ps = con.prepareStatement(SQL);
			ps.setString(1, nvo.getUsuario());
			ps.setString(2, nvo.getContrasenia());
			ps.setString(3, nvo.getNombre().getNombre());
			ps.setString(4, nvo.getTipoUsuario().toString());
			
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
	
	public void ModificarContrasenia(String cont, String id) {
		String SQL = "UPDATE usuarios SET contrasenia =? WHERE idusuario ='"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, cont);
			
			int res = ps.executeUpdate();
			if(res>0)
				JOptionPane.showMessageDialog(null, "Contrase�a modificada");
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void ModificarNombre(String nombre, String id) {
		String SQL = "UPDATE usuarios SET nombre =? WHERE idusuario ='"+id+"'";
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
	
	public void ModificarPrivilegio(String priv, String id) {
		String SQL = "UPDATE usuarios SET tipoUsuario =? WHERE idusuario ='"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, priv);
			
			int res = ps.executeUpdate();
			if(res>0)
				JOptionPane.showMessageDialog(null, "Privilegio Modificado");
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void ModificarUsuario(String nvo, String id) {
		String SQL = "UPDATE usuarios SET usuario =? WHERE idusuario = '"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, nvo);
			
			int res = ps.executeUpdate();
			
			if(res>0)
				JOptionPane.showMessageDialog(null, "Usuario Modificado");
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Usuario> BuscarUsuario(String usuario){
		
		ArrayList<Usuario> temp = new ArrayList<Usuario>();
		
		for(Usuario prueba : usuarios)
			if(prueba.getUsuario().equals(usuario))
				temp.add(prueba);
		
		return temp;

	}
	
	public ArrayList<Usuario> BuscarID(String id){
		int idtemp= Integer.parseInt(id);
		ArrayList<Usuario> temp = new ArrayList<Usuario>();
		
		for(Usuario prueba : usuarios)
			if(prueba.getId()==idtemp)
				temp.add(prueba);
		
		return temp;
	}
	
	public boolean Confirmar(String nuevo) {
		
		int cen = 0;
		
		for(Usuario temp : usuarios)
			if(temp.getUsuario().equals(nuevo)) {
				cen = 1;
				break;
			}
		
		if(cen==1)
			return true;
		else
			return false;
		
	}

	
	public void EliminarUsuario(String id) {
		String SQL = "DELETE FROM usuarios WHERE idusuario = '"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			
			int res = ps.executeUpdate();
			
			if(res>0)
				JOptionPane.showMessageDialog(null, "Usuario Eliminado");
			else
				JOptionPane.showMessageDialog(null, "Error al Eliminar");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
} 


	