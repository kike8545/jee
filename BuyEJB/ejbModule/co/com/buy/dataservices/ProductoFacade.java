package co.com.buy.dataservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.buy.dataservices.local.ProductoFacadeLocal;
import co.com.buy.entities.Producto;

@Stateless
public class ProductoFacade implements ProductoFacadeLocal {
	
	@PersistenceContext(unitName = "BuyEJBPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findAll() {
		try {
			
			List<Producto> productos = (List<Producto>) this.em.createNamedQuery("producto.findAll")
																.getResultList();
			return new ArrayList<Producto>(productos);	
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error en la consulta de productos");
		}
		return null;
	}

}
