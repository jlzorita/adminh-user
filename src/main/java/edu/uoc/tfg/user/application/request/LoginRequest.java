package edu.uoc.tfg.user.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.user.Session;
import edu.uoc.tfg.user.domain.Usuario;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @Getter
    @NotNull
    private final Usuario usuario;
    @Getter
    @NotNull
    private final String sesion;

    @JsonCreator
    public LoginRequest(@JsonProperty("usuarioData") @NotNull final Usuario usuario, String sesion) {
        this.usuario = usuario;
        this.sesion = sesion;
        // Crear / reiniciar sesión
        Session.removeUsuario(usuario.getUsuario());
        Session.addUsuario(usuario.getUsuario(), sesion);
    }
}