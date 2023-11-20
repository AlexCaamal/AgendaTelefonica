package Aplicacion;

import Dominio.ServicioDomTelefono;
import Interfaces.ServiciosAplicacion;
import Modelos.Respuesta;
import Modelos.Telefono;
import Repositorio.RepositorioTelefono;
import View.DashBorad;
import View.ModuloTelefono;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ServicioTelefono implements ActionListener, MouseListener, ServiciosAplicacion, KeyListener {

    ModuloTelefono tel;
    RepositorioTelefono Repo = new RepositorioTelefono();
    ServicioDomTelefono Serv = new ServicioDomTelefono();
    private int Id = 0;

    public ServicioTelefono(ModuloTelefono tel) {
        this.tel = tel;
        this.tel.btn_atras.addActionListener(this);
        this.tel.btn_atras.addMouseListener(this);
        this.tel.btn_crear_tel.addActionListener(this);
        this.tel.btn_crear_tel.addMouseListener(this);
        this.tel.btn_eliminar_tel.addActionListener(this);
        this.tel.btn_eliminar_tel.addMouseListener(this);
        this.tel.btn_modificar_tel.addActionListener(this);
        this.tel.btn_modificar_tel.addMouseListener(this);
        this.tel.btn_nuevo_tel.addActionListener(this);
        this.tel.btn_nuevo_tel.addMouseListener(this);
        this.tel.tb_telefono.addMouseListener(this);
        this.tel.txt_telefono.addKeyListener(this);
        this.Consultar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tel.btn_crear_tel) {
            this.Crear();

        } else if (e.getSource() == this.tel.btn_eliminar_tel) {
            this.Eliminar();
            this.Botones(true, false);
            this.LimpiarCampos();

        } else if (e.getSource() == this.tel.btn_modificar_tel) {
            this.Modificar();

        } else if (e.getSource() == this.tel.btn_nuevo_tel) {
            this.Botones(true, false);
            this.LimpiarCampos();

        } else if (e.getSource() == this.tel.btn_atras) {
            DashBorad modulo = new DashBorad();
            modulo.setVisible(true);
            this.tel.dispose();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == tel.txt_telefono) {
            calcularCantidadLetra(tel.txt_telefono, tel.txt_cant);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tel.tb_telefono) {
            int id = this.TablaEvento();
            this.ObtenerPorId(id);
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

        if (e.getSource() == this.tel.btn_atras) {
            this.tel.btn_atras.setBackground(Color.RED);

        } else if (e.getSource() == this.tel.btn_crear_tel) {
            this.tel.btn_crear_tel.setBackground(Color.RED);

        } else if (e.getSource() == this.tel.btn_eliminar_tel) {
            this.tel.btn_eliminar_tel.setBackground(Color.RED);

        } else if (e.getSource() == this.tel.btn_modificar_tel) {
            this.tel.btn_modificar_tel.setBackground(Color.RED);

        } else if (e.getSource() == this.tel.btn_nuevo_tel) {
            this.tel.btn_nuevo_tel.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Color cl = new Color(51, 51, 51);

        if (e.getSource() == this.tel.btn_atras) {
            this.tel.btn_atras.setBackground(cl);

        } else if (e.getSource() == this.tel.btn_crear_tel) {
            this.tel.btn_crear_tel.setBackground(cl);

        } else if (e.getSource() == this.tel.btn_eliminar_tel) {
            this.tel.btn_eliminar_tel.setBackground(cl);

        } else if (e.getSource() == this.tel.btn_modificar_tel) {
            this.tel.btn_modificar_tel.setBackground(cl);

        } else if (e.getSource() == this.tel.btn_nuevo_tel) {
            this.tel.btn_nuevo_tel.setBackground(cl);
        }
    }

    @Override
    public void Consultar() {

        Respuesta<List<Telefono>> respuesta = Repo.Obtener();

        if (respuesta.esError()) {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }

        Respuesta respuestaDom = Serv.CargarTabla(this.tel, respuesta.contenido);

        if (respuestaDom.esError()) {
            JOptionPane.showMessageDialog(null, respuestaDom.mensaje);
        }
    }

    @Override
    public void Crear() {

        String tele = this.tel.txt_telefono.getText();

        Telefono telefono = new Telefono(0, tele, 0);

        Respuesta<Boolean> respuestaRepo = Repo.ObtenerRepetido(telefono.getTelefono(), 0);

        if (respuestaRepo.esExito()) {

            Respuesta respuesta = Serv.VerificarNumero(telefono, respuestaRepo.contenido);

            if (respuesta.esExito()) {

                Respuesta respuestaSave = Repo.Crear(telefono);

                if (respuestaSave.esExito()) {
                    this.LimpiarCampos();
                    this.Consultar();
                    JOptionPane.showMessageDialog(null, "Se ha agregado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, respuestaSave.mensaje);
                }
            } else {
                JOptionPane.showMessageDialog(null, respuesta.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuestaRepo.mensaje);
        }
    }

    @Override
    public void Eliminar() {

        Telefono tel = new Telefono(this.Id);

        Respuesta<Telefono> respuestaRepo = Repo.ObtenerPorId(tel.getId());

        if (respuestaRepo.esExito()) {

            Respuesta respuesta = Repo.Elimiar(respuestaRepo.contenido);

            if (respuesta.esExito()) {
                this.Consultar();
                JOptionPane.showMessageDialog(null, "Se ha Eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, respuesta.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuestaRepo.mensaje);
        }

    }

    @Override
    public void Modificar() {

        String Telefono = this.tel.txt_telefono.getText();

        Telefono tel = new Telefono(this.Id, Telefono, 0);

        Respuesta<Boolean> respuestaRep = Repo.ObtenerRepetido(tel.getTelefono(), tel.getId());

        if (respuestaRep.esExito()) {

            Respuesta respuesta = Serv.VerificarNumero(tel, respuestaRep.contenido);

            if (respuesta.esExito()) {

                Respuesta respuestaSave = Repo.Modificar(tel);

                if (respuestaSave.esExito()) {
                    this.Consultar();
                    JOptionPane.showMessageDialog(null, "Se ha modificado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, respuestaSave.mensaje);
                }

            } else {
                JOptionPane.showMessageDialog(null, respuesta.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuestaRep.mensaje);
        }
    }

    @Override
    public void ObtenerPorId(int Id) {
        Respuesta<Telefono> respuesta = Repo.ObtenerPorId(Id);

        if (respuesta.esExito()) {
            this.tel.txt_telefono.setText(respuesta.contenido.getTelefono());
            this.Botones(false, true);
        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }
    }

    private void LimpiarCampos() {
        this.tel.txt_telefono.setText("");
        this.tel.txt_telefono.requestFocus(true);
    }

    private void Botones(Boolean ver1, Boolean ver2) {
        this.tel.btn_crear_tel.setEnabled(ver1);
        this.tel.btn_modificar_tel.setVisible(ver2);
        this.tel.btn_nuevo_tel.setVisible(ver2);
        this.tel.btn_eliminar_tel.setVisible(ver2);
    }

    private int TablaEvento() {
        int fila = tel.tb_telefono.getSelectedRow();
        return this.Id = Integer.parseInt(tel.tb_telefono.getValueAt(fila, 0).toString());
    }

    private void calcularCantidadLetra(JTextField text, JTextField lb) {
        String numero = text.getText();
        int cantidad = numero.length();
        lb.setText("" + (cantidad == 0 ? "" : cantidad));
    }
}
