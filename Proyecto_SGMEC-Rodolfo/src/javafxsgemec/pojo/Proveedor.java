package javafxsgemec.pojo;

public class Proveedor {

    private int idProveedor;
    private String nombreProveedor;
    private String correoElect;
    private String telefono;
    
    public Proveedor(){
    }

    public Proveedor(int idProovedor, String nombreProveedor, String correoElect, String telefono) {
        this.idProveedor = idProovedor;
        this.nombreProveedor = nombreProveedor;
        this.correoElect = correoElect;
        this.telefono = telefono;
    }

    public int getIdProovedor() {
        return idProveedor;
    }

    public void setIdProovedor(int idProovedor) {
        this.idProveedor = idProovedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCorreoElect() {
        return correoElect;
    }

    public void setCorreoElect(String correoElect) {
        this.correoElect = correoElect;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;

    }
    
    @Override
    public String toString(){
        return nombreProveedor;
    }

}
