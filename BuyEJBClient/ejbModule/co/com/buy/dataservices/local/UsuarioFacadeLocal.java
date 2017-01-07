package co.com.buy.dataservices.local;

import javax.ejb.Local;
import co.com.buy.entities.Usuario;

@Local
public interface UsuarioFacadeLocal {
	/**
	 * Método usado para buscar un usuaro por id
	 * 
	 * @param idUsuario
	 * 
	 * @return --> Usuario
	 */
	Usuario findByIdUsuario(Integer idUsuario);
}
