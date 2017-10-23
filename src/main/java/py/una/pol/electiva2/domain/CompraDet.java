package py.una.pol.electiva2.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CompraDet implements BaseEntity {

	private static final String SEQUENCE = "compra_det_id_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
	private Long id;

	@ManyToOne()
	@JoinColumn(foreignKey = @ForeignKey(name = "producto_compra_det_fk"))
	private Producto producto;

	@ManyToOne()
	@JoinColumn(foreignKey = @ForeignKey(name = "compra_cab_compra_det_fk"))
	private CompraCab compraCab;

	private Integer cantidad;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Producto getProducto() {

		return producto;
	}

	public void setProducto(Producto producto) {

		this.producto = producto;
	}

	public Integer getCantidad() {

		return cantidad;
	}

	public void setCantidad(Integer cantidad) {

		this.cantidad = cantidad;
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
		CompraDet other = (CompraDet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "CompraDet [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}

	public CompraCab getCompraCab() {

		return compraCab;
	}

	public void setCompraCab(CompraCab compraCab) {

		this.compraCab = compraCab;
	}

}
