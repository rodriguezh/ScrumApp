package app.com.scrumapp.models;

/**
 * Created by Fernando on 15/04/2018.
 */

public class Estado {
    private int id;
    private String tipo;
    private String estado;

    public Estado(int id, String tipo, String estado) {
        super();
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
    }
    public Estado() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
        return "Estado {id=" + id + ", tipo=\'" + tipo +'\'' + ", estado=\'" + estado + "\'}";
    }


}
