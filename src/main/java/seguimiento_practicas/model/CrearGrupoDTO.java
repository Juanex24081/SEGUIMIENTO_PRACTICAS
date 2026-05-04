package seguimiento_practicas.model;

public class CrearGrupoDTO {

    private String nombreGrupo;
    private int idDocente;
    private int idAsesor;

    public CrearGrupoDTO(String nombreGrupo, int idDocente, int idAsesor) {
        this.nombreGrupo = nombreGrupo;
        this.idDocente = idDocente;
        this.idAsesor = idAsesor;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public int getIdAsesor() {
        return idAsesor;
    }
}