<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container p-2 d-flex justify-content-center align-items-center" style="height: 100vh">
        <div class="card">
            <div class="card-header">
                Acesse sua conta
            </div>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" name="email" class="form-control" id="email">
                    </div>
                    <div class="form-group">
                        <label for="password">Senha</label>
                        <input type="password" name="password" class="form-control" id="password">
                    </div>
                    <div class="d-flex gap-1 justify-content-between align-items-center">
                        <button type="submit" class="btn btn-primary mt-2">Entrar</button>
                        <a href="cadastro">Cadastre-se</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
</body>
</html>