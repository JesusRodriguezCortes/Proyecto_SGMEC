package javafxsgemec.pojo;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private long telefono;
    private String correoElectronico;
    private int idUsuario;
    private String nombreUsuario;
    private String password;
    private int idRoles;
    private String nivelAcceso;
    
    public Cliente(){
        
    }
    
    public Cliente(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, 
                   String direccion, long telefono, String correoElectronio, int idUsuario, 
                   String nombreUsuario, String password, int idRoles, String nivelAcceso) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronio;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.idRoles = idRoles;
        this.nivelAcceso = nivelAcceso;
    }

    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public int getIdCliente(){
        return this.idCliente;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }
    
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getTelefono() {
        return this.telefono;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public int getIdUsuario(){
        return this.idUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
        
    public String nombreUsuario(){
        return this.nombreUsuario;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setIdRoles(int idRoles){
        this.idRoles = idRoles;
    }
        
    public int getIdRoles(){
        return this.idRoles;
    }
    
    public void setNivelAcceso(String nivelAcceso){
        this.nivelAcceso = nivelAcceso;
    }
        
    public String getNivelAcceso(){
        return this.nivelAcceso;
    }
    
    @Override
    public String toString(){
        return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
    }
}
