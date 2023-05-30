package javafxsgemec.pojo;

public class PedidoRefaccion {
    
    private int idPedido;
    private String fechaPedido;
    private String numeroPedido;
    private float TotalPedido;
    private String direccionEntrega;

    public PedidoRefaccion() {
    }

    
    public PedidoRefaccion(int idPedido, String fechaPedido, String numeroPedido, float TotalPedido, String direccionEntrega) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
        this.TotalPedido = TotalPedido;
        this.direccionEntrega = direccionEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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
        return TotalPedido;
    }

    public void setTotalPedido(float TotalPedido) {
        this.TotalPedido = TotalPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    
}
