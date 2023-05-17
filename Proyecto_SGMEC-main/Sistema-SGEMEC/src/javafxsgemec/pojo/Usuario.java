package javafxsgemec.pojo;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String contrasenia;
    private String nivelDeAcceso;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Override
    public String toString() {
        return nombre + " "+ apellidoPaterno + " " + apellidoMaterno;
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

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNivelDeAcceso() {
        return nivelDeAcceso;
    }
}
