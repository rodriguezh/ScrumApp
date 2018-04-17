package app.com.scrumapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HUInicialResponse {
    @SerializedName("id_sprint")
    @Expose
    private int id_sprint;
    @SerializedName("nombre_hu")
    @Expose
    private String nombre_hu;
    @SerializedName("prioridad_hu")
    @Expose
    private int prioridad_hu;
    @SerializedName("peso_hu")
    @Expose
    private int peso_hu;
    @SerializedName("id_hu")
    @Expose
    private int id_hu;
    @SerializedName("id_proyecto")
    @Expose
    private int id_proyecto;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("criterio_aceptacion")
    @Expose
    private String criterio_aceptacion;

    public HUInicialResponse(int id_sprint, String nombre_hu, int prioridad_hu, int peso_hu) {
        this.id_sprint = id_sprint;
        this.nombre_hu = nombre_hu;
        this.prioridad_hu = prioridad_hu;
        this.peso_hu = peso_hu;
    }

    public HUInicialResponse() {

    }

    public int getId_hu() {
        return id_hu;
    }

    public void setId_hu(int id_hu) {
        this.id_hu = id_hu;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCriterio_aceptacion() {
        return criterio_aceptacion;
    }

    public void setCriterio_aceptacion(String criterio_aceptacion) {
        this.criterio_aceptacion = criterio_aceptacion;
    }

    public int getId_sprint() {
        return id_sprint;
    }

    public void setId_sprint(int id_sprint) {
        this.id_sprint = id_sprint;
    }

    public String getNombre_hu() {
        return nombre_hu;
    }

    public void setNombre_hu(String nombre_hu) {
        this.nombre_hu = nombre_hu;
    }

    public int getPrioridad_hu() {
        return prioridad_hu;
    }

    public void setPrioridad_hu(int prioridad_hu) {
        this.prioridad_hu = prioridad_hu;
    }

    public int getPeso_hu() {
        return peso_hu;
    }

    public void setPeso_hu(int peso_hu) {
        this.peso_hu = peso_hu;
    }

    @Override
    public String toString() {
        return "HistoriadeUsuarioInicial{" +
                "id_sprint=" + id_sprint +
                ", nombre_hu='" + nombre_hu + '\'' +
                ", prioridad_hu=" + prioridad_hu +
                ", peso_hu=" + peso_hu +
                '}';
    }
}
