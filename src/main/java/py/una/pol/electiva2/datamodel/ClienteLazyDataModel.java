package py.una.pol.electiva2.datamodel;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Cliente;

@Named("clienteDM")
@SessionScoped
public class ClienteLazyDataModel extends GenericDM<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5238310087313226652L;

}
