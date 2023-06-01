package javafxsgemec.pojo;

public class RefaccionesCompradas {
    
    private int idPedidoRefacciones;
    private String numeroPedido;
    private int idRefaccion;
    private String nombreRefaccion;
    private int refaccCompradas;
    private float precioNetoRefacc;

    public RefaccionesCompradas() {
    }

    public RefaccionesCompradas(int idPedidoRefacciones, String numeroPedido, int idRefaccion, String nombreRefaccion, int refaccCompradas, float precioNetoRefacc) {
        this.idPedidoRefacciones = idPedidoRefacciones;
        this.numeroPedido = numeroPedido;
        this.idRefaccion = idRefaccion;
        this.nombreRefaccion = nombreRefaccion;
        this.refaccCompradas = refaccCompradas;
        this.precioNetoRefacc = precioNetoRefacc;
    }

    public int getIdPedidoRefacciones() {
        return idPedidoRefacciones;
    }

    public void setIdPedidoRefacciones(int idPedidoRefacciones) {
        this.idPedidoRefacciones = idPedidoRefacciones;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public String getNombreRefaccion() {
        return nombreRefaccion;
    }

    public void setNombreRefaccion(String nombreRefaccion) {
        this.nombreRefaccion = nombreRefaccion;
    }

    public int getRefaccCompradas() {
        return refaccCompradas;
    }

    public void setRefaccCompradas(int refaccCompradas) {
        this.refaccCompradas = refaccCompradas;
    }

    public float getPrecioNetoRefacc() {
        return precioNetoRefacc;
    }

    public void setPrecioNetoRefacc(float precioNetoRefacc) {
        this.precioNetoRefacc = precioNetoRefacc;
    }
    
    @Override
    public String toString(){
        return this.nombreRefaccion+" -> "+this.refaccCompradas+" -> "+this.precioNetoRefacc;
    }
}
