package Modelo;

public class Producto {
	int IdProducto;
	String Descripcion;
	String Precio;
	String Cantidad;
	String Provedor;

	public Producto() {

	}

	public int getIdProducto() {
		return IdProducto;
	}

	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getPrecio() {
		return Precio;
	}

	public void setPrecio(String precio) {
		Precio = precio;
	}

	public String getCantidad() {
		return Cantidad;
	}

	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}

	public String getProvedor() {
		return Provedor;
	}

	public void setProvedor(String provedor) {
		Provedor = provedor;
	}
}
