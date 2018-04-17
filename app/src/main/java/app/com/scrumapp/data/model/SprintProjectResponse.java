package app.com.scrumapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.com.scrumapp.models.Proyecto;

/**
 * Created by Fernando on 15/04/2018.
 */

public class SprintProjectResponse {

   /* @SerializedName("items")
    @Expose
    private List<Proyecto> proyectoList;


    public SprintProjectResponse(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public SprintProjectResponse() {
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public String toString() {
        return "SprintProjectResponse{" +
                "proyectoList=" + proyectoList +
                '}';
    }*/
   @SerializedName("status")
   @Expose
   private int status;
    @SerializedName("message")
    @Expose
    private List<Proyecto> hiSprintList;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Proyecto> getMessage() {
        return hiSprintList;
    }

    public void setMessage(List<Proyecto> hiSprintList) {
        this.hiSprintList = hiSprintList;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", message=" + hiSprintList +
                '}';
    }
}
