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
    Refaccion refaccion;
    int refaccionesCompradas;
    float precioNetoRefacciones;

    public RefaccionComprada() {
    }

    public RefaccionComprada(Refaccion refaccion, int refaccionesCompradas) {
        this.refaccion = refaccion;
        this.refaccionesCompradas = refaccionesCompradas;
    }

    public Refaccion getRefaccion() {
        return refaccion;
    }

    public void setRefaccion(Refaccion refaccion) {
        this.refaccion = refaccion;
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
        this.precioNetoRefacciones = refaccion.getPrecioCompra() * refaccionesCompradas;
    }
    
    @Override
    public String toString() {
        return refaccion.getNombreRefaccion(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
