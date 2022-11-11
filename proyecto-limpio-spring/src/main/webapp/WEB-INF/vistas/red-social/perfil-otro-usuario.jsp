<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <title>${usuario.nombre}</title>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="container mt-5">
    <c:if test="${not empty msjFollow}">
        <p class="alert alert-info text-center my-3">${msjFollow} ${usuario.nombre}!</p>
    </c:if>
    <div class="row">
        <div class="col-4 bg-dark text-center py-2">
            <img src="${pageContext.request.contextPath}/img/${usuario.imagen.id}.jpg" alt="perfil" width="150" height="150">
        </div>
        <div class="col-8">
            <p style="font-size: 1.6em" class="px-3 border-bottom border-dark">${usuario.nombre}</p>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/red-social/seguir/${usuario.id}" method="post">
        <button type="submit" class="btn btn-info px-5 my-4">Seguir</button>
    </form>
</main>

</body>
</html>
