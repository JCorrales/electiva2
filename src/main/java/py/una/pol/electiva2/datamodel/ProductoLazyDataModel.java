package py.una.pol.electiva2.datamodel;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Producto;

@Named("productoDM")
@SessionScoped
public class ProductoLazyDataModel extends GenericDM<Producto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911703880355913081L;

}
