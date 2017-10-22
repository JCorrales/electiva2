package py.una.pol.electiva2.dao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Producto;

@Named("productoDM")
@SessionScoped
public class LazyProductoDataModel extends GenericDM<Producto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
