<%@ page import="java.util.Map" %>
<%@ page import="ar.edu.unlam.tallerweb1.domain.libros.Libro" %>
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

                            <%
                                Map<Integer, Integer> libroIdYPromedio = (Map<Integer, Integer>) pageContext.findAttribute("libroIdYPromedio");
                                Libro libro = (Libro) pageContext.findAttribute("libro");
                                int calificacion = libroIdYPromedio.get(libro.getId());

                                for(int i=0; i< calificacion; i++){
                                    out.print("<span class='fa fa-star text-warning'></span>");
                                }

                                for(int i=0; i< 5-calificacion; i++){
                                    out.print("<span class='fa fa-star'></span>");
                                }
                            %>

                            <%
                                Map<Integer, Integer> libroIdyCantidadDeUsuariosCalificaron = (Map<Integer, Integer>) pageContext.findAttribute("libroIdYCantidadDeUsuariosCalificaron");
                                int cantidadDeUsuariosCalificaron = libroIdyCantidadDeUsuariosCalificaron.get(libro.getId());
                                out.print("<span>(" + cantidadDeUsuariosCalificaron + ")</span>");

                                if(cantidadDeUsuariosCalificaron == 0) {
                                    out.print("<br><span class=\"text-warning\">Sin calificar</span>");
                                }
                            %>

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
                        <c:if test="${usuario.imagen != null}">
                            <img src="${pageContext.request.contextPath}/img/${usuario.imagen.id}.jpg" alt="Foto de perfil del usuario" class="p1"
                                 style="width: 6em; height: 10em; margin-right: 1em;">
                        </c:if>
                        <c:if test="${usuario.imagen == null}">
                            <div style="width: 6em; height: 10em; margin-right: 1em;">
                                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="54"
                                     height="54" viewBox="0 0 24 24" stroke-width="1.5" stroke="#000" fill="none" stroke-linecap="round"
                                     stroke-linejoin="round">
                                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                                    <circle cx="12" cy="12" r="9" />
                                    <circle cx="12" cy="10" r="3" />
                                    <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                                </svg>
                            </div>
                        </c:if>
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
