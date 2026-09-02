package com.sica.modelo;

public class Persona {
    private int id;
    private String documento;
    private String nombre;
    private String tipoPersona; //rol de la persona dentro del sistema al momento de ingresar a las instalaciones.
    private Empresa empresa;

    public Persona(int id, String documento, String nombre, String tipoPersona, Empresa empresa) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.tipoPersona = tipoPersona; 
        this.empresa = empresa;
    }

    public int getId() { return id; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipoPersona; }
    public Empresa getEmpresa() { return empresa; }
}