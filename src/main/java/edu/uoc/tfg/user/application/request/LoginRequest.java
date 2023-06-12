package edu.uoc.tfg.user.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.user.Sesion;
import edu.uoc.tfg.user.domain.Usuario;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @Getter
    @NotNull
    private final Usuario usuario;
    @Getter
    @NotNull
    private final String[] sesion = new String[2];

    @JsonCreator
    public LoginRequest(@JsonProperty("usuarioData") @NotNull final Usuario usuario, String sesion) {
        this.usuario = usuario;
        this.sesion[0] = sesion;

        // Crear / reiniciar sesión
        Sesion.removeUsuario(usuario.getUsuario());
        Sesion.addUsuario(usuario.getUsuario(), this.sesion);
    }
}