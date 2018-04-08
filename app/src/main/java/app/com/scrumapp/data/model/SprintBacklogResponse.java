package app.com.scrumapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SprintBacklogResponse {


    @SerializedName("next")
    @Expose
    private Object next;

    @SerializedName("items")
    @Expose
    private List<HistoriadeUsuarioInicial> historiadeUsuarioInicials;

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public List<HistoriadeUsuarioInicial> getHistoriadeUsuarioInicials() {
        return historiadeUsuarioInicials;
    }

    public void setHistoriadeUsuarioInicials(List<HistoriadeUsuarioInicial> historiadeUsuarioInicials) {
        this.historiadeUsuarioInicials = historiadeUsuarioInicials;
    }

    @Override
    public String toString() {
        return "SprintBacklogResponse{" +
                "next='" + next + '\'' +
                ", historiadeUsuarioInicials=" + historiadeUsuarioInicials +
                '}';
    }
}
