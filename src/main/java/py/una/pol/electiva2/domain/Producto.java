package py.una.pol.electiva2.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Producto implements BaseEntity {

	private static final String SEQUENCE = "producto_id_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
	private Long id;

	private String codigo;
	private String nombre;
	private String descripcion;
	private Long precio;
	private Long stock = 0L;
	@ManyToOne()
	@JoinColumn(foreignKey = @ForeignKey(name = "proveedor_producto_fk"))
	private Proveedor proveedor;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public String getCodigo() {

		return codigo;
	}

	public void setCodigo(String codigo) {

		this.codigo = codigo;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public String getDescripcion() {

		return descripcion;
	}

	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	public Long getPrecio() {

		return precio;
	}

	public void setPrecio(Long precio) {

		this.precio = precio;
	}

	public Long getStock() {

		return stock;
	}

	public void setStock(Long stock) {

		this.stock = stock;
	}

	public Proveedor getProveedor() {

		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {

		this.proveedor = proveedor;
	}

	@Override
	public String toString() {

		return "Producto [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", stock=" + stock + ", proveedor=" + proveedor + "]";
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
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
