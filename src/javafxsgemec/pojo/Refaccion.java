package javafxsgemec.pojo;

public class Refaccion {
    
    private int idRefaccion;
    private String nombre;
    private String codigoRefaccion;
    private int idTipoRefaccion;
    private String tipoRefaccion;
    private int idDiagnostico;
    private String fechaInicio;
    private String fechaFin;
    
    
    public Refaccion() {}

    public Refaccion(int idRefaccion, String nombre, String codigoRefaccion, int idTipoRefaccion, String tipoRefaccion, int idDiagnostico, String fechaInicio, String fechaFin) {
        this.idRefaccion = idRefaccion;
        this.nombre = nombre;
        this.codigoRefaccion = codigoRefaccion;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoRefaccion() {
        return codigoRefaccion;
    }

    public void setCodigoRefaccion(String codigoRefaccion) {
        this.codigoRefaccion = codigoRefaccion;
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
        return this.nombre+" "+this.tipoRefaccion;
    }
}
