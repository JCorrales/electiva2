package py.una.pol.electiva2.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.BaseEntity;

public class BaseDAO<M extends BaseEntity> {

	@PersistenceContext
	protected EntityManager entityManager;
	@Resource
	protected UserTransaction userTransaction;
	protected Type argumentType;
	protected Class<?> argumentClass;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() {

		this.argumentType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			this.argumentClass = this.getClass().getClassLoader().loadClass(argumentType.getTypeName());
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

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
			M noDetach = find(entity.getId());
			if (noDetach != null)
				entityManager.remove(noDetach);
			userTransaction.commit();
			LOGGER.info("borrado {}", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public M find(Long id) {

		Object entity = entityManager.find(argumentClass, id);
		if (entity != null)
			return (M) entity;
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<M> findAll() {

		try {
			userTransaction.begin();
			Query query = entityManager.createQuery("FROM " + argumentClass.getSimpleName());
			List<M> resultList = query.getResultList();
			userTransaction.commit();
			LOGGER.info("registros encontrados {}", resultList.size());
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}
}
