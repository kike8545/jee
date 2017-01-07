package co.com.buy.dataservices;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.buy.dataservices.local.CategoriaFacadeLocal;
import co.com.buy.entities.Categoria;

@Stateless
public class CategoriaFacade implements CategoriaFacadeLocal{

	@PersistenceContext(unitName="BuyEJBPU")
	private EntityManager em;
	
	@Override
	public Categoria findByIdCategoria(Integer idCategoria) {
		try {
			
			Categoria categoria = (Categoria) this.em.createNamedQuery("Categoria.findByIdCategoria")
													.setParameter("idCategoria", idCategoria)
														.getSingleResult();
			
			return categoria;
			
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error...");
		}
		
		return null;
	}
}
