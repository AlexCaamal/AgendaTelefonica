package Aplicacion;

import Dominio.ServicioDomTelefono;
import Dominio.ServicioDomUsuario;
import Dominio.ServicioDomVinculacion;
import Interfaces.ServiciosAplicacion;
import Modelos.Respuesta;
import Modelos.Telefono;
import Modelos.usuario;
import Modelos.usuarioTelefono;
import Repositorio.RepositorioTelefono;
import Repositorio.RepositorioUsuario;
import View.DashBorad;
import View.ModuloVinculacion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jdk.nashorn.internal.scripts.JO;

public class ServicioVinculacion implements ActionListener, MouseListener, ServiciosAplicacion, KeyListener {

    ModuloVinculacion vin;
    RepositorioUsuario RepoUs = new RepositorioUsuario();
    ServicioDomUsuario SerUs = new ServicioDomUsuario();
    ServicioDomTelefono ServTel = new ServicioDomTelefono();
    RepositorioTelefono RepoTel = new RepositorioTelefono();
    ServicioDomVinculacion Serv = new ServicioDomVinculacion();
    private int IdTel = 0;
    private int IdUser = 0;

    public ServicioVinculacion(ModuloVinculacion vin) {
        this.vin = vin;
        this.vin.btn_atras.addActionListener(this);
        this.vin.btn_atras.addMouseListener(this);
        this.vin.btn_crear_tel.addActionListener(this);
        this.vin.btn_crear_tel.addMouseListener(this);
        this.vin.btn_modificar_tel.addActionListener(this);
        this.vin.btn_modificar_tel.addMouseListener(this);
        this.vin.txt_telefono.addKeyListener(this);
        this.vin.txt_nombre.addKeyListener(this);
        this.vin.tb_telefono_user.addMouseListener(this);
        this.vin.tb_usuario_vin.addMouseListener(this);
        this.Consultar("");
        this.ConsultaTelefono("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vin.btn_crear_tel) {
            this.Crear();

        } else if (e.getSource() == this.vin.btn_modificar_tel) {
            this.Eliminar();

        } else if (e.getSource() == this.vin.btn_atras) {
            DashBorad modulo = new DashBorad();
            modulo.setVisible(true);
            this.vin.dispose();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vin.tb_telefono_user) {
            int id = this.TablaEventoTel(1);
            this.ObtenerPorId(id, 1);

        } else if (e.getSource() == vin.tb_usuario_vin) {
            int id = this.TablaEventoTel(2);
            this.ObtenerPorId(id, 2);
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
        if (e.getSource() == this.vin.btn_atras) {
            this.vin.btn_atras.setBackground(Color.RED);

        } else if (e.getSource() == this.vin.btn_crear_tel) {
            this.vin.btn_crear_tel.setBackground(Color.RED);

        } else if (e.getSource() == this.vin.btn_modificar_tel) {
            this.vin.btn_modificar_tel.setBackground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Color cl = new Color(51, 51, 51);

        if (e.getSource() == this.vin.btn_atras) {
            this.vin.btn_atras.setBackground(cl);

        } else if (e.getSource() == this.vin.btn_crear_tel) {
            this.vin.btn_crear_tel.setBackground(cl);

        } else if (e.getSource() == this.vin.btn_modificar_tel) {
            this.vin.btn_modificar_tel.setBackground(cl);
        }
    }

    @Override
    public void Consultar() {
    }

    public void Consultar(String criterio) {
        Respuesta<List<usuario>> respuesta = RepoUs.Obtener(criterio);

        if (respuesta.esError()) {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }

        Respuesta respuestaDom = SerUs.CargarTablaVinculacion(this.vin, respuesta.contenido);

        if (respuestaDom.esError()) {
            JOptionPane.showMessageDialog(null, respuestaDom.mensaje);
        }
    }

    public void ConsultaTelefono(String criterio) {

        Respuesta<List<usuarioTelefono>> respuesta = RepoTel.ObtenerVinculacion(criterio);

        if (respuesta.esExito()) {

            Respuesta respuestaDom = ServTel.CargarTablaVinculacion(this.vin, respuesta.contenido);

            if (respuestaDom.esError()) {
                JOptionPane.showMessageDialog(null, respuestaDom.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }

    }

    @Override
    public void Crear() {

        Respuesta<Telefono> respuesta = RepoTel.ObtenerPorId(IdTel);

        if (respuesta.esExito()) {

            Respuesta respuestaServ = Serv.VerificarVincular(respuesta.contenido, IdUser);

            if (respuestaServ.esExito()) {

                Respuesta respuestaSave = RepoTel.Vincular(IdTel, IdUser);

                if (respuestaSave.esExito()) {
                    this.vin.txt_telefono.setText("");
                    this.vin.txt_nombre.setText("");
                    this.ConsultaTelefono("");
                    JOptionPane.showMessageDialog(null, "La vinculación se ha realizado correctamente.");

                } else {
                    JOptionPane.showMessageDialog(null, respuestaSave.mensaje);
                }

            } else {
                JOptionPane.showMessageDialog(null, respuestaServ.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }
    }

    @Override
    public void Eliminar() {
        Respuesta<Telefono> respuesta = RepoTel.ObtenerPorId(IdTel);

        if (respuesta.esExito()) {

            Respuesta respuestaServ = Serv.VerificarDesvincular(respuesta.contenido);

            if (respuestaServ.esExito()) {

                Respuesta respuestaSave = RepoTel.Desvincular(IdTel);

                if (respuestaSave.esExito()) {
                    this.vin.txt_telefono.setText("");
                    this.vin.txt_nombre.setText("");
                    JOptionPane.showMessageDialog(null, "La desvinculación se ha realizado correctamente.");
                    this.ConsultaTelefono("");
                } else {
                    JOptionPane.showMessageDialog(null, respuestaSave.mensaje);
                }

            } else {
                JOptionPane.showMessageDialog(null, respuestaServ.mensaje);
            }
        } else {
            JOptionPane.showMessageDialog(null, respuesta.mensaje);
        }
    }

    @Override
    public void Modificar() {
    }

    @Override
    public void ObtenerPorId(int Id) {
    }

    public void ObtenerPorId(int Id, int tipo) {
        if (tipo == 1) {
            Respuesta<Telefono> respuesta = RepoTel.ObtenerPorId(Id);

            if (respuesta.esExito()) {
                this.vin.txt_telefono.setText(respuesta.contenido.getTelefono());
            } else {
                JOptionPane.showMessageDialog(null, respuesta.mensaje);
            }

        } else if (tipo == 2) {
            Respuesta<usuario> respuesta = RepoUs.ObtenerPorId(Id);

            if (respuesta.esExito()) {
                this.vin.txt_nombre.setText(respuesta.contenido.Nombre + " " + respuesta.contenido.Apellido);
            } else {
                JOptionPane.showMessageDialog(null, respuesta.mensaje);
            }
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
        if (e.getSource() == vin.txt_telefono) {
            calcularCantidadLetra(vin.txt_telefono, vin.txt_cant);

            Respuesta respuestaText = this.ValidarNumero();

            if (respuestaText.esExito()) {
                this.ConsultaTelefono(this.vin.txt_telefono.getText());

            } else {
                JOptionPane.showMessageDialog(null, respuestaText.mensaje);
            }
        } else if (e.getSource() == vin.txt_nombre) {
            this.Consultar(this.vin.txt_nombre.getText());

        }

    }

    private void calcularCantidadLetra(JTextField text, JTextField lb) {
        String numero = text.getText();
        int cantidad = numero.length();
        lb.setText("" + (cantidad == 0 ? "" : cantidad));
    }

    private Respuesta ValidarNumero() {
        String numero = this.vin.txt_telefono.getText();

        if (numero.isEmpty()) {
            return new Respuesta();
        }

        if (!numero.matches("\\d+")) {
            return new Respuesta("El Teléfono debe contener solo números.");
        }

        if (numero.length() > 10) {
            return new Respuesta("El Teléfono debe contener 10 digitos");
        }

        return new Respuesta();
    }

    private int TablaEventoTel(int tipoTabla) {

        if (tipoTabla == 1) {
            int fila = vin.tb_telefono_user.getSelectedRow();
            return this.IdTel = Integer.parseInt(vin.tb_telefono_user.getValueAt(fila, 0).toString());
        } else if (tipoTabla == 2) {
            int fila = vin.tb_usuario_vin.getSelectedRow();
            return this.IdUser = Integer.parseInt(vin.tb_usuario_vin.getValueAt(fila, 0).toString());
        }

        return 0;
    }

}
