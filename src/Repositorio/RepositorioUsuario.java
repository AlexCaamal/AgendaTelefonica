package Repositorio;

import Conexion.ConexionBD;
import Modelos.Respuesta;
import Modelos.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public Respuesta<List<usuario>> Obtener(String criterio) {

        List<usuario> listaUsuarios = new ArrayList<usuario>();
        String sql = "";
        try {
            if(!criterio.isEmpty()){
                sql = "SELECT*FROM usuario WHERE Activo = 1 AND Nombre LIKE '%"+criterio+"%' OR Apellido LIKE '%"+criterio+"%'";
            }else{
                sql = "SELECT*FROM usuario WHERE Activo = 1;";
            }
            
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            usuario user;

            while (rs.next()) {
                user = new usuario();
                user.setId(rs.getInt("Id_Cliente"));
                user.setNombre(rs.getString("Nombre"));
                user.setApellido(rs.getString("Apellido"));
                listaUsuarios.add(user);
            }

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta<List<usuario>>("Error Interno: " + e.getMessage());
        }

        return new Respuesta<List<usuario>>(listaUsuarios);
    }

    public Respuesta<usuario> ObtenerPorId(int idUser) {

        usuario user = null;

        try {
            String sql = "SELECT*FROM usuario WHERE Activo = 1 AND Id_Cliente = ?;";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new usuario();
                user.setId(rs.getInt("Id_Cliente"));
                user.setNombre(rs.getString("Nombre"));
                user.setApellido(rs.getString("Apellido"));
            }
            conexion.close();
            rs.close();
            ps.close();

            if (user == null) {
                return new Respuesta<usuario>("El usuario fue previamente eliminado.");
            }

        } catch (Exception e) {
            return new Respuesta<usuario>("Error Interno: " + e.getMessage());
        }

        return new Respuesta<usuario>(user);
    }

    public Respuesta Crear(usuario user) {
        try {
            String sql = "INSERT INTO usuario (Nombre, Apellido) VALUES (?,?)";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, user.Nombre);
            ps.setString(2, user.Apellido);
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta<usuario>("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta Modificar(usuario user) {
        try {
            String sql = "UPDATE usuario SET Nombre = ?, Apellido = ? WHERE Id_Cliente = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, user.Nombre);
            ps.setString(2, user.Apellido);
            ps.setInt(3, user.getId());
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta Elimiar(usuario user) {
        try {
            String sql = "UPDATE usuario SET Activo = 0 WHERE Id_Cliente = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }
}
