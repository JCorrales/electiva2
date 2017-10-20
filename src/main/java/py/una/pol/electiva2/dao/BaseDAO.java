package py.una.pol.electiva2.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.BaseEntity;

public class BaseDAO<M extends BaseEntity> {

	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private UserTransaction userTransaction;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public M create(M entity) {

		try {
			userTransaction.begin();
			entityManager.persist(entity);
			userTransaction.commit();
			LOGGER.info("creado {}", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

	public M edit(M entity) {

		try {
			userTransaction.begin();
			entityManager.merge(entity);
			userTransaction.commit();
			LOGGER.info("editado {}", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public M destroy(M entity) {

		try {
			userTransaction.begin();
			entityManager.remove(entity);
			userTransaction.commit();
			LOGGER.info("borrado {}", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
