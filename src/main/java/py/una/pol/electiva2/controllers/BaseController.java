package py.una.pol.electiva2.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import py.una.pol.electiva2.dao.BaseDAO;
import py.una.pol.electiva2.domain.BaseEntity;

public abstract class BaseController<M extends BaseEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5441549478756438853L;

	private Type argumentType;
	private Class<?> argumentClass;
	private M entity;
	private BaseDAO<M> entityDAO;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {

		this.argumentType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			this.argumentClass = this.getClass().getClassLoader().loadClass(argumentType.getTypeName());
			this.entity = (M) this.argumentClass.newInstance();
		} catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public M getEntity() {

		return entity;
	}

	public void setEntity(M entity) {

		this.entity = entity;
	}

	@Inject
	public void setEntityDAO(BaseDAO<M> baseDAO) {

		this.entityDAO = baseDAO;
	}

	public BaseDAO<M> getEntityDAO() {

		return this.entityDAO;
	}

	@SuppressWarnings("unchecked")
	public void nuevo() {

		try {
			this.entity = (M) this.argumentClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public void guardar() {

		if (entity.getId() != null) {
			getEntityDAO().edit(entity);
		} else {
			getEntityDAO().create(entity);
		}

	}

	public void destroy(M entity) {

		getEntityDAO().destroy(entity);
		reload();
	}

	public void reload() {

		// https://stackoverflow.com/questions/570015/how-do-i-reload-a-page-without-a-postdata-warning-in-javascript
		// https://stackoverflow.com/questions/32947472/how-to-reload-page-when-a-button-is-clicked
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
