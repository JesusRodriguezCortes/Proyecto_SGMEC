package javafxsgemec.pojo;

public class Refaccion {
    private String nombre;
    private String tipo;
    private int piezasDispo;
    private float precio;
    
    public Refaccion(){
    }

    public Refaccion(String nombre, String tipo, int piezasDispo, float precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.piezasDispo = piezasDispo;
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return this.tipo;
    }

    public void setPiezasDispo(int piezasDispo) {
        this.piezasDispo = piezasDispo;
    }

    public int getPiezasDispo() {
        return this.piezasDispo;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return this.precio;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
