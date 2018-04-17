package app.com.scrumapp.models;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fernando on 15/04/2018.
 */

public class Sprint {
    @SerializedName("id_sprint")
    @Expose
    private int id_sprint;
    @SerializedName("id_pb")
    @Expose
    private int id_pb;
    @SerializedName("nombre_pb")
    @Expose
    private String nombre_pb;
    @SerializedName("compromiso_st")
    @Expose
    private int compromiso_st;
    @SerializedName("peso_total_pb")
    @Expose
    private int peso_total_pb;
    @SerializedName("estado_st")
    @Expose
    private int estado_st;
    @SerializedName("total_peso")
    @Expose
    private int total_peso;
    public Sprint() {
        super();
    }

    public int getId_pb() {
        return id_pb;
    }

    public void setId_pb(int id_pb) {
        this.id_pb = id_pb;
    }

    public int getId_sprint() {
        return id_sprint;
    }

    public void setId_sprint(int id_sprint) {
        this.id_sprint = id_sprint;
    }

    public String getNombre_pb() {
        return nombre_pb;
    }

    public void setNombre_pb(String nombre_pb) {
        this.nombre_pb = nombre_pb;
    }

    public int getCompromiso_st() {
        return compromiso_st;
    }

    public void setCompromiso_st(int compromiso_st) {
        this.compromiso_st = compromiso_st;
    }

    public int getPeso_total_pb() {
        return peso_total_pb;
    }

    public void setPeso_total_pb(int peso_total_pb) {
        this.peso_total_pb = peso_total_pb;
    }

    public int getEstado_st() {
        return estado_st;
    }

    public void setEstado_st(int estado_st) {
        this.estado_st = estado_st;
    }

    public int getTotal_peso() {
        return total_peso;
    }

    public void setTotal_peso(int total_peso) {
        this.total_peso = total_peso;
    }


    @Override
    public String toString() {
        return "Sprint{id_sprint=" + id_sprint + ", id_pb=" + id_pb + ", nombre_pb=\'" + nombre_pb +  '\'' + ", compromiso_st="
                + compromiso_st + ", peso_total_pb=" + peso_total_pb + ", estado_st=" + estado_st + ", total_peso="
                + total_peso + "}";
    }


}
