package co.com.buy.categoria.service;

import java.io.Serializable;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.buy.entities.Categoria;
import co.com.buy.functionalities.local.AdminCategoriaServicesLocal;

import com.amazonaws.util.json.JSONObject;

@Path("/CategoriaService")
@Stateless
public class CategoriaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AdminCategoriaServicesLocal adminCategoriaService; //Metodo local para crear buscar una categoria por id
	
	@POST
	@Path("/findCategoriaById")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> findCategoriaById(@FormParam("params") JSONObject params){
		try {
			
			HashMap<String, Object> response = new HashMap<String, Object>();
			Integer idCategoria = params.getInt("idCategoria");
			/**
			 * Procedemos a buscar la categoría por su id
			 */
			Categoria categoria = this.adminCategoriaService.findById(idCategoria);
			
			response.put("idcategoria", categoria.getId());
			response.put("nombreCategoria", categoria.getNombreCategoria());
			
			return response;
			
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error...");
		}
		
		return null;
	}
}
