package javafxsgemec.pojo;

public class Rol {
    private int idRoles;
    private String nivelAcceso;

    public Rol(){
        
    }
    
    public Rol(int idRoles, String nivelAcceso) {
        this.idRoles = idRoles;
        this.nivelAcceso = nivelAcceso;
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
    public String toString(){
        return this.nivelAcceso;
    }
}
