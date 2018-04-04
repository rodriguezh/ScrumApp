package app.com.scrumapp.models;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fernando on 29/03/2018.
 */

public class HistoriadeUsuario {

    private int id;
     private int id_hu;
     private int id_proyecto;
     private int id_sprint;
     private String descripcion;
     private String criterio_aceptacion;
     private int esfuerzo;
     private String prioridad;

     private double tiempoEstimado;
     private String estado;
     private String tiempoTranscurrido;
     private String fechaInicio;
     private String fechaFin;
     private String informacionadicional;
     private String motivocancelacion;
     private Usuario desarrollador;
     private boolean asignada;

    public HistoriadeUsuario(int id_hu, int id_proyecto, int id_sprint, String descripcion, String criterio_aceptacion, int esfuerzo, String prioridad, double tiempoEstimado, String estado, String tiempoTranscurrido, String fechaInicio, String fechaFin, String informacionadicional, String motivocancelacion, Usuario desarrollador) {
        this.id_hu = id_hu;
        this.id_proyecto = id_proyecto;
        this.id_sprint = id_sprint;
        this.descripcion = descripcion;
        this.criterio_aceptacion = criterio_aceptacion;
        this.esfuerzo = esfuerzo;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
        this.estado = estado;
        this.tiempoTranscurrido = tiempoTranscurrido;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.informacionadicional = informacionadicional;
        this.motivocancelacion = motivocancelacion;
        this.desarrollador = desarrollador;
    }

    public HistoriadeUsuario() {
    }

    public boolean isAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId_sprint() {
        return id_sprint;
    }

    public void setId_sprint(int id_sprint) {
        this.id_sprint = id_sprint;
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

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(String tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getInformacionadicional() {
        return informacionadicional;
    }

    public void setInformacionadicional(String informacionadicional) {
        this.informacionadicional = informacionadicional;
    }

    public String getMotivocancelacion() {
        return motivocancelacion;
    }

    public void setMotivocancelacion(String motivocancelacion) {
        this.motivocancelacion = motivocancelacion;
    }

    public Usuario getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Usuario desarrollador) {
        this.desarrollador = desarrollador;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id_hu", id_hu);
        result.put("id_proyecto", id_proyecto);
        result.put("id_sprint", id_sprint);
        result.put("descripcion", descripcion);
        result.put("criterio_aceptacion", criterio_aceptacion);
        result.put("esfuerzo", esfuerzo);
        result.put("prioridad", prioridad);
        result.put("estado", estado);
        result.put("tiempoEstimado", tiempoEstimado);
        result.put("asignada", asignada);
        return result;
    }


    @Override
    public String toString() {
        return "HistoriadeUsuario{" +
                " id_hu=" + id_hu +
                ", id_proyecto=" + id_proyecto +
                ", id_sprint=" + id_sprint +
                ", descripcion='" + descripcion + '\'' +
                ", criterio_aceptacion='" + criterio_aceptacion + '\'' +
                ", esfuerzo=" + esfuerzo +
                ", prioridad='" + prioridad + '\'' +
                ", tiempoEstimado=" + tiempoEstimado +
                ", estado='" + estado + '\'' +
                ", tiempoTranscurrido='" + tiempoTranscurrido + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", informacionadicional='" + informacionadicional + '\'' +
                ", motivocancelacion='" + motivocancelacion + '\'' +
                ", desarrollador=" + desarrollador +
                ", asignada=" + asignada +
                '}';
    }
}
