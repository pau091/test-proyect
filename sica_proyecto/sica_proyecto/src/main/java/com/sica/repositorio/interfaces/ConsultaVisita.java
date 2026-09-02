package com.sica.repositorio.interfaces;

import com.sica.modelo.Visita;


public interface ConsultaVisita {
    void registrarIngreso(int personaId);
    Visita buscarVisitaActiva(int personaId);
    void registrarSalida(int visitaId);


}
