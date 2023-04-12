package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.ParSesion;
import edu.uoc.tfg.user.domain.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findUsuario(String usuario);
    Long enviarSesion(ParSesion parsesion);


}
