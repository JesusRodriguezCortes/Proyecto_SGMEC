package javafxsgemec.modelo.pojo;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String contrasenia;
    private String nivelDeAcceso;

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNivelDeAcceso() {
        return nivelDeAcceso;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setNivelDeAcceso(String nivelDeAcceso) {
        this.nivelDeAcceso = nivelDeAcceso;
    }

    public Usuario(int idUsuario, String usuario) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Usuario(int idUsuario, String usuario, String contrasenia, String nivelDeAcceso) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nivelDeAcceso = nivelDeAcceso;
    }

    public Usuario() {
    }
}
