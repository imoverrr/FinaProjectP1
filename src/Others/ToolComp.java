package Others;

import java.awt.Component;
import java.awt.Container;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// se encargara de realizar varias funciones repetivas en algunos controladores
// sobre acciones en los componentes, como limpiar, validar, etc.
public class ToolComp { // Tool Component 
    
    private ToolComp () {
    }
    
    
    //Este metodo validara los campos ingresados de forma sencilla
    //comporbando si estan vacios los jTextFields de un objeto tipo contenedor
    public static boolean validateFields(Container cp){
        for (Component comp : cp.getComponents()) {
            if (comp instanceof JTextField && ((JTextField) comp).getText().trim().equals("")) {
                JOptionPane.showMessageDialog(cp, "The Field of " 
                        + ((JTextField) comp).getToolTipText() 
                        + " can't be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    

    //Este metodo es para limpiar todos los textFilds de un contenedor
    public static void cleanFields(Container cp){
        //Limpiar todos los textFields
        for (Component comp : cp.getComponents()) {
            if (comp instanceof JTextField) {
               ((JTextField) comp).setText("");
            }
        }
    }


    //Este metodo comporbara si un correo electronico es valido [Gracias maestro por este code :) ]
    public static boolean validateEmail(JTextField correo){
        if (Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Aa-z0-9-]+)*@"
                +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(correo.getText()).find()) {
            return true;
        }
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(correo),
                "The email entered is invalid!", "Warning!", JOptionPane.WARNING_MESSAGE);
        return false;
    } 

}
