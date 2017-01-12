package co.com.buy.producto.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.buy.entities.Producto;
import co.com.buy.functionalities.local.AdminProductoServicesLocal;

import com.amazonaws.util.json.JSONObject;

@Path("/ProductoService")
@Stateless
public class ProductoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private AdminProductoServicesLocal adminProductoService; //Metodo local para buscar todos los productos
	
	@POST
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<?> findAll(@FormParam("params") JSONObject params) {
		try {
			
			this.initContextAdminProductoServices();
			/**
			 * Procedemos a buscar todos los productos
			 */
			List<Producto> productos = (List<Producto>) this.adminProductoService.findAll(); 
			List<Map<String, Object>> listToReturn = new ArrayList<Map<String,Object>>();
			
			for (Producto producto : productos) {
				HashMap<String, Object> response = new HashMap<String, Object>();
				
				response.put("idProducto", producto.getIdProducto());
				response.put("nombreProducto", producto.getNombreProducto());
				response.put("categoria", producto.getCategoria());
				response.put("precio", producto.getPrecio());
				response.put("cantidad", 1);
				
				listToReturn.add(response);
			}
			
			return listToReturn;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error en ProductoService REST");
		}
		return null;
	}
	/**
	 * Método usado para realizar la 
	 * inicialización del contexto manual
	 */
	private void initContextAdminProductoServices(){
		try {
			
			Properties env = new Properties();
			
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
            env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
            InitialContext ini = new InitialContext(env);

            this.adminProductoService=(AdminProductoServicesLocal)ini.lookup(AdminProductoServicesLocal.LOOKUP);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
