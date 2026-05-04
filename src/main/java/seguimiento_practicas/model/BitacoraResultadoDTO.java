package seguimiento_practicas.model;

public class BitacoraResultadoDTO {

    public int calificacion;
    public String comentario;
    public String estado;

    public BitacoraResultadoDTO(int calificacion, String comentario, String estado) {
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.estado = estado;
    }
}