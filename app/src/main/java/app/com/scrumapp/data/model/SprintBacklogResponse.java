package app.com.scrumapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SprintBacklogResponse {


    /*@SerializedName("next")
    @Expose
    private Object next;

    @SerializedName("items")
    @Expose
    private ArrayList<HistoriadeUsuarioInicial> historiadeUsuarioInicials;

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public ArrayList<HistoriadeUsuarioInicial> getHistoriadeUsuarioInicials() {
        return historiadeUsuarioInicials;
    }

    public void setHistoriadeUsuarioInicials(ArrayList<HistoriadeUsuarioInicial> historiadeUsuarioInicials) {
        this.historiadeUsuarioInicials = historiadeUsuarioInicials;
    }

    @Override
    public String toString() {
        return "SprintBacklogResponse{" +
                "next='" + next + '\'' +
                ", historiadeUsuarioInicials=" + historiadeUsuarioInicials +
                '}';
    }*/
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private List<HUInicialResponse> hiSprintList;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<HUInicialResponse> getMessage() {
        return hiSprintList;
    }

    public void setMessage(List<HUInicialResponse> hiSprintList) {
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
