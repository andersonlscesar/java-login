<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%
  ArrayList<String> errors  = (ArrayList<String>) request.getAttribute("errors");
  String nome = request.getParameter("name");
  String email = request.getParameter("email");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Área de cadastro</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container p-2 d-flex justify-content-center align-items-center" style="height: 100vh">
  <div class="card">
    <div class="card-header">
      Cadastre-se
    </div>

   <%-- VERIFICANDO SE HÁ ERROS --%>
    <% if (errors != null) { %>
      <div class="alert alert-danger m-2">
        <% for (String error : errors) { %>
            <%= error %>
        <% }%>
      </div>
    <%}%>
    <%-- VERIFICANDO SE HÁ ERROS --%>

    <div class="card-body">
      <form action="novo-usuario" method="POST">

        <div class="form-group">
          <label for="name">Nome</label>
          <input type="text" name="name" class="form-control" id="name" value="<%= nome != null ? nome : ""%>">
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" name="email" class="form-control" id="email" value="<%= email != null ? email : ""%>">
        </div>

        <div class="form-group">
          <label for="password">Senha</label>
          <input type="password" name="password" class="form-control" id="password">
        </div>

        <div class="form-group">
          <label for="password_confirmation">Confirmar senha</label>
          <input type="password" name="password_confirmation" class="form-control" id="password_confirmation">
        </div>

        <div class="d-flex gap-1 justify-content-between align-items-center">
          <button type="submit" class="btn btn-primary mt-2">Cadastrar</button>
          <a href="login">Acesse sua conta</a>
        </div>

      </form>
    </div>
  </div>
</div>
</body>
</html>