package model.DAO;

import model.DBConnection;
import model.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() {
        this.conn = DBConnection.getConnection();
    }

    public void insert(Usuario usuario) {
        String query = "INSERT INTO usuarios(nome, email, senha) VALUES (?, ?, ?)";

        try {
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.executeUpdate();
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
