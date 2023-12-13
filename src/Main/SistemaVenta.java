package Main;

import Vista.VistaLogin;
import com.formdev.flatlaf.FlatLightLaf;
import controlador.ControladorLogin;
import javax.swing.UIManager;

public class SistemaVenta {

    public static void main(String[] args) {
        
        //Estableciendo el look and feel a toda la interfaz que se cree a partir de aqui
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        //Creacion e inilizacion de la vista login
        VistaLogin login = new VistaLogin();
        ControladorLogin CtrlLogin = new ControladorLogin(login);
        login.setVisible(true);
    }
}
