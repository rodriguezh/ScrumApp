package app.com.scrumapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.com.scrumapp.models.Sprint;

public class ApiResponse {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private List<Sprint> hiSprintList;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Sprint> getMessage() {
        return hiSprintList;
    }

    public void setMessage(List<Sprint> hiSprintList) {
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
