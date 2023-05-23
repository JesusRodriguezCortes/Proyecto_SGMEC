/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.pojo;

/**
 *
 * @author je_zu
 */
public class RefaccionComprada {
    private String nombreRefaccion;
    private String tipoRefaccion;
    private int refaccionesCompradas;
    private float precioNetoRefacciones;
    private float precioCompra;

    
    public RefaccionComprada() {
    }

    public RefaccionComprada(String nombreRefaccion, String tipoRefaccion, int refaccionesCompradas, float precioNetoRefacciones, float precioCompra) {
        this.nombreRefaccion = nombreRefaccion;
        this.tipoRefaccion = tipoRefaccion;
        this.refaccionesCompradas = refaccionesCompradas;
        this.precioNetoRefacciones = precioNetoRefacciones;
        this.precioCompra = precioCompra;
    }

    public String getNombreRefaccion() {
        return nombreRefaccion;
    }

    public void setNombreRefaccion(String nombreRefaccion) {
        this.nombreRefaccion = nombreRefaccion;
    }

    public String getTipoRefaccion() {
        return tipoRefaccion;
    }

    public void setTipoRefaccion(String tipoRefaccion) {
        this.tipoRefaccion = tipoRefaccion;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    public int getRefaccionesCompradas() {
        return refaccionesCompradas;
    }

    public void setRefaccionesCompradas(int refaccionesCompradas) {
        this.refaccionesCompradas = refaccionesCompradas;
    }

    public float getPrecioNetoRefacciones() {
        return precioNetoRefacciones;
    }

    public void setPrecioNetoRefacciones() {
        this.precioNetoRefacciones = precioCompra * refaccionesCompradas;
    }
    
    @Override
    public String toString() {
        return nombreRefaccion; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
