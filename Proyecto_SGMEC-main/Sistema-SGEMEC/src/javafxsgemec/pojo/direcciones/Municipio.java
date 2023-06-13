package javafxsgemec.pojo.direcciones;

public class Municipio {
        
    private int idMunicipio;
    private String nombreMunicipio;
    private int idEstado;
    private String nombreEstado;

    public Municipio() {
    }

    public Municipio(int idMunicipio, String nombreMunicipio, int idEstado, String nombreEstado) {
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
    @Override
    public String toString(){
        return this.nombreMunicipio;
    }
}
