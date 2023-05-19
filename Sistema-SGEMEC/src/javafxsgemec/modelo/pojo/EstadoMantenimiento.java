/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.modelo.pojo;

/**
 *
 * @author alfto
 */
public class EstadoMantenimiento {
    
    private boolean enviadoPorCliente;
    private boolean recibidoPorEmpresa;
    private boolean enEspera;
    private boolean enValoracion;
    private boolean diagnosticado;
    private boolean pagoRealizado;

    public EstadoMantenimiento() {
    }

    public EstadoMantenimiento(boolean enviadoPorCliente, boolean recibidoPorEmpresa, boolean enEspera, boolean enValoracion, boolean diagnosticado, boolean pagoRealizado) {
        this.enviadoPorCliente = enviadoPorCliente;
        this.recibidoPorEmpresa = recibidoPorEmpresa;
        this.enEspera = enEspera;
        this.enValoracion = enValoracion;
        this.diagnosticado = diagnosticado;
        this.pagoRealizado = pagoRealizado;
    }

    public boolean isEnviadoPorCliente() {
        return enviadoPorCliente;
    }

    public void setEnviadoPorCliente(boolean enviadoPorCliente) {
        this.enviadoPorCliente = enviadoPorCliente;
    }

    public boolean isRecibidoPorEmpresa() {
        return recibidoPorEmpresa;
    }

    public void setRecibidoPorEmpresa(boolean recibidoPorEmpresa) {
        this.recibidoPorEmpresa = recibidoPorEmpresa;
    }

    public boolean isEnEspera() {
        return enEspera;
    }

    public void setEnEspera(boolean enEspera) {
        this.enEspera = enEspera;
    }

    public boolean isEnValoracion() {
        return enValoracion;
    }

    public void setEnValoracion(boolean enValoracion) {
        this.enValoracion = enValoracion;
    }

    public boolean isDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(boolean diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }
    
}
