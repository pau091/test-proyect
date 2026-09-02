package com.sica.repositorio.interfaces;

import com.sica.modelo.Usuario;

//consulta al usuario q quiere ingresar a las empresas
public interface ConsultaUsuario {
   Usuario buscarPorEmail(String email);
   

}
