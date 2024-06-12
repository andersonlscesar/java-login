package controller;

import jakarta.jms.Message;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.IOException;
import model.DAO.UsuarioDAO;
import model.entities.Usuario;

@WebServlet(name = "Login", urlPatterns={ "/cadastro", "/login", "/novo-usuario" })

public class Login extends HttpServlet {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuarioEntity = new Usuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);

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
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome         = request.getParameter("name");
        String email        = request.getParameter("email");
        String senha        = request.getParameter("password");
        String senhaConfirm = request.getParameter("password_confirmation");


        ArrayList<String> errors = new ArrayList<>();

//        if (nome == null || nome.trim().isEmpty()) {
//            errors.add("O nome é obrigatório");
//        } else if (email == null || email.trim().isEmpty()) {
//            errors.add("O email é obrigatório");
//        } else if (senha == null || senha.trim().isEmpty()) {
//            errors.add("Informe uma senha");
//        } else if (senhaConfirm == null || senhaConfirm.trim().isEmpty()) {
//            errors.add("Confirme sua senha");
//        } else if (!senha.equals(senhaConfirm)) {
//            errors.add("As senhas não coincidem");
//        }
//
//        if (!errors.isEmpty()) {
//            request.setAttribute("errors", errors);
//            request.getRequestDispatcher("subscribe.jsp").forward(request, response);
//        } else {
//            usuarioEntity.setNome(nome);
//            usuarioEntity.setEmail(email);
//
//            usuarioDAO.insert();
//            response.sendRedirect("index.jsp");
//        }

    }

    protected void subscribe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("subscribe.jsp");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
