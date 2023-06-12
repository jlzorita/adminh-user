package edu.uoc.tfg.user;

import java.util.HashMap;
import java.util.Map;

import edu.uoc.tfg.user.infrastructure.kafka.KafkaConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;

@Log4j2
public class Sesion {
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

     public static Long enviarSesion(SesionData sesionData, KafkaTemplate kafkaTemplate) {
        log.trace("Send " + sesionData);
        kafkaTemplate.send(KafkaConstants.TOPIC_SESSION_CRM, sesionData);
        kafkaTemplate.send(KafkaConstants.TOPIC_SESSION_CORE, sesionData);
        return 1l;
    }


}
