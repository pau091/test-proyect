package com.sica.control;

import com.sica.excepcion.ErrorNoEncontrado;
import com.sica.servicio.impl.ControladorEntradasImpl;
import com.sica.servicio.interfaces.ControladorEntradas;

public class GestorIngresos {
    private ControladorEntradas controlador = new ControladorEntradasImpl();
    public void registrarEntrada(String documento) throws ErrorNoEncontrado {
        controlador.procesarIngreso(documento);
    }
    public void registrarSalida(String documento) throws ErrorNoEncontrado{
        controlador.procesarSalida(documento);
    }

}
