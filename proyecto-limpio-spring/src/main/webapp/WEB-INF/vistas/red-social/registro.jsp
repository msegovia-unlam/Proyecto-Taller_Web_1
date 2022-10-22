<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp"%>

<c:if test="${not empty exitoso}">
    <p class="alert alert-dismissible alert-success text-center my-3 ">${exitoso}</p>
</c:if>
<c:if test="${not empty error}">
    <p class="alert alert-dismissible alert-danger text-center my-3 ">${error}</p>
</c:if>
<article class="container w-50 p-3">

    <form:form modelAttribute="datosRegistro" action = "validar-registro" method="POST">
        <fieldset class="pb-2">
            <legend>Registrate</legend>
            <div class="form-group">
                <div class="form-floating mb-3">
                    <form:input path="usuarioName" type="text" class="form-control" id="floatingUser" placeholder="User1234"
                                name="usuarioName" required="required"/>
                    <label for="floatingEmail">Nombre de Usuario</label>
                </div>

                <div class="form-floating mb-3">
                    <form:input path="email" type="email" class="form-control" id="floatingEmail" placeholder="name@example.com"
                                name="email" required="required"/>
                    <label for="floatingUser">Correo electrónico</label>
                </div>

                <div class="form-floating">
                    <form:input type="password" class="form-control" id="floatingPassword" placeholder="Password"
                           name="password" path="password" required="required"/>
                    <label for="floatingPassword">Contraseña</label>
                </div>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-dark">Registrate</button>
    </form:form>
    <p>¿Ya tienes una cuenta? <a class="text-dark" href="${pageContext.request.contextPath}/red-social/">Inicia
        sesión</a></p>
</article>


<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>