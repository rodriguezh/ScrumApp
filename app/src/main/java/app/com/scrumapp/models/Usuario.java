package app.com.scrumapp.models;

public class Usuario {
    private String identificacion;
    private String nombre;
    private String rol;

    public Usuario(String identificacion, String nombre, String rol) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Usuario() {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identifiacion) {
        this.identificacion = identifiacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
