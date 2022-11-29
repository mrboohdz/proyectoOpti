package backend.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class baseDatos {
	//Nuestra base de datos tiene estos datos para acceder.
	private final String BASE = "9WGR2E3VXb"; 
	private final String URL = "jdbc:mysql://remotemysql.com/" + BASE+"?autoReconnect=true";
	private final String USERNAME = "9WGR2E3VXb";
 	private final String PASSWORD= "CZrc1tBbiF";
	
	//Vamos a crear un metodo para ingresar a ella y no hacer esto
	//Cada clase en la que usamos la base de datos
	public Connection getConexion() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
			//System.out.println("Conexi�n Exitosa");
		}
		catch(SQLException|ClassNotFoundException e) {
			System.out.println(e);
		}
		return con;
	}
	
}


