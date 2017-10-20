package py.una.pol.electiva2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
