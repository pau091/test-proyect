package com.sica.servicio.impl;

// Importación de excepciones
import com.sica.excepcion.ErrorAutenticacion;
import com.sica.excepcion.ErrorSinPermiso;

// Importación del modelo
import com.sica.modelo.Usuario;

// Importaciones corregidas de interfaces e implementaciones
import com.sica.repositorio.interfaces.ConsultaUsuario;
import com.sica.repositorio.mysql.ConsultaUsuarioMySQL;
import com.sica.servicio.interfaces.RegistradorAuditoria;
import com.sica.servicio.interfaces.ValidadorAcceso;

/**
 * Implementación de la lógica de negocio para la autenticación 
 * y verificación de permisos de usuarios en el sistema.
 */
public class ValidadorAccesoImpl implements ValidadorAcceso {

    // Se asigna la implementación correcta para consultar Usuarios (ConsultaUsuarioMySQL)
    private ConsultaUsuario usuarioDAO = new ConsultaVisitaMySQL();
    
    // Instancia del servicio de auditoría
    private RegistradorAuditoria auditoria = new RegistradorAuditoriaImpl();

    @Override
    public Usuario autenticar(String email, String password) throws ErrorAutenticacion {
        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario == null || !usuario.getPassword().equals(password)) {
            auditoria.registrarEvento(null, "LOGIN_FALLIDO", "usuarios", null, "Correo: " + email);
            throw new ErrorAutenticacion("Credenciales incorrectas o usuario inactivo.");
        }

        auditoria.registrarEvento(usuario.getId(), "LOGIN_EXITOSO", "usuarios", usuario.getId(), "Inicio de sesión exitoso");
        return usuario;
    }

    @Override
    public void validarPermiso(Usuario usuario, String permisoRequerido) throws ErrorSinPermiso {
        if (usuario == null || !usuario.getRol().tienePermiso(permisoRequerido)) {
            throw new ErrorSinPermiso("Acceso denegado. Se requiere el permiso: " + permisoRequerido);
        }
    }
}