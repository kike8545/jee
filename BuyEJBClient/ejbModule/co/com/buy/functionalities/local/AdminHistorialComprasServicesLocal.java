package co.com.buy.functionalities.local;

import java.util.List;

import javax.ejb.Remote;

import co.com.buy.entities.HistorialCompras;

@Remote
public interface AdminHistorialComprasServicesLocal {
	
	
	public static final String LOOKUP="BuyEAR/AdminHistorialComprasServices/remote-co.com.buy.functionalities.local.AdminHistorialComprasServicesLocal";
	/**
	 * Método usado para buscar historial de un usuario
	 * 
	 * @autor Jeremy
	 * @since 2017-01-01
	 * @version 10.6.2
	 * @sprint 14
	 * 
	 * @param usuario
	 * @return --> HistorialUsuario
	 */
	List<HistorialCompras> findByHistorialCompras(Integer usuario);
}
