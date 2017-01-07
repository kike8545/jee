package co.com.buy.historialcompras.services;

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

import co.com.buy.entities.HistorialCompras;
import co.com.buy.functionalities.local.AdminHistorialComprasServicesLocal;

import com.amazonaws.util.json.JSONObject;

@Path("/HistorialComprasService")
@Stateless
public class HistorialComprasService implements Serializable {

	private static final long serialVersionUID = 1L;

	private AdminHistorialComprasServicesLocal adminHistorialComprasService; 
	/**
	 * Método usado para...
	 * 
	 * @author Jeremy De Avila
	 * @since 2017-01-06
	 * @version 10.6.4
	 * @Sprint 15
	 * 																			
	 * @param params --> JSONOBject con parámetros al servicio
	 * @return --> Arreglo con los historiasl de compra por usuario HistorialCompras
	 */
	@POST
	@Path("/findByHistorialCompras")
	@Produces(MediaType.APPLICATION_JSON)
	public List<?> findByHistorialCompras(@FormParam("params") JSONObject params) {
		try {
			/**
			 * Inicializamos contexto
			 */
			this.initContextAdminHistorialComprasServices();
			
			List<Map<String, Object>> responseToReturn = new ArrayList<Map<String, Object>>();
			
			Integer usuario = params.getInt("idUsuario");
			/**
			 * Procedemos a buscar el historial de compras por id de usuario
			 */
			List<HistorialCompras> historialCompras = this.adminHistorialComprasService.findByHistorialCompras(usuario);
			
			for (HistorialCompras historialCompra : historialCompras) {
				HashMap<String, Object> response = new HashMap<String, Object>();
				
				response.put("usuario", historialCompra.getUsuario().getIdUsuario());
				response.put("producto", historialCompra.getProducto().getIdProducto());
				response.put("total", historialCompra.getTotal());
				
				responseToReturn.add(response);
				
				System.out.println(historialCompra.getIdHistorial());
			}
			// response.put("idHistorial", historialCompras.getIdHistorial());
			
			return responseToReturn;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error en HistorialComprasService REST *****"	+ e + "******");
		}
		return null;
	}

	/**
	 * Método usado para realizar la inicialización del contexto manual
	 */
	private void initContextAdminHistorialComprasServices() {
		try {

			Properties env = new Properties();

			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			InitialContext ini = new InitialContext(env);

			this.adminHistorialComprasService = (AdminHistorialComprasServicesLocal)ini.lookup(AdminHistorialComprasServicesLocal.LOOKUP);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
