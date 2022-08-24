package com.s3.t.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Mensaje {
    String mensaje ;

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
