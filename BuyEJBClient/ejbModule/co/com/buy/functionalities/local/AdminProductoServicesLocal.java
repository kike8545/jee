package co.com.buy.functionalities.local;

import java.util.List;

import javax.ejb.Remote;

import co.com.buy.entities.Producto;

@Remote
public interface AdminProductoServicesLocal {
	
	public static final String LOOKUP="BuyEAR/AdminProductoServices/remote-co.com.buy.functionalities.local.AdminProductoServicesLocal";
	/**                                           
	 * Método usado para buscar los productos
	 * 
	 * @autor Jeremy
	 * @since 2017-01-01
	 * @version 10.6.2
	 * @sprint 14
	 * 
	 * @return --> Producto
	 */
	List<Producto> findAll();
}
