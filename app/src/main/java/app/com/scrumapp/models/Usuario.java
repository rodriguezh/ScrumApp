package app.com.scrumapp.models;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    @SerializedName("Id")
    @Expose
    private String identificacion;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    private String rol;
    @SerializedName("correo")
    @Expose
    private String email;
    @SerializedName("clave")
    @Expose
    private String password;
    @SerializedName("id_proyecto")
    @Expose
    private int idProyecto;


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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("identificacion", identificacion);
        result.put("nombre", nombre);
        result.put("rol", rol);
        return result;
    }


    @Override
    public String toString() {
        return nombre;
    }

    public void setEmail(String email){ this.email = email; }

    public String getEmail(){ return this.email; }

    public void setPassword(String password){ this.password = password; }

    public String getPassword(){ return this.password; }

    public void setIdProyecto(int idProyecto){ this.idProyecto = idProyecto; }

    public int getIdProyecto(){ return this.idProyecto; }
}
