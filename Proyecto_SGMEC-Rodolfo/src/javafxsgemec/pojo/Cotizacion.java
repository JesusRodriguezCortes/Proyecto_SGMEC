package javafxsgemec.pojo;


public class Cotizacion {
    private double reparacionSoftware;
    private double reparacionHardWare;
    private double actualizaciones;
    private double servicioLimpieza;
    private double costoRepusto;
    private double manoDeObra;
    private double garantia;
    private int idDispositivo;
    private Double idDiagnostico;

    public Cotizacion(double reparacionSoftware, double reparacionHardWare, double actualizaciones, double servicioLimpieza, double costoRepusto, double manoDeObra, int idDispositivo) {
        this.reparacionSoftware = reparacionSoftware;
        this.reparacionHardWare = reparacionHardWare;
        this.actualizaciones = actualizaciones;
        this.servicioLimpieza = servicioLimpieza;
        this.costoRepusto = costoRepusto;
        this.manoDeObra = manoDeObra;
        this.idDispositivo = idDispositivo;
        this.garantia=garantia;
    }
    

    public Cotizacion(double reparacionSoftware, double reparacionHardWare, double actualizaciones, double servicioLimpieza, double costoRepusto, double manoDeObra) {
        this.reparacionSoftware = reparacionSoftware;
        this.reparacionHardWare = reparacionHardWare;
        this.actualizaciones = actualizaciones;
        this.servicioLimpieza = servicioLimpieza;
        this.costoRepusto = costoRepusto;
        this.manoDeObra = manoDeObra;
    }

    public Cotizacion() {
    }

    public void setReparacionSoftware(double reparacionSoftware) {
        this.reparacionSoftware = reparacionSoftware;
    }

    public void setReparacionHardWare(double reparacionHardWare) {
        this.reparacionHardWare = reparacionHardWare;
    }

    public void setActualizaciones(double actualizaciones) {
        this.actualizaciones = actualizaciones;
    }

    public void setServicioLimpieza(double servicioLimpieza) {
        this.servicioLimpieza = servicioLimpieza;
    }

    public void setCostoRepusto(double costoRepusto) {
        this.costoRepusto = costoRepusto;
    }

    public void setManoDeObra(double manoDeObra) {
        this.manoDeObra = manoDeObra;
    }

    public void setGarantia(double garantia) {
        this.garantia = garantia;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public double getReparacionSoftware() {
        return reparacionSoftware;
    }

    public double getReparacionHardWare() {
        return reparacionHardWare;
    }

    public double getActualizaciones() {
        return actualizaciones;
    }

    public double getServicioLimpieza() {
        return servicioLimpieza;
    }

    public double getCostoRepusto() {
        return costoRepusto;
    }

    public double getManoDeObra() {
        return manoDeObra;
    }
    public int getIdDispositivo() {
        return idDispositivo;
    }

    public double getGarantia() {
        return garantia;
    }

    public double getIdDiagnostico() {
        return this.idDiagnostico;
    }
    public void setIdDiagnostico(Double idDiagnostico){
        this.idDiagnostico=idDiagnostico;
    }
}

