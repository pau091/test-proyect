package com.sica.servicio.interfaces;

public interface RegistradorAuditoria {
    void registrarEvento(Integer usuarioId, String accion, String tabla, Integer registroId, String detalles);
}