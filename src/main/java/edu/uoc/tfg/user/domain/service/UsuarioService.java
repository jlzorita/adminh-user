package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.SesionData;
import edu.uoc.tfg.user.application.request.LoginRequest;
import edu.uoc.tfg.user.domain.Usuario;

import java.util.Optional;

public interface  UsuarioService {
    Optional<Usuario> buscaUsuario(String usuario);
    //Long enviarSesion(SesionData parsesion);

    int login(LoginRequest loginRequest);
    Boolean logout(String usuario);

}
