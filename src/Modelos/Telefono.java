
package Modelos;

public class Telefono {
    private int Id;
    private String Telefono;
    private int IdUsuario;

    public Telefono(int Id, String Telefono, int IdUsuario) {
        this.Id = Id;
        this.Telefono = Telefono;
        this.IdUsuario = IdUsuario;
    }

    public Telefono(int Id) {
        this.Id = Id;
    }

    public Telefono() {
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
    
}
