package javafxsgemec.pojo;

public class RefaccionSucursal {
    
    private int idRefaccSucursal;
    private int idRefaccion;
    private String nombreRefacc;
    private int idSucursal;
    private String direccionSucursal;
    private int pzasDisponiblesVenta;
    private float precioVenta;

    public RefaccionSucursal() {
    }

    public RefaccionSucursal(int idRefaccSucursal, int idRefaccion, String nombreRefacc, int idSucursal, String direccionSucursal, int pzasDisponiblesVenta, float precioVenta) {
        this.idRefaccSucursal = idRefaccSucursal;
        this.idRefaccion = idRefaccion;
        this.nombreRefacc = nombreRefacc;
        this.idSucursal = idSucursal;
        this.direccionSucursal = direccionSucursal;
        this.pzasDisponiblesVenta = pzasDisponiblesVenta;
        this.precioVenta = precioVenta;
    }

    public int getIdRefaccSucursal() {
        return idRefaccSucursal;
    }

    public void setIdRefaccSucursal(int idRefaccSucursal) {
        this.idRefaccSucursal = idRefaccSucursal;
    }

    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public String getNombreRefacc() {
        return nombreRefacc;
    }

    public void setNombreRefacc(String nombreRefacc) {
        this.nombreRefacc = nombreRefacc;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public int getPzasDisponiblesVenta() {
        return pzasDisponiblesVenta;
    }

    public void setPzasDisponiblesVenta(int pzasDisponiblesVenta) {
        this.pzasDisponiblesVenta = pzasDisponiblesVenta;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    @Override
    public String toString(){
        return this.nombreRefacc;
    }
}
