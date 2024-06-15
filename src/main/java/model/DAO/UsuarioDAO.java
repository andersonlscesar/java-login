package model.DAO;

import model.DBConnection;
import model.entities.Usuario;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.Encryptor;


/**
 * The type Usuario dao.
 */
public class UsuarioDAO {

    private Connection conn;
    /**
     * Insert.
     *
     * @param usuario the usuario
     */
    public void insert(Usuario usuario) {
        String query = "INSERT INTO usuarios(nome, email, senha) VALUES (?, ?, ?)";
        try {
            this.conn = DBConnection.getConnection();
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

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    public Usuario getUserByEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuarioEntity = null;

        try {
            this.conn = DBConnection.getConnection();
            usuarioEntity = new Usuario();
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                usuarioEntity.setId(rs.getString(1));
                usuarioEntity.setNome(rs.getString(2));
                usuarioEntity.setEmail(rs.getString(3));
                usuarioEntity.setSenha(rs.getString(4));
            } else {
                usuarioEntity = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuarioEntity;
    }

}
