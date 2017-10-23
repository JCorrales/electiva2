package py.una.pol.electiva2.converter;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean(name = "entityManagerProvider", eager = true)
@ApplicationScoped
public class EntityManagerProvider {

	@PersistenceContext
	protected EntityManager entityManager;
	@Resource
	protected UserTransaction userTransaction;

	public EntityManager getEntityManager() {

		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public UserTransaction getUserTransaction() {

		return userTransaction;
	}

	public void setUserTransaction(UserTransaction userTransaction) {

		this.userTransaction = userTransaction;
	}
}
