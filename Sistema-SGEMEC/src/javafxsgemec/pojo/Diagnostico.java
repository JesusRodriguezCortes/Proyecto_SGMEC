package javafxsgemec.pojo;

public class Diagnostico {
    private float cotizacion;
    private String fechaCotizacion;
    private String resultadoCotizacion;
    
    public Diagnostico(){ 
    }

    public Diagnostico(float cotizacion, String fechaCotizacion, String resultadoCotizacion) {
        this.cotizacion = cotizacion;
        this.fechaCotizacion = fechaCotizacion;
        this.resultadoCotizacion = resultadoCotizacion;
    }
    
    public void setCotizacion(float cotizacion) {
        this.cotizacion = cotizacion;
    }
    
    public float getCotizacion() {
        return cotizacion;
    }
    
    public void setFechaCotizacion(String fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }
    
    public String getFechaCotizacion() {
        return fechaCotizacion;
    }
    
    public void setResultadoCotizacion(String resultadoCotizacion) {
        this.resultadoCotizacion = resultadoCotizacion;
    }
    
    public String getResultadoCotizacion() {
        return resultadoCotizacion;
    }
    
    @Override
    public String toString(){
        return this.resultadoCotizacion;
    }
}
