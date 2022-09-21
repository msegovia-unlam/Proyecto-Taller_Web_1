<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.html"%>

<article class="container w-25 p-5">
    <form action="">
        <fieldset class="pb-2">
            <legend>Registrate</legend>
            <div class="form-group">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingEmail" placeholder="name@example.com"
                           name="email">
                    <label for="floatingEmail">Nombre de Usuario</label>
                </div>

                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingUser" placeholder="name@example.com"
                           name="email">
                    <label for="floatingUser">Correo electrónico</label>
                </div>

                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
                           name="password">
                    <label for="floatingPassword">Contraseña</label>
                </div>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary">Submit</button>
        <p>¿Ya tienes una cuenta? <a href="login">Inicia sesión</a></p>
    </form>
</article>

<%@include file="common html/footer.html" %>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>