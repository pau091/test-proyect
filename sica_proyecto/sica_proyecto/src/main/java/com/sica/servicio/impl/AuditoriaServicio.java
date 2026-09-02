package com.sica.servicio.impl;

import com.sica.configuracion.ConexionBD;
import com.sica.utilidad.SesionUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuditoriaServicio {

    public static void registrarAccion(String accion, String tabla, Integer registroId, String detalles) {
        String sql = "INSERT INTO bitacora_auditoria (usuario_id, accion_realizada, tabla_afectada, registro_id_afectado, detalles) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = ConexionBD.getInstancia();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            Integer usuarioId = null;
            if (SesionUsuario.getInstancia().getUsuarioActual() != null) {
                usuarioId = SesionUsuario.getInstancia().getUsuarioActual().getId();
            }

            if (usuarioId != null) {
                stmt.setInt(1, usuarioId);
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            stmt.setString(2, accion);
            stmt.setString(3, tabla);
            
            if (registroId != null) {
                stmt.setInt(4, registroId);
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            stmt.setString(5, detalles);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}