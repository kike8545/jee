package co.com.buy.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
@NamedNativeQueries({
	@NamedNativeQuery(name="Categoria.findByIdCategoria",
					  query="SELECT * FROM categoria WHERE idCategoria = ?idCategoria",
					  resultClass=Categoria.class)
})

public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idCategoria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idCategoria;
	
	@Column(name="nombreCategoria")
	private String nombreCategoria;

	public Categoria() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}

	public Integer getId() {
		return idCategoria;
	}

	public void setId(Integer id) {
		this.idCategoria = id;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
}
