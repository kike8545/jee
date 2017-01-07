package co.com.buy.functionalities;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.buy.dataservices.local.UsuarioFacadeLocal;
import co.com.buy.entities.Usuario;
import co.com.buy.functionalities.local.AdminUsuarioServicesLocal;

@Stateless
public class AdminUsuarioServices implements AdminUsuarioServicesLocal {

	@EJB
	private UsuarioFacadeLocal usuariosFacade;
	
	@Override
	public Usuario findByIdUsuario(Integer idUsuario) {
		try {
			return this.usuariosFacade.findByIdUsuario(idUsuario);
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al implementar AdminUsuarioServicesLocal");
		}
		return null;
	}

}
