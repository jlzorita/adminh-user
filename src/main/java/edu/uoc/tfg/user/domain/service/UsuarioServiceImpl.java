package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.SesionData;
import edu.uoc.tfg.user.Session;
import edu.uoc.tfg.user.application.request.LoginRequest;
import edu.uoc.tfg.user.domain.Usuario;
import edu.uoc.tfg.user.domain.repository.UsuarioRepository;
import edu.uoc.tfg.user.infrastructure.kafka.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final KafkaTemplate<String, SesionData> kafkaTemplate;


    @Override
    public Optional<Usuario> buscaUsuario(String usuario) {
        return usuarioRepository.buscaUsuario(usuario);
    }

    @Override
    public Long enviarSesion(SesionData sesionData) {
        log.trace("Send " + sesionData);
        kafkaTemplate.send(KafkaConstants.TOPIC_SESSION_CRM, sesionData);
        kafkaTemplate.send(KafkaConstants.TOPIC_SESSION_CORE, sesionData);
        return 1l;
    }

    @Override
    public int login(LoginRequest loginRequest) {
        Optional<Usuario> user = buscaUsuario(loginRequest.getUsuario().getUsuario());
        if(user.isPresent()) {
            if(user.get().getPassword().equals(loginRequest.getUsuario().getPassword())){
                int level = user.get().getNivel().getValor();
                Long clienteId = user.get().getClienteId();

                SesionData sesionData = new SesionData();
                sesionData.setUsuario(user.get().getUsuario());
                String[] par = new String[3];

                par[0] = Session.getSesion(user.get().getUsuario())[0];
                par[1] = Integer.toString(level);
                par[2] = Long.toString(clienteId);

                sesionData.setSesion(par);

                Session.setLevel(user.get().getUsuario(), level, clienteId);
                sesionData.setAlta(true);
                enviarSesion(sesionData);
                return user.get().getNivel().getValor();
            } else return -1;

        } else return -1;
    }

    @Override
    public Boolean logout(String usuario) {
        if(!Session.comprobarSesionExiste(usuario)){
            return false;
        }else{
            Session.removeUsuario(usuario);
            SesionData sesionData = new SesionData();
            sesionData.setUsuario(usuario);
            sesionData.setAlta(false);
            enviarSesion(sesionData);
        }
        return true;
    }
}
