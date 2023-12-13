package controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Others.ToolComp;
import Vista.VistaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class ControladorCliente implements ActionListener, MouseListener{
    
    //Variables
    private DefaultTableModel tableModel = new DefaultTableModel();
    
    //Vistas
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private VistaSistema vistaCliente;
    
    //Cosntructor
    public ControladorCliente(VistaSistema vistaCliente){
        
        //Inicializacion
        this.vistaCliente = vistaCliente;
        this.cliente = new Cliente();
        this.clienteDAO = new ClienteDAO();
        
        //Agregar listener
        this.vistaCliente.btnEditarCliente.addActionListener(this);
        this.vistaCliente.btnLimpiarCampos.addActionListener(this);
        this.vistaCliente.btnEliminarCliente.addActionListener(this);
        this.vistaCliente.btnNuevoCliente.addActionListener(this);
        this.vistaCliente.tblCliente.addMouseListener(this);
        this.vistaCliente.btnBuscar.addActionListener(this);

        
        //Columnas de la tabla
        tableModel.addColumn("ID");
        tableModel.addColumn("DNI");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Telefono");
        tableModel.addColumn("Direccion");
        MostrarClientes("");
    }
    //////////////////////////////////////////////////// FUNCIONES
    
    /// INETAR AGREGAR CLIENTE A LA BASE DE DATOS
    private void AgregarCliente(){
        if (ToolComp.validateFields(vistaCliente.pnlClienteFields)) {
            
            //Llenando el modelo
            cliente.setDni(vistaCliente.tfDNI.getText());
            cliente.setNombre(vistaCliente.tfNombre.getText());
            cliente.setTelefono(vistaCliente.tfTelefono.getText());
            cliente.setDireccion(vistaCliente.tfDireccion.getText());
            
            if (clienteDAO.Agreagar(cliente)) {
                JOptionPane.showMessageDialog(vistaCliente, "Se agrego el cliente con exito");
                ToolComp.cleanFields(vistaCliente.pnlClienteFields);
                MostrarClientes("");
            } else {
                JOptionPane.showMessageDialog(vistaCliente, 
                        "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    // FUNCION PARA ELEMINAR REGISTRO
    private void EliminarCliente(){
        try {
            cliente.setId(Integer.parseInt( vistaCliente.tblCliente.getValueAt(vistaCliente.tblCliente.getSelectedRow(), 0).toString()));
            
            if (clienteDAO.Eliminar(cliente)) {
                JOptionPane.showMessageDialog(vistaCliente, "Se Elimino el cliente con exito");
                ToolComp.cleanFields(vistaCliente.pnlClienteFields);
                MostrarClientes("");
            } else {
                JOptionPane.showMessageDialog(vistaCliente, 
                        "Error al intentar eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaCliente, 
                        "Seleccione un cliente", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    //FUNCION PARA ACTUALIZAR CLIENTES
    private void ActualizarCliente() {
       try{
            if (ToolComp.validateFields(vistaCliente.pnlClienteFields)) {
            
                //Llenando el modelo
                cliente.setId(Integer.parseInt( vistaCliente.tblCliente.getValueAt(vistaCliente.tblCliente.getSelectedRow(), 0).toString()));
                cliente.setDni(vistaCliente.tfDNI.getText());
                cliente.setNombre(vistaCliente.tfNombre.getText());
                cliente.setTelefono(vistaCliente.tfTelefono.getText());
                cliente.setDireccion(vistaCliente.tfDireccion.getText());
            
                if (clienteDAO.Actualiar(cliente)) {
                    JOptionPane.showMessageDialog(vistaCliente, "Se Actualizo el cliente con exito");
                    ToolComp.cleanFields(vistaCliente.pnlClienteFields);
                    MostrarClientes("");
                } else {
                    JOptionPane.showMessageDialog(vistaCliente, 
                            "Error al intentar Actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaCliente, 
                        "Seleccione un cliente", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    // FUNCION PARA MOSTRAR LOS CLIENTES EN LA TABLA
    private void MostrarClientes(String buscar) {
        
        //Vaciar la tabla
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.removeRow(i);
            i = i - 1;
        }
        
        ArrayList<Cliente> clientes = clienteDAO.ObtenerClientes(buscar);
        Object[] obj = new Object[5];
        
        for (int i = 0; i < clientes.size(); i++) {
            obj[0] = clientes.get(i).getId();
            obj[1] = clientes.get(i).getDni();
            obj[2] = clientes.get(i).getNombre();
            obj[3] = clientes.get(i).getTelefono();
            obj[4] = clientes.get(i).getDireccion();
            
            tableModel.addRow(obj);
        }
        
        vistaCliente.tblCliente.setModel(tableModel);
    }
    
    // ACTUALIZAR FORMULARIO CLIENTES
    private void RellenarFormulario() {
        int fila = vistaCliente.tblCliente.getSelectedRow();
        vistaCliente.tfDNI.setText(vistaCliente.tblCliente.getValueAt(fila, 1).toString());
        vistaCliente.tfNombre.setText(vistaCliente.tblCliente.getValueAt(fila, 2).toString());
        vistaCliente.tfTelefono.setText(vistaCliente.tblCliente.getValueAt(fila, 3).toString());
        vistaCliente.tfDireccion.setText(vistaCliente.tblCliente.getValueAt(fila, 4).toString());
    }
    
    ///////////////////////////////////////////////// SOBRESCRITURA DE EVENTOS DE LOS LISTENERS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCliente.btnNuevoCliente) {
            AgregarCliente();
        }
        if (e.getSource() == vistaCliente.btnEditarCliente) {
            ActualizarCliente();
        }
        if (e.getSource() == vistaCliente.btnBuscar) {
            MostrarClientes(vistaCliente.tfBuscar.getText());
        }
        if (e.getSource() == vistaCliente.btnEliminarCliente) {
            EliminarCliente();
        }
        if (e.getSource() == vistaCliente.btnLimpiarCampos) {
            ToolComp.cleanFields(vistaCliente.pnlClienteFields);
            MostrarClientes("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vistaCliente.tblCliente) {
            RellenarFormulario();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
