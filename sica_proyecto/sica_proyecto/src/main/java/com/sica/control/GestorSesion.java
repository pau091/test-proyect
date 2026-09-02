package com.sica.control;

import com.sica.excepcion.ErrorAutenticacion;
import com.sica.modelo.Usuario;
import com.sica.servicio.impl.ValidadorAccesoImpl;
import com.sica.servicio.interfaces.ValidadorAcceso;
import com.sica.utilidad.SesionUsuario;
public class GestorSesion {
    private ValidadorAcceso validador = new ValidadorAccesoImpl ();

    public boolean iniciarSesion(String email, String password) throws ErrorAutenticacion {
        Usuario usuario = validador.autenticar(email, password);
        if(usuario !=null) {
            SesionUsuario.getInstancia().iniciarSesion(usuario);
            return true;

        }
        return true;

    }
    public void cerrarSesion(){
        SesionUsuario.getInstancia().cerrarSesion();
    }
}


