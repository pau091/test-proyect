package com.sica.repositorio.mysql;

import com.sica.configuracion.ConexionBD;
import com.sica.modelo.Empresa;
import com.sica.repositorio.interfaces.ConsultaEmpresa;

import java.sql.*;

public class ConsultaEmpresaMySQL implements ConsultaEmpresa {
    @Override
    public Empresa buscarPorId(int id) {
        String sql = "SELECT id, nombre, nit FROM empresas WHERE id = ?";
        try {
            Connection conn = ConexionBD.getInstancia();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empresa(rs.getInt("id"), rs.getString("nombre"), rs.getString("nit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}