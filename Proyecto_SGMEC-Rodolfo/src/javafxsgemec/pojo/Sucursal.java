package javafxsgemec.pojo;

public class Sucursal {
    
    private int idSucursal;
    private String direccionSuc;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, String direccionSuc) {
        this.idSucursal = idSucursal;
        this.direccionSuc = direccionSuc;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSuc;
    }

    public void setDireccionSucursal(String direccionSuc) {
        this.direccionSuc = direccionSuc;
    }
    
    @Override
    public String toString(){
        return this.direccionSuc;
    }
}
