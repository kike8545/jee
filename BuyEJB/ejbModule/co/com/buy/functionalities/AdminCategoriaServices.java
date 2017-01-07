package co.com.buy.functionalities;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.buy.dataservices.local.CategoriaFacadeLocal;
import co.com.buy.entities.Categoria;
import co.com.buy.functionalities.local.AdminCategoriaServicesLocal;

@Stateless
public class AdminCategoriaServices implements AdminCategoriaServicesLocal{

	@EJB
	private CategoriaFacadeLocal categoriaFacade; //le agregue el private y corrio ¿por qué?
	
	@Override
	public Categoria findById(Integer idCategoria) {
		try {
			
			return this.categoriaFacade.findByIdCategoria(idCategoria);
			
		} catch (Exception e) {
			System.out.println("Error al implementar AdminCategoriaServicesLocal");
		}
		return null;
	}
}
