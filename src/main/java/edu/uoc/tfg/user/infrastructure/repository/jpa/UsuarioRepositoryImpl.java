package edu.uoc.tfg.user.infrastructure.repository.jpa;

import edu.uoc.tfg.user.domain.Usuario;
import edu.uoc.tfg.user.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final SpringDataUsuarioRepository jpaRepository;


    @Override
    public Optional<Usuario> findUsuario(String usuario) {
        return jpaRepository.findUsuario(usuario).map(UsuarioEntity::toDomain);
    }

}
