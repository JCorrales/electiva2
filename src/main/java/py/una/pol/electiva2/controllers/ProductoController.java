package py.una.pol.electiva2.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.una.pol.electiva2.domain.Producto;

@Named
@SessionScoped
public class ProductoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Producto producto = new Producto();

	public Producto getProducto() {

		return producto;
	}

	public void setProducto(Producto producto) {

		this.producto = producto;
	}

}
