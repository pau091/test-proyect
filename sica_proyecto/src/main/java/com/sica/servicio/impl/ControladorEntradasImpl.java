package com.sica.servicio.impl;

import com.sica.excepcion.ErrorNoEncontrado;
import com.sica.modelo.Persona;
import com.sica.modelo.Visita;
import com.sica.repositorio.interfaces.ConsultaPersona;
import com.sica.repositorio.interfaces.ConsultaVisita;
import com.sica.repositorio.mysql.ConsultaPersonaMySQL;
import com.sica.repositorio.mysql.ConsultaVisitaMySQL;
import com.sica.servicio.interfaces.ControladorEntradas;

public class ControladorEntradasImpl implements ControladorEntradas {

    private final ConsultaPersona personaDAO;
    private final ConsultaVisita visitaDAO;

    public ControladorEntradasImpl() {
        this.personaDAO = new ConsultaPersonaMySQL();
        this.visitaDAO = new ConsultaVisitaMySQL();
    }

    public ControladorEntradasImpl(ConsultaPersona personaDAO, ConsultaVisita visitaDAO) {
        this.personaDAO = personaDAO;
        this.visitaDAO = visitaDAO;
    }

    @Override
    public void procesarIngreso(String documento) throws ErrorNoEncontrado {
        Persona p = personaDAO.buscarPorDocumento(documento);
        if (p == null) {
            throw new ErrorNoEncontrado("Persona no registrada en el sistema.");
        }
        visitaDAO.registrarIngreso(p.getId());
    }

    @Override
    public void procesarSalida(String documento) throws ErrorNoEncontrado {
        Persona p = personaDAO.buscarPorDocumento(documento);
        if (p == null) {
            throw new ErrorNoEncontrado("Persona no registrada.");
        }
        Visita v = visitaDAO.buscarVisitaActiva(p.getId());
        if (v == null) {
            throw new ErrorNoEncontrado("La persona no tiene un ingreso activo registrado.");
        }
        visitaDAO.registrarSalida(v.getId());
    }
}