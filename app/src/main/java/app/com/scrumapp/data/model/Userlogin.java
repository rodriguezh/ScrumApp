package app.com.scrumapp.data.model;

public class Userlogin {

    private String rol;
    private int  Id_rol;
    private String  clave;
    private int Id;
    private String  Usuario;

    public Userlogin(String rol, int id_rol, String clave, int id, String usuario) {
        this.rol = rol;
        Id_rol = id_rol;
        this.clave = clave;
        Id = id;
        Usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getId_rol() {
        return Id_rol;
    }

    public void setId_rol(int id_rol) {
        Id_rol = id_rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public double getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    @Override
    public String toString() {
        return "Userlogin{" +
                "rol='" + rol + '\'' +
                ", Id_rol=" + Id_rol +
                ", clave='" + clave + '\'' +
                ", Id=" + Id +
                ", Usuario='" + Usuario + '\'' +
                '}';
    }
}
