package co.com.buy.functionalities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.buy.dataservices.local.HistorialComprasFacadeLocal;
import co.com.buy.entities.HistorialCompras;
import co.com.buy.functionalities.local.AdminHistorialComprasServicesLocal;

@Stateless
public class AdminHistorialComprasServices implements AdminHistorialComprasServicesLocal {

	@EJB
	private HistorialComprasFacadeLocal historialComprasFacade;

	@Override
	public List<HistorialCompras> findByHistorialCompras(Integer usuario) {
		try {
			
			return new ArrayList<HistorialCompras> (this.historialComprasFacade.findByUsuario(usuario));
									
		} catch (Exception e) {
			
			System.out.println("Error al implementar AdminHistorialComprasServicesLocal");
		}
		return null;
	}
}
