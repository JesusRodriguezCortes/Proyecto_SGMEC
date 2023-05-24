package javafxsgemec.pojo;

/**
 *
 * @author je_zu
 */
public class RefaccionComprada {
    private Refaccion refaccion;
    private int refaccionesCompradas;
    private float precioNetoRefacciones;

    
    public RefaccionComprada() {
    }

    public RefaccionComprada(Refaccion refaccion, int refaccionesCompradas, float precioNetoRefacciones) {
        this.refaccion = refaccion;
        this.refaccionesCompradas = refaccionesCompradas;
        this.precioNetoRefacciones = precioNetoRefacciones;
    }

    public Refaccion getRefaccion() {
        return refaccion;
    }

    public void setRefaccion(Refaccion refaccion) {
        this.refaccion = refaccion;
    }
 
    public int getRefaccionesCompradas() {
        return refaccionesCompradas;
    }

    public void setRefaccionesCompradas(int refaccionesCompradas) {
        this.refaccionesCompradas = refaccionesCompradas;
    }

    public float getPrecioNetoRefacciones() {
        return precioNetoRefacciones;
    }

    public void setPrecioNetoRefacciones() {
        this.precioNetoRefacciones = refaccion.getPrecioCompra() * refaccionesCompradas;
    }
    
    public String getNombreRefaccion(){
        return refaccion.getNombreRefaccion();
    }
    
    public String getTipoRefaccion(){
        return refaccion.getTipoRefaccion();
    }
    
    public float getPrecioCompra(){
        return refaccion.getPrecioCompra();
    }
    
    public int getIdRefaccion(){
        return refaccion.getIdRefaccion();
    }
}
    
