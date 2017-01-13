package co.com.buy.dataservices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.buy.dataservices.local.HistorialComprasFacadeLocal;
import co.com.buy.entities.HistorialCompras;


@Stateless
public class HistorialComprasFacade implements HistorialComprasFacadeLocal{

	@PersistenceContext(unitName ="BuyEJBPU")
	private EntityManager  em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialCompras> findByUsuario(Integer usuario){
		try {
			
			List<HistorialCompras> historialCompras = (List<HistorialCompras>) this.em.createNamedQuery("HistorialCompras.findByHistorialCompras").
					setParameter("usuario", usuario)
						.getResultList();
			
			return historialCompras;
			
		} catch (Exception e) {
			System.out.println("Error al consultar usuario");
		}
		return null;
	}

	@Override
	public void save(HistorialCompras historialCompras) {
		try {

			this.em.persist(historialCompras);
			this.em.flush();
			this.em.refresh(historialCompras);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
