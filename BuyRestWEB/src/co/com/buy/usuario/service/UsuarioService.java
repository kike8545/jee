package co.com.buy.usuario.service;

import java.io.Serializable;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;

import com.amazonaws.util.json.JSONObject;

import co.com.buy.entities.Usuario;
import co.com.buy.functionalities.local.AdminUsuarioServicesLocal;

@Path("/UsuarioService")
@Stateless
public class UsuarioService implements Serializable {

	private AdminUsuarioServicesLocal adminUsuarioServicesLocal; //Metodo local para buscar usuarios por id

	private static final long serialVersionUID = 1L;

	public HashMap<String, Object> findByIdUsuario(
			@FormParam("params") JSONObject params) {
		try {
			HashMap<String, Object> response = new HashMap<String, Object>();
			Integer idUsuario = params.getInt("idUsuario");
			/**
			 * Procedemos a buscar el usuario por id
			 */
			Usuario usuario = this.adminUsuarioServicesLocal.findByIdUsuario(idUsuario);

			response.put("idUsuario", usuario.getIdUsuario());
			response.put("mail", usuario.getMail());
			response.put("nombre", usuario.getNombre());
			response.put("pass", usuario.getPass());

			return response;

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en UsuarioService REST");
		}
		return null;
	}
}
