package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.Carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Setter
@Getter
@ToString
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "rol")
	private Rol rol;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "activo")
	private Boolean activo = false;


//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(
//			name = "Carrito",
//			joinColumns = { @JoinColumn(name = "usuario_id") },
//			inverseJoinColumns = { @JoinColumn(name = "libro_id") }
//	)
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set <Carrito> carrito;


}
