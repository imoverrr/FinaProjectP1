package controlador;

import Vista.VistaSistema;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ControladorSistema implements ActionListener{
    
    
    //Variables
    
    //Creacion de vistas
    VistaSistema vistaSistema;
    
    //Creacion de controladores
    ControladorCliente CtrlCliente;
    ControladorProveedor CtrlProveedor;
    
    //Constructor
    public ControladorSistema(VistaSistema vistaSistema){
        
        //Inicializacion de vistas
        this.vistaSistema = vistaSistema;
        
        //Inicializacion de controladores
        this.CtrlCliente = new ControladorCliente(vistaSistema);
        this.CtrlProveedor = new ControladorProveedor(vistaSistema);
        
        //Agregar listener
        for (Component comp : vistaSistema.pnlLeft.getComponents()) {
            if (comp instanceof JButton){
                ((JButton) comp).addActionListener(this);
            }
        }
        
        // Configuraciones adicionales de la vista
        this.vistaSistema.setLocationRelativeTo(null);
    }
    
    
    //////////////////////////////////////////////////// FUNCIONES

    
    
    ///////////////////////////////////////////////// SOBRESCRITURA DE EVENTOS DE LOS LISTENERS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaSistema.btnMenu1){
            vistaSistema.tpMenu.setSelectedIndex(0);
        }
        if (e.getSource() == vistaSistema.btnMenu2){
            vistaSistema.tpMenu.setSelectedIndex(1);
        }
        if (e.getSource() == vistaSistema.btnMenu3){
            vistaSistema.tpMenu.setSelectedIndex(2);
        }
        if (e.getSource() == vistaSistema.btnMenu4){
            vistaSistema.tpMenu.setSelectedIndex(3);
        }
        if (e.getSource() == vistaSistema.btnMenu5){
            vistaSistema.tpMenu.setSelectedIndex(4);
        }
        if (e.getSource() == vistaSistema.btnMenu6){
            vistaSistema.tpMenu.setSelectedIndex(5);
        }
    }
    
    
}
