package py.una.pol.electiva2.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.dao.CompraCabDAO;
import py.una.pol.electiva2.dao.ProductoDAO;
import py.una.pol.electiva2.dao.ProveedorDAO;
import py.una.pol.electiva2.domain.CompraCab;
import py.una.pol.electiva2.domain.CompraDet;
import py.una.pol.electiva2.domain.Producto;
import py.una.pol.electiva2.domain.Proveedor;

@ManagedBean
@ViewScoped
public class CompraCabController extends BaseController<CompraCab> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2930187215532451280L;

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Inject
	private ProductoDAO productoDAO;
	@Inject
	private ProveedorDAO proveedorDAO;
	@Inject
	private CompraCabDAO compraCabDAO;

	private Proveedor proveedor;

	private List<CompraDet> selectedDetails;

	public List<CompraDet> getCompraDetByProveedor() {

		if (proveedor == null) {
			return new ArrayList<>();
		}
		List<Producto> productoList = productoDAO.findByProveedor(proveedor);
		List<CompraDet> compraDetList = new ArrayList<>();
		for (Producto producto : productoList) {
			CompraDet compraDet = new CompraDet();
			compraDet.setCantidad(1);
			compraDet.setProducto(producto);
			compraDetList.add(compraDet);
		}
		LOGGER.info("encontrado {} productos para proveedor {}", compraDetList.size(), proveedor);
		return compraDetList;
	}

	public Proveedor getProveedor() {

		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {

		LOGGER.info("setting proveedor {}", proveedor);
		this.proveedor = proveedor;
	}

	public List<CompraDet> getSelectedDetails() {

		return selectedDetails;
	}

	public void setSelectedDetails(List<CompraDet> selectedDetails) {

		LOGGER.info("##### setting details {}", selectedDetails.size());
		this.selectedDetails = selectedDetails;
	}

	public List<Proveedor> getProveedores() {

		return proveedorDAO.findAll();
	}

	@Override
	public void guardar() {

		try {
			compraCabDAO.comprar(getEntity(), getSelectedDetails(), proveedor);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {

		LOGGER.info("**** {}", event);
		selectedDetails.add((CompraDet) event.getObject());

	}

	// public void onRowUnselect(UnselectEvent event) {
	//
	// FacesMessage msg = new FacesMessage("CompraDet Unselected", ((CompraDet)
	// event.getObject()).getId());
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// }

}
