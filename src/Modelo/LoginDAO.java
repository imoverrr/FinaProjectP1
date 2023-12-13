package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {
    private Conexion cn = new Conexion();

    public boolean autenticar(Login loginModel) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try (
            Connection cx = cn.conectarBaseDatos();
            PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, loginModel.getCorreo());
            ps.setString(2, loginModel.getPass());

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; 
    }
}

