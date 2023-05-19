package javafxsgemec.pojo;

public class Proveedor {
    private int idProovedor;
    private String nombre;
    private String correoElect;
    private String telefono;
    
    public Proveedor(){
    }

    public Proveedor(int idProovedor, String nombre, String correoElect, String telefono) {
        this.idProovedor = idProovedor;
        this.nombre = nombre;
        this.correoElect = correoElect;
        this.telefono = telefono;
    }

    public int getIdProovedor() {
        return idProovedor;
    }

    public void setIdProovedor(int idProovedor) {
        this.idProovedor = idProovedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return nombre;
    }
}
