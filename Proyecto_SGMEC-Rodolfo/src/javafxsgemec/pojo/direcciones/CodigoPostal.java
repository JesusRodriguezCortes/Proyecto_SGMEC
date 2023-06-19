package javafxsgemec.pojo.direcciones;

public class CodigoPostal {
    
    private int idCodigoPostal;
    private String codigoPostal;
    private int idMunicipio;
    private String nombreMunicipio;

    public CodigoPostal() {
    }

    public CodigoPostal(int idCodigoPostal, String codigoPostal, int idMunicipio, String nombreMunicipio) {
        this.idCodigoPostal = idCodigoPostal;
        this.codigoPostal = codigoPostal;
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
    }

    public int getIdCodigoPostal() {
        return idCodigoPostal;
    }

    public void setIdCodigoPostal(int idCodigoPostal) {
        this.idCodigoPostal = idCodigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    @Override
    public String toString() {
        return this.codigoPostal;
    }
    
    
}
