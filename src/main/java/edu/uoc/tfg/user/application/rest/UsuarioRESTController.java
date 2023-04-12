package edu.uoc.tfg.user.application.rest;

import edu.uoc.tfg.user.ParSesion;
import edu.uoc.tfg.user.application.request.LoginRequest;
import edu.uoc.tfg.user.domain.Usuario;
import edu.uoc.tfg.user.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import edu.uoc.tfg.user.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
@RestController
public class UsuarioRESTController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public Integer login(@RequestBody LoginRequest loginRequest){

        log.trace("login usuario");

        Optional<Usuario> user = usuarioService.findUsuario(loginRequest.getUsuario().getUsuario());

        if(user.isPresent()) {
            if(user.get().getPassword().equals(loginRequest.getUsuario().getPassword())){
                int level = user.get().getNivel().getValor();

                ParSesion parSesion = new ParSesion();
                parSesion.setUsuario(user.get().getUsuario());
                parSesion.setSesion(Session.getSesion(user.get().getUsuario()));
                usuarioService.enviarSesion(parSesion);
                return level;
            } else return 401; //401 - No autorizado

        } else return 401; //401 - No autorizado
    }

    @GetMapping("/user/sesion/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable String usuario) {
        return Session.getSesion(usuario);
    }
}
