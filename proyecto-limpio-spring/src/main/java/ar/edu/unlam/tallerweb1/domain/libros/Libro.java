package ar.edu.unlam.tallerweb1.domain.libros;

import javax.persistence.*;

@Entity
@Table(name="Libro")
public class Libro {

    @Id @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "numeroDePaginas")
    private Integer numeroDePaginas;

    @Column(name = "autor")
    private String autor;

    @Column(name = "genero")
    private String genero;

    @Column(name = "imagen")
    private String imagen;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }
    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public long getId() { return id;  }
    public void setId(long id) { this.id = id; }
}
