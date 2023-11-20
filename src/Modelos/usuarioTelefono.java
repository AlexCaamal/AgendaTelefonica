package Modelos;

public class usuarioTelefono {

    private int Id;
    private String Telefono;
    private int IdUsuario;
    private String NombreUsuario;

    public usuarioTelefono(int Id, String Telefono, int IdUsuario, String NombreUsuario) {
        this.Id = Id;
        this.Telefono = Telefono;
        this.IdUsuario = IdUsuario;
        this.NombreUsuario = NombreUsuario;
    }

    public usuarioTelefono() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
}
