package py.una.pol.electiva2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Proveedor implements BaseEntity {

	private static final String SEQUENCE = "proveedor_id_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
	private Long id;

	private String ruc;
	private String nombre;
	private String observacion;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public String getRuc() {

		return ruc;
	}

	public void setRuc(String ruc) {

		this.ruc = ruc;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public String getObservacion() {

		return observacion;
	}

	public void setObservacion(String observacion) {

		this.observacion = observacion;
	}

}
