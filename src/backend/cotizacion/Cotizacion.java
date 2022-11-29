package backend.cotizacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import backend.catalogo.Articulo;

public class Cotizacion {
	
	private int idCotizacion;
	private float total;
	private String fechaEmision;
	private String fechaVencimiento;
	private Cliente responsable;
	private Articulo art;
	private ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
	
	public Cotizacion() {
		
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

	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	public Cliente getResponsable() {
		return responsable;
	}
	public void setResponsable(Cliente responsable) {
		this.responsable = responsable;
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


}
