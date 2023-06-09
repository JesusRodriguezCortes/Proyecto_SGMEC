package javafxsgemec.pojo;

public class Refaccion {


    private int idRefaccion;
    private String nombreRefaccion;
    private String tipoRefaccion;
    private String codigoRefaccion;
    private int pzasDisponiblesCompra;
    private int pzasDisponiblesVenta;
    private float precioCompra;
    private float precioVenta;
   
    public Refaccion(){
    }

    public Refaccion(int idRefaccion, String nombreRefaccion, String tipoRefaccion, String codigoRefaccion, int pzasDisponiblesCompra, int pzasDisponiblesVenta, float precioCompra, float precioVenta) {
        this.idRefaccion = idRefaccion;
        this.nombreRefaccion = nombreRefaccion;
        this.tipoRefaccion = tipoRefaccion;
        this.codigoRefaccion = codigoRefaccion;
        this.pzasDisponiblesCompra = pzasDisponiblesCompra;
        this.pzasDisponiblesVenta = pzasDisponiblesVenta;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    

    public String getNombreRefaccion() {
        return nombreRefaccion;
    }

    public void setNombreRefaccion(String nombreRefaccion) {
        this.nombreRefaccion = nombreRefaccion;
    }

    public String getTipoRefaccion() {
        return tipoRefaccion;
    }

    public void setTipoRefaccion(String tipoRefaccion) {
        this.tipoRefaccion = tipoRefaccion;
    }

    public String getCodigoRefaccion() {
        return codigoRefaccion;
    }

    public void setCodigoRefaccion(String codigoRefaccion) {
        this.codigoRefaccion = codigoRefaccion;
    }

    public int getPzasDisponiblesCompra() {
        return pzasDisponiblesCompra;
    }

    public void setPzasDisponiblesCompra(int pzasDisponiblesCompra) {
        this.pzasDisponiblesCompra = pzasDisponiblesCompra;
    }

    public int getPzasDisponiblesVenta() {
        return pzasDisponiblesVenta;
    }

    public void setPzasDisponiblesVenta(int pzasDisponiblesVenta) {
        this.pzasDisponiblesVenta = pzasDisponiblesVenta;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

   
    @Override
    public String toString(){
        return this.nombreRefaccion;
    }

}
