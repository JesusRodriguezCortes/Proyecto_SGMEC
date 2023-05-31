package javafxsgemec.pojo.direcciones;

public class Direccion {
    
    private int idDireccion;
    private String nombreCalle;
    private String numeroExt;
    private String numeroInt;

    public Direccion() {
    }

    public Direccion(int idDireccion, String nombreCalle, String numeroExt, String numeroInt) {
        this.idDireccion = idDireccion;
        this.nombreCalle = nombreCalle;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    @Override
    public String toString() {
        return this.nombreCalle+" "+this.numeroExt+" "+this.numeroInt;
    }
}
