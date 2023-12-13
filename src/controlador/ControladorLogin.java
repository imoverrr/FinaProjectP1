package controlador;


import Modelo.Login;
import Modelo.LoginDAO;
import Others.ToolComp;
import Vista.VistaLogin;
import Vista.VistaSistema;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;



public class ControladorLogin  implements MouseListener, MouseMotionListener {
    
    //Variables
    private int xMouse, yMouse;
    
    //Vistas
    private Login loginModel;
    private LoginDAO loginDAO;
    private VistaLogin vistaLogin;
    private VistaSistema vistaSistema;

    //Controlador
    public ControladorLogin(VistaLogin vistaLogin) {
        
        //Inicializacion
        this.vistaLogin = vistaLogin;
        this.loginModel = new Login();
        this.loginDAO = new LoginDAO();
        this.vistaSistema = new VistaSistema();
        
        //Agregar listener
        this.vistaLogin.lblLogin.addMouseListener(this);
        this.vistaLogin.pnlHeader.addMouseMotionListener(this);
        this.vistaLogin.pnlHeader.addMouseListener(this);
        this.vistaLogin.lblExit.addMouseListener(this);

        // Configuraciones adicionales de la vista
        this.vistaLogin.setLocationRelativeTo(null);
    }
    
    //////////////////////////////////////////////////// FUNCIONES

    private void Autenticar() {
        if (ToolComp.validateFields(vistaLogin.pnlMain) && ToolComp.validateEmail(vistaLogin.txtCorreo)) {
            
            //Llenando el modelo
            loginModel.setCorreo(vistaLogin.txtCorreo.getText());
            loginModel.setPass(new String(vistaLogin.txtPass.getPassword()));
            
            if (loginDAO.autenticar(loginModel)) {
                JOptionPane.showMessageDialog(vistaLogin, "Inicio de sesión exitoso");
                ControladorSistema controladorSistema = new ControladorSistema(vistaSistema);
                this.vistaSistema.setVisible(true);
                vistaLogin.dispose();
            } else {
                JOptionPane.showMessageDialog(vistaLogin, 
                        "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    ///////////////////////////////////////////////// SOBRESCRITURA DE EVENTOS DE LOS LISTENERS
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistaLogin.lblLogin) {
            Autenticar();
        }
        if (e.getSource() == vistaLogin.lblExit) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vistaLogin.pnlHeader){
            xMouse = e.getX();
            yMouse = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == vistaLogin.lblExit) {
            vistaLogin.pnlExit.setBackground(Color.red);
            vistaLogin.lblExit.setForeground(Color.white);
        }
        if (e.getSource() == vistaLogin.lblLogin) {
            vistaLogin.pnlLogin.setBackground(new Color(0,156,223));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == vistaLogin.lblExit) {
            vistaLogin.pnlExit.setBackground(Color.white);
            vistaLogin.lblExit.setForeground(Color.BLACK);
        }
        if (e.getSource() == vistaLogin.lblLogin) {
            vistaLogin.pnlLogin.setBackground(new Color(0,134,190));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == vistaLogin.pnlHeader){
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            vistaLogin.setLocation(x - xMouse,y - yMouse);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
