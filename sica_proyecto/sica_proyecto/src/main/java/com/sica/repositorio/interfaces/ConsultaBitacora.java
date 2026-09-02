package com.sica.repositorio.interfaces;

public interface ConsultaBitacora {
    void guardar(Integer usuarioId, String accion, String tabla, Integer registroId, String detalles );

}