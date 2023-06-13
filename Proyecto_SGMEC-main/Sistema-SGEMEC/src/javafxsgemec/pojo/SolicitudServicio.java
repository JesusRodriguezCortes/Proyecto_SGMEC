package javafxsgemec.pojo;

public class SolicitudServicio {
    
    private int idSolicitud;
    private String numeroGuia;
    private int idDiagnostico;
    private int idPaqueteria;
    private String nombrePaqueteria;

    public SolicitudServicio() {
    }

    public SolicitudServicio(int idSolicitud, String numeroGuia, int idDiagnostico, int idPaqueteria, String nombrePaqueteria) {
        this.idSolicitud = idSolicitud;
        this.numeroGuia = numeroGuia;
        this.idDiagnostico = idDiagnostico;
        this.idPaqueteria = idPaqueteria;
        this.nombrePaqueteria = nombrePaqueteria;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(int idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public String getNombrePaqueteria() {
        return nombrePaqueteria;
    }

    public void setNombrePaqueteria(String nombrePaqueteria) {
        this.nombrePaqueteria = nombrePaqueteria;
    }
}
