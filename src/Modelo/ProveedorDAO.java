package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProveedorDAO {
    private Conexion cn = new Conexion();
    

    //AGREGAR PROVEEDOR 
    public boolean Agreagar(Proveedor proveedor){
        String sql = "INSERT INTO proveedor (dni, nombre, telefono, direccion) VALUES (?,?,?,?)";
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, proveedor.getDni());
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; 
    }
    
    //FUNCION PARA ELIMIANR LOS PROVEEDOR DE LA BASE DE DATOS
    public boolean Eliminar(Proveedor proveedor){
        String sql = "DELETE FROM proveedor WHERE id = " + proveedor.getId();
        
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
    
    
    
    //FUNCION PARA ACTUALIZAR UN PROVEEDOR EN LA BASE DE DATOS
    public boolean Actualiar (Proveedor proveedor){
        String sql = "UPDATE proveedor SET dni = ?, nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {
            
            ps.setString(1, proveedor.getDni());
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setInt(5, proveedor.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; 
    }
    
    
    //OBTENER TODOS LOS RERGISTROS DE UNA TABLA MEDIANTE UN ARREGLO DE LISTA
    public ArrayList<Proveedor> ObtenerProveedores(String buscar) {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE CONCAT(id, dni, nombre, telefono, direccion) LIKE '%"+buscar+"%'";
        
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Proveedor proveedor = new Proveedor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return proveedores;
    }
}
