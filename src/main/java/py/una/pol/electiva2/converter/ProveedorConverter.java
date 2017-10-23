package py.una.pol.electiva2.converter;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.BaseEntity;
import py.una.pol.electiva2.domain.Proveedor;

@FacesConverter("proveedorConverter")
@SessionScoped
public class ProveedorConverter implements Serializable, Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 189033291602135199L;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		LOGGER.info("convirtiendo a objecto {}", value);
		try {
			EntityManagerProvider ep = (EntityManagerProvider) context.getExternalContext().getApplicationMap()
					.get("entityManagerProvider");
			Proveedor p = ep.getEntityManager().find(Proveedor.class, Long.parseLong(value));
			LOGGER.info("obtenido como objeto {}", p);
			return p;
		} catch (Exception e) {
			LOGGER.error("erro al convertir value {} {}", value, e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		LOGGER.info("convirtiendo {} a string", value);
		if (value != null) {
			return ((BaseEntity) value).getId().toString();
		}
		return null;

	}

}
