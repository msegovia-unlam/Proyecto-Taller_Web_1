<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.jsp" %>

<main class="container">
    <article class="card-body mb-3">
        <c:if test="${not empty libroAgregado}">
            <p class="alert alert-success text-center my-3">${libroAgregado} <a href="${pageContext.request.contextPath}/carrito" class="alert-link">Ver carrito</a> </p>
        </c:if>
        <c:if test="${not empty libroNoAgregado}">
            <p class="alert alert-info text-center my-3">${libroNoAgregado} <a href="${pageContext.request.contextPath}/login" class="alert-link">iniciar sesion</a> </p>
        </c:if>
        <h2 class="col-sm-12 my-4  text-uppercase">${libro.titulo}</h2>
        <div class="d-flex gap-5">
            <img class="w-25" src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="${libro.titulo}-imagen">
            <div>
                <div class="card">
                    <div class="card-body">
                        <p class="mb-0">Autor: ${libro.autor}</p>
                        <p class="my-1">Editorial: ${libro.editorial}</p>
                        <h4 >$${libro.precioDeVenta},00</h4>
                        <div class="grid">
                            <button class="btn btn-primary rounded-pill" type="button">Comprar Ahora</button>
                            <form action="${pageContext.request.contextPath}/agregar-a-carrito/${libro.id}" method="POST">
                            <button href="" class="btn btn-success rounded-pill" type="submit">Agregar al carrito</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row" style="width: 22rem;">
                    <label for="customRange2" class="form-label">¿Ya leíste este libro? ¡Rankealo!</label>
                    <input type="range" class="form-range" min="1" max="5" id="customRange2">
                    <div class="clearfix">
                        <strong class="float-start">1</strong>
                        <strong class="float-end">5</strong>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <article>
        <h2>Sinopsis</h2>
        <p class="w-75">${libro.sinopsis}</p>
        <h3>Detalles del libro</h3>
        <p>Numero de páginas: ${libro.numeroDePaginas}</p>
        <p>Edición: ${libro.editorial}</p>
        <p>ISBN: ${libro.ISBN}</p>
    </article>

    <article>
        <h2>Libros relacionados</h2>
        <c:forEach items="${librosALaVenta}" var="libro">

            <div class="card col-3 " style="width: 225px">
                <div class="card-body">
                    <a class="text-decoration-none" href="libro/${libro.id}">
                        <h4 class="card-title text-uppercase text-center">${libro.titulo}</h4>
                        <img class="text-center w-100" src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="${libro.titulo}-imagen">
                    </a>
                    <p class="card-text my-1">${libro.autor}</p>
                    <h4 class="card-text">$${libro.precioDeVenta},00</h4>
                </div>
            </div>

        </c:forEach>
    </article>

</main>

<%@include file="common html/footer.html" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
