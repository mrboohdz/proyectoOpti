package backend.conexion;

import java.sql.*;


public class baseDatos {
	//Nuestra base de datos tiene estos datos para acceder.
	private final String BASE = "poocatalogo"; 
	private final String URL = "jdbc:mysql://localhost:3306/" + BASE+"?useSSL=false";
	private final String USERNAME = "root";
 	private final String PASSWORD= "Nmhm0926";
	
	//Vamos a crear un metodo para ingresar a ella y no hacer esto
	//Cada clase en la que usamos la base de datos
	public Connection getConexion() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
			//System.out.println("Conexión Exitosa");
		}
		catch(SQLException|ClassNotFoundException e) {
			System.out.println(e);
		}
		return con;
	}
	

}
