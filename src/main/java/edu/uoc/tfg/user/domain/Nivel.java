package edu.uoc.tfg.user.domain;

import lombok.*;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Nivel {
    PROPIETARIO(0),
    PRESIDENTE(1),
    ADMINISTRADOR(2);

    private int valor;
    private Nivel(int value){
        this.valor = value;
    }

    public int getValor() {
        return valor;
    }
}
