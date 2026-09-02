package com.sica.servicio.impl;

import com.sica.repositorio.interfaces.ConsultaBitacora;
import com.sica.repositorio.mysql.ConsultaBitacoraMySQL;
import com.sica.servicio.interfaces.RegistradorAuditoria;

public class RegistradorAuditoriaImpl implements RegistradorAuditoria {
    private ConsultaBitacora bitacoraDAO = new ConsultaBitacoraMySQL();

    @Override
    public void registrarEvento(Integer usuarioId, String accion, String tabla, Integer registroId, String detalles) {
        bitacoraDAO.guardar(usuarioId, accion, tabla, registroId, detalles);
    }
}