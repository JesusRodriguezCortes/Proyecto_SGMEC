package javafxsgemec.pojo;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String password;
    private int idRoles;
    private String nivelAcceso;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String password, int idRoles, String nivelAcceso) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.idRoles = idRoles;
        this.nivelAcceso = nivelAcceso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String toString() {
        return this.nombreUsuario+" "+this.nivelAcceso;
    }
}