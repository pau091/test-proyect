package com.sica.utilidad;
import com.sica.modelo.Usuario;

public class SesionUsuario {
    private static SesionUsuario instancia;
    private Usuario usuarioActual;

    private SesionUsuario(){}

    public static SesionUsuario getInstancia(){
        if(instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }


    public void iniciarSesion(Usuario usuario) {this.usuarioActual = usuario;}
    public void cerrarSesion() {this.usuarioActual = null; }
    public Usuario getUsuarioActual() {return usuarioActual; }
}
