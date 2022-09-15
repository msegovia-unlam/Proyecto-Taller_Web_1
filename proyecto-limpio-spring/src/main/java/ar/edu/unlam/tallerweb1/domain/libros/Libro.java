package ar.edu.unlam.tallerweb1.domain.libros;

public class Libro {

    private String titulo;
    private Integer numeroDePaginas;
    private String autor;
    private String genero;

    public Libro(String titulo, Integer numeroDePaginas, String autor, String genero) {
        this.titulo = titulo;
        this.numeroDePaginas = numeroDePaginas;
        this.autor = autor;
        this.genero = genero;
    }

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
}
