package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.io.IOException;
import model.DAO.UsuarioDAO;
import model.entities.Usuario;

import util.Encryptor;

@WebServlet(name = "Login", urlPatterns={ "/cadastro", "/login", "/novo-usuario", "/home" })

public class Login extends HttpServlet {
    Usuario usuarioEntity = new Usuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/cadastro":
                subscribe(request, response);
                break;
            case "/login":
                login(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/novo-usuario":
                try {
                    insert(request, response);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/home":
                signIn(request, response);
                break;
        }
    }

    protected void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        Usuario usuario = usuarioDAO.getUserByEmail(email);

        ArrayList<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            errors.add("Informe o email");
        }else if (senha == null || senha.trim().isEmpty()) {
            errors.add("informe a senha");
        }else if (usuario == null) {
            errors.add("Email não encontrado");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String nome         = request.getParameter("name");
        String email        = request.getParameter("email");
        String senha        = request.getParameter("password");
        String senhaConfirm = request.getParameter("password_confirmation");

        Encryptor encryptor = new Encryptor();

        ArrayList<String> errors = new ArrayList<>();

        if (nome == null || nome.trim().isEmpty()) {
            errors.add("O nome é obrigatório");
        } else if (email == null || email.trim().isEmpty()) {
            errors.add("O email é obrigatório");
        } else if (senha == null || senha.trim().isEmpty()) {
            errors.add("Informe uma senha");
        } else if (senhaConfirm == null || senhaConfirm.trim().isEmpty()) {
            errors.add("Confirme sua senha");
        } else if (!senha.equals(senhaConfirm)) {
            errors.add("As senhas não coincidem");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("subscribe.jsp").forward(request, response);
        } else {

            usuarioEntity.setNome(nome);
            usuarioEntity.setEmail(email);
            usuarioEntity.setSenha(encryptor.encryptString(senha));
            usuarioDAO.insert(usuarioEntity);
            response.sendRedirect("index.jsp");

        }

    }

    protected void subscribe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("subscribe.jsp");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
