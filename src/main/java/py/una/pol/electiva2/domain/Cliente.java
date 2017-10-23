package py.una.pol.electiva2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Cliente implements BaseEntity {

	private static final String SEQUENCE = "cliente_id_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
	private Long id;

	private String ruc;
	private String nombre;
	private String apellido;

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

	public String getApellido() {

		return apellido;
	}

	public void setApellido(String apellido) {

		this.apellido = apellido;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// >:O https://developer.jboss.org/thread/187299
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Cliente [id=" + id + ", ruc=" + ruc + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
