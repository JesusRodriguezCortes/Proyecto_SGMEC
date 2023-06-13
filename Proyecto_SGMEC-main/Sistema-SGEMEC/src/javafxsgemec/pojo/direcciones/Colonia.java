package javafxsgemec.pojo.direcciones;

public class Colonia {
    
    private int idColonia;
    private String nombreColonia;
    private int idDireccion;
    private String direccion;

    public Colonia() {
    }

    public Colonia(int idColonia, String nombreColonia, int idDireccion, String direccion) {
        this.idColonia = idColonia;
        this.nombreColonia = nombreColonia;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
    }

    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return this.nombreColonia;
    } 
}
