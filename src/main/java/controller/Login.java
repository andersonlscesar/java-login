package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.util.ArrayList;

import java.io.IOException;
import model.DAO.UsuarioDAO;
import model.entities.Usuario;

import  util.Encryptor;

@WebServlet(name = "Login", urlPatterns={ "/cadastro", "/login", "/novo-usuario", "/home", "/logout" })

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
                    insert(request, response);
                break;
            case "/home":
                    signIn(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    protected void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        Usuario usuario = usuarioDAO.getUserByEmail(email);

        ArrayList<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            errors.add("Informe o email");
        } else if (senha == null || senha.trim().isEmpty()) {
            errors.add("Informe a senha");
        } else if (usuario == null) {
            errors.add("Email não cadastrado");
        } else if (!Encryptor.checkPassword(senha, usuario.getSenha())) {
            errors.add("Senha incorreta");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (Encryptor.checkPassword(senha, usuario.getSenha())) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("home.jsp");
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        String nome         = request.getParameter("name");
        String email        = request.getParameter("email");
        String senha        = request.getParameter("password");
        String senhaConfirm = request.getParameter("password_confirmation");

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
        } else if (usuarioDAO.getUserByEmail(email) !=null) {
            errors.add("Este email já está em uso");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("subscribe.jsp").forward(request, response);
        } else {
            usuarioEntity.setNome(nome);
            usuarioEntity.setEmail(email);
            usuarioEntity.setSenha(Encryptor.encryptString(senha));
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
