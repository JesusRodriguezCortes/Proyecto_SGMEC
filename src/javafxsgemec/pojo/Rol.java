package javafxsgemec.pojo;

public class Rol {
    
    private int idRol;
    private String nivelAcceso;

    public Rol(){}
    
    public Rol(int idRol, String nivelAcceso) {
        this.idRol = idRol;
        this.nivelAcceso = nivelAcceso;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRoles) {
        this.idRol = idRoles;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
    
    @Override
    public String toString(){
        return this.nivelAcceso;
    }
}
