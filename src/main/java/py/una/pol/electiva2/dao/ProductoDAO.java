package py.una.pol.electiva2.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.Producto;
import py.una.pol.electiva2.domain.Proveedor;

@Named
@SessionScoped
public class ProductoDAO extends BaseDAO<Producto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2779544446147560632L;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	protected EntityManager entityManager;
	@Resource
	protected UserTransaction userTransaction;

	@SuppressWarnings("unchecked")
	public List<Producto> findByProveedor(Proveedor proveedor) {

		LOGGER.info("********** Buscando por proveedor {}", proveedor);
		try {
			userTransaction.begin();
			Query query = entityManager.createQuery("SELECT p FROM Producto p WHERE p.proveedor.id = ?1");
			query.setParameter(1, proveedor.getId());
			List<Producto> resultList = query.getResultList();
			LOGGER.info("****productos encontrados {}", resultList.size());
			userTransaction.commit();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

}
