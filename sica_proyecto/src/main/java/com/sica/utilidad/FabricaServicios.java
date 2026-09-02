package com.sica.utilidad;

import com.sica.servicio.impl.*;
import com.sica.servicio.interfaces.*;
//se retornan las instancias (Una instancia es la creación real en memoria de un objeto basado en las instrucciones de una clase.)
//instancia --> NEW 

public class FabricaServicios {
    public static ValidadorAcceso crearValidadorAcceso(){return new ValidadorAccesoImpl(); }
    public static ControladorEntradas crearControladorEntradas() {return new ControladorEntradasImpl();}
    public static RegistradorAuditoria crearRegistradorAuditoria() {return new RegistradorAuditoriaImpl();}

}
