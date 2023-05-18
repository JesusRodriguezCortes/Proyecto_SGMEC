package javafxsgemec.pojo;

public class Proveedor {
    private int idProovedor;
    private String nombre;
    private String correoElect;
    private String telefono;
    private String direccion;
    
    public Proveedor(){
    }

    public Proveedor(int idProovedor, String nombre, String correoElect, String telefono, String direccion) {
        this.idProovedor = idProovedor;
        this.nombre = nombre;
        this.correoElect = correoElect;
        this.telefono = telefono;
        this.direccion = direccion;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    @Override
    public String toString(){
        return nombre;
    }
}
