package py.una.pol.electiva2.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CompraCab implements BaseEntity {

	private static final String SEQUENCE = "compra_cab_id_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
	private Long id;

	private Date fecha;

	@ManyToOne()
	@JoinColumn(foreignKey = @ForeignKey(name = "proveedor_compra_cab_fk"))
	private Proveedor proveedor;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Date getFecha() {

		return fecha;
	}

	public void setFecha(Date fecha) {

		this.fecha = fecha;
	}

	public Proveedor getProveedor() {

		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {

		this.proveedor = proveedor;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraCab other = (CompraCab) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "CompraCab [id=" + id + ", fecha=" + fecha + ", proveedor=" + proveedor + "]";
	}

}
