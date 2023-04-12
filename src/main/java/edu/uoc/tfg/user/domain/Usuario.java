package edu.uoc.tfg.user.domain;

import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String usuario;
    private String password;
    private Nivel nivel;

}
