<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Buscar</title>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="container-sm">
    <c:if test="${not empty mensajeError}">
        <p class="alert alert-info text-center my-3">${mensajeError}</p>
    </c:if>
    <div class="container-fluid mt-3 w-25">
        <div class="bs-component mb-3">
            <div class="btn-group container" role="group" aria-label="Basic radio toggle button group">
                <c:if test="${not empty param.get('busquedaLibro') && empty param.get('busquedaUsuario')}">
                    <a href="${pageContext.request.contextPath}/red-social/busqueda?busquedaLibro=${param.get("busquedaLibro")}">
                        <label class="btn btn-outline-dark">Libros</label>
                    </a>
                    <a href="${pageContext.request.contextPath}/red-social/busqueda?busquedaUsuario=${param.get("busquedaLibro")}">
                        <label class="btn btn-outline-dark">Usuarios</label>
                    </a>
                </c:if>
                <c:if test="${not empty param.get('busquedaUsuario') && empty param.get('busquedaLibro')}">
                    <a href="${pageContext.request.contextPath}/red-social/busqueda?busquedaLibro=${param.get("busquedaUsuario")}">
                        <label class="btn btn-outline-dark">Libros</label>
                    </a>
                    <a href="${pageContext.request.contextPath}/red-social/busqueda?busquedaUsuario=${param.get("busquedaUsuario")}">
                        <label class="btn btn-outline-dark">Usuarios</label>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
    <c:if test="${not empty libros}">
        <c:forEach items="${libros}" var="libro">
            <div class="d-flex w-50 flex-column container-fluid p-2 justify-content-center border-bottom my-2">
                <div class="d-flex w-100">
                    <a href="${pageContext.request.contextPath}/red-social/libro/${libro.id}">
                        <img src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="" class="p1"
                             style="width: 6em; height: 10em; margin-right: 1em;">
                    </a>
                    <div class="d-flex flex-column">
                        <a href="${pageContext.request.contextPath}/red-social/libro/${libro.id}">
                            <h5 class="py-2">${libro.titulo}</h5>
                        </a>
                        <p class="text-muted">Por: ${libro.autor}</p>
                        <div>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star"></span>
                            <span class="text-danger">(3)</span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty usuarios}">
        <c:forEach items="${usuarios}" var="usuario">
            <div class="d-flex w-50 flex-column container-fluid p-2 justify-content-center border-bottom my-2">
                <div class="d-flex w-100">
                    <a href="${pageContext.request.contextPath}/red-social/usuario/${usuario.id}">
                        <img src="" alt="Foto de perfil del usuario" class="p1"
                             style="width: 6em; height: 10em; margin-right: 1em;">
                    </a>
                    <div class="d-flex flex-column">
                        <a href="${pageContext.request.contextPath}/red-social/usuario/${usuario.id}">
                            <h5 class="py-2">${usuario.nombre}</h5>
                        </a>
                        <p class="text-muted">Algun dato del usuario</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</main>

</body>
</html>
