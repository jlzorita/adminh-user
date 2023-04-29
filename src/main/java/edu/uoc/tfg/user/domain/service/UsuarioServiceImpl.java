package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.ParSesion;
import edu.uoc.tfg.user.domain.Usuario;
import edu.uoc.tfg.user.domain.repository.UsuarioRepository;
import edu.uoc.tfg.user.infrastructure.kafka.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final KafkaTemplate<String, ParSesion> kafkaTemplate;

    @Override
    public Optional<Usuario> findUsuario(String usuario) {
        return usuarioRepository.findUsuario(usuario);
    }

    @Override
    public Long enviarSesion(ParSesion parSesion) {
        log.trace("Send " + parSesion);
        kafkaTemplate.send(KafkaConstants.TOPIC_SESSION_ADD_CRM, parSesion);
        kafkaTemplate.send(KafkaConstants.TOPIC_SESSION_ADD_CORE, parSesion);
        return 1l;
    }

}
