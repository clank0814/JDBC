/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author Mario
 */
public class Producto {
    
    private Integer codigo;
    private String nombre;
    private Double precio;
    private Fabricante cod_Fabricante;

// CONSTRUCTOR DE LA LLAVE FORANEA    
    public Producto() {
        this.cod_Fabricante = new Fabricante();
    }

    public Producto(Integer codigo, String nombre, Double precio, Fabricante cod_Fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cod_Fabricante = cod_Fabricante;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Fabricante getCod_Fabricante() {
        return cod_Fabricante;
    }

    public void setCod_Fabricante(Fabricante cod_Fabricante) {
        this.cod_Fabricante = cod_Fabricante;
    }
    
    
    
    
}
