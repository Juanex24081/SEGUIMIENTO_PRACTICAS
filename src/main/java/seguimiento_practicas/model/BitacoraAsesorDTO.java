package seguimiento_practicas.model;

public class BitacoraAsesorDTO {

    public int idBitacora;
    public String estudiante;
    public int idSesion;

    public String archivo;
    public String estado;

    public int calificacion;

    public String fecha;

    public BitacoraAsesorDTO(
            int idBitacora,
            String estudiante,
            int idSesion,
            String archivo,
            String estado,
            int calificacion,
            String fecha
    ) {

        this.idBitacora = idBitacora;
        this.estudiante = estudiante;
        this.idSesion = idSesion;
        this.archivo = archivo;
        this.estado = estado;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }
}
