package javafxsgemec.pojo;

public class Dispositivo {
    private int idDispositivo;
    private String marca;
    private String modelo;
    private String usuarioDisp;
    private String passwordDisp;
    private String error;
    private String estado;
    private byte[] foto;
    private int idCliente;
    private String nombreCliente;

    public Dispositivo(){
        
    }

    public Dispositivo(int idDispositivo, String marca, String modelo, String usuarioDisp, String passwordDisp, String error, String estado, byte[] foto, int idCliente, String nombreCliente) {
        this.idDispositivo = idDispositivo;
        this.marca = marca;
        this.modelo = modelo;
        this.usuarioDisp = usuarioDisp;
        this.passwordDisp = passwordDisp;
        this.error = error;
        this.estado = estado;
        this.foto = foto;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUsuarioDisp() {
        return usuarioDisp;
    }

    public void setUsuarioDisp(String usuarioDisp) {
        this.usuarioDisp = usuarioDisp;
    }

    public String getPasswordDisp() {
        return passwordDisp;
    }

    public void setPasswordDisp(String passwordDisp) {
        this.passwordDisp = passwordDisp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    @Override
    public String toString(){
        return this.marca+" "+this.modelo;
    }
}
