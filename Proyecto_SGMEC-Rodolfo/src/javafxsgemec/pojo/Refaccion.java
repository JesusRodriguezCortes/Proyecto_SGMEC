package javafxsgemec.pojo;

public class Refaccion {
    
    private int idRefaccion;
    private String nombreRefaccion;
    private String codigoRefaccion;
    private float precioCompra;
    private int pzasDisponiblesCompra;  
    private float precioVenta;
    private int pzasDisponiblesVenta;
    private int idTipoRefaccion;
    private String tipoRefaccion;
    private int idDiagnostico;
    private String fechaInicio;
    private String fechaFin;
    
    
    public Refaccion() {}

    public Refaccion(int idRefaccion, String nombreRefaccion, String codigoRefaccion, float precioCompra, int pzasDisponiblesCompra, float precioVenta, int pzasDisponiblesVenta, int idTipoRefaccion, String tipoRefaccion, int idDiagnostico, String fechaInicio, String fechaFin) {
        this.idRefaccion = idRefaccion;
        this.nombreRefaccion = nombreRefaccion;
        this.codigoRefaccion = codigoRefaccion;
        this.precioCompra = precioCompra;
        this.pzasDisponiblesCompra = pzasDisponiblesCompra;
        this.precioVenta = precioVenta;
        this.pzasDisponiblesVenta = pzasDisponiblesVenta;
        this.idTipoRefaccion = idTipoRefaccion;
        this.tipoRefaccion = tipoRefaccion;
        this.idDiagnostico = idDiagnostico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public String getCodigoRefaccion() {
        return codigoRefaccion;
    }

    public void setCodigoRefaccion(String codigoRefaccion) {
        this.codigoRefaccion = codigoRefaccion;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPzasDisponiblesCompra() {
        return pzasDisponiblesCompra;
    }

    public void setPzasDisponiblesCompra(int pzasDisponiblesCompra) {
        this.pzasDisponiblesCompra = pzasDisponiblesCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getPzasDisponiblesVenta() {
        return pzasDisponiblesVenta;
    }

    public void setPzasDisponiblesVenta(int pzasDisponiblesVenta) {
        this.pzasDisponiblesVenta = pzasDisponiblesVenta;
    }

    public int getIdTipoRefaccion() {
        return idTipoRefaccion;
    }

    public void setIdTipoRefaccion(int idTipoRefaccion) {
        this.idTipoRefaccion = idTipoRefaccion;
    }

    public String getTipoRefaccion() {
        return tipoRefaccion;
    }

    public void setTipoRefaccion(String tipoRefaccion) {
        this.tipoRefaccion = tipoRefaccion;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @Override
    public String toString(){
        return this.nombreRefaccion+" "+this.tipoRefaccion;
    }
}
