package co.com.buy.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "historialcompras")
@NamedNativeQueries({
	@NamedNativeQuery(name = "HistorialCompras.findByHistorialCompras", 
			query = "SELECT * FROM historialcompras WHERE usuario = ?usuario", 
			resultClass = HistorialCompras.class) 
	})

public class HistorialCompras implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idHistorial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idHistorial;

	@JoinColumn(name = "usuario", referencedColumnName = "idUsuario")
	@ManyToOne
	private Usuario usuario;

	@JoinColumn(name = "producto", referencedColumnName = "idProducto")
	@ManyToOne
	private Producto producto;
	
	@Column(name="cantidad")
	private String cantidad;
	
	@Column(name="total")
	private String total;
	
	
	public HistorialCompras() {
		super();
	}

	public Integer getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(Integer idHistorial) {
		this.idHistorial = idHistorial;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idHistorial == null) ? 0 : idHistorial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistorialCompras other = (HistorialCompras) obj;
		if (idHistorial == null) {
			if (other.idHistorial != null)
				return false;
		} else if (!idHistorial.equals(other.idHistorial))
			return false;
		return true;
	}
	
	
}
