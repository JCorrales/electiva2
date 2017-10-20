package py.una.pol.electiva2.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import py.una.pol.electiva2.dao.LazyProductoDataModel;
import py.una.pol.electiva2.dao.ProductoDAO;
import py.una.pol.electiva2.domain.Producto;

@ManagedBean
@SessionScoped
public class ProductoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ProductoDAO productoDAO;
	@Inject
	private LazyProductoDataModel dataModel;

	private Producto producto = new Producto();

	public Producto getProducto() {

		return producto;
	}

	public void setProducto(Producto producto) {

		this.producto = producto;
	}

	public void guardar() {

		if (producto.getId() != null) {
			productoDAO.edit(producto);
		} else {
			productoDAO.create(producto);
		}

	}

	public LazyProductoDataModel getDataModel() {

		return dataModel;
	}

	public void setDataModel(LazyProductoDataModel dataModel) {

		this.dataModel = dataModel;
	}

}
