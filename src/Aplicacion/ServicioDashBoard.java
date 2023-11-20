package Aplicacion;

import View.DashBorad;
import View.ModuloTelefono;
import View.ModuloUsuario;
import View.ModuloVinculacion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ServicioDashBoard implements ActionListener, MouseListener {

    DashBorad dash;

    public ServicioDashBoard(DashBorad dash) {
        this.dash = dash;
        this.dash.btn_numeros.addActionListener(this);
        this.dash.btn_usuario.addActionListener(this);
        this.dash.btn_vinculacion.addActionListener(this);
        this.dash.btn_numeros.addMouseListener(this);
        this.dash.btn_usuario.addMouseListener(this);
        this.dash.btn_vinculacion.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.dash.btn_numeros) {
            ModuloTelefono modulo = new ModuloTelefono();
            modulo.setVisible(true);
            this.dash.dispose();

        } else if (e.getSource() == this.dash.btn_usuario) {
            ModuloUsuario modulo = new ModuloUsuario();
            modulo.setVisible(true);
            this.dash.dispose();

        } else if (e.getSource() == this.dash.btn_vinculacion) {
            ModuloVinculacion modulo = new ModuloVinculacion();
            modulo.setVisible(true);
            this.dash.dispose();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == this.dash.btn_numeros) {
            this.dash.btn_numeros.setBackground(Color.red);

        } else if (e.getSource() == this.dash.btn_usuario) {
            this.dash.btn_usuario.setBackground(Color.red);

        } else if (e.getSource() == this.dash.btn_vinculacion) {
            this.dash.btn_vinculacion.setBackground(Color.red);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Color cl = new Color(51, 51, 51);

        if (e.getSource() == this.dash.btn_numeros) {
            this.dash.btn_numeros.setBackground(cl);

        } else if (e.getSource() == this.dash.btn_usuario) {
            this.dash.btn_usuario.setBackground(cl);

        } else if (e.getSource() == this.dash.btn_vinculacion) {
            this.dash.btn_vinculacion.setBackground(cl);

        }
    }

}
