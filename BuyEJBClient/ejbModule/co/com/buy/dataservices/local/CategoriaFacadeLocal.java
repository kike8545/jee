package co.com.buy.dataservices.local;

import javax.ejb.Local;

import co.com.buy.entities.Categoria;

@Local
public interface CategoriaFacadeLocal {
	/**
	 * Método usado para buscar una categoria por id
	 * 
	 * @param idCategoria
	 * 
	 * @return --> Categoria
	 * 
	 */
	Categoria findByIdCategoria(Integer idCategoria);
}
