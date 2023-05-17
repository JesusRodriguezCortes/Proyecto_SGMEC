package javafxsgemec.pojo;

public class Servicio {
    private float precio;
    private String tipoServicio;
    
    public Servicio(){
    }

    public Servicio(float precio, String tipoServicio) {
        this.precio = precio;
        this.tipoServicio = tipoServicio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio(){
        return this.precio;
    }
    
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
    public String getTipoServicio(){
        return this.tipoServicio;
    }
    
    @Override
    public String toString(){
        return this.tipoServicio+" "+this.precio;
    }
}
