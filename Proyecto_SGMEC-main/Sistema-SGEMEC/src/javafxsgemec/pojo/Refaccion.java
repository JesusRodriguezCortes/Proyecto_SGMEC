package javafxsgemec.pojo;

public class Refaccion {
    private int idRefaccion;
    private String nombre;
    private String tipoRefaccion;
    private String SKU;
    private int pzsCompra;
    private int pzsVenta;
    private float precioVenta;
    private float precioCompra;
    private int idProveedor;  
    private String nombreProveedor;
    private int idDiagnostico;
    private String fechaFin;
    
    public Refaccion(){
    }

    public Refaccion(int idRefaccion, String nombre, String tipoRefaccion, String SKU, int pzsCompra, int pzsVenta, float precioVenta, float precioCompra, int idProveedor) {
        this.idRefaccion = idRefaccion;
        this.nombre = nombre;
        this.tipoRefaccion = tipoRefaccion;
        this.SKU = SKU;
        this.pzsCompra = pzsCompra;
        this.pzsVenta = pzsVenta;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.idProveedor = idProveedor;
    }

    public Refaccion(int idRefaccion, String nombre, String tipoRefaccion, String SKU, int pzsCompra, int pzsVenta, float precioVenta, float precioCompra, int idProveedor, String nombreProveedor, int idDiagnostico, String fechaFin) {
        this.idRefaccion = idRefaccion;
        this.nombre = nombre;
        this.tipoRefaccion = tipoRefaccion;
        this.SKU = SKU;
        this.pzsCompra = pzsCompra;
        this.pzsVenta = pzsVenta;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.idDiagnostico = idDiagnostico;
        this.fechaFin = fechaFin;
    }

    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoRefaccion() {
        return tipoRefaccion;
    }

    public void setTipoRefaccion(String tipoRefaccion) {
        this.tipoRefaccion = tipoRefaccion;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public int getPzsCompra() {
        return pzsCompra;
    }

    public void setPzsCompra(int pzsCompra) {
        this.pzsCompra = pzsCompra;
    }

    public int getPzsVenta() {
        return pzsVenta;
    }

    public void setPzsVenta(int pzsVenta) {
        this.pzsVenta = pzsVenta;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    @Override
    public String toString(){
        return this.nombre;
    }
}
