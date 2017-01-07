package co.com.buy.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import co.com.buy.categoria.service.CategoriaService;
import co.com.buy.historialcompras.services.HistorialComprasService;
import co.com.buy.producto.service.ProductoService;
import co.com.buy.usuario.service.UsuarioService;
/**
 * Esta clase será la encargada de registrar todas las 
 * clases, utilidades o recursos encargados de exponer 
 * servicios REST a nuestra app
 * 
 * @author Jeremy
 */
@ApplicationPath("/")
public class RestApplication extends Application{
	
	public Set<Class<?>> getClasses(){
		Set<Class<?>> clasess = new HashSet<Class<?>>();
		clasess.add(CategoriaService.class);
		clasess.add(HistorialComprasService.class);
		clasess.add(ProductoService.class);
		clasess.add(UsuarioService.class);
		return clasess; 
	}
}
