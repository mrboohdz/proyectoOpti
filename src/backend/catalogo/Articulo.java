package backend.catalogo;

public class Articulo {
	
	
	private int id;
	private String nombre;
	private float precio;
	private int stock;
	private int cantidad;
	private float total;
	private String proveedor;
	
	public Articulo() {
		
	}
	
	public void CalcularTotal() {
		setTotal(getCantidad()*getPrecio());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getProveedor() {
		// TODO Auto-generated method stub
		return proveedor;
	}
	
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	

}
