package seguimiento_practicas.model;

public class SesionDTO {

    public int id;
    public String estado;
    public String convenio;
    public String docente;
    public String fecha;

    public SesionDTO(int id, String estado, String convenio, String docente, String fecha) {
        this.id = id;
        this.estado = estado;
        this.convenio = convenio;
        this.docente = docente;
        this.fecha = fecha;
    }
}