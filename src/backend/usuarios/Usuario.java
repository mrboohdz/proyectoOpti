package backend.usuarios;

import backend.cotizacion.Persona;

public class Usuario {
	private String usuario;
	private String contrasenia;
	private Persona nombre;
	private TipoUsuario tipoUsuario;
	private int id;
	

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public Persona getNombre() {
		return nombre;
	}
	public void setNombre(Persona nombre) {
		this.nombre = nombre;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
