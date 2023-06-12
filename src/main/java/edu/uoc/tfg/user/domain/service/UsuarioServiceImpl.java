package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.SesionData;
import edu.uoc.tfg.user.Sesion;
import edu.uoc.tfg.user.application.request.LoginRequest;
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
    private final KafkaTemplate<String, SesionData> kafkaTemplate;


    @Override
    public Optional<Usuario> buscaUsuario(String usuario) {
        return usuarioRepository.buscaUsuario(usuario);
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

                par[0] = Sesion.getSesion(user.get().getUsuario())[0];
                par[1] = Integer.toString(level);
                par[2] = Long.toString(clienteId);

                sesionData.setSesion(par);

                Sesion.setLevel(user.get().getUsuario(), level, clienteId);
                sesionData.setAlta(true);
                Sesion.enviarSesion(sesionData, kafkaTemplate);
                return user.get().getNivel().getValor();
            } else return -1;

        } else return -1;
    }

    @Override
    public Boolean logout(String usuario) {
        if(!Sesion.comprobarSesionExiste(usuario)){
            return false;
        }else{
            Sesion.removeUsuario(usuario);
            SesionData sesionData = new SesionData();
            sesionData.setUsuario(usuario);
            sesionData.setAlta(false);
            Sesion.enviarSesion(sesionData, kafkaTemplate);
        }
        return true;
    }
}
