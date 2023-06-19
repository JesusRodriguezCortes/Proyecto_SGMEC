package javafxsgemec.pojo;

public class Usuario {
    
    private int idUsuario;
    private String username;
    private String password;
    private String idRol;
    private String nivelDeAcceso;
    
    public Usuario() {}

    public Usuario(int idUsuario, String username, String password, String idRol, String nivelDeAcceso) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
        this.nivelDeAcceso = nivelDeAcceso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    public void setNivelDeAcceso(String nivelDeAcceso){
        this.nivelDeAcceso = nivelDeAcceso;
    }
    
    public String getNivelDeAcceso() {
        return nivelDeAcceso;
    }
    
    @Override
    public String toString(){
        return this.nivelDeAcceso;
    }
}
