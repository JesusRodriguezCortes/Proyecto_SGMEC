package javafxsgemec.pojo;

public class EquipoComputo {
    private String marca;
    private String modelo;
    private String caracteristicas;
    private String usuario;
    private String password;

    public EquipoComputo(){
    }
    
    public EquipoComputo(String marca, String modelo, String caracteristicas, String usuario, String password) {
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristicas = caracteristicas;
        this.usuario = usuario;
        this.password = password;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return this.marca;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getModelo() {
        return this.modelo;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getUsuario() {
        return this.usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String toString(){
        return this.marca+" "+this.modelo;
    }
}
