package py.una.pol.electiva2.dao;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Producto;

@Named
@SessionScoped
public class ProductoDAO extends BaseDAO<Producto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
