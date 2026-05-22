package seguimiento_practicas.model;

public class ResumenDTO {

    private String nombreEstudiante;
    private int horas;
    private String grupo;
    private String docente;
    private int sesiones;
    private double promedio;

    public ResumenDTO(
            String nombreEstudiante,
            int horas,
            String grupo,
            String docente,
            int sesiones,
            double promedio
    ) {
        this.nombreEstudiante = nombreEstudiante;
        this.horas = horas;
        this.grupo = grupo;
        this.docente = docente;
        this.sesiones = sesiones;
        this.promedio = promedio;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public int getHoras() {
        return horas;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getDocente() {
        return docente;
    }

    public int getSesiones() {
        return sesiones;
    }

    public double getPromedio() {
        return promedio;
    }
}