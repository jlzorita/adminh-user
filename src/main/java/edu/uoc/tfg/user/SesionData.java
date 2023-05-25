package edu.uoc.tfg.user;

import lombok.Getter;
import lombok.Setter;

public class SesionData {
    @Getter
    @Setter
    private String usuario;
    @Getter
    @Setter
    private String[] sesion = new String[3];

    @Getter
    @Setter
    private boolean alta;
}
