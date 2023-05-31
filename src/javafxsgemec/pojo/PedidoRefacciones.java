package javafxsgemec.pojo;

public class PedidoRefacciones {
    
    private int idPedidoRefacc;
    private String fechaPedido;
    private int numeroPedido;
    private float totalPedido;
    private String direccionEntrega;

    public PedidoRefacciones (){
        
    }

    public PedidoRefacciones(int idPedidoRefacc, String fechaPedido, int numeroPedido, float totalPedido) {
        this.idPedidoRefacc = idPedidoRefacc;
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
        this.totalPedido = totalPedido;
    }
    
    public int getIdPedidoRefacc() {
        return idPedidoRefacc;
    }

    public void setIdPedidoRefacc(int idPedidoRefacc) {
        this.idPedidoRefacc = idPedidoRefacc;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
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
    public String toString() {
        return "PedidoRefacciones{" + "fechaPedido=" + fechaPedido + ", numeroPedido=" + numeroPedido + ", totalPedido=" + totalPedido + '}';
    }
}
