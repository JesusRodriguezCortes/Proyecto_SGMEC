package javafxsgemec.pojo;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String correoElect;
    private long telefono;
    private String direccion;
    
    public Proveedor(){
    }

    public Proveedor(int idProveedor, String nombre, String correoElect, long telefono, String direccion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.correoElect = correoElect;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
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

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
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
