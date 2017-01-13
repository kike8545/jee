package co.com.buy.dataservices.local;

import java.util.List;

import javax.ejb.Local;

import co.com.buy.entities.HistorialCompras;


@Local
public interface HistorialComprasFacadeLocal {
	/**
	 * Método usado para buscar una categoria por id
	 * 
	 * @param idCategoria
	 * 
	 * @return --> Categoria
	 */
	List<HistorialCompras> findByUsuario(Integer usuario);
	/**
	 * Método usado para guardar el historial de una comrpa
	 */
	void save(HistorialCompras historialCompras);
}
