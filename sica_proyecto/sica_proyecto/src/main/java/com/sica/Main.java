package com.sica;

import com.sica.modelo.Usuario;
import com.sica.utilidad.SesionUsuario;
import com.sica.vista.PantallaFuncionario;
import com.sica.vista.PantallaGuarda;
import com.sica.vista.PantallaLogin;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SICA ===");

        // Bucle para permitir regresar a la pantalla de Login sin finalizar el programa
        while (true) {
            PantallaLogin login = new PantallaLogin();
            boolean exito = login.mostrar();

            if (exito) {
                Usuario usuario = SesionUsuario.getInstancia().getUsuarioActual();

                if (usuario == null || usuario.getRol() == null) {
                    System.err.println("Error: El usuario no tiene un rol asignado en la sesión.");
                    continue;
                }

                String nombreRol = usuario.getRol().getNombreRol();
                System.out.println("Rol detectado: " + nombreRol);

                // 1. Roles de Seguridad
                if ("Guardia".equalsIgnoreCase(nombreRol) || 
                    "Guarda".equalsIgnoreCase(nombreRol) || 
                    "Guarda de Seguridad".equalsIgnoreCase(nombreRol)) {

                    System.out.println("Cargando interfaz de Guarda de Seguridad...");
                    new PantallaGuarda().mostrarMenu();

                // 2. Roles de Funcionario (Se agrega 'Funcionario de Empresa')
                } else if ("Funcionario".equalsIgnoreCase(nombreRol) || 
                           "Funcionario de Empresa".equalsIgnoreCase(nombreRol)) {

                    System.out.println("Cargando interfaz de Funcionario...");
                    new PantallaFuncionario().mostrarMenu();

                // 3. Roles de Administración / Superusuario
                } else if ("Admin".equalsIgnoreCase(nombreRol) || 
                           "Administrador".equalsIgnoreCase(nombreRol) || 
                           "Superusuario".equalsIgnoreCase(nombreRol)) {

                    System.out.println("Cargando interfaz de Administrador...");
                    new PantallaFuncionario().mostrarMenu();

                } else {
                    System.out.println("El rol '" + nombreRol + "' no tiene un menú asignado.");
                }

                System.out.println("\n--- Sesión finalizada. Regresando a la pantalla de login... ---\n");

            } else {
                System.out.println("No se pudo iniciar sesión o se canceló el acceso.");
                break; // Sale definitivamente del programa si le das 'Cancelar' o cierras el Login
            }
        }
    }
}