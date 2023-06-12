package edu.uoc.tfg.user.application.rest;

import edu.uoc.tfg.user.application.request.LoginRequest;
import edu.uoc.tfg.user.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import edu.uoc.tfg.user.Sesion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
@RestController
public class UsuarioRESTController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> login(@RequestBody LoginRequest loginRequest){

        log.trace("login usuario");
        int level =  usuarioService.login(loginRequest);

        if(level >= 0) return ResponseEntity.ok().body(level);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity logout(@PathVariable String usuario){

        if(usuarioService.logout(usuario)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/user/sesion/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public String[] getUser(@PathVariable String usuario) {
        return Sesion.getSesion(usuario);
    }
}
