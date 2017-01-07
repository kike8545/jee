package co.com.buy.functionalities;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.buy.dataservices.local.ProductoFacadeLocal;
import co.com.buy.entities.Producto;
import co.com.buy.functionalities.local.AdminProductoServicesLocal;

@Stateless
public class AdminProductoServices implements AdminProductoServicesLocal {

	@EJB
	private ProductoFacadeLocal productoFacade;

	@Override
	public List<Producto> findAll() {
		try {
			
			return new ArrayList<Producto>(this.productoFacade.findAll());
			
		} catch (Exception e) {
			System.out.println("Error al implementar AdminProductoServicesLocal");
		}
		return null;
	}
}
