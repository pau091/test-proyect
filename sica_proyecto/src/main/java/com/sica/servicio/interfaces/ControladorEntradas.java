package com.sica.servicio.interfaces;

import com.sica.excepcion.ErrorNoEncontrado;

public interface ControladorEntradas {
    void procesarIngreso(String documento) throws ErrorNoEncontrado;
    void procesarSalida(String documento) throws ErrorNoEncontrado;
}