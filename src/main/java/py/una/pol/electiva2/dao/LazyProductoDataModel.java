package py.una.pol.electiva2.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.Producto;

@Named
@SessionScoped
public class LazyProductoDataModel extends LazyDataModel<Producto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private UserTransaction userTransaction;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() {
		LOGGER.info("HOLA");
	}

	@Override
	public List<Producto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
	
		LOGGER.info("load int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters");
		return null;
	}

	@Override
	public List<Producto> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {

		LOGGER.info("load lista");
		String order = "ORDER BY ";
		for (SortMeta sortMeta : multiSortMeta) {
			LOGGER.info("creando order, columna {} dirección {}", sortMeta.getSortField(), sortMeta.getSortOrder());
			order += sortMeta.getSortField() + " " + getOrderDir(sortMeta.getSortOrder()) + ", ";
		}
		order.substring(0, order.length() - 1 - ", ".length());

		String filter = "WHERE ";
		for (Entry<String, Object> filtro : filters.entrySet()) {
			LOGGER.info("creando filtro, {} {}", filtro.getKey(), filtro.getValue());
		}

		return entityManager.createQuery("SELECT * FROM Producto").getResultList();
	}

	private String getOrderDir(SortOrder dir) {

		if (SortOrder.DESCENDING.equals(dir)) {
			return "desc";
		}
		return "asc";
	}

	@Override
	public Object getRowKey(Producto object) {

		return object.getId();
	}

}
