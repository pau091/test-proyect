package com.sica.repositorio.mysql;

import com.sica.configuracion.ConexionBD;
import com.sica.modelo.Visita;
import com.sica.repositorio.interfaces.ConsultaVisita;
import com.sica.servicio.impl.RegistradorAuditoriaImpl;
import com.sica.servicio.interfaces.RegistradorAuditoria;

import java.sql.*;

public class ConsultaVisitaMySQL implements ConsultaVisita {

    private final RegistradorAuditoria auditoria = new RegistradorAuditoriaImpl();

    @Override
    public boolean registrarIngreso(int personaId) {
        String sql = "INSERT INTO visitas (persona_id, fecha_entrada, estado_visita_id) VALUES (?, NOW(), 1)";
        try (Connection conn = ConexionBD.getInstancia();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, personaId);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int visitaId = rs.getInt(1);
                    auditoria.registrarEvento(null, "CHECK_IN", "visitas", visitaId, "Ingreso registrado con éxito.");
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registrarSalida(int visitaId) {
        String sql = "UPDATE visitas SET fecha_salida = NOW(), estado_visita_id = 2 WHERE id = ?";
        try (Connection conn = ConexionBD.getInstancia();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, visitaId);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                auditoria.registrarEvento(null, "CHECK_OUT", "visitas", visitaId, "Salida registrada con éxito.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Visita buscarVisitaActiva(int personaId) {
        String sql = "SELECT * FROM visitas WHERE persona_id = ? AND estado_visita_id = 1";
        try (Connection conn = ConexionBD.getInstancia();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Visita v = new Visita();
                v.setId(rs.getInt("id"));
                v.setPersonaId(rs.getInt("persona_id"));
                
                Timestamp timestamp = rs.getTimestamp("fecha_entrada");
                if (timestamp != null) {
                    v.setFechaEntrada(timestamp.toLocalDateTime());
                }
                
                v.setEstadoVisitaId(rs.getInt("estado_visita_id"));
                return v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}