package ar.edu.unlam.tallerweb1.domain.libros;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class ImagenLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
}
