package javafxsgemec.pojo;

import javafxsgemec.pojo.Usuario;
import java.util.ArrayList;

public class UsuarioRespuesta {
    private int respuestaConexion;
    private Usuario usuarioRespuesta;
    public UsuarioRespuesta() {
    }

    public void setRespuestaConexion(int respuestaConexion) {
        this.respuestaConexion = respuestaConexion;
    }

    public void setUsuarioRespuesta(Usuario usuarioRespuesta) {
        this.usuarioRespuesta = usuarioRespuesta;
    }

    public int getRespuestaConexion() {
        return respuestaConexion;
    }

    public Usuario getUsarioRespuesta() {
        return this.usuarioRespuesta;
    }

    public UsuarioRespuesta(Usuario usuarioRespuesta) {
        this.usuarioRespuesta = usuarioRespuesta;
    }

    public UsuarioRespuesta(int respuestaConexion, Usuario usuarioRespuesta) {
        this.respuestaConexion = respuestaConexion;
        this.usuarioRespuesta = usuarioRespuesta;
    }

}
