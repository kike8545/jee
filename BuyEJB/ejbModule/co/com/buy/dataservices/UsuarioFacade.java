package co.com.buy.dataservices;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.buy.dataservices.local.UsuarioFacadeLocal;
import co.com.buy.entities.Usuario;

@Stateless
public class UsuarioFacade implements UsuarioFacadeLocal {
	
	@PersistenceContext(unitName="BuyEJBPU")
    private EntityManager em;


	@Override
	public Usuario findByIdUsuario(Integer idUsuario) {
		try {
			
			 Usuario usuario = (Usuario) em.createNamedQuery("usuario.findByIdUsuario")
					.setParameter("idUsuario", idUsuario).getSingleResult();
			 
			 return usuario;
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al implementar UsuarioFacadeLocal");
		}
		return null;
	}
	
	
}
