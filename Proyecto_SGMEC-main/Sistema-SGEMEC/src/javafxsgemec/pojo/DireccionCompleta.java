package javafxsgemec.pojo;

public class DireccionCompleta {
     
    private int idEstado;
    private String nombreEstado;
    private int idMunicipio;
    private String nombreMunicipio;
    private int idColonia;
    private String nombreColonia;
    private int idCodigoPostal;
    private String codigoPostal;
    private int idDireccion;
    private String calle;
    private String numeroExt;
    private String numeroInt;

    public DireccionCompleta() {
    }

    public DireccionCompleta(int idEstado, String nombreEstado, int idMunicipio, String nombreMunicipio, int idColonia, String nombreColonia, int idCodigoPostal, String codigoPostal, int idDireccion, String calle, String numeroExt, String numeroInt) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.idColonia = idColonia;
        this.nombreColonia = nombreColonia;
        this.idCodigoPostal = idCodigoPostal;
        this.codigoPostal = codigoPostal;
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
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

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
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
    public String toString(){
        return this.nombreMunicipio+" "+this.nombreColonia+" "+this.codigoPostal+" "+this.calle+" "+this.numeroExt+" "+this.numeroInt;
    }
}
