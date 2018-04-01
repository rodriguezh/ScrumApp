package app.com.scrumapp.models;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fernando on 29/03/2018.
 */

public class HistoriadeUsuario {

    private String id;
    private String idHU;
    private String descripcion;
    private String criteriosAceptacion;
    private String nombreHu;
    private int peso;
    private int prioridad;

    public HistoriadeUsuario(String idHU, String descripcion, String criteriosAceptacion) {
        this.idHU = idHU;
        this.descripcion = descripcion;
        this.criteriosAceptacion = criteriosAceptacion;
    }

    public HistoriadeUsuario(String id, String idHU, String descripcion, String criteriosAceptacion, String nombreHu, int peso, int prioridad) {
        this.id = id;
        this.idHU = idHU;
        this.descripcion = descripcion;
        this.criteriosAceptacion = criteriosAceptacion;
        this.nombreHu = nombreHu;
        this.peso = peso;
        this.prioridad = prioridad;
    }

    public HistoriadeUsuario() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreHu() {
        return nombreHu;
    }

    public void setNombreHu(String nombreHu) {
        this.nombreHu = nombreHu;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String get_id() {
        return id;
    }

    public void set_id(String _id) {
        this.id = _id;
    }

    public String getIdHU() {
        return idHU;
    }

    public void setIdHU(String idHU) {
        this.idHU = idHU;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCriteriosAceptacion() {
        return criteriosAceptacion;
    }

    public void setCriteriosAceptacion(String criteriosAceptacion) {
        this.criteriosAceptacion = criteriosAceptacion;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("idHU", idHU);
        result.put("descripcion", descripcion);
        result.put("criteriosAceptacion", criteriosAceptacion);
        result.put("nombreHu", nombreHu);
        result.put("peso", peso);
        result.put("prioridad", prioridad);
        return result;
    }

}
