package javafxsgemec.pojo;

public class PedidoRefaccion {
   
    private int idPedidoRefaccion;
    private String fechaPedido;
    private String numeroPedido;
    private float totalPedido;
    private String direccionEntrega;

    public PedidoRefaccion() {}

    public PedidoRefaccion(int idPedidoRefaccion, String fechaPedido, String numeroPedido, float totalPedido, String direccionEntrega) {
        this.idPedidoRefaccion = idPedidoRefaccion;
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
        this.totalPedido = totalPedido;
        this.direccionEntrega = direccionEntrega;
    }
    
    public int getIdPedidoRefaccion() {
        return idPedidoRefaccion;
    }

    public void setIdPedidoRefaccion(int idPedidoRefaccion) {
        this.idPedidoRefaccion = idPedidoRefaccion;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public float getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(float totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    
    @Override
    public String toString(){
        return this.numeroPedido;
    }
}
