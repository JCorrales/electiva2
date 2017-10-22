package py.una.pol.electiva2.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.BaseEntity;

public abstract class GenericDM<M extends BaseEntity> extends LazyDataModel<M> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private UserTransaction userTransaction;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private Type argumentType;
	private Class<?> argumentClass;
	private List<String> fieldsNames = new ArrayList<>();

	@PostConstruct
	public void init() {

		this.argumentType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		LOGGER.info("Se ha creado el LazyDataTable de {}", argumentType.getTypeName());
		try {
			this.argumentClass = this.getClass().getClassLoader().loadClass(argumentType.getTypeName());
			for (Field field : this.argumentClass.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())
						|| Modifier.isTransient(field.getModifiers()))
					continue;
				fieldsNames.add(field.getName());
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<M> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		LOGGER.info("load int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters");
		return super.load(first, pageSize, sortField, sortOrder, filters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {

		LOGGER.info("load con multiple sorting");
		String sql = getSelect();
		if (filters != null && !filters.isEmpty()) {
			String filter = " \nWHERE ";
			for (Entry<String, Object> filtro : filters.entrySet()) {
				if (!isField(filtro.getKey())) {
					continue;
				}
				filter += getMatchExpression(filtro) + getParamExpression(filtro) + " AND";
			}
			filter = filter.substring(0, filter.length() - " AND".length());
			sql += filter;
		}
		if (multiSortMeta != null && !multiSortMeta.isEmpty()) {
			String order = " \nORDER BY ";
			for (SortMeta sortMeta : multiSortMeta) {
				if (!isField(sortMeta.getSortField()))
					continue;
				LOGGER.info("creando order, columna {} dirección {}", sortMeta.getSortField(), sortMeta.getSortOrder());
				order += sortMeta.getSortField() + " " + getOrderDir(sortMeta.getSortOrder()) + ", ";
			}
			order = order.substring(0, order.length() - ", ".length());
			sql += order;
		}
		sql += "\n OFFSET " + first + " LIMIT " + pageSize;
		Query query = entityManager.createNativeQuery(sql, argumentClass);
		setParams(query, filters);
		LOGGER.info("ejecutando consulta {}", sql);
		List<M> resultList = query.getResultList();
		LOGGER.info("Registros encontrados {}", resultList.size());
		setRowCount(resultList.size());
		return resultList;
	}

	private String getSelect() {

		// https://www.thoughts-on-java.org/jpa-native-queries/
		String select = "SELECT ";
		for (String name : fieldsNames) {
			select += "o." + name + ", ";
		}
		select = select.substring(0, select.length() - ", ".length());
		select += " FROM " + argumentClass.getSimpleName() + " o";
		return select;
	}

	private void setParams(Query query, Map<String, Object> filters) {

		Set<Entry<String, Object>> entries = filters.entrySet();
		int index = 1;
		for (Entry<String, Object> entry : entries) {
			query.setParameter(index, entry.getValue());
			index++;
		}
	}

	private String getOrderDir(SortOrder dir) {

		if (SortOrder.DESCENDING.equals(dir)) {
			return "desc";
		}
		return "asc";
	}

	@Override
	public Object getRowKey(M object) {

		return object.getId();
	}

	private String getMatchExpression(Map.Entry<String, Object> filtro) {

		Class<?> fieldType = Void.class;
		try {
			fieldType = argumentClass.getDeclaredField(filtro.getKey()).getType();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		switch (fieldType.getName()) {
			case "java.lang.String":
				return " " + filtro.getKey() + " ILIKE ";
			default:
				return " CAST(" + filtro.getKey() + " AS VARCHAR) = ";
		}
	}

	private boolean isField(String key) {

		for (String name : this.fieldsNames) {
			if (name.equals(key))
				return true;
		}
		return false;
	}

	private String getParamExpression(Entry<String, Object> filtro) {

		Class<?> fieldType = Void.class;
		try {
			fieldType = argumentClass.getDeclaredField(filtro.getKey()).getType();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		switch (fieldType.getName()) {
			case "java.lang.String":
				return "'%'||?||'%'";
			default:
				return getDefaultParamExpression(fieldType, filtro);
		}
	}

	private String getDefaultParamExpression(Class<?> fieldType, Entry<String, Object> filtro) {

		if (Number.class.isAssignableFrom(fieldType)) {
			filtro.setValue(filtro.getValue().toString().replaceAll(Pattern.quote("."), "").replaceAll(",", ""));
		}
		return " ? ";
	}

}
