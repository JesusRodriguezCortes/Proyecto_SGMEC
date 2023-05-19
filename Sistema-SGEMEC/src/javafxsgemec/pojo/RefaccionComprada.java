package javafxsgemec.pojo;

public class RefaccionComprada {
    private int idRefaccComprada;
    private int idPedidoRefacc;
    private int idRefaccion;
    
    public RefaccionComprada(){
        
    }

    public RefaccionComprada(int idRefaccComprada, int idPedidoRefacc, int idRefaccion) {
        this.idRefaccComprada = idRefaccComprada;
        this.idPedidoRefacc = idPedidoRefacc;
        this.idRefaccion = idRefaccion;
    }

    public int getIdRefaccComprada() {
        return idRefaccComprada;
    }

    public void setIdRefaccComprada(int idRefaccComprada) {
        this.idRefaccComprada = idRefaccComprada;
    }

    public int getIdPedidoRefacc() {
        return idPedidoRefacc;
    }

    public void setIdPedidoRefacc(int idPedidoRefacc) {
        this.idPedidoRefacc = idPedidoRefacc;
    }

    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }
}
