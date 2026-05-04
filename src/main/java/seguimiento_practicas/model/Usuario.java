package seguimiento_practicas.model;

public class Usuario {

    private long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;
    private long cedula;

    public Usuario() {}

    public Usuario(long id, String nombre, String correo, String contrasena, String rol, long cedula) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.cedula = cedula;
    }

    // Getters y setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public long getCedula() { return cedula; }
    public void setCedula(long cedula) { this.cedula = cedula; }
}
