package py.una.pol.electiva2.controllers;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import py.una.pol.electiva2.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductoController extends BaseController<Producto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455954501903162338L;
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = -1089588317286581294L;
	//
	// @Inject
	// private ProductoDAO productoDAO;
	//
	// private Producto producto = new Producto();
	//
	// public Producto getProducto() {
	//
	// return producto;
	// }
	//
	// public void setProducto(Producto producto) {
	//
	// this.producto = producto;
	// }
	//
	// public void nuevo() {
	//
	// this.producto = new Producto();
	// }
	//
	// public void guardar() {
	//
	// if (producto.getId() != null) {
	// productoDAO.edit(producto);
	// } else {
	// productoDAO.create(producto);
	// }
	//
	// }
	//
	// public void destroy(Producto producto) {
	//
	// productoDAO.destroy(producto);
	// reload();
	// }
	//
	// public void reload() {
	//
	// //
	// https://stackoverflow.com/questions/570015/how-do-i-reload-a-page-without-a-postdata-warning-in-javascript
	// //
	// https://stackoverflow.com/questions/32947472/how-to-reload-page-when-a-button-is-clicked
	// ExternalContext ec =
	// FacesContext.getCurrentInstance().getExternalContext();
	// try {
	// ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
