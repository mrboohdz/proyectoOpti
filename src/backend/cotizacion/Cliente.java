package backend.cotizacion;

public class Cliente {
	private String nombre;
	private String direccion;
	private String rfc;
	private String noTelefono;
	private TipoPersona tipoPersona;
	private String razonSocial;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNoTelefono() {
		return noTelefono;
	}
	public void setNoTelefono(String noTelefono) {
		this.noTelefono = noTelefono;
	}
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	public String tipoPersona (TipoPersona persona) {
		return persona.name();
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
}
