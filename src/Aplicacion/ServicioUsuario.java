package Aplicacion;

import Dominio.ServicioDomUsuario;
import Interfaces.ServiciosAplicacion;
import Modelos.Respuesta;
import Modelos.usuario;
import Repositorio.RepositorioTelefono;
import Repositorio.RepositorioUsuario;
import View.DashBorad;
import View.ModuloUsuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ServicioUsuario implements ActionListener, MouseListener, ServiciosAplicacion {

    ModuloUsuario user;
    RepositorioUsuario repo = new RepositorioUsuario();
    ServicioDomUsuario SerUser = new ServicioDomUsuario();
    RepositorioTelefono RepoTel = new RepositorioTelefono();
    private int Id = 0;

    public ServicioUsuario(ModuloUsuario user) {
        this.user = user;
        this.user.btn_atras.addActionListener(this);
        this.user.btn_atras.addMouseListener(this);
        this.user.btn_crear_user.addActionListener(this);
        this.user.btn_crear_user.addMouseListener(this);
        this.user.btn_eliminar_user.addActionListener(this);
        this.user.btn_eliminar_user.addMouseListener(this);
        this.user.btn_modificar_user.addActionListener(this);
        this.user.btn_modificar_user.addMouseListener(this);
        this.user.btn_nuevo_user.addActionListener(this);
        this.user.btn_nuevo_user.addMouseListener(this);
        this.user.tb_usuario.addMouseListener(this);

        this.Consultar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.user.btn_atras) {
            DashBorad modulo = new DashBorad();
            modulo.setVisible(true);
            this.user.dispose();

        } else if (e.getSource() == this.user.btn_crear_user) {
            this.Crear();

        } else if (e.getSource() == this.user.btn_eliminar_user) {
            this.Eliminar();
            this.Botones(true, false);
            this.LimpiarCampos();

        } else if (e.getSource() == this.user.btn_modificar_user) {
            this.Modificar();

        } else if (e.getSource() == this.user.btn_nuevo_user) {
            this.Botones(true, false);
            this.LimpiarCampos();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == user.tb_usuario) {
            int idUsuario = this.TablaEvento();
            this.ObtenerPorId(idUsuario);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == this.user.btn_atras) {
            this.user.btn_atras.setBackground(Color.RED);

        } else if (e.getSource() == this.user.btn_crear_user) {
            this.user.btn_crear_user.setBackground(Color.RED);

        } else if (e.getSource() == this.user.btn_eliminar_user) {
            this.user.btn_eliminar_user.setBackground(Color.RED);

        } else if (e.getSource() == this.user.btn_modificar_user) {
            this.user.btn_modificar_user.setBackground(Color.RED);

        } else if (e.getSource() == this.user.btn_nuevo_user) {
            this.user.btn_nuevo_user.setBackground(Color.RED);

        } else if (e.getSource() == this.user.btn_atras) {
            this.user.btn_atras.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Color cl = new Color(51, 51, 51);

        if (e.getSource() == this.user.btn_atras) {
            this.user.btn_atras.setBackground(cl);

        } else if (e.getSource() == this.user.btn_crear_user) {
            this.user.btn_crear_user.setBackground(cl);

        } else if (e.getSource() == this.user.btn_eliminar_user) {
            this.user.btn_eliminar_user.setBackground(cl);

        } else if (e.getSource() == this.user.btn_modificar_user) {
            this.user.btn_modificar_user.setBackground(cl);

        } else if (e.getSource() == this.user.btn_nuevo_user) {
            this.user.btn_nuevo_user.setBackground(cl);

        } else if (e.getSource() == this.user.btn_atras) {
            this.user.btn_atras.setBackground(cl);
        }
    }

    private int TablaEvento() {
        int fila = user.tb_usuario.getSelectedRow();
        return this.Id = Integer.parseInt(user.tb_usuario.getValueAt(fila, 0).toString());
    }

    private void Botones(Boolean ver1, Boolean ver2) {
        this.user.btn_crear_user.setEnabled(ver1);
        this.user.btn_modificar_user.setVisible(ver2);
        this.user.btn_nuevo_user.setVisible(ver2);
        this.user.btn_eliminar_user.setVisible(ver2);
    }

    private void LimpiarCampos() {
        this.user.txt_nombre.setText("");
        this.user.txt_apellido.setText("");
        this.user.txt_nombre.requestFocus(true);
    }

    @Override
    public void Consultar() {

        Respuesta<List<usuario>> respuesta = repo.Obtener("");

        if (respuesta.esError()) {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }

        Respuesta respuestaDom = SerUser.CargarTabla(this.user, respuesta.contenido);

        if (respuestaDom.esError()) {
            JOptionPane.showMessageDialog(null, respuestaDom.mensaje);
        }
    }

    @Override
    public void Crear() {
        String Nombre = this.user.txt_nombre.getText();
        String Apellido = this.user.txt_apellido.getText();

        usuario user = new usuario(0, Nombre, Apellido);

        Respuesta respuesta = SerUser.VerificarUsuario(user);

        if (respuesta.esExito()) {
            Respuesta respuestaSave = repo.Crear(user);

            if (respuestaSave.esExito()) {
                this.user.txt_apellido.setText("");
                this.user.txt_nombre.setText("");
                this.Consultar();
                JOptionPane.showMessageDialog(null, "Se ha agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, respuestaSave.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }

    }

    @Override
    public void Eliminar() {
        usuario user = new usuario(this.Id);

        Respuesta respuestaTel = RepoTel.DesvincularParaTodoUsuario(user.Id);

        if (respuestaTel.esExito()) {
            
            Respuesta respuesta = repo.Elimiar(user);

            if (respuesta.esExito()) {
                this.Consultar();
                JOptionPane.showMessageDialog(null, "Se ha Eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, respuesta.mensaje);
            }
        }else{
            JOptionPane.showMessageDialog(null, respuestaTel.mensaje);
        }
    }

    @Override
    public void Modificar() {
        String Nombre = this.user.txt_nombre.getText();
        String Apellido = this.user.txt_apellido.getText();

        usuario user = new usuario(this.Id, Nombre, Apellido);

        Respuesta respuesta = SerUser.VerificarUsuario(user);

        if (respuesta.esExito()) {

            Respuesta respuestaSave = repo.Modificar(user);

            if (respuestaSave.esExito()) {
                this.Consultar();
                JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, respuestaSave.mensaje);
            }

        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }
    }

    @Override
    public void ObtenerPorId(int Id) {
        Respuesta<usuario> respuesta = repo.ObtenerPorId(Id);

        if (respuesta.esExito()) {
            this.user.txt_nombre.setText(respuesta.contenido.Nombre);
            this.user.txt_apellido.setText(respuesta.contenido.Apellido);
            this.Botones(false, true);
        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }
    }
}
