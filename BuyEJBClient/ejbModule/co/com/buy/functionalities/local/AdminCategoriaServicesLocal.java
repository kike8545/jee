package co.com.buy.functionalities.local;

import javax.ejb.Local;
import co.com.buy.entities.Categoria;

@Local
public interface AdminCategoriaServicesLocal {
	/**
	 * Método usado para buscar una categoria por id
	 * 
	 * @autor Jeremy
	 * @since 2017-01-01
	 * @version 10.6.2
	 * @sprint 14
	 * 
	 * @param idCategoria
	 * @return --> Categoria
	 */
	Categoria findById(Integer idCategoria);
}
