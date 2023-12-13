package controlador;

import Modelo.Cliente;
import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Others.ToolComp;
import Vista.VistaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControladorProveedor implements ActionListener, MouseListener{
    
    //Variables
    private DefaultTableModel tableModel = new DefaultTableModel();
    
    //Vistas
    private Proveedor proveedor;
    private ProveedorDAO proveedorDAO;
    private VistaSistema vistaProveedor;
    
    //Constructor
    public ControladorProveedor(VistaSistema vistaProveedor){
        
        this.vistaProveedor = vistaProveedor;
        this.proveedor = new Proveedor();
        this.proveedorDAO = new ProveedorDAO();
        
        //Agregar listener
        this.vistaProveedor.btnEditarClienteProv.addActionListener(this);
        this.vistaProveedor.btnLimpiarCamposProv.addActionListener(this);
        this.vistaProveedor.btnEliminarClienteProv.addActionListener(this);
        this.vistaProveedor.btnNuevoClienteProv.addActionListener(this);
        this.vistaProveedor.TableProveedor.addMouseListener(this);
        this.vistaProveedor.btnBuscarProv.addActionListener(this);
        
        
        //Columnas de la tabla
        tableModel.addColumn("ID");
        tableModel.addColumn("DNI");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Telefono");
        tableModel.addColumn("Direccion");
        MostrarProveedor("");
    }

    //////////////////////////////////////////////////// FUNCIONES
    
    /// INETAR AGREGAR PROVEEDOR A LA BASE DE DATOS
    private void AgregarProveedor(){
        if (ToolComp.validateFields(vistaProveedor.pnlClienteFieldsProv)) {
            
            //Llenando el modelo
            proveedor.setDni(vistaProveedor.tfDNIProv.getText());
            proveedor.setNombre(vistaProveedor.tfNombreProv.getText());
            proveedor.setTelefono(vistaProveedor.tfTelefonoProv.getText());
            proveedor.setDireccion(vistaProveedor.tfDireccionProv.getText());
            
            if (proveedorDAO.Agreagar(proveedor)) {
                JOptionPane.showMessageDialog(vistaProveedor, "Se agrego el Proveedor con exito");
                ToolComp.cleanFields(vistaProveedor.pnlClienteFieldsProv);
                MostrarProveedor("");
            } else {
                JOptionPane.showMessageDialog(vistaProveedor, 
                        "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    // FUNCION PARA ELEMINAR REGISTRO
    private void EliminarProveedor(){
        try {
            proveedor.setId(Integer.parseInt( vistaProveedor.TableProveedor.getValueAt(vistaProveedor.TableProveedor.getSelectedRow(), 0).toString()));
            
            if (proveedorDAO.Eliminar(proveedor)) {
                JOptionPane.showMessageDialog(vistaProveedor, "Se Elimino el Proveedor con exito");
                ToolComp.cleanFields(vistaProveedor.pnlClienteFieldsProv);
                MostrarProveedor("");
            } else {
                JOptionPane.showMessageDialog(vistaProveedor, 
                        "Error al intentar eliminar el Proveedor", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaProveedor, 
                        "Seleccione un Proveedor", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    //FUNCION PARA ACTUALIZAR PROVEEDOR
    private void ActualizarProveedor() {
       try{
            if (ToolComp.validateFields(vistaProveedor.pnlClienteFieldsProv)) {
            
                //Llenando el modelo
                proveedor.setId(Integer.parseInt( vistaProveedor.TableProveedor.getValueAt(vistaProveedor.TableProveedor.getSelectedRow(), 0).toString()));
                proveedor.setDni(vistaProveedor.tfDNIProv.getText());
                proveedor.setNombre(vistaProveedor.tfNombreProv.getText());
                proveedor.setTelefono(vistaProveedor.tfTelefonoProv.getText());
                proveedor.setDireccion(vistaProveedor.tfDireccionProv.getText());
            
                if (proveedorDAO.Actualiar(proveedor)) {
                    JOptionPane.showMessageDialog(vistaProveedor, "Se Actualizo el Proveedor con exito");
                    ToolComp.cleanFields(vistaProveedor.pnlClienteFieldsProv);
                    MostrarProveedor("");
                } else {
                    JOptionPane.showMessageDialog(vistaProveedor, 
                            "Error al intentar Actualizar el Proveedor", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaProveedor, 
                        "Seleccione un Proveedor", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    // FUNCION PARA MOSTRAR LOS PROVEEDORES EN LA TABLA
    private void MostrarProveedor(String buscar) {
        
        //Vaciar la tabla
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.removeRow(i);
            i = i - 1;
        }
        
        ArrayList<Proveedor> proveeres = proveedorDAO.ObtenerProveedores(buscar);
        Object[] obj = new Object[5];
        
        for (int i = 0; i < proveeres.size(); i++) {
            obj[0] = proveeres.get(i).getId();
            obj[1] = proveeres.get(i).getDni();
            obj[2] = proveeres.get(i).getNombre();
            obj[3] = proveeres.get(i).getTelefono();
            obj[4] = proveeres.get(i).getDireccion();
            
            tableModel.addRow(obj);
        }
        
        vistaProveedor.TableProveedor.setModel(tableModel);
    }
    
    
    // ACTUALIZAR FORMULARIO PROVEEDOR
    private void RellenarFormulario() {
        int fila = vistaProveedor.TableProveedor.getSelectedRow();
        vistaProveedor.tfDNIProv.setText(vistaProveedor.TableProveedor.getValueAt(fila, 1).toString());
        vistaProveedor.tfNombreProv.setText(vistaProveedor.TableProveedor.getValueAt(fila, 2).toString());
        vistaProveedor.tfTelefonoProv.setText(vistaProveedor.TableProveedor.getValueAt(fila, 3).toString());
        vistaProveedor.tfDireccionProv.setText(vistaProveedor.TableProveedor.getValueAt(fila, 4).toString());
    }
    
    ///////////////////////////////////////////////// SOBRESCRITURA DE EVENTOS DE LOS LISTENERS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaProveedor.btnNuevoClienteProv) {
            AgregarProveedor();
        }
        if (e.getSource() == vistaProveedor.btnEditarClienteProv) {
            ActualizarProveedor();
        }
        if (e.getSource() == vistaProveedor.btnBuscarProv) {
            MostrarProveedor(vistaProveedor.tfBuscarProv.getText());
        }
        if (e.getSource() == vistaProveedor.btnEliminarClienteProv) {
            EliminarProveedor();
        }
        if (e.getSource() == vistaProveedor.btnLimpiarCamposProv) {
            ToolComp.cleanFields(vistaProveedor.pnlClienteFieldsProv);
            MostrarProveedor("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vistaProveedor.TableProveedor) {
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
