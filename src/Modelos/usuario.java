
package Modelos;

public class usuario {
    public int Id;
    public String Nombre;
    public String Apellido;

    public usuario(int Id, String Nombre, String Apellido) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
    }

    public usuario(int Id) {
        this.Id = Id;
    }

    public usuario() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
}
