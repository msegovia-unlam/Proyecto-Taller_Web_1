package ar.edu.unlam.tallerweb1.domain.usuarios;

public interface ServicioRegistro {
    Usuario consultarUsuario(String email, String usuarioName);

    void almacenarUsuario(Usuario usuarioBuscado);
}
