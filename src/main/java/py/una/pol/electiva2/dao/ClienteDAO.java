package py.una.pol.electiva2.dao;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Cliente;

@Named
@SessionScoped
public class ClienteDAO extends BaseDAO<Cliente> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4094132032879415988L;

}
