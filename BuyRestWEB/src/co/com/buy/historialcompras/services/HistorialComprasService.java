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

import co.com.buy.entities.Categoria;
import co.com.buy.entities.HistorialCompras;
import co.com.buy.entities.Producto;
import co.com.buy.entities.Usuario;
import co.com.buy.functionalities.local.AdminHistorialComprasServicesLocal;

import com.amazonaws.util.json.JSONArray;
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
	 * @return --> Arreglo con los historiasl de compra por usuario
	 *         HistorialCompras
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
		
			Integer usuario = params.getJSONObject("user").getInt("idUsuario");//nombre del campor del objeto JSon que viene de la BD
			/**
			 * Procedemos a buscar el historial de compras por id de usuario
			 */
			List<HistorialCompras> historialCompras = this.adminHistorialComprasService
					.findByHistorialCompras(usuario);

			for (HistorialCompras historialCompra : historialCompras) {
				HashMap<String, Object> response = new HashMap<String, Object>();

				response.put("usuario", historialCompra.getUsuario().getIdUsuario());
				response.put("producto", historialCompra.getProducto().getIdProducto());
				response.put("total", historialCompra.getTotal());
				

				responseToReturn.add(response);

				System.out.println("******" + historialCompra.getIdHistorial() + "******");
			}
			// response.put("idHistorial", historialCompras.getIdHistorial());

			return responseToReturn;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error en HistorialComprasService REST ***** " + e + " ******");
		}
		return null;
	}
	
	@POST
	@Path("/saveHistorialComprasByUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public void cretateHistorialComprasByUsuario(@FormParam("params") JSONObject params) {
		/**
		 * Éste metodo inserta los productor comprados en el historial de usuario,
		 * teniendo en cuenta 
		 * 
		 * @author Jeremy De Avila
		 * @since 10/01/2016
		 * @param params --> 
		 */
		try {
			/**
			 * Inicializamos contexto
			 */
			this.initContextAdminHistorialComprasServices();
			
			/**
			 * Procedemos a insertar el historial de compras por id de usuario
			 */
			Integer idUsuario = params.getJSONObject("user").getInt("idUsuario");//nombre del campor del objeto JSon que viene de la BD
			Integer sizeProductosComprados = params.getJSONArray("producto").length();//cantidad de productos comprados para insertar en historialCompras
			
			if (sizeProductosComprados != 0 && sizeProductosComprados != null
					&& idUsuario != 0 && idUsuario != null) {// si hay productos y usuario, se insertan
				idUsuario=params.getJSONObject("user").getInt("idUsuario");
				String mail = params.getJSONObject("user").getString("mail");
				String nombre = params.getJSONObject("user").getString("nombre");
				String pass = params.getJSONObject("user").getString("pass");
				
				/**
				 * se llenan los datos con el usuario de la comprar para asignarlo historialCompras
				 */
				Usuario user = new Usuario();
				user.setIdUsuario(idUsuario);
				user.setNombre(nombre);
				user.setMail(mail);
				user.setPass(pass);
	            
				JSONArray arrayProductos = params.getJSONArray("producto");// Se le asignan los productos a un array
				
				for (int i = 0; i < sizeProductosComprados; i++) {
					
					/*
					 * se contruye el objeto de tipo categoria
					 * */
					Categoria categoria = new Categoria();
					categoria.setId(arrayProductos.getJSONObject(i).getJSONObject("categoria").getInt("id"));   
     				categoria.setNombreCategoria(arrayProductos.getJSONObject(i).getJSONObject("categoria").getString("nombreCategoria"));
					
     				/*
					 * se contruye el objeto de tipo producto
					 * */
					Producto producto = new Producto();
					producto.setIdProducto(arrayProductos.getJSONObject(i).getInt("idProducto"));
					producto.setNombreProducto(arrayProductos.getJSONObject(i).getString("nombreProducto"));
					producto.setCategoria(categoria);
					producto.setPrecio(arrayProductos.getJSONObject(i).getInt("precio"));
					
					/*
					 * se contruye el objeto de tipo HistorialCompras a insertar
					 * */
					
					HistorialCompras historialCompras = new HistorialCompras();
					historialCompras.setUsuario(user);
					historialCompras.setProducto(producto);
					historialCompras.setCantidad("1");
					historialCompras.setTotal(producto.getPrecio().toString());
					
		            adminHistorialComprasService.saveHistorialCompras(historialCompras);
		            
				} 
			} else {
				System.out.println("Ha ocurrido un error, no hay productos y/o usuario para ingresar al historial ******");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error en la inserción de Historial ***** " + e + " ******");
		
		}
	
	}

	/**
	 * Método usado para realizar la inicialización del contexto manual
	 */
	private void initContextAdminHistorialComprasServices() {
		try {

			Properties env = new Properties();

			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			InitialContext ini = new InitialContext(env);

			this.adminHistorialComprasService = (AdminHistorialComprasServicesLocal) ini
					.lookup(AdminHistorialComprasServicesLocal.LOOKUP);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
