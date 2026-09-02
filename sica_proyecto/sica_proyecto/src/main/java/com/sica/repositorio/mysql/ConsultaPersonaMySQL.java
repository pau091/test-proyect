package com.sica.repositorio.mysql;

import com.sica.configuracion.ConexionBD;
import com.sica.modelo.Persona;
import com.sica.repositorio.interfaces.ConsultaPersona;

import java.sql.*;

public class ConsultaPersonaMySQL implements ConsultaPersona {
    @Override
    public Persona buscarPorDocumento(String documento) {
        String sql = "SELECT id, documento_identidad, nombre, tipo_persona FROM personas WHERE documento_identidad = ?";
        try {
            Connection conn = ConexionBD.getInstancia();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Persona(
                    rs.getInt("id"),
                    rs.getString("documento_identidad"),
                    rs.getString("nombre"),
                    rs.getString("tipo_persona"),
                    null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}