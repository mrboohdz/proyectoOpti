package backend.cotizacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import backend.catalogo.Articulo;

public class Cotizacion {
	
	private int idCotizacion;
	private TipoPersona tipoPersona;
	private float total;
	private String fechaEmision;
	private String fechaVencimiento;
	private Persona responsable;
	private String empresa;
	private String razonSocial;
	private Articulo art;
	private ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
	
	public Cotizacion() {
		
	}
	
	
	//Para usar en la base de datos como String
	public String tipoPersona (TipoPersona persona) {
		return persona.name();
	}
	
	//Se usa para agregar la cantidad al articulo que nos mandan, para agregarlo totalmente a la lista
	public void NuevoArticulo(Articulo art, int cantidad) {
		art.setCantidad(cantidad);
		art.CalcularTotal();
		listaArticulos.add(art);
	}
	
	public void EliminarArticulo(int id) {
		listaArticulos.remove(id);
	}
	
	
	public String FechaHoy() {
		fechaEmision = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return fechaEmision;
	}
	
	public String FechaVenc() {
		fechaVencimiento = LocalDate.now().plusWeeks(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return fechaVencimiento;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	public Persona getResponsable() {
		return responsable;
	}
	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public ArrayList<Articulo> getListaArticulos() {
		return listaArticulos;
	}
	public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}
	public int getIdCotizacion() {
		return idCotizacion;
	}
	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	public Articulo getArt() {
		return art;
	}
	public void setArt(Articulo art) {
		this.art = art;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


}
