package javafxsgemec.pojo;

import java.util.List;
import javafxsgemec.pojo.Refaccion;

public class Diagnostico {
    private int idDiagnostico;
    private String resultadoDiagnostico;
    private int idServicio;
    private String nombreServicio;
    private float cotizacion;
    private String fechaInicio;
    private String fechaFin;
    private int idDispositivo;
    private String marcaDispositivo;
    private String modeloDispositivo;
    private String errorDispositivo;
    private String Estado;
    private byte[] Imagen;
    private List<Refaccion> refacciones;
    
    public Diagnostico(){ 
    }

    public Diagnostico(int idDiagnostico, String resultadoDiagnostico, int idServicio, String nombreServicio, 
                       float cotizacion, String fechaInicio, String fechaFin, int idDispositivo, 
                       String marcaDispositivo, String modeloDispositivo, String errorDispositivo, 
                       String Estado, byte[] Imagen, List<Refaccion> refacciones) {
        this.idDiagnostico = idDiagnostico;
        this.resultadoDiagnostico = resultadoDiagnostico;
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.cotizacion = cotizacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idDispositivo = idDispositivo;
        this.marcaDispositivo = marcaDispositivo;
        this.modeloDispositivo = modeloDispositivo;
        this.errorDispositivo = errorDispositivo;
        this.Estado = Estado;
        this.Imagen = Imagen;
        this.refacciones = refacciones;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getResultadoDiagnostico() {
        return resultadoDiagnostico;
    }

    public void setResultadoDiagnostico(String resultadoDiagnostico) {
        this.resultadoDiagnostico = resultadoDiagnostico;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public float getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(float cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getMarcaDispositivo() {
        return marcaDispositivo;
    }

    public void setMarcaDispositivo(String marcaDispositivo) {
        this.marcaDispositivo = marcaDispositivo;
    }

    public String getModeloDispositivo() {
        return modeloDispositivo;
    }

    public void setModeloDispositivo(String modeloDispositivo) {
        this.modeloDispositivo = modeloDispositivo;
    }

    public String getErrorDispositivo() {
        return errorDispositivo;
    }

    public void setErrorDispositivo(String errorDispositivo) {
        this.errorDispositivo = errorDispositivo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    public List<Refaccion> getRefacciones() {
        return refacciones;
    }

    public void setRefacciones(List<Refaccion> refacciones) {
        this.refacciones = refacciones;
    }

   @Override
    public String toString(){
        return this.nombreServicio+" "+this.cotizacion;
    }
}
