package co.com.buy.dataservices.local;

import java.util.List;
import javax.ejb.Local;

import co.com.buy.entities.Producto;


@Local
public interface ProductoFacadeLocal {
	/**
	 * Método usado para buscar todos los productos
	 * 
	 * 
	 * @return --> Producto
	 */
	List<Producto> findAll();
}
