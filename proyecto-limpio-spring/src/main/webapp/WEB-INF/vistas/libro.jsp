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

<c:if test="${msj != null}">
    <p class="alert alert-success text-center my-3">${msj}</p>
</c:if>

<main class="container">
    <div class="row">


        <article class="card-body mb-3 col-7">
            <h2 class="col-sm-12 my-4  text-uppercase">${libro.titulo}</h2>
            <div class="d-flex gap-5">
                <img class="w-25" src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg"
                     alt="${libro.titulo}-imagen">
                <div>
                    <div class="card">
                        <div class="card-body">
                            <p class="mb-0">Autor: ${libro.autor}</p>
                            <p class="my-1">Editorial: ${libro.editorial}</p>
                            <h4>$${libro.precioDeVenta},00</h4>
                            <div class="grid">
                                <form action="${pageContext.request.contextPath}/comprar/${libro.id}">
                                    <button class="btn btn-primary rounded-pill m-2" type="submit">Comprar Ahora
                                    </button>
                                </form>
                                <button class="btn btn-success rounded-pill" type="button">Agregar al carrito</button>
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

        <article class="mb-3 col-5">
            <h2 class="mt-4">Sinopsis</h2>
            <p class="w-75">${libro.sinopsis}</p>
            <h3>Detalles del libro</h3>
            <p>Numero de páginas: ${libro.numeroDePaginas}</p>
            <p>Edición: ${libro.editorial}</p>
            <p>ISBN: ${libro.ISBN}</p>
        </article>

    </div>

    <article>
        <h2>Libros del mismo autor</h2>
        <c:forEach items="${librosALaVenta}" var="libro">

            <div class="card col-3 " style="width: 225px">
                <div class="card-body">
                    <a class="text-decoration-none" href="/${libro.id}">
                        <h4 class="card-title text-uppercase text-center">${libro.titulo}</h4>
                        <img class="text-center w-100"
                             src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg"
                             alt="${libro.titulo}-imagen">
                    </a>
                    <p class="card-text my-1">${libro.autor}</p>
                    <h4 class="card-text">$${libro.precioDeVenta},00</h4>
                </div>
            </div>
        </c:forEach>
    </article>

    <div class="d-flex flex-row flex-nowrap">
        <c:forEach items="${relacionados}" var="relacionado">
            <div class="card-body m-2 col-3">
                <a class="text-decoration-none" href="${relacionado.id}">
                    <h4 class="card-title text-uppercase text-center">${libro.titulo}</h4>
                    <img class="text-center w-100"
                         src="${pageContext.request.contextPath}/img/${relacionado.imagen.id}.jpg"
                         alt="${relacionado.titulo}-imagen">
                </a>
                <p class="card-text my-1">${relacionado.autor}</p>
                <h4 class="card-text">$${relacionado.precioDeVenta},00</h4>
            </div>
        </c:forEach>
    </div>
</main>

<%@include file="common html/footer.html" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
