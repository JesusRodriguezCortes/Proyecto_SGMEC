package javafxsgemec.pojo;

public class Proveedor {
    private String nombre;
    private String correoElect;
    private long telefono;
    
    public Proveedor(){
    }

    public Proveedor(String nombre, String correoElect, long telefono) {
        this.nombre = nombre;
        this.correoElect = correoElect;
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public void setCorreoElect(String correoElect) {
        this.correoElect = correoElect;
    }
    
    public String getCorreoElect() {
        return this.correoElect;
    }
        
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    
    public long getTelefono(){
        return this.telefono;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
