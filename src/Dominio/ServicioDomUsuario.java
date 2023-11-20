package Dominio;

import Modelos.Respuesta;
import Modelos.usuario;
import View.ModuloUsuario;
import View.ModuloVinculacion;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ServicioDomUsuario {

    public Respuesta CargarTabla(ModuloUsuario modulo, List<usuario> listUser) {

        try {
            DefaultTableModel modeloTabla = (DefaultTableModel) modulo.tb_usuario.getModel();
            modeloTabla.setRowCount(0);

            int columnas;
            int[] ancho = {25, 100, 100};
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                modulo.tb_usuario.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
            }

            columnas = ancho.length;

            for (usuario usuario : listUser) {
                Object[] fila = {usuario.Id, usuario.Nombre, usuario.Apellido};
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            return new Respuesta("Ha ocurrido un error en la consulta de Usuarios.");
        }

        return new Respuesta();
    }
    
    public Respuesta VerificarUsuario(usuario user){
        
        if(user.getNombre() == null || user.getNombre().isEmpty())
            return new Respuesta("El campo Nombre es Requerido.");
        
        if(user.getNombre().length() > 50)
            return new Respuesta("La cantidad de caracteres del campo Nombre son 50.");
        
        if(user.getApellido()== null || user.getApellido().isEmpty())
            return new Respuesta("El campo Apellido es Requerido.");
        
        if(user.getApellido().length() > 50)
            return new Respuesta("La cantidad de caracteres del campo Apellido son 50.");
        
        return new Respuesta();
    }
    
        public Respuesta CargarTablaVinculacion(ModuloVinculacion modulo, List<usuario> listUser) {

        try {
            DefaultTableModel modeloTabla = (DefaultTableModel) modulo.tb_usuario_vin.getModel();
            modeloTabla.setRowCount(0);

            int columnas;
            int[] ancho = {25, 100, 100};
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                modulo.tb_usuario_vin.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
            }

            columnas = ancho.length;

            for (usuario usuario : listUser) {
                Object[] fila = {usuario.Id, usuario.Nombre, usuario.Apellido};
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            return new Respuesta("Ha ocurrido un error en la consulta de Usuarios.");
        }

        return new Respuesta();
    }
}
