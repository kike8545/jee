package co.com.buy.functionalities.local;

import javax.ejb.Remote;

import co.com.buy.entities.Usuario;

@Remote
public interface AdminUsuarioServicesLocal {
	public static final String LOOKUP = "BuyEAR/AdminUsuarioServices/remote-co.com.buy.functionalities.local.AdminUsuarioServicesLocal";

	/**
	 * 
	 * Metodo usado para buscar usuarios por id
	 * 
	 * @author jeremy
	 * @since 2017.01.02
	 * @version 10.6.1
	 * @sprint 14
	 * 
	 * @param idUsuario
	 * @return --> usuario
	 */

	Usuario findByIdUsuario(Integer idUsuario);
}
