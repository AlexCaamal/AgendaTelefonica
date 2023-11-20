package Repositorio;

import Conexion.ConexionBD;
import Modelos.Respuesta;
import Modelos.Telefono;
import Modelos.usuarioTelefono;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTelefono {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public Respuesta<List<Telefono>> Obtener() {

        List<Telefono> listaTelefono = new ArrayList<Telefono>();

        try {
            String sql = "SELECT*FROM numero_telefonico WHERE Activo = 1;";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            Telefono tel;

            while (rs.next()) {
                tel = new Telefono();
                tel.setId(rs.getInt("Id"));
                tel.setTelefono(rs.getString("Telefono"));
                listaTelefono.add(tel);
            }

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta<List<Telefono>>("Error Interno: " + e.getMessage());
        }

        return new Respuesta<List<Telefono>>(listaTelefono);
    }

    public Respuesta<List<usuarioTelefono>> ObtenerVinculacion(String criterio) {

        List<usuarioTelefono> listaTelefono = new ArrayList<usuarioTelefono>();
        String sql = "";
        try {
            if (!criterio.isEmpty()) {
                sql = "SELECT num.Id, num.Telefono, CONCAT(us.Nombre,' ', us.Apellido) as NombreC FROM numero_telefonico num "
                        + "LEFT JOIN usuario us on num.Id_Usuario = us.Id_Cliente WHERE num.Activo = 1 AND num.Telefono LIKE '%" + criterio + "%'";
            } else {
                sql = "SELECT num.Id, num.Telefono, CONCAT(us.Nombre,' ', us.Apellido) as NombreC FROM numero_telefonico num "
                        + "LEFT JOIN usuario us on num.Id_Usuario = us.Id_Cliente WHERE num.Activo = 1;";
            }
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            usuarioTelefono tel;

            while (rs.next()) {
                tel = new usuarioTelefono();
                tel.setId(rs.getInt("Id"));
                tel.setTelefono(rs.getString("Telefono"));
                tel.setNombreUsuario(rs.getString("NombreC"));
                listaTelefono.add(tel);
            }

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta<List<usuarioTelefono>>("Error Interno: " + e.getMessage());
        }

        return new Respuesta<List<usuarioTelefono>>(listaTelefono);
    }

    public Respuesta<Boolean> ObtenerRepetido(String numero, int Id) {

        try {
            String sql = "SELECT COUNT(Id) as Repetidos FROM numero_telefonico WHERE Activo = 1 AND Telefono = ? AND Id != ?;";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, numero);
            ps.setInt(2, Id);
            rs = ps.executeQuery();

            int repetidos = 0;

            while (rs.next()) {
                repetidos = rs.getInt("Repetidos");
            }

            conexion.close();
            rs.close();
            ps.close();

            if (repetidos != 0) {
                return new Respuesta<Boolean>(true);
            }

        } catch (Exception e) {
            return new Respuesta<Boolean>("Error Interno: " + e.getMessage());
        }

        return new Respuesta<Boolean>(false);
    }

    public Respuesta Crear(Telefono tel) {
        try {
            String sql = "INSERT INTO numero_telefonico (Telefono, Id_Usuario) VALUES (?,?)";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, tel.getTelefono());
            ps.setInt(2, tel.getIdUsuario());
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta<Telefono>("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta<Telefono> ObtenerPorId(int id) {

        Telefono tel = null;

        try {
            String sql = "SELECT*FROM numero_telefonico WHERE Activo = 1 AND Id = ?;";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                tel = new Telefono();
                tel.setId(rs.getInt("Id"));
                tel.setTelefono(rs.getString("Telefono"));
                tel.setIdUsuario(rs.getInt("Id_Usuario"));
            }
            conexion.close();
            rs.close();
            ps.close();

            if (tel == null) {
                return new Respuesta<Telefono>("El Teléfono fue previamente eliminado.");
            }

        } catch (Exception e) {
            return new Respuesta<Telefono>("Error Interno: " + e.getMessage());
        }

        return new Respuesta<Telefono>(tel);
    }

    public Respuesta Elimiar(Telefono tel) {

        if (tel.getIdUsuario() != 0) {
            return new Respuesta("No se puede eliminar, ya que tiene vinculado a un usuario.");
        }

        try {
            String sql = "UPDATE numero_telefonico SET Activo = 0 WHERE Id = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, tel.getId());
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta Modificar(Telefono user) {
        try {
            String sql = "UPDATE numero_telefonico SET Telefono = ? WHERE Id = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, user.getTelefono());
            ps.setInt(2, user.getId());
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta Vincular(int idTel, int idUser) {
        try {
            String sql = "UPDATE numero_telefonico SET Id_Usuario = ? WHERE Id = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setInt(2, idTel);
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta Desvincular(int idTel) {
        try {
            String sql = "UPDATE numero_telefonico SET Id_Usuario = 0 WHERE Id = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idTel);
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        return new Respuesta();
    }

    public Respuesta DesvincularParaTodoUsuario(int idUser) {
        List<Integer> ids = new ArrayList<>();

        try {
            String sql = "SELECT Id FROM numero_telefonico WHERE Id_Usuario = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("Id"));
            }

            conexion.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            return new Respuesta("Error Interno: " + e.getMessage());
        }

        Connection conexion2 = ConexionBD.GetConnection();
        PreparedStatement ps2;

        try {
            conexion2.setAutoCommit(false);

            for (Integer id : ids) {
                try {
                    String sql = "UPDATE numero_telefonico SET Id_Usuario = 0 WHERE Id = ?";
                    ps2 = conexion2.prepareStatement(sql);
                    ps2.setInt(1, id);
                    ps2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();  // Puedes manejar la excepción de manera adecuada según tus necesidades
                    // Si hay un error en algún registro, revertir todas las actualizaciones
                    conexion2.rollback();
                    return new Respuesta("Error Interno: " + e.getMessage());
                }
            }

            // Si llegamos aquí, todas las actualizaciones fueron exitosas
            conexion2.commit();
        } catch (SQLException e) {
            try {
                if (conexion2 != null) {
                    conexion2.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            return new Respuesta("Error Interno: " + e.getMessage());
        } finally {
            try {
                if (conexion2 != null) {
                    conexion2.setAutoCommit(true);
                    conexion2.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return new Respuesta();
    }
}
