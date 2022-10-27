<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <title>Modificar Perfil</title>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<article class="container w-50 p-5">

    <%--@elvariable id="usuario" type="ar.edu.unlam.tallerweb1.domain.usuarios.Usuario"--%>
    <form:form action="${pageContext.request.contextPath}/red-social/actualizarPerfil"  method="post" modelAttribute="usuario" enctype="multipart/form-data">
        <fieldset>
            <legend>"${usuario.nombre}"</legend>

            <form:input path="id" type="hidden" id="id" value="${usuario.id}" />
            <form:input path="id" type="hidden" id="rol" value="${usuario.rol}" />
            <form:input path="id" type="hidden" id="activo" value="${usuario.activo}" />
            <div class="form-group">
                <label class="col-form-label mt-4" for="nombre">Nombre</label>
                <form:input type="text" class="form-control"  id="nombre" path="nombre" />
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="nombre">Email</label>
                <form:input type="text" class="form-control"  id="email" path="email" />
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="nombre">Password</label>
                <form:input type="password" class="form-control"  id="password" path="password" />
            </div>

            </div>
            <button type="submit" class="btn btn-primary mt-4">Actualizar</button>
        </fieldset>
    </form:form>
</article>

<%@include file="../common html/footer.html" %>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
