
package com.sica.repositorio.interfaces;

import com.sica.modelo.Persona;
//las consultas deben ir con interface por programacion orientada objetos (POO)
public interface ConsultaPersona {
    Persona buscarPorDocumento(String documento);

}
