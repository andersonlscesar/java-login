<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.entities.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Home - <%= usuario.getNome() %> </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container p-2 d-flex justify-content-center align-items-center" style="height: 100vh">
  <h1>Olá, <%= usuario.getNome() %></h1>
</div>
</body>
</html>