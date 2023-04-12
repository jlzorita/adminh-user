package edu.uoc.tfg.user.domain.repository;

import edu.uoc.tfg.user.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findUsuario(String usuario);
}