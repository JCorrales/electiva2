package py.una.pol.electiva2.datamodel;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Proveedor;

@Named("proveedorDM")
@SessionScoped
public class ProveedorLazyDataModel extends GenericDM<Proveedor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911703880355913081L;

}
