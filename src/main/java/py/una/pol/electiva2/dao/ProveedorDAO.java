package py.una.pol.electiva2.dao;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Proveedor;

@Named
@SessionScoped
public class ProveedorDAO extends BaseDAO<Proveedor> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4558516222762821444L;

}
