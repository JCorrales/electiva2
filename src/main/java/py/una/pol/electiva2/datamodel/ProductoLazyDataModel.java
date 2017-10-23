package py.una.pol.electiva2.datamodel;

import java.util.Map.Entry;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.SortMeta;
import py.una.pol.electiva2.domain.Producto;

@Named("productoDM")
@SessionScoped
public class ProductoLazyDataModel extends GenericDM<Producto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911703880355913081L;

	@Override
	protected String getSelect() {

		String select = "SELECT ";
		for (String name : fieldsNames) {
			select += alias + "." + name + ", ";
		}
		select += alias + ".proveedor_id, p.id , p.ruc, p.nombre, p.observacion FROM Producto " + alias
				+ " JOIN Proveedor p ON " + alias + ".proveedor_id = p.id ";
		return select;
	}

	@Override
	protected boolean isField(String key) {

		if ("proveedor.nombre".equals(key)) {
			return true;
		}
		return super.isField(key);
	}

	@Override
	protected String getMatchExpression(Entry<String, Object> filtro) {

		LOGGER.info("filtrando {}", filtro.getKey());
		if ("proveedor.nombre".equals(filtro.getKey())) {
			return "p.nombre ILIKE ";
		}
		return super.getMatchExpression(filtro);
	}

	@Override
	protected String getParamExpression(Entry<String, Object> filtro) {

		if ("proveedor.nombre".equals(filtro.getKey())) {
			return "'%'||?||'%'";
		}
		return super.getParamExpression(filtro);
	}

	@Override
	protected String getSortFieldExpression(SortMeta sortMeta) {

		if ("proveedor.nombre".equals(sortMeta.getSortField())) {
			return "p.nombre";
		}
		return super.getSortFieldExpression(sortMeta);
	}

}
