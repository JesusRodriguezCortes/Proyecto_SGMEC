package javafxsgemec.pojo;

public class Dispositivo {
    
    private int idDispositivo;
    private String marca;
    private String modelo;
    private String usuario;
    private String password;
    private String errorDispos;
    private byte[] foto;
    private String caracteristicas;
    private int idCliente;
    private String nombreCliente;
    private int idEstado;
    private String estado;

    public Dispositivo() {}

    public Dispositivo(int idDispositivo, String marca, String modelo, String usuario, String password, String errorDispos, byte[] foto, String caracteristicas, int idCliente, String nombreCliente, int idEstado, String estado) {
        this.idDispositivo = idDispositivo;
        this.marca = marca;
        this.modelo = modelo;
        this.usuario = usuario;
        this.password = password;
        this.errorDispos = errorDispos;
        this.foto = foto;
        this.caracteristicas = caracteristicas;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idEstado = idEstado;
        this.estado = estado;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorDispos() {
        return errorDispos;
    }

    public void setErrorDispos(String errorDispos) {
        this.errorDispos = errorDispos;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
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

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return this.marca+" "+this.modelo;
    }
}