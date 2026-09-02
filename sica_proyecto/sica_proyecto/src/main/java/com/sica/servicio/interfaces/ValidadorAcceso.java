package com.sica.servicio.interfaces;

import com.sica.excepcion.ErrorAutenticacion;
import com.sica.excepcion.ErrorSinPermiso;
import com.sica.modelo.Usuario;

public interface ValidadorAcceso {
    Usuario autenticar(String email, String password) throws ErrorAutenticacion;
    void validarPermiso(Usuario usuario, String permisoRequerido) throws ErrorSinPermiso;
}