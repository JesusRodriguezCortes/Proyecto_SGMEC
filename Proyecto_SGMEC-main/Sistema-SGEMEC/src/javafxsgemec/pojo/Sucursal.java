/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.pojo;

/**
 *
 * @author je_zu
 */
public class Sucursal {
    private int idSucursal;
    private String direccionSucursal;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, String direccionSucursal) {
        this.idSucursal = idSucursal;
        this.direccionSucursal = direccionSucursal;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    @Override
    public String toString() {
        return direccionSucursal;
    }
}
