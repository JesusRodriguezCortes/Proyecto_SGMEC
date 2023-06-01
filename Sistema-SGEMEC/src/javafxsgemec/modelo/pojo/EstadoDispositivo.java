/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.modelo.pojo;

/**
 *
 * @author alfto
 */
public class EstadoDispositivo {
    
    private String estado;
    private int idEstadoDispositivo;

    public EstadoDispositivo(String estado, int idEstadoDispositivo) {
        this.estado = estado;
        this.idEstadoDispositivo = idEstadoDispositivo;
    }

    public EstadoDispositivo() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEstadoDispositivo() {
        return idEstadoDispositivo;
    }

    public void setIdEstadoDispositivo(int idEstadoDispositivo) {
        this.idEstadoDispositivo = idEstadoDispositivo;
    }
    
}
