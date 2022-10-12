<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Logo</a>
            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="">Libros
                            <span class="visually-hidden">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/novedades">Novedades</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Top 100</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Comics y manga</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Locales</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Guia de compra</a>
                    </li>
                    <c:if test="${sessionScope.get('ROL').ordinal() == 0}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/">Administrar Libros</a>
                        </li>
                    </c:if>
                </ul>
                <c:if test="${sessionScope.get('ROL') == null}">
                    <form action="${pageContext.request.contextPath}/login">
                        <button class="btn btn-secondary my-2 my-sm-0" name="inisiarSesion" type="submit">Iniciar Sesión
                        </button>
                    </form>
                    <form action="${pageContext.request.contextPath}/registro">
                        <button class="btn btn-secondary my-2 my-sm-0 mx-3" name="registrarse" type="submit">Registrarse
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.get('ROL') != null}">
                    <form action="${pageContext.request.contextPath}/cerrar-sesion">
                        <button class="btn btn-secondary my-2 my-sm-0 mx-3" name="cerrarSesion" type="submit">Cerrar Sesion
                        </button>
                    </form>
                </c:if>
            </div>
        </div>
    </nav>
</header>