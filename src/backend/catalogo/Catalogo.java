package backend.catalogo;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import backend.conexion.baseDatos;

public class Catalogo  {
	private ResultSet rs = null; 
	private PreparedStatement ps = null;
	private static baseDatos bd = new baseDatos();
	private static final Connection con = bd.getConexion();
	
	private ArrayList<Articulo> articulos;
	private Articulo art;
	
	public Catalogo(){
		try {
			//Accedemos a la base de datos para crear la lista de artículos
			articulos = new ArrayList<Articulo>();  
			
			//Con este String indicamos la busqueda en la base
			String SQL = "SELECT id, nombre, precio, stock FROM articulo";
			
			//Se preparan los datos y se ejecuta la busqueda
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			//Llenamos la lista de articulos con los datos que tenemos en la base de datos
			while(rs.next()) {
				Articulo art = new Articulo();
				
				art.setId(rs.getInt(1));
				art.setNombre(rs.getString(2));
				art.setPrecio(rs.getFloat(3));
				art.setStock(rs.getInt(4));
				
				articulos.add(art);
			}
		}catch(SQLException e) { 
			System.out.println(e);
		}
	}
	
	public ArrayList<Articulo> BuscarArticulo(String nombre) {
		ArrayList<Articulo> temp = new ArrayList<Articulo>();
		for(Articulo art : articulos)
			if(art.getNombre().equals(nombre)) {
				temp.add(art);
			}
		
		return temp;
	}
	
	public ArrayList<Articulo> BuscarArticulo(int id) {
		ArrayList<Articulo> temp = new ArrayList<Articulo>();
		for(Articulo art : articulos)
			if(art.getId() == id) 
				temp.add(art);
		
		return temp;

	}

	 public Articulo CrearArticulo(int id) {
		 Articulo temp = new Articulo();
		 
		 for(Articulo prueba : articulos)
			 if(prueba.getId()==id)
				 temp = prueba;
		 
		 return temp;
	} 
	
	public void AgregarArticulo(Articulo art) {
		//Para agregar a la base de datos primero preparamos el String que se enviara
		String SQL = "INSERT INTO articulo(nombre, precio, stock) VALUES (?,?,?)";
		try {
			ps = con.prepareStatement(SQL);
			
			//Ingresamos los datos que entraran en la base de datos
			ps.setString(1, art.getNombre());
			ps.setFloat(2, art.getPrecio());
			ps.setInt(3, art.getStock());
			
			//Al ejecutar la busqueda, nos regresara un entero para confirmar que se realizo
			//correctamente, lo usaremos para confirmar con el usuario en la condicional
			int res = ps.executeUpdate();
			if(res>0)
				JOptionPane.showMessageDialog(null, "Articulo Guardado");
			else
				JOptionPane.showMessageDialog(null, "Error al Guardar");
			
		}catch(SQLException e) {
			System.out.print(e);
		}
	}
	
	public void EliminarArticulo(String id) {
		//Se prepara el String para la base de datos
		String SQL = "DELETE FROM articulo WHERE id = '"+id+"'";
		try {
			ps = con.prepareStatement(SQL);
			//Si todo sale bien, nos regresa un entero, se usará para 
			//confirmar al usuario que fue eliminado
			int res = ps.executeUpdate();
			
			if(res > 0)
				JOptionPane.showMessageDialog(null, "Articulo eliminado");
			else
				JOptionPane.showMessageDialog(null, "Error al eliminar");
			
		}catch(SQLException e) {
			System.out.print(e);
		}
	}
	
	public void ModificarArticulo(Articulo art) {
		//Se prepara el String para la base de datos sin los valores
		String SQL = "UPDATE articulo SET nombre=?,precio=?,stock=? WHERE id=?";
		try {
			ps = con.prepareStatement(SQL);
			//Se agregan los valores al string para la base de datos
			ps.setString(1, art.getNombre());
			ps.setFloat(2, art.getPrecio());
			ps.setInt(3, art.getStock());
			ps.setInt(4, art.getId());
			
			//Si todo sale bien, nos regresa un entero, se usará para 
			//confirmar al usuario que fue eliminado
			int res = ps.executeUpdate();
			
			if(res>0)
				JOptionPane.showMessageDialog(null, "Articulo Modificado");
			else
				JOptionPane.showMessageDialog(null, "Error al guardar");
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Articulo getArt() {
		return art;
	}

	public void setArt(Articulo art) {
		this.art = art;
	}
	

}


