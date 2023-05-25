package edu.uoc.tfg.user;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Session {

    protected static Map<String,String[]> sesiones = new HashMap<>();


    public static void addUsuario(String usuario, String[] sesion){
        log.trace(sesion);
        sesiones.put(usuario,sesion);
    }

    public static void removeUsuario(String usuario){
        if(sesiones.containsKey(usuario))
            sesiones.remove(usuario);
    }

    public static boolean comprobarSesionExiste(String usuario){
        return sesiones.containsKey(usuario);
    }

    public static boolean setLevel(String usuario, int nivel, Long clienteId){
        String[] value = new String[3];
        value[0] = sesiones.get(usuario)[0];
        value[1] = Integer.toString(nivel);
        value[2] = Long.toString(clienteId);
        sesiones.put(usuario, value);
        return true;
    }

    public static String[] getSesion(String usuario){
        if(sesiones.containsKey(usuario))
            return sesiones.get(usuario);
        else return null;
    }


}
