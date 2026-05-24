package seguimiento_practicas.model;

public class ComentarioDTO {

    public int id;

    public String usuario;
    public String rol;

    public String comentario;
    public String fecha;

    public ComentarioDTO(
            int id,
            String usuario,
            String rol,
            String comentario,
            String fecha
    ) {

        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
        this.comentario = comentario;
        this.fecha = fecha;
    }
}