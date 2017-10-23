package py.una.pol.electiva2.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.dao.ProveedorDAO;
import py.una.pol.electiva2.domain.Producto;
import py.una.pol.electiva2.domain.Proveedor;

@ManagedBean
@ViewScoped
public class ProductoController extends BaseController<Producto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455954501903162338L;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Inject
	private ProveedorDAO proveedorDAO;

	@Override
	@PostConstruct
	public void init() {

		Producto p = new Producto();
		p.setProveedor(new Proveedor());
		setEntity(p);
		super.init();
	}

	public List<Proveedor> getProveedores() {

		return proveedorDAO.findAll();
	}

	@Override
	public void setEntity(Producto entity) {

		// TODO Auto-generated method stub
		super.setEntity(entity);
	}

	@Override
	public Producto getEntity() {

		// TODO Auto-generated method stub
		return super.getEntity();
	}

	public void onSelectProvider(ActionEvent action) {

		LOGGER.info("{}", action);

	}

	@Override
	public void guardar() {

		LOGGER.info("************ guardando{}", getEntity());
		super.guardar();
	}

}
