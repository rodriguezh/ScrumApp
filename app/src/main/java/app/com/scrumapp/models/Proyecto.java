package app.com.scrumapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Proyecto  {

    private int id;
    private int id_estado;
    private String nombre_proyecto;
    private int estim_spring_esfuerzo;
    private String usuario_crea;
    private String usuario_modifica;
    private String created_at;
    private String updated_at;
    private Estado estado;

    public Proyecto(int id, int id_estado, String nombre_proyecto, int estim_spring_esfuerzo, String usuario_crea,
                    String usuario_modifica, String created_at, String updated_at) {
        super();
        this.id = id;
        this.id_estado = id_estado;
        this.nombre_proyecto = nombre_proyecto;
        this.estim_spring_esfuerzo = estim_spring_esfuerzo;
        this.usuario_crea = usuario_crea;
        this.usuario_modifica = usuario_modifica;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Proyecto() {
    }



    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public int getEstim_spring_esfuerzo() {
        return estim_spring_esfuerzo;
    }

    public void setEstim_spring_esfuerzo(int estim_spring_esfuerzo) {
        this.estim_spring_esfuerzo = estim_spring_esfuerzo;
    }

    public String getUsuario_crea() {
        return usuario_crea;
    }

    public void setUsuario_crea(String usuario_crea) {
        this.usuario_crea = usuario_crea;
    }

    public String getUsuario_modifica() {
        return usuario_modifica;
    }

    public void setUsuario_modifica(String usuario_modifica) {
        this.usuario_modifica = usuario_modifica;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Proyecto{id=" + id + ", id_estado=" + id_estado + ", nombre_proyecto=\'" + nombre_proyecto
                + '\'' + " , estim_spring_esfuerzo=" + estim_spring_esfuerzo + ", usuario_crea=\'" + usuario_crea  + '\'' +
                ", usuario_modifica=\'" + usuario_modifica +  '\'' + ", created_at=\'" + created_at + '\'' +", updated_at=\'" + updated_at
                + '\'' + ", estado=" + estado + "}";
    }

}
