package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_productos")
public class Producto {
	@Id	
	private String idprod; 	
	private String descripcion; 	
	private int stock; 
	private double precio; 
	private int idcategoria; 
	private String estado;
	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Producto(String idprod, String descripcion, int stock, double precio, int idcategoria, String estado) {
		super();
		this.idprod = idprod;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.idcategoria = idcategoria;
		this.estado = estado;
	}
	public Producto() {
		super();
	}
	@Override
	public String toString() {
		return "Producto [idprod=" + idprod + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio
				+ ", idcategoria=" + idcategoria + ", estado=" + estado + "]";
	}
	
	
}
