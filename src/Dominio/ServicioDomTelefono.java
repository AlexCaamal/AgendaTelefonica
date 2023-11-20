package Dominio;

import Modelos.Respuesta;
import Modelos.Telefono;
import Modelos.usuarioTelefono;
import View.ModuloTelefono;
import View.ModuloVinculacion;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ServicioDomTelefono {

    public Respuesta CargarTabla(ModuloTelefono modulo, List<Telefono> listUser) {

        try {
            DefaultTableModel modeloTabla = (DefaultTableModel) modulo.tb_telefono.getModel();
            modeloTabla.setRowCount(0);

            int columnas;
            int[] ancho = {25, 100};
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                modulo.tb_telefono.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
            }

            columnas = ancho.length;

            for (Telefono tel : listUser) {
                Object[] fila = {tel.getId(), tel.getTelefono()};
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            return new Respuesta("Ha ocurrido un error en la consulta de Telefonos.");
        }

        return new Respuesta();
    }
    
    public Respuesta CargarTablaVinculacion(ModuloVinculacion modulo, List<usuarioTelefono> listUser) {

        try {
            DefaultTableModel modeloTabla = (DefaultTableModel) modulo.tb_telefono_user.getModel();
            modeloTabla.setRowCount(0);

            int columnas;
            int[] ancho = {25, 100, 100};
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                modulo.tb_telefono_user.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
            }

            columnas = ancho.length;

            for (usuarioTelefono tel : listUser) {
                Object[] fila = {tel.getId(), tel.getTelefono(), (tel.getNombreUsuario() == null ? "Sin Vincular" : tel.getNombreUsuario())};
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            return new Respuesta("Ha ocurrido un error en la consulta de Teléfonos.");
        }

        return new Respuesta();
    }

    public Respuesta VerificarNumero(Telefono tel, Boolean numRepetido) {

        if (tel.getTelefono()== null || tel.getTelefono().isEmpty()) {
            return new Respuesta("El campo Teléfono es Requerido.");
        }

        if (!tel.getTelefono().matches("\\d+")) {
            return new Respuesta("El Teléfono debe contener solo números.");
        }

        if (tel.getTelefono().length() > 10 || tel.getTelefono().length() < 10) {
            return new Respuesta("El Teléfono debe contener 10 digitos");
        }
        
        if (numRepetido) {
            return new Respuesta("El Teléfono ya se encuentra registrado.");
        }
        
        return new Respuesta();
    }
}
