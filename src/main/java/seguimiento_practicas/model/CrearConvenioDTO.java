package seguimiento_practicas.model;

import java.time.LocalDate;

public class CrearConvenioDTO {

    private String empresa;
    private String estado;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;

    private int idAsesor;

    public CrearConvenioDTO(
            String empresa,
            String estado,
            LocalDate fecha_inicio,
            LocalDate fecha_fin,
            int idAsesor
    ) {

        this.empresa = empresa;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.idAsesor = idAsesor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaInicio() {
        return fecha_inicio;
    }

    public LocalDate getFechaFin() {
        return fecha_fin;
    }

    public int getIdAsesor() {
        return idAsesor;
    }
}