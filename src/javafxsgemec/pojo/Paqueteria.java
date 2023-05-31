package javafxsgemec.pojo;

public class Paqueteria {
    
    private int idPaqueteria;
    private String nombrePaque;

    public Paqueteria() {
    }

    public Paqueteria(int idPaqueteria, String nombrePaque) {
        this.idPaqueteria = idPaqueteria;
        this.nombrePaque = nombrePaque;
    }

    public int getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(int idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public String getNombrePaque() {
        return nombrePaque;
    }

    public void setNombrePaque(String nombrePaque) {
        this.nombrePaque = nombrePaque;
    }
    
    @Override
    public String toString(){
        return this.nombrePaque;
    }
}
