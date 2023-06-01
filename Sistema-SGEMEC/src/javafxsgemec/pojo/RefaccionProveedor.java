package javafxsgemec.pojo;

public class RefaccionProveedor {
    
    private int idRefaccProveedor;
    private int idProveedor;
    private String nombreProveedor;
    private int idRefaccion;
    private String nombreRefacc;
    private int pzasDisponiblesCompra;
    private float precioCompra;

    public RefaccionProveedor() {
    }

    public RefaccionProveedor(int idRefaccProveedor, int idProveedor, String nombreProveedor, int idRefaccion, String nombreRefacc, int pzasDisponiblesCompra, float precioCompra) {
        this.idRefaccProveedor = idRefaccProveedor;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.idRefaccion = idRefaccion;
        this.nombreRefacc = nombreRefacc;
        this.pzasDisponiblesCompra = pzasDisponiblesCompra;
        this.precioCompra = precioCompra;
    }

    public void setIdRefaccProveedor(int idRefaccProveedor) {
        this.idRefaccProveedor = idRefaccProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public void setNombreRefacc(String nombreRefacc) {
        this.nombreRefacc = nombreRefacc;
    }

    public void setPzasDisponiblesCompra(int pzasDisponiblesCompra) {
        this.pzasDisponiblesCompra = pzasDisponiblesCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    @Override
    public String toString(){
        return this.nombreRefacc;
    }
}
