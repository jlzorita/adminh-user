package edu.uoc.tfg.user.infrastructure.repository.jpa;

import edu.uoc.tfg.user.domain.Nivel;
import edu.uoc.tfg.user.domain.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity implements DomainTranslatable<Usuario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "password")
    private String password;

    @Column(name = "nivel")
    private Nivel nivel;

    @Column(name = "cliente_id")
    private Long clienteId;

    public static UsuarioEntity fromDomain(Usuario user) {
        if (user == null) {
            return null;
        }

        return UsuarioEntity.builder()
                .id(user.getId())
                .usuario(user.getUsuario())
                .password(user.getPassword())
                .nivel(user.getNivel())
                .clienteId(user.getClienteId())
                .build();
    }
    @Override
    public Usuario toDomain() {
        return Usuario.builder()
                .id(this.getId())
                .usuario(this.getUsuario())
                .password(this.getPassword())
                .nivel(this.getNivel())
                .clienteId(this.clienteId)
                .build();
    }
}