package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteDAO {
    private Conexion cn = new Conexion();
    
    //AGREGAR CLIENTE 
    public boolean Agreagar(Cliente cliente){
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion) VALUES (?,?,?,?)";
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; 
    }
    
    //FUNCION PARA ELIMIANR LOS CLIENTES DE LA BASE DE DATOS
    public boolean Eliminar(Cliente cliente){
        String sql = "DELETE FROM clientes WHERE id = " + cliente.getId();
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; 
    }
    
    
    
    //FUNCION PARA ACTUALIZAR UN CLIENTE EN LA BASE DE DATOS
    public boolean Actualiar (Cliente cliente){
        String sql = "UPDATE clientes SET dni = ?, nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {
            
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setInt(5, cliente.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; 
    }
    
    
    //OBTENER TODOS LOS RERGISTROS DE UNA TABLA MEDIANTE UN ARREGLO DE LISTA
    public ArrayList<Cliente> ObtenerClientes(String buscar) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE CONCAT(id, dni, nombre, telefono, direccion) LIKE '%"+buscar+"%'";
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Cliente cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return clientes;
    }
}
