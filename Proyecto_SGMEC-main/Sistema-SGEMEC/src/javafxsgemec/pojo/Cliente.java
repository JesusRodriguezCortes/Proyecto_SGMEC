package javafxsgemec.pojo;

public class Cliente {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private long telefono;
    
    public Cliente(){
        
    }
    
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, long telefono) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getTelefono() {
        return this.telefono;
    }
    
    @Override
    public String toString(){
        return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
    }
}
