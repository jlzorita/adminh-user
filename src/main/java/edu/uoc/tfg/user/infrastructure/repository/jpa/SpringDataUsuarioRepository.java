package edu.uoc.tfg.user.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("from UsuarioEntity u where u.usuario = ?1")
    Optional<UsuarioEntity> findUsuario(String usuario);
}
